package ru.arhiser.huffman;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        
        String text = "where there's a will there's a way";

        // вычисляем частоты символов в тексте
        TreeMap<Character, Integer> frequencies = countFrequency(text);

        // генерируем список листов дерева
        ArrayList<CodeTreeNode> codeTreeNodes = new ArrayList<>();
        for(Character c: frequencies.keySet()) {
            codeTreeNodes.add(new CodeTreeNode(c, frequencies.get(c)));
        }

        // строим кодовое дерево с помощью алгоритма Хаффмана
        CodeTreeNode tree = huffman(codeTreeNodes);

        // генерируем таблицу префиксных кодов для кодируемых символов с помощью кодового дерева
        TreeMap<Character, String> codes = new TreeMap<>();
        for(Character c: frequencies.keySet()) {
            codes.put(c, tree.getCodeForCharacter(c, ""));
        }

        System.out.println("Таблица префиксных кодов: " + codes.toString());

        // кодируем текст, заменяем сиволы соответствующими кодами
        StringBuilder encoded = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            encoded.append(codes.get(text.charAt(i)));
        }

        System.out.println("Размер исходной строки: " + text.getBytes().length * 8 + " бит");
        System.out.println("Размер сжатой строки: " + encoded.length() + " бит");
        System.out.println("Биты сжатой строки: " + encoded);

        // декодируем сжатую информацию обратно
        String decoded = huffmanDecode(encoded.toString(), tree);

        System.out.println("Расшифровано: " + decoded);

        // тест сжатия файла
        //fileCompressTest();
    }

    private static TreeMap<Character, Integer> countFrequency(String text) {
        TreeMap<Character, Integer> freqMap = new TreeMap<>();
        for (int i = 0; i < text.length(); i++) {
            Character c = text.charAt(i);
            Integer count = freqMap.get(c);
            freqMap.put(c, count != null ? count + 1 : 1);
        }
        return freqMap;
    }

    private static CodeTreeNode huffman(ArrayList<CodeTreeNode> codeTreeNodes) {
        while (codeTreeNodes.size() > 1) {
            Collections.sort(codeTreeNodes);
            CodeTreeNode left = codeTreeNodes.remove(codeTreeNodes.size() - 1);
            CodeTreeNode right = codeTreeNodes.remove(codeTreeNodes.size() - 1);

            CodeTreeNode parent = new CodeTreeNode(null, right.weight + left.weight, left, right);
            codeTreeNodes.add(parent);
        }
        return  codeTreeNodes.get(0);
    }

    private static String huffmanDecode(String encoded, CodeTreeNode tree) {
        StringBuilder decoded = new StringBuilder();

        CodeTreeNode node = tree;
        for (int i = 0; i < encoded.length(); i++) {
            node = encoded.charAt(i) == '0' ? node.left : node.right;
            if (node.content != null) {
                decoded.append(node.content);
                node = tree;
            }
        }
        return decoded.toString();
    }

    // класс для представления кодового дерева
    private static class CodeTreeNode implements Comparable<CodeTreeNode> {

        Character content;
        int weight;
        CodeTreeNode left;
        CodeTreeNode right;

        public CodeTreeNode(Character content, int weight) {
            this.content = content;
            this.weight = weight;
        }

        public CodeTreeNode(Character content, int weight, CodeTreeNode left, CodeTreeNode right) {
            this.content = content;
            this.weight = weight;
            this.left = left;
            this.right = right;
        }

        @Override
        public int compareTo(CodeTreeNode o) {
            return o.weight - weight;
        }

        // извлечение кода для символа
        public String getCodeForCharacter(Character ch, String parentPath) {
            if (content == ch) {
                return  parentPath;
            } else {
                if (left != null) {
                    String path = left.getCodeForCharacter(ch, parentPath + 0);
                    if (path != null) {
                        return path;
                    }
                }
                if (right != null) {
                    String path = right.getCodeForCharacter(ch, parentPath + 1);
                    if (path != null) {
                        return path;
                    }
                }
            }
            return null;
        }
    }

    // класс реализующий битовый массив
    public static class BitArray {
        int size;
        byte[] bytes;

        private byte[] masks = new byte[] {0b00000001, 0b00000010, 0b00000100, 0b00001000,
                0b00010000, 0b00100000, 0b01000000, (byte) 0b10000000};

        public BitArray(int size) {
            this.size = size;
            int sizeInBytes = size / 8;
            if (size % 8 > 0) {
                sizeInBytes = sizeInBytes + 1;
            }
            bytes = new byte[sizeInBytes];
        }

        public BitArray(int size, byte[] bytes) {
            this.size = size;
            this.bytes = bytes;
        }

        public int get(int index) {
            int byteIndex = index / 8;
            int bitIndex = index % 8;
            return (bytes[byteIndex] & masks[bitIndex]) != 0 ? 1 : 0;
        }

        public void set(int index, int value) {
            int byteIndex = index / 8;
            int bitIndex = index % 8;
            if (value != 0) {
                bytes[byteIndex] = (byte) (bytes[byteIndex] | masks[bitIndex]);
            } else {
                bytes[byteIndex] = (byte) (bytes[byteIndex] & ~masks[bitIndex]);
            }
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < size; i++) {
                sb.append(get(i) > 0 ? '1' : '0');
            }
            return sb.toString();
        }

        public int getSize() {
            return size;
        }

        public int getSizeInBytes() {
            return bytes.length;
        }

        public byte[] getBytes() {
            return bytes;
        }
    }

    // сохранение таблицы частот и сжатой информации в файл
    private static void saveToFile(File output, Map<Character, Integer> frequencies, String bits) {
        try {
            DataOutputStream os = new DataOutputStream(new FileOutputStream(output));
            os.writeInt(frequencies.size());
            for (Character character: frequencies.keySet()) {
                os.writeChar(character);
                os.writeInt(frequencies.get(character));
            }
            int compressedSizeBits = bits.length();
            BitArray bitArray = new BitArray(compressedSizeBits);
            for (int i = 0; i < bits.length(); i++) {
                bitArray.set(i, bits.charAt(i) != '0' ? 1 : 0);
            }

            os.writeInt(compressedSizeBits);
            os.write(bitArray.bytes, 0, bitArray.getSizeInBytes());
            os.flush();
            os.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // загрузка сжатой информации и таблицы частот из файла
    private static void loadFromFile(File input, Map<Character, Integer> frequencies, StringBuilder bits) {
        try {
            DataInputStream os = new DataInputStream(new FileInputStream(input));
            int frequencyTableSize = os.readInt();
            for (int i = 0; i < frequencyTableSize; i++) {
                frequencies.put(os.readChar(), os.readInt());
            }
            int dataSizeBits = os.readInt();
            BitArray bitArray = new BitArray(dataSizeBits);
            os.read(bitArray.bytes, 0, bitArray.getSizeInBytes());
            os.close();

            for (int i = 0; i < bitArray.size; i++) {
                bits.append(bitArray.get(i) != 0 ? "1" : 0);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // проверка алгоритма сжатия на текстовом файле
    private static void fileCompressTest() {
        try {
            // загрузка содержимого файла в виде строки
            String content = new String(Files.readAllBytes(Paths.get(".\\src\\ru\\arhiser\\huffman\\voyna-i-mir-tom-1.txt")));

            // вычисление таблицы частот с которыми встречаются символы в тексте
            TreeMap<Character, Integer> frequencies = countFrequency(content);

            ArrayList<CodeTreeNode> codeTreeNodes = new ArrayList<>();

            // генерация листов будущего дерева для символов текста
            for(Character c: frequencies.keySet()) {
                codeTreeNodes.add(new CodeTreeNode(c, frequencies.get(c)));
            }
            // построение кодового дерева алгоритмом Хаффмана
            CodeTreeNode tree = huffman(codeTreeNodes);

            // постоение таблицы префиксных кодов для символов исходного текста
            TreeMap<Character, String> codes = new TreeMap<>();
            for (Character c: frequencies.keySet()) {
                codes.put(c, tree.getCodeForCharacter(c, ""));
            }

            // кодирование текста префиксными кодами
            StringBuilder encoded = new StringBuilder();
            for (int i = 0; i < content.length(); i++) {
                encoded.append(codes.get(content.charAt(i)));
            }

            // сохранение сжатой информации в файл
            File file = new File(".\\src\\ru\\arhiser\\huffman\\compressed.huf");
            saveToFile(file, frequencies, encoded.toString());


            TreeMap<Character, Integer> frequencies2 = new TreeMap<>();
            StringBuilder encoded2 = new StringBuilder();
            codeTreeNodes.clear();

            // извлечение сжатой информации из файла
            loadFromFile(file, frequencies2, encoded2);

            // генерация листов и постоение кодового дерева Хаффмана на основе таблицы частот сжатого файла
            for(Character c: frequencies2.keySet()) {
                codeTreeNodes.add(new CodeTreeNode(c, frequencies2.get(c)));
            }
            CodeTreeNode tree2 = huffman(codeTreeNodes);

            // декодирование обратно исходной информации из сжатой
            String decoded = huffmanDecode(encoded2.toString(), tree2);

            // сохранение в файл декодированной информации
            Files.write(Paths.get(".\\src\\ru\\arhiser\\huffman\\decompressed.txt"), decoded.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
