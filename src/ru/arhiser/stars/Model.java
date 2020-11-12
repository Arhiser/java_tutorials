package ru.arhiser.stars;

import java.util.ArrayList;

public class Model {

    public static float INITIAL_Z_COORD = -3;

    public static float MOTION_SPEED = 0.001f;

    ArrayList<Point> points = new ArrayList<>();

    public void update(long elapsedTime) {
        float birthChance = 0.02f;

        if (random(0, 1) < birthChance) {
            points.add(new Point(random(-1, 1), random(-1, 1), INITIAL_Z_COORD));
        }

        for (Point point: points) {
            point.z += elapsedTime * MOTION_SPEED;
        }

        points.removeIf(point -> point.z >= 0);
    }

    private float random(float from, float to) {
        return (float) (from + (to - from) * Math.random());
    }

    public ArrayList<Point> getPoints() {
        return points;
    }
}
