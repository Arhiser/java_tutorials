package ru.arhiser.visual.samples.array;

import ru.arhiser.visual.engine.animator.Animator;
import ru.arhiser.visual.engine.scene.AnimationProducer;
import ru.arhiser.visual.impl.IntArrayDrawable;

import java.util.List;

public class SelectionSortAnimationProducer implements AnimationProducer<IntArrayDrawable> {

    int[] array;

    public SelectionSortAnimationProducer(int[] array) {
        this.array = array;
    }

    @Override
    public List<Animator> makeAnimation(IntArrayDrawable sceneRoot) {

        SortAnimationRecorder sortAnimationRecorder = new SortAnimationRecorder(sceneRoot, array);

        for (int i = 0; i < array.length; i++) {
            int minIndex = min(array, i, array.length);
            int tmp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = tmp;
            sortAnimationRecorder.recordStep(array);
        }

        return sortAnimationRecorder.getAnimationQueue();
    }

    private static int min(int[] array, int start, int end) {
        int minIndex = start;
        int minValue = array[start];
        for (int i = start + 1; i < end; i++) {
            if (array[i] < minValue) {
                minValue = array[i];
                minIndex = i;
            }
        }
        return minIndex;
    }
}
