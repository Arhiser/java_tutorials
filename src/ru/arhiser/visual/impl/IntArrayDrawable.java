package ru.arhiser.visual.impl;

import ru.arhiser.visual.engine.drawable.DrawableGroup;
import ru.arhiser.visual.engine.drawable.DrawableObject;
import ru.arhiser.visual.engine.drawable.Rectangle;
import ru.arhiser.visual.raster.Raster;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class IntArrayDrawable extends DrawableGroup {

    HashMap<Integer, DrawableObject> drawableMap = new HashMap<>();

    int padding = 10;

    int cellWidth;

    int arrayMaxValue;

    public IntArrayDrawable(int x, int y, int width, int height, int[] array) {
        super(x, y, width, height);

        arrayMaxValue = Arrays.stream(array).max().getAsInt();

        cellWidth = (width - (array.length + 1) * padding) / array.length;

        for (int i = 0; i < array.length; i++) {
            int position = getPositionX(i);
            DrawableObject drawableObject =  new Rectangle(position, height - getCellHeight(array[i]), cellWidth, getCellHeight(array[i]), 0xffffffff - array[i] * 2);
            drawableMap.put(array[i], drawableObject);
            drawables.add(drawableObject);
        }
    }
    
    public DrawableObject getDrawableForIndex(int index) {
        return drawableMap.get(index);
    }

    public int getPositionX(int index) {
        return (index + 1) * padding + index * cellWidth;
    }

    private int getCellHeight(int value) {
        return (int)(getHeight() * ((float)value / arrayMaxValue));
    }

    @Override
    public void draw(Raster raster) {
        for (int i = y; i < y + height; i++) {
            for (int j = x; j < x + width; j++) {
                raster.getPixels()[i * raster.getWidth() + j] = 0xff000000;
            }
        }
        super.draw(raster);
    }

    public List<DrawableObject> getDrawables() {
        return drawables;
    }
}
