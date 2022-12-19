package ru.arhiser.multithreading.parallel2;

import ru.arhiser.Utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.IOException;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

public class Single {

    public static void main(String[] args) throws IOException {
        BufferedImage picture = ImageIO.read(new File(".\\src\\ru\\arhiser\\multithreading\\parallel2\\photo_.jpg"));

        picture = Utils.toBufferedImage(picture.getScaledInstance(3840, 2160, Image.SCALE_SMOOTH));

        BufferedImage out = new BufferedImage(picture.getWidth(), picture.getHeight(), TYPE_INT_RGB);
        int[] destPixels = ((DataBufferInt) out.getRaster().getDataBuffer()).getData();
        int[] sourcePixels = ((DataBufferInt) picture.getRaster().getDataBuffer()).getData();

        final int BLUR_SIZE = 4;

        Utils.measureTime(new Runnable() {
            @Override
            public void run() {
                for (int y = 0; y < out.getHeight(); y++) {
                    for (int x = 0; x < out.getWidth(); x++) {
                        destPixels[y * out.getWidth() + x] = getOutValue(sourcePixels, out.getWidth(), x, y, BLUR_SIZE);
                    }
                }
            }
        }, "blur");

        Utils.showImageWindow(out);
    }

    private static int getOutValue(int[] source, int line, int x, int y, int blurSize) {
        int halfSize = blurSize / 2;
        int count = 0;
        int rsum = 0;
        int gsum = 0;
        int bsum = 0;
        for (int i = y - halfSize; i <= y + halfSize; i++) {
            for (int j = x - halfSize; j <= x + halfSize; j++) {
                if (j >=0 && i >= 0 && j < line && (i < source.length / line)) {
                    count += 1;
                    rsum += (source[i * line + j] & 0xff0000) >> 16;
                    gsum += (source[i * line + j] & 0xff00) >> 8;
                    bsum += (source[i * line + j] & 0xff);
                }
            }
        }
        return 0xff000000 | ((rsum / count) << 16) | ((gsum / count) << 8) | (bsum / count);
    }

}
