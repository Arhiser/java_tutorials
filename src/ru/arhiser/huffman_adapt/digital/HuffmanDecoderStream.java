package ru.arhiser.huffman_adapt.digital;

import java.io.IOException;
import java.io.OutputStream;


/**
 * Декодер.
 * На вход подаем байты, сжатые адаптивным методом Хаффмана,
 * на выходе в целевой поток outputStream получаем разархивированную информацию
 */
public class HuffmanDecoderStream extends OutputStream {

    private static final int MODE_READ_UNENCODED_BYTE = 1;
    private static final int MODE_READ_ENCODED_CHAR = 2;

    private final EncodingModel encodingModel;
    private final OutputStream outputStream;

    private CodeTreeNode currentNode;

    private final BitBuffer bitBuffer = new BitBuffer(8);

    private int mode = MODE_READ_UNENCODED_BYTE;

    public HuffmanDecoderStream(OutputStream outputStream) {
        this.outputStream = outputStream;
        encodingModel = new EncodingModelRefreshing();
    }

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

    /**
     * Декодируем очередной бит
     * @param bit - очередной бит из закодированной последовательности
     * @throws IOException
     */
    private void handleBit(int bit) throws IOException {
        if (mode == MODE_READ_UNENCODED_BYTE) { // если мы сейчас читаем незакодированный символ, то пишем бит в буфер, пока не накопится 8 бит
            bitBuffer.append(bit);
            if (bitBuffer.current == 8) {   // если накопили 8 бит, то
                encodingModel.updateByCharacter(bitBuffer.bytes[0]);    // обновляем модель считанным незакодированным символом
                outputStream.write(bitBuffer.bytes[0]);                 // выдаем символ на выход
                bitBuffer.setCurrent(0);
                mode = MODE_READ_ENCODED_CHAR;                          // переключаемся в режим чтения закодированного символа
                currentNode = encodingModel.getTree();
            }
        } else {                                            // если мы сейчас читаем закодированный символ
            if (bit == 1) {                                 // если бит == 1
                currentNode = currentNode.right;            // делаем шаг по дереву направо
            } else {
                currentNode = currentNode.left;             // иначе делаем шаг по дереву налево
            }
            if (currentNode == encodingModel.getZeroNode()) {   // если мы пришли в escape символ,
                mode = MODE_READ_UNENCODED_BYTE;                // переключаемся в режим чтения незакодированного символа
            }
            if (currentNode.content != null) {                  // если пришли в обычный узел
                encodingModel.updateByCharacter(currentNode.content); // обновляем модель декодирванным символом
                outputStream.write(currentNode.content);              // выдаем декодированный символ на выход
                currentNode = encodingModel.getTree();                // возвращаемся в начало дерева
            }
        }
    }

    @Override
    public void close() throws IOException {
        outputStream.flush();
        outputStream.close();
    }
}
