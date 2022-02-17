package ru.arhiser.visual.samples.array;

import ru.arhiser.visual.raster.Raster;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.IOException;

public class RasterFileWriter {

    Raster raster;

    BufferedImage image;

    String path;

    int frameNumber;

    public RasterFileWriter(Raster raster, String path) {
        this.raster = raster;
        this.path = path;
        image = new BufferedImage(raster.getWidth(), raster.getHeight(), BufferedImage.TYPE_INT_ARGB);
    }

    public void writeNextFrame() {
        try {
            int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
            for (int i = 0; i < raster.getWidth() * raster.getHeight(); i++) {
                pixels[i] = raster.getPixels()[i];
            }

            File file = new File(path + "img_" + frameNumber + ".png");
            ImageIO.write(image, "png", file);
            frameNumber += 1;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
