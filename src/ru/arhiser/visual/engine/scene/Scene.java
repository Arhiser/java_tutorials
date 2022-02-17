package ru.arhiser.visual.engine.scene;

import ru.arhiser.visual.engine.animator.Animator;
import ru.arhiser.visual.raster.Raster;
import ru.arhiser.visual.engine.drawable.DrawableObject;

import java.util.ArrayList;
import java.util.List;

public class Scene {
    Raster raster;

    DrawableObject root;

    ArrayList<Animator> animatorQueue = new ArrayList<>();

    int getWidth() {
        return raster.getWidth();
    }

    int getHeight() {
        return raster.getHeight();
    }

    public Scene(Raster raster, DrawableObject root, List<Animator> animators) {
        this.raster = raster;
        this.root = root;
        animatorQueue.addAll(animators);
    }

    public boolean renderNext() {
        root.draw(raster);
        return makeAnimationStep();
    }

    public boolean makeAnimationStep() {
        if (animatorQueue.isEmpty()) {
            return false;
        }
        Animator animator = animatorQueue.get(0);
        boolean result = animator.makeStep();
        if (!result) {
            animatorQueue.remove(0);
        }
        return true;
    }
}
