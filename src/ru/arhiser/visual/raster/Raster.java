package ru.arhiser.visual.raster;

public class Raster {
    int width;
    int height;
    int[] pixels;

    public Raster(int width, int height) {
        this.width = width;
        this.height = height;
        this.pixels = new int[width * height];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int[] getPixels() {
        return pixels;
    }

    public int getSize() {
        return pixels.length;
    }
}
