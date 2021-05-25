package ru.arhiser.huffman_adapt.digital;

import java.io.IOException;
import java.util.*;

public class EncodingModelSimple implements EncodingModel {

    public final CodeTreeNode ZERO_NODE = new CodeTreeNode(null, 0);

    private ArrayList<CodeTreeNode> nodeList = new ArrayList<>();
    CodeTreeNode tree = ZERO_NODE;
    HashMap<Integer, CodeTreeNode> nodeCache = new HashMap<>();
    private BitBuffer bitBuffer = new BitBuffer(256);

    @Override
    public void updateByCharacter(int value) {
        CodeTreeNode node = nodeCache.get(value);
        if (node != null) {
            node.weight += 1;
        } else {
            node = new CodeTreeNode(value, 1);
            nodeCache.put(value, node);
        }
        buildHuffmanTree(nodeCache);
    }

    @Override
    public void writeCodeForCharacter(Integer value, BitToByteWriter bitWriter) throws IOException {
        CodeTreeNode node;
        if (value != null) {
            node = nodeCache.get(value);
        } else {
            node = ZERO_NODE;
        }
        bitBuffer.setCurrent(0);
        while (node.parent != null) {
            if (node.parent.left == node) {
                bitBuffer.append(0);
            } else {
                bitBuffer.append(1);
            }
            node = node.parent;
        }
        for (int i = bitBuffer.current - 1; i >= 0 ; i--) {
            bitWriter.writeBit(bitBuffer.get(i));
        }
    }

    private CodeTreeNode buildHuffmanTree(Map<Integer, CodeTreeNode> nodeCache) {
        nodeList.clear();

        nodeList.addAll(nodeCache.values());

        nodeList.add(ZERO_NODE);

        while (nodeList.size() > 1) {
            Collections.sort(nodeList);
            CodeTreeNode left = nodeList.remove(nodeList.size() - 1);
            CodeTreeNode right = nodeList.remove(nodeList.size() - 1);
            CodeTreeNode parent = new CodeTreeNode(null, right.weight + left.weight, left, right, null);
            left.parent = parent;
            right.parent = parent;
            nodeList.add(parent);
        }

        tree = nodeList.get(0);
        return tree;
    }

    @Override
    public CodeTreeNode getTree() {
        return tree;
    }

    @Override
    public CodeTreeNode getZeroNode() {
        return ZERO_NODE;
    }

    @Override
    public boolean contains(int value) {
        return nodeCache.containsKey(value);
    }
}
