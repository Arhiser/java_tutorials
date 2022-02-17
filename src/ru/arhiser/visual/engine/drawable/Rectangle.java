package ru.arhiser.visual.engine.drawable;

import ru.arhiser.visual.raster.Raster;

public class Rectangle extends DrawableObject {

    public Rectangle(int x, int y, int width, int height, int color) {
        super(x, y, width, height);
        this.color = color;
    }

    @Override
    public void draw(Raster raster) {
        for (int i = y; i < y + height; i++) {
            for (int j = x; j < x + width; j++) {
                raster.getPixels()[i * raster.getWidth() + j] = color;
            }
        }
    }
}
