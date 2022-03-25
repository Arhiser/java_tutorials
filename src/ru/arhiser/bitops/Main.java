package ru.arhiser.bitops;

public class Main {

    public static void main(String[] args) {

        int i = 0b00000100;
        System.out.println(i);
        System.out.println(Integer.toBinaryString(i));

        System.out.println("---побитовый сдвиг вправо---");
        System.out.println("в десятичном виде: " + (i >> 1));
        System.out.println("в двоичном виде: " + binaryStr(i >> 1));

        System.out.println("---побитовый сдвиг влево---");
        System.out.println("в десятичном виде: " + (i << 1));
        System.out.println("в двоичном виде: " + binaryStr(i << 1));

        System.out.println("Быстро умножить число на 4: " + (i << 2));
        System.out.println("Быстро поделить число на 4: " + (i >>> 2));


        int b1 = 0b00001001;
        int b2 = 0b00001010;

        System.out.println("Побитовое И (AND)");
        printBinary(b1);
        printBinary(b2);
        printBinary(b1 & b2);

        System.out.println("Побитовое ИЛИ (OR)");
        printBinary(b1);
        printBinary(b2);
        printBinary(b1 | b2);

        System.out.println("Исключающее ИЛИ (XOR)");
        printBinary(b1);
        printBinary(b2);
        printBinary(b1 ^ b2);

        System.out.println("Инверсия (NOT)");
        printBinary(b1);
        printBinary(~b1);

        System.out.println("**************");
        //установка бита
        int k = 0b11010000;
        bitSetting(k);

        System.out.println("**************");
        //сброс бита в 0
        bitReset(k);

        System.out.println("**************");
        //проверка бита
        bitCheck(k);

        System.out.println("**************");
        // упаковка 4 байт в int
        int color = bytesToInt();

        System.out.println("**************");
        // извлечение байтов из int
        b1 = color & 0xFF;
        b2 = (color & 0xFF << 8) >> 8;
        int b3 = (color & 0xFF << 16) >> 16;
        int b4 = (color & 0xFF << 24) >> 24;
        bytesExtract(b1, b2, b3, b4);

        System.out.println("**************");
        // проверка битового массива
        BitArray bitArray = new BitArray(100);
        bitArray.set(0, 1);
        bitArray.set(9, 1);

        bitArray.set(5, 1);
        bitArray.set(5, 0);

        System.out.println("Bit Array:");
        System.out.println(bitArray.toString());
    }

    private static void bytesExtract(int b1, int b2, int b3, int b4) {
        System.out.println("Extracting bytes from int:");
        printBinary(b1);
        printBinary(b2);
        printBinary(b3);
        printBinary(b4);
    }

    private static int bytesToInt() {
        int r = 64;
        int g = 128;
        int b = 32;
        int alpha = 255;
        int color = alpha << 24 | r << 16 | g << 8 | b;
        System.out.println("Packing 4 bytes to int:");
        System.out.println(Integer.toBinaryString(color));
        return color;
    }

    private static void bitCheck(int k) {
        System.out.println("Bit Checking:");
        System.out.println((k & 0b00001000) > 0 ? "1" : "0");
    }

    private static void bitReset(int k) {
        printBinary(k);
        System.out.println("Reset bit to 0:");
        printBinary(k & 0b11110111);
    }

    private static void bitSetting(int k) {
        printBinary(k);
        System.out.println("Bit Setting:");
        printBinary(k | 0b00000010);
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

        public int get(int index) {
            int byteIndex = index / 8;
            int bitIndex = index % 8;
            return (bytes[byteIndex] & masks[bitIndex]) > 0 ? 1 : 0;
        }

        public void set (int index, int value) {
            int byteIndex = index / 8;
            int bitIndex = index % 8;
            if (value > 0) {
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
    }

    // функции для вывода разрядов байта в форматированном виде
    public static void printBinary(int b) {
        System.out.println("0b" + Integer.toBinaryString(0b100000000 | (b & 0xff)).substring(1));
    }

    public static String binaryStr(int b) {
        return "0b" + Integer.toBinaryString(0b100000000 | (b & 0xff)).substring(1);
    }
}
