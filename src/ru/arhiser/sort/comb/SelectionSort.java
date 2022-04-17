package ru.arhiser.sort.comb;

public class SelectionSort {

    public static void sort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int minIndex = min(array, i, array.length);
            int tmp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = tmp;
        }
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
