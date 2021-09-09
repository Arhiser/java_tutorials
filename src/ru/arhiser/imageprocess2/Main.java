package ru.arhiser.imageprocess2;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.IOException;

/**
 * Исходники для видео: https://youtu.be/WG0mS-o1zSA
 */
public class Main {
    /*
    public static void main(String[] args) {

        byte byteVal = (byte) 251;
        System.out.println(byteVal);

        int intVal = byteVal;
        System.out.println(intVal);

        intVal = 0xff & byteVal;
        System.out.println(intVal);

        System.out.println((byte) 128);

        System.out.println(negative((byte) 15));

        byte b1 = 5;
        byte b2 = 7;
        System.out.println(b1 - b2);
        System.out.println(b1 + ~b2 + 1);

        byte b3 = 100;
        byte b4 = 100;
        byte b5 = (byte) (b3 + b4);
        System.out.println(b5);
    }

    static byte negative(byte value) {
        return (byte) (256 - value);
    }

    static byte negative2(byte value) {
        return (byte) (~value + 1);
    }

}
     */


    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame();
        frame.setSize(1200, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        BufferedImage myPicture = ImageIO.read(new File(".\\src\\ru\\arhiser\\imageprocess2\\photo_3.jpg"));

        Image scaled = myPicture.getScaledInstance(1200, 800, Image.SCALE_AREA_AVERAGING);

        BufferedImage scaledImage = toBufferedImage(scaled);

        JLabel picLabel = new JLabel(new ImageIcon(toGray(scaledImage)));

        BorderLayout borderLayout = new BorderLayout();
        frame.getContentPane().setLayout(borderLayout);
        frame.getContentPane().add(picLabel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    public static BufferedImage toGray(BufferedImage source) {
        BufferedImage grayImage = new BufferedImage(source.getWidth(), source.getHeight(),
                BufferedImage.TYPE_BYTE_GRAY);

        byte[] outBuffer = ((DataBufferByte)grayImage.getRaster().getDataBuffer()).getData();
        int[] inBuffer = ((DataBufferInt)source.getRaster().getDataBuffer()).getData();

        for (int i = 0; i < source.getWidth() * grayImage.getHeight(); i++) {
            int color = inBuffer[i];
            int r = color >> 16 & 0xff;
            int g = color >> 8 & 0xff;
            int b = color & 0xff;
            int gray = (r + g + b) / 3;
            outBuffer[i] = (byte) gray;
        }
        return grayImage;
    }

    public static BufferedImage toBufferedImage(Image img) {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        return bimage;
    }


}

