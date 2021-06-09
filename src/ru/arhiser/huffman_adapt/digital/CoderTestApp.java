package ru.arhiser.huffman_adapt.digital;

import javax.swing.*;
import java.awt.*;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class CoderTestApp {

    public static void main(String[] args) {
        JFrame frame = new JFrame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new GridLayout(3, 2, 1, 1));

        TextField text = new TextField();
        TextField encoded = new TextField();
        TextField decoded = new TextField();
        frame.getContentPane().add(new Label("Исходный текст:", Label.RIGHT));
        frame.getContentPane().add(text);
        frame.getContentPane().add(new Label("Сжатый текст:", Label.RIGHT));
        frame.getContentPane().add(encoded);
        frame.getContentPane().add(new Label("Раскодированный текст:", Label.RIGHT));
        frame.getContentPane().add(decoded);

        text.addTextListener(new TextListener() {
            @Override
            public void textValueChanged(TextEvent e) {
                encodeAndDecode(text.getText(), encoded, decoded);
            }
        });

        frame.setSize(800, text.getPreferredSize().height  * 3);

        frame.pack();
        frame.setVisible(true);

        text.setText("abbaacca");
    }

    private static void encodeAndDecode(String text, TextField encoded, TextField decoded) {
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
