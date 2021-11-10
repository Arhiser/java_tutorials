package ru.arhiser.fract_noise;

public class Noise {

    int scale;

    Random random;

    public Noise(int scale, Random random) {
        this.scale = scale;
        this.random = random;
    }

    public float getValue(int x, int y) {
        int xgrid = x / scale;
        int ygrid = y / scale;
        int xgridNext = xgrid + 1;
        int ygridNext = ygrid + 1;

        int xStart = xgrid * scale;
        int xEnd = xgridNext * scale;
        int yStart = ygrid * scale;
        int yEnd = ygridNext * scale;

        float value12 = random.getRandomValue(xgrid, ygrid);
        float value22 = random.getRandomValue(xgridNext, ygrid);
        float value21 = random.getRandomValue(xgridNext, ygridNext);
        float value11 = random.getRandomValue(xgrid, ygridNext);

        float w12 = ((float) (xEnd - x) * (yEnd - y)) / ((xEnd - xStart) * (yEnd - yStart));
        float w22 = ((float) (x - xStart) * (yEnd - y)) / ((xEnd - xStart) * (yEnd - yStart));
        float w21 = ((float) (x - xStart) * (y - yStart)) / ((xEnd - xStart) * (yEnd - yStart));
        float w11 = ((float) (xEnd - x) * (y - yStart)) / ((xEnd - xStart) * (yEnd - yStart));

        float value = value11 * w11 + value12 * w12 + value21 * w21 + value22 * w22;

        return value;
    }

}
