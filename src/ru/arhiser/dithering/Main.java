package ru.arhiser.dithering;

import ru.arhiser.Utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

public class Main {
    public static void main(String[] args) throws IOException {
        System.setProperty("sun.java2d.uiScale", "1");

        BufferedImage picture = ImageIO.read(new File(".\\src\\ru\\arhiser\\dithering\\IMG_20180824_121235.jpg"));

        picture = Utils.toBufferedImage(picture.getScaledInstance(3840, 2160, Image.SCALE_SMOOTH));

        int[] pixels = ((DataBufferInt)picture.getRaster().getDataBuffer()).getData();

        Arrays.setAll(pixels, i -> {
            int color = pixels[i];
            int bwColor = (int) (0.0722 * (color & 0xff) + 0.7152 * (color >> 8 & 0xff) + 0.2126 * (color >> 16 & 0xff));
            return 0xff000000 | bwColor & 0xff | ((bwColor & 0xff) << 8) | ((bwColor & 0xff) << 16);
        });

        picture = dithering(picture);

/*
        int[] noise = new int[pixels.length];
        Arrays.setAll(noise, i -> 128 - (int)(Math.random() * 255));

        Arrays.setAll(pixels, i -> {
            int color = pixels[i];
            int bwColor = color & 0xff;
            int sum = bwColor + noise[i];
            if (sum > 255) {
                sum = 255;
            }
            if (sum < 0) {
                sum = 0;
            }
            return 0xff000000 | sum & 0xff | ((sum & 0xff) << 8) | ((sum & 0xff) << 16);
        });

        Arrays.setAll(pixels, i -> {
            int color = pixels[i];
            int bwColor = color & 0xff;
            return  bwColor > 127 ? 0xffffffff : 0xff000000;
        });
*/
        Utils.showImageWindow(picture);
    }

    private static BufferedImage dithering(BufferedImage img) {
        BufferedImage bwOnlyImage = new BufferedImage(img.getWidth(), img.getHeight(), TYPE_INT_RGB);
        int[] destPixels = ((DataBufferInt) bwOnlyImage.getRaster().getDataBuffer()).getData();
        int[] sourcePixels = ((DataBufferInt) img.getRaster().getDataBuffer()).getData();
        int size = img.getHeight() * img.getWidth();

        int error = 0;

        for (int i = 0; i < size; i++) {
            int pixel = 0xff & sourcePixels[i];
            int value = pixel > error ? 255 : 0;
            error += value - pixel;
            destPixels[i] = 0xff000000 | value << 16 | value << 8 | value;
        }
        return bwOnlyImage;
    }
}
