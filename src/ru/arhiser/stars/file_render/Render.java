package ru.arhiser.stars.file_render;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Render {

    public void draw(BufferedImage image, Model model) {
        Graphics2D graphics = image.createGraphics();
        graphics.setPaint(new Color (0, 0, 0, 96));
        graphics.fillRect( 0, 0, image.getWidth(), image.getHeight());

        for (Point point: model.getPoints()) {
            float sx = image.getWidth() / 2f + (image.getWidth() / 2f * point.x / point.z);
            float sy = image.getHeight() / 2f + (image.getHeight() / 2f * point.y / point.z);

            int isx = Math.round(sx);
            int isy = Math.round(sy);

            int sx2 = Math.round(sx + ((sx - isx > 0) ? 1 : -1));
            int sy2 = Math.round(sy + ((sy - isy > 0) ? 1 : -1));

            if (sx2 < image.getWidth() && sx2 >= 0
                    && sy2 < image.getHeight() && sy2 >= 0) {
                float gain = (Math.abs(sx - isx) + Math.abs(sy - isy)) / 2;
                float colorGain = ((float)(255 + (int)(point.z * (255 / Math.abs(Model.INITIAL_Z_COORD))))) / 255f;
                colorGain = colorGain * gain;
                int colorR = (point.color & 0xff0000) >> 16;
                int colorG = (point.color & 0xff00) >> 8;
                int colorB = (point.color & 0xff);
                image.setRGB(sx2, sy2, 0xff000000
                        | (int)(colorR * colorGain) << 16
                        | (int)(colorG * colorGain) << 8
                        | (int)(colorB * colorGain));
            }

            if (isx < image.getWidth() && isx >= 0
                    && isy < image.getHeight() && isy >= 0) {
                float colorGain = ((float)(255 + (int)(point.z * (255 / Math.abs(Model.INITIAL_Z_COORD))))) / 255f;
                int colorR = (point.color & 0xff0000) >> 16;
                int colorG = (point.color & 0xff00) >> 8;
                int colorB = (point.color & 0xff);
                image.setRGB(isx, isy, 0xff000000
                        | (int)(colorR * colorGain) << 16
                        | (int)(colorG * colorGain) << 8
                        | (int)(colorB * colorGain));
            }
        }
    }
}
