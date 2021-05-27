package ru.arhiser.huffman_adapt.digital;

/**
 * Класс реализующий битовый массив
 */
public class BitBuffer {
    int size;
    int current;
    byte[] bytes;

    private byte[] masks = new byte[] {0b00000001, 0b00000010, 0b00000100, 0b00001000,
            0b00010000, 0b00100000, 0b01000000, (byte) 0b10000000};

    public BitBuffer(int size) {
        this.size = size;
        int sizeInBytes = size >> 3; // int sizeInBytes = size / 8;
        if (size % 8 > 0) {
            sizeInBytes = sizeInBytes + 1;
        }
        bytes = new byte[sizeInBytes];
    }

    public BitBuffer(int size, byte[] bytes) {
        this.size = size;
        this.bytes = bytes;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int get(int index) {
        int byteIndex = index >> 3;     // int sizeInBytes = size / 8;  оптимизация - замена деления битовыми операциями
        int bitIndex = index & 0b111;   // int bitIndex = index % 8;
        return (bytes[byteIndex] & masks[bitIndex]) != 0 ? 1 : 0;
    }

    public void set(int index, int value) {
        int byteIndex = index >> 3;
        int bitIndex = index & 0b111;
        if (value != 0) {
            bytes[byteIndex] = (byte) (bytes[byteIndex] | masks[bitIndex]);
        } else {
            bytes[byteIndex] = (byte) (bytes[byteIndex] & ~masks[bitIndex]);
        }
    }

    public void append(int value) {
        int byteIndex = current >> 3;
        int bitIndex = current & 0b111;
        if (value != 0) {
            bytes[byteIndex] = (byte) (bytes[byteIndex] | masks[bitIndex]);
        } else {
            bytes[byteIndex] = (byte) (bytes[byteIndex] & ~masks[bitIndex]);
        }
        current += 1;
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
