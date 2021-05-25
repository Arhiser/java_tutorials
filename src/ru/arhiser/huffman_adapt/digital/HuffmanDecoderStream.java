package ru.arhiser.huffman_adapt.digital;

import java.io.IOException;
import java.io.OutputStream;

public class HuffmanDecoderStream extends OutputStream {

    private static final int MODE_READ_UNENCODED_BYTE = 1;
    private static final int MODE_READ_ENCODED_CHAR = 2;

    private final EncodingModel encodingModel;
    private final OutputStream outputStream;

    private CodeTreeNode currentNode;

    private final BitBuffer bitBuffer = new BitBuffer(8);

    private int mode = MODE_READ_UNENCODED_BYTE;

    public HuffmanDecoderStream(EncodingModel encodingModel, OutputStream outStream) {
        this.outputStream = outStream;
        this.encodingModel = encodingModel;
        currentNode = encodingModel.getTree();
    }

    @Override
    public void write(int b) throws IOException {
        int mask = 1;
        for (int i = 0; i < 8; i++) {
            handleBit((b & mask) > 0 ? 1 : 0);
            mask <<= 1;
        }
    }

    private void handleBit(int bit) throws IOException {
        if (mode == MODE_READ_UNENCODED_BYTE) {
            bitBuffer.append(bit);
            if (bitBuffer.current == 8) {
                encodingModel.updateByCharacter(bitBuffer.bytes[0]);
                outputStream.write(bitBuffer.bytes[0]);
                bitBuffer.setCurrent(0);
                mode = MODE_READ_ENCODED_CHAR;
                currentNode = encodingModel.getTree();
            }
        } else {
            if (bit == 1) {
                currentNode = currentNode.right;
            } else {
                currentNode = currentNode.left;
            }
            if (currentNode == encodingModel.getZeroNode()) {
                mode = MODE_READ_UNENCODED_BYTE;
            }
            if (currentNode.content != null) {
                encodingModel.updateByCharacter(currentNode.content);
                outputStream.write(currentNode.content);
                currentNode = encodingModel.getTree();
            }
        }
    }

    @Override
    public void close() throws IOException {
        outputStream.flush();
        outputStream.close();
    }
}
