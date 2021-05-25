package ru.arhiser.huffman_adapt.digital;

import java.util.ArrayList;

public class CodeTreeNode implements Comparable<CodeTreeNode> {

    Integer content;
    int weight;
    CodeTreeNode left;
    CodeTreeNode right;
    CodeTreeNode parent;

    public CodeTreeNode(Integer content, int weight) {
        this.content = content;
        this.weight = weight;
    }

    public CodeTreeNode(Integer content, int weight, CodeTreeNode left, CodeTreeNode right, CodeTreeNode parent) {
        this.content = content;
        this.weight = weight;
        this.left = left;
        this.right = right;
        this.parent = parent;
    }

    @Override
    public int compareTo(CodeTreeNode o) {
        return o.weight - weight;
    }
}
