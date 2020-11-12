package ru.arhiser.stars.file_render;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Специальная версия с более продвинутым рендером,
 * которая не показывает кадры в окне, а сохраняет в файлы,
 * из которых потом можно собрать видеоролик, вот такой:
 * https://youtu.be/hSUhiYyqmus
 */
public class Main {
    public static void main(String[] args) throws IOException {
        final int screenWidth = 1920;
        final int screenHeight = 1080;

        JFrame frame = new JFrame();
        frame.setSize(screenWidth, screenHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        BufferedImage image = new BufferedImage(screenWidth, screenHeight,
                BufferedImage.TYPE_INT_ARGB);

        ImageIcon imageIcon = new ImageIcon(image);
        JLabel picLabel = new JLabel(imageIcon);

        BorderLayout borderLayout = new BorderLayout();
        frame.getContentPane().setLayout(borderLayout);
        frame.getContentPane().add(picLabel, BorderLayout.CENTER);

        frame.setVisible(true);

        Model model = new Model();
        Render render = new Render();

        int frameCounter = 1;
        while (frame.isVisible() && frameCounter <= 60000) {
            model.update(1);
            render.draw(image, model);
            File file = new File("D:\\arhiser\\youtube2\\stars\\img_" + frameCounter + ".png");
            ImageIO.write(image, "png", file);
            frameCounter++;
        }
    }
}
