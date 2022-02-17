package ru.arhiser.visual.engine.drawable;

import ru.arhiser.visual.raster.Raster;

import java.util.ArrayList;

public class DrawableGroup extends DrawableObject {
    protected ArrayList <DrawableObject> drawables = new ArrayList<>();

    public DrawableGroup(int x, int y, int width, int height) {
        super(x, y, width, height);
    }


    @Override
    public void draw(Raster raster) {
        for(DrawableObject drawable: drawables) {
            drawable.draw(raster);
        }
    }
}
