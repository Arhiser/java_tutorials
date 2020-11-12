package ru.arhiser.stars;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Render {

    public void draw(BufferedImage image, Model model) {
        Graphics2D graphics = image.createGraphics();
        graphics.setPaint(new Color(0, 0, 0));
        graphics.fillRect(0, 0, image.getWidth(), image.getHeight());

        for (Point point: model.getPoints()) {
            int sx = (int)(image.getWidth() / 2f + (image.getWidth() / 2f * point.x / point.z));
            int sy = (int)(image.getHeight() / 2f + (image.getHeight() / 2f * point.y / point.z));
            if (sx < image.getWidth() && sx > 0
                && sy < image.getHeight() && sy > 0) {
                int color = 255 + (int)(point.z * (255 / Math.abs(Model.INITIAL_Z_COORD)));
                image.setRGB(sx, sy, 0xff000000 | color << 16 | color << 8 | color);
            }
        }
    }
}
