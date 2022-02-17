package ru.arhiser.visual.engine.scene;

import ru.arhiser.visual.engine.animator.Animator;
import ru.arhiser.visual.engine.drawable.DrawableObject;

import java.util.List;

public interface AnimationProducer<T extends DrawableObject> {
    List<Animator> makeAnimation(T sceneRoot);
}
