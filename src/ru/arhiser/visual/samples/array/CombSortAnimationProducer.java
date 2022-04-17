package ru.arhiser.visual.samples.array;

import ru.arhiser.visual.engine.animator.Animator;
import ru.arhiser.visual.engine.scene.AnimationProducer;
import ru.arhiser.visual.impl.IntArrayDrawable;

import java.util.List;

public class CombSortAnimationProducer implements AnimationProducer<IntArrayDrawable> {

    int[] array;

    public CombSortAnimationProducer(int[] array) {
        this.array = array;
    }

    @Override
    public List<Animator> makeAnimation(IntArrayDrawable sceneRoot) {

        SortAnimationRecorder sortAnimationRecorder = new SortAnimationRecorder(sceneRoot, array);

        int gap = array.length;

        boolean isSorted = false;
        while (!isSorted || gap != 1) {

            if (gap > 1) {
                gap = gap * 10 / 13; // gap / 1.3
            } else {
                gap = 1;
            }

            isSorted = true;
            for (int i = gap; i < array.length; i++) {
                sortAnimationRecorder.highliteElements(array[i - gap], array[i]);
                if (array[i - gap] > array[i]) {
                    int tmp = array[i];
                    array[i] = array[i - gap];
                    array[i - gap] = tmp;
                    isSorted = false;

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
