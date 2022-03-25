package ru.arhiser.huffman_adapt.digital;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class EncodeAndDecodeApp {
    static void encodeAndDecode(String text, TextField encoded, TextField decoded) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        HuffmanEncoderStream huffmanEncoderStream = new HuffmanEncoderStream(outputStream);
        try {
            huffmanEncoderStream.write(text.getBytes(StandardCharsets.US_ASCII));
            huffmanEncoderStream.close();
            encoded.setText(outputStream.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        ByteArrayOutputStream decodeResultStream = new ByteArrayOutputStream();
        HuffmanDecoderStream huffmanDecoderStream = new HuffmanDecoderStream(decodeResultStream);
        try {
            huffmanDecoderStream.write(outputStream.toByteArray());
            huffmanDecoderStream.close();
            decoded.setText(decodeResultStream.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
