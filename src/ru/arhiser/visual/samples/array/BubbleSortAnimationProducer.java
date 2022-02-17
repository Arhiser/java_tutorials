package ru.arhiser.visual.samples.array;

import ru.arhiser.visual.engine.animator.Animator;
import ru.arhiser.visual.engine.animator.AnimatorGroup;
import ru.arhiser.visual.engine.animator.PropertyAnimator;
import ru.arhiser.visual.engine.animator.evaluator.IntLinearEvaluator;
import ru.arhiser.visual.engine.drawable.DrawableObject;
import ru.arhiser.visual.engine.scene.AnimationProducer;
import ru.arhiser.visual.impl.IntArrayDrawable;

import java.util.ArrayList;
import java.util.List;

public class BubbleSortAnimationProducer implements AnimationProducer<IntArrayDrawable> {

    int[] array;

    public BubbleSortAnimationProducer(int[] array) {
        this.array = array;
    }

    @Override
    public List<Animator> makeAnimation(IntArrayDrawable sceneRoot) {

        SortAnimationRecorder sortAnimationRecorder = new SortAnimationRecorder(sceneRoot, array);

        for (int i = 0; i < array.length; i++) {
            for (int j = 1; j < array.length - i; j++) {
                if (array[j - 1] > array[j]) {
                    int tmp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = tmp;

                    sortAnimationRecorder.recordStep(array);
                }
            }
        }

        return sortAnimationRecorder.getAnimationQueue();
    }
/*
    @Override
    public List<Animator> makeAnimation(IntArrayDrawable sceneRoot) {
        ArrayList<Animator> animators = new ArrayList<>();

        ArrayList<Integer> positions = new ArrayList<>();
        for (DrawableObject drawableObject: sceneRoot.getDrawables()) {
            positions.add(drawableObject.getX());
        }

        for (int i = 0; i < array.length; i++) {
            for (int j = 1; j < array.length - i; j++) {
                if (array[j - 1] > array[j]) {

                    DrawableObject drawable1 = sceneRoot.getDrawableForIndex(array[j - 1]);
                    DrawableObject drawable2 = sceneRoot.getDrawableForIndex(array[j]);
                    PropertyAnimator<Integer> animator1 = new PropertyAnimator<>(drawable1.getX(), drawable2.getX(),
                            30, drawable1::setX, new IntLinearEvaluator());
                    PropertyAnimator<Integer> animator2 = new PropertyAnimator<>(drawable2.getX(), drawable1.getX(),
                            30, drawable2::setX, new IntLinearEvaluator());
                    AnimatorGroup animatorGroup = new AnimatorGroup(animator1, animator2);
                    animators.add(animatorGroup);

                    int x = drawable1.getX();
                    drawable1.setX(drawable2.getX());
                    drawable2.setX(x);

                    int tmp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = tmp;
                }
            }
        }

        for (int i = 0; i < positions.size(); i++) {
            sceneRoot.getDrawables().get(i).setX(positions.get(i));
        }

        return animators;
    }
 */
}
