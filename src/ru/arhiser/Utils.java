package ru.arhiser;

import javax.swing.*;
import java.awt.*;

public class Utils {

    public static void showImageWindow(Image image) {
        showImageWindow(image, 1024, 768);
    }

    public static void showImageWindow(Image image, int width, int height) {
        JFrame frame = new JFrame();
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel picLabel = new JLabel(new ImageIcon(image));

        BorderLayout borderLayout = new BorderLayout();
        frame.getContentPane().setLayout(borderLayout);
        frame.getContentPane().add(picLabel, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}
