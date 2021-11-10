package ru.arhiser.fract_noise;

import ru.arhiser.Utils;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Main {

    private static final int IMAGE_WIDTH = 1920;
    private static final int IMAGE_HEIGHT = 1080;

    public static void main(String[] args) {

        BufferedImage image = new BufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT, BufferedImage.TYPE_INT_RGB);

        FractalNoise fractalNoise = new FractalNoise(512,
                new Random(image.getWidth(), 100000), 9);

        int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
        int pixelIndex = 0;
        for (int i = 0; i < image.getHeight(); i++) {
            for (int j = 0; j < image.getWidth(); j++) {
                int value = 0xff & (int)(fractalNoise.getValue(j, i) * 255);
                pixels[pixelIndex++] = 0xff000000 | value << 16 | value << 8 | value;
            }
        }

        Utils.showImageWindow(image, IMAGE_WIDTH, IMAGE_HEIGHT);
    }
}
