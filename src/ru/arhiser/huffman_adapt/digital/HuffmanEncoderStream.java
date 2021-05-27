package ru.arhiser.huffman_adapt.digital;

import java.io.IOException;
import java.io.OutputStream;


/**
 * Кодировщик, осуществляющий сжатие информации адаптивным методом Хаффмана.
 * На вход подаем байты, которые надо сжать, на выходе в outputStream получаем сжатую информацию
 */
public class HuffmanEncoderStream extends OutputStream {

    EncodingModel encodingModel;

    BitToByteWriter bitWriter;

    public HuffmanEncoderStream(OutputStream outputStream) {
        this.encodingModel = new EncodingModelRefreshing();
        bitWriter = new BitToByteWriter(outputStream);
    }

    public HuffmanEncoderStream(EncodingModel encodingModel, OutputStream outStream) {
        this.encodingModel = encodingModel;
        bitWriter = new BitToByteWriter(outStream);
    }

    @Override
    public void write(int b) throws IOException {
        if (encodingModel.contains(b)) {                            // если символ уже есть в модели
            encodingModel.writeCodeForCharacter(b, bitWriter);      // выдаем на выход код этого символа
        } else {                                                        // если символа еще нет,
            encodingModel.writeCodeForCharacter(null, bitWriter);  // выдаем escape-символ в выходной поток
            bitWriter.writeByte(b);                                     // выдаем незакодированный символ в выходной поток
        }
        encodingModel.updateByCharacter(b);                         // обновляем модель текущим символом
    }

    @Override
    public void close() throws IOException {
        encodingModel.writeCodeForCharacter(null, bitWriter);
        bitWriter.close();
    }
}
