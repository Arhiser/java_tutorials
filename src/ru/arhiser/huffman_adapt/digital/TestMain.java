package ru.arhiser.huffman_adapt.digital;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestMain {
    public static void main(String[] args) {
       // codecTest();
        //fileCodecTest();
        fileCodecDigitalTest();
        //createTestFile();
    }

    /**
     * проверка кодирования строки
     */
    private static void codecTest() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        HuffmanEncoderStream huffmanEncoderStream = new HuffmanEncoderStream(new EncodingModelRefreshing(), byteArrayOutputStream);

        //String text = "aab";
        String text = "On the other hand, we denounce with righteous indignation and dislike men who are so beguiled and demoralized by the charms of pleasure of the moment, so blinded by desire, that they cannot foresee the pain and trouble that are bound to ensue; and equal blame belongs to those who fail in their duty through weakness of will, which is the same as saying through shrinking from toil and pain. These cases are perfectly simple and easy to distinguish. In a free hour, when our power of choice is untrammelled and when nothing prevents our being able to do what we like best, every pleasure is to be welcomed and every pain avoided. But in certain circumstances and owing to the claims of duty or the obligations of business it will frequently occur that pleasures have to be repudiated and annoyances accepted. The wise man therefore always holds in these matters to this principle of selection: he rejects pleasures to secure other greater pleasures, or else he endures pains to avoid worse pains.";
        try {
            huffmanEncoderStream.write(text.getBytes(StandardCharsets.UTF_8));
            huffmanEncoderStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] encoded = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.reset();

        HuffmanDecoderStream huffmanDecoderStream = new HuffmanDecoderStream(new EncodingModelRefreshing(), byteArrayOutputStream);
        try {
            for (int i = 0; i < encoded.length; i++) {
                huffmanDecoderStream.write(encoded[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert byteArrayOutputStream.toString().equals(text);
    }

    /**
     * проверка кодирования файла
     */
    private static void fileCodecTest() {
        try {
            String content = new String(Files.readAllBytes(Paths.get(".\\src\\ru\\arhiser\\huffman\\voyna-i-mir-tom-1.txt")));
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(".\\src\\ru\\arhiser\\huffman_adapt\\digital\\voyna-i-mir-tom-1.huf"));
            HuffmanEncoderStream huffmanEncoderStream = new HuffmanEncoderStream(new EncodingModelRefreshing(), bufferedOutputStream);
            huffmanEncoderStream.write(content.getBytes(StandardCharsets.UTF_8));
            huffmanEncoderStream.close();

            BufferedOutputStream decodeOutput = new BufferedOutputStream(new FileOutputStream(".\\src\\ru\\arhiser\\huffman_adapt\\digital\\decoded.txt"));
            HuffmanDecoderStream huffmanDecoderStream = new HuffmanDecoderStream(new EncodingModelRefreshing(), decodeOutput);
            BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(".\\src\\ru\\arhiser\\huffman_adapt\\digital\\voyna-i-mir-tom-1.huf"));
            byte[] buffer = new byte[1024];
            int readNum;
            while ((readNum = inputStream.read(buffer)) > 0) {
                huffmanDecoderStream.write(buffer, 0, readNum);
            }
            decodeOutput.close();
            huffmanDecoderStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * проверка кодирования файла с чтением через FileInputStream
     */
    private static void fileCodecDigitalTest() {
        try {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(".\\src\\ru\\arhiser\\huffman_adapt\\digital\\voyna-i-mir-tom-1.huf"));
            HuffmanEncoderStream huffmanEncoderStream = new HuffmanEncoderStream(new EncodingModelRefreshing(), bufferedOutputStream);

            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(".\\src\\ru\\arhiser\\huffman\\voyna-i-mir-tom-1.txt"));
            byte[] buffer = new byte[2048];
            int read;
            while ((read = bis.read(buffer, 0, buffer.length)) > 0) {
                huffmanEncoderStream.write(buffer, 0, read);
            }
            bis.close();
            huffmanEncoderStream.close();

            BufferedOutputStream decodeOutput = new BufferedOutputStream(new FileOutputStream(".\\src\\ru\\arhiser\\huffman_adapt\\digital\\decoded.txt"));
            HuffmanDecoderStream huffmanDecoderStream = new HuffmanDecoderStream(new EncodingModelRefreshing(), decodeOutput);
            BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(".\\src\\ru\\arhiser\\huffman_adapt\\digital\\voyna-i-mir-tom-1.huf"));
            int readNum;
            while ((readNum = inputStream.read(buffer)) != -1) {
                huffmanDecoderStream.write(buffer, 0, readNum);
            }
            decodeOutput.close();
            huffmanDecoderStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
