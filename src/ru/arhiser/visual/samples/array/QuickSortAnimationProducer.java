package ru.arhiser.visual.samples.array;

import ru.arhiser.visual.engine.animator.Animator;
import ru.arhiser.visual.engine.scene.AnimationProducer;
import ru.arhiser.visual.impl.IntArrayDrawable;

import java.util.List;

public class QuickSortAnimationProducer implements AnimationProducer<IntArrayDrawable> {

    int[] array;

    SortAnimationRecorder sortAnimationRecorder;

    public QuickSortAnimationProducer(int[] array) {
        this.array = array;
    }

    @Override
    public List<Animator> makeAnimation(IntArrayDrawable sceneRoot) {

        sortAnimationRecorder = new SortAnimationRecorder(sceneRoot, array);

        quickSort(array,  0, array.length - 1);

        return sortAnimationRecorder.getAnimationQueue();
    }

    public void quickSort(int[] arr, int from, int to) {

        if (from < to) {

            int divideIndex = partition(arr, from, to);

            quickSort(arr, from, divideIndex - 1);

            quickSort(arr, divideIndex, to);
        }
    }

    private  int partition(int[] arr, int from, int to) {
        int rightIndex = to;
        int leftIndex = from;

        int pivot = arr[from + (to - from) / 2];
        while (leftIndex <= rightIndex) {

            while (arr[leftIndex] < pivot) {
                leftIndex++;
            }

            while (arr[rightIndex] > pivot) {
                rightIndex--;
            }

            if (leftIndex <= rightIndex) {
                swap(arr, rightIndex, leftIndex);
                leftIndex++;
                rightIndex--;
            }
        }
        return leftIndex;
    }

    private void swap(int[] array, int index1, int index2) {
        int tmp  = array[index1];
        array[index1] = array[index2];
        array[index2] = tmp;
        sortAnimationRecorder.recordStep(array);
    }
}
