package ru.arhiser;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Utils {

    public static void showImageWindow(Image image) {
        showImageWindow(image, image.getWidth(null), image.getHeight(null));
    }

    public static void showImageWindow(Image image, int width, int height) {

        if (image.getWidth(null) != width || image.getHeight(null) != height) {
            image = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        }

        JFrame frame = new JFrame();
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel picLabel = new JLabel(new ImageIcon(image));

        BorderLayout borderLayout = new BorderLayout();
        frame.getContentPane().setLayout(borderLayout);
        frame.getContentPane().add(picLabel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    public static void measureTime(Runnable task, String taskName) {
        long startTime = System.currentTimeMillis();
        task.run();
        long elapsed = System.currentTimeMillis() - startTime;
        System.out.println(taskName + ": " + elapsed + " ms");
    }

    public static void assertTrue(boolean condition) {
        if (!condition) {
            throw new AssertionError();
        }
    }

    public static BufferedImage toBufferedImage(Image img) {
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();
        return bimage;
    }
}
