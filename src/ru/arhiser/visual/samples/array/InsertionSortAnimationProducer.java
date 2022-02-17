package ru.arhiser.visual.samples.array;

import ru.arhiser.visual.engine.animator.Animator;
import ru.arhiser.visual.engine.scene.AnimationProducer;
import ru.arhiser.visual.impl.IntArrayDrawable;

import java.util.List;

public class InsertionSortAnimationProducer implements AnimationProducer<IntArrayDrawable> {

    int[] array;

    public InsertionSortAnimationProducer(int[] array) {
        this.array = array;
    }

    @Override
    public List<Animator> makeAnimation(IntArrayDrawable sceneRoot) {

        SortAnimationRecorder sortAnimationRecorder = new SortAnimationRecorder(sceneRoot, array);
/*
        for (int i = 1; i < array.length; i++) {
            int current = array[i];
            int j = i;
            while (j > 0 && array[j - 1] > current) {
                array[j] = array[j - 1];
                array[j - 1] = current;
                j--;
                sortAnimationRecorder.recordStep(array);
            }
            array[j] = current;
            sortAnimationRecorder.recordStep(array);
        }
        */

        for (int i = 1; i < array.length; i++) {
            int current = array[i];
            int j = i;
            while (j > 0 && array[j - 1] > current) {
                array[j] = array[j - 1];
                j--;
            }
            array[j] = current;
            sortAnimationRecorder.recordStep(array);
        }

        return sortAnimationRecorder.getAnimationQueue();
    }

}
