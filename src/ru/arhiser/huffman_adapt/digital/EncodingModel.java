package ru.arhiser.huffman_adapt.digital;

import java.io.IOException;

public interface EncodingModel {

    void updateByCharacter(int value);

    void writeCodeForCharacter(Integer value, BitToByteWriter bitWriter) throws IOException;

    CodeTreeNode getTree();

    CodeTreeNode getZeroNode();

    boolean contains(int value);
}
