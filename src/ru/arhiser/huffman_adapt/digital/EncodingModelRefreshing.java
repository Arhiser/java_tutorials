package ru.arhiser.huffman_adapt.digital;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class EncodingModelRefreshing implements EncodingModel {

    public final CodeTreeNode ZERO_NODE = new CodeTreeNode(null, 0);

    private CodeTreeNode tree = ZERO_NODE;
    private final List<CodeTreeNode> nodeList = new ArrayList<>();
    private final BitBuffer bitBuffer = new BitBuffer(1024);
    private final CodeTreeNode[] nodeCache = new CodeTreeNode[256];

    public EncodingModelRefreshing() {
        nodeList.add(ZERO_NODE);
    }


    @Override
    public void updateByCharacter(int value) {
        CodeTreeNode node = nodeCache[((byte)value) & 0xff];
        CodeTreeNode parent;
        if (node != null) {
            node.weight = node.weight + 1;
            parent = node.parent;
        } else {
            CodeTreeNode zeroNodeParent = ZERO_NODE.parent;
            CodeTreeNode newNode = new CodeTreeNode(value, 1, null, null, null);
            CodeTreeNode intermediate = new CodeTreeNode(null, 0, ZERO_NODE, newNode, zeroNodeParent);
            nodeCache[((byte)value) & 0xff] = newNode;
            newNode.parent = intermediate;
            if (zeroNodeParent != null) {
                zeroNodeParent.left = intermediate;
            } else {
                tree = intermediate;
            }
            nodeList.remove(nodeList.size() - 1);
            nodeList.add(intermediate);
            nodeList.add(newNode);
            nodeList.add(ZERO_NODE);

            ZERO_NODE.parent = intermediate;

            parent = newNode.parent;
        }
        while (parent != null) {
            parent.weight = parent.weight + 1;
            parent = parent.parent;
        }

        while (reorderNodes());
    }

    private boolean reorderNodes() {
        int i = 1;
        for (; i < nodeList.size(); i++) {
            if (nodeList.get(i - 1).weight < nodeList.get(i).weight) {
                break;
            }
        }
        if (i != nodeList.size()) {
            CodeTreeNode first = nodeList.get(i);
            int j = 0;
            for (; j < i; j++) {
                if (nodeList.get(j).weight < first.weight) {
                    break;
                }
            }

            CodeTreeNode firstParent = first.parent;
            CodeTreeNode second = nodeList.get(j);
            if (first.parent == second.parent) {
                if (first.weight < second.weight) {
                    firstParent.left = first;
                    firstParent.right = second;
                } else {
                    firstParent.left = second;
                    firstParent.right = first;
                }
            } else {
                if (second.parent != first) {
                    if (second.parent.left == second) {
                        second.parent.left = first;
                    } else {
                        second.parent.right = first;
                    }
                    first.parent = second.parent;
                    if (firstParent.left == first) {
                        firstParent.left = second;
                    } else {
                        firstParent.right = second;
                    }
                    second.parent = firstParent;
                }
            }

            nodeList.remove(j);
            nodeList.add(j, first);
            nodeList.remove(i);
            nodeList.add(i, second);
            return true;
        }
        return false;
    }

    @Override
    public void writeCodeForCharacter(Integer value, BitToByteWriter bitWriter) throws IOException {
        CodeTreeNode node;
        if (value != null) {
            node = nodeCache[value.byteValue() & 0xff];
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
        return nodeCache[((byte)value) & 0xff] != null;
    }

}
