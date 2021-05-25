package ru.arhiser.huffman_adapt.digital;

import java.io.IOException;
import java.io.OutputStream;

public class HuffmanEncoderStream extends OutputStream {

    EncodingModel encodingModel;

    BitToByteWriter bitWriter;

    public HuffmanEncoderStream(EncodingModel encodingModel, OutputStream outStream) {
        this.encodingModel = encodingModel;
        bitWriter = new BitToByteWriter(outStream);
    }

    @Override
    public void write(int b) throws IOException {
        if (encodingModel.contains(b)) {
            encodingModel.writeCodeForCharacter(b, bitWriter);
        } else {
            encodingModel.writeCodeForCharacter(null, bitWriter);
            bitWriter.writeByte(b);
        }
        encodingModel.updateByCharacter(b);
    }

    @Override
    public void close() throws IOException {
        encodingModel.writeCodeForCharacter(null, bitWriter);
        bitWriter.close();
    }
}
