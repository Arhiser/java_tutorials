package ru.arhiser.sort.comb;

import ru.arhiser.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class PerformanceTest {
    public static void main(String[] args) {
        ArrayList<Integer> generated = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            generated.add(i);
        }
        Collections.shuffle(generated);

        int[] array1 = generated.stream().mapToInt(i->i).toArray();
        int[] array2 = new int[array1.length];
        System.arraycopy(array1, 0, array2, 0, array1.length);
        int[] array3 = new int[array1.length];
        System.arraycopy(array1, 0, array3, 0, array1.length);
        int[] array4 = new int[array1.length];
        System.arraycopy(array1, 0, array4, 0, array1.length);
        int[] array5 = new int[array1.length];
        System.arraycopy(array1, 0, array5, 0, array1.length);
        int[] array6 = new int[array1.length];
        System.arraycopy(array1, 0, array6, 0, array1.length);
        int[] array7 = new int[array1.length];
        System.arraycopy(array1, 0, array7, 0, array1.length);
        int[] array8 = new int[array1.length];
        System.arraycopy(array1, 0, array8, 0, array1.length);


        Utils.measureTime(() -> BubbleSort.sort(array1), "Bubble sort");
        Utils.measureTime(() -> BubbleSortKnuth.sort(array2), "Bubble sort Knuth");

        Utils.assertTrue(Arrays.equals(array1, array2));

        System.out.println();

        Utils.measureTime(() -> BubbleSort.sort(array4), "Bubble sort");
        Utils.measureTime(() -> BubbleSortKnuth.sort(array3), "Bubble sort Knuth");

        System.out.println();

        Utils.measureTime(() -> BubbleSort.sort(array5), "Bubble sort");
        Utils.measureTime(() -> BubbleSortKnuth.sort(array6), "Bubble sort Knuth");
        Utils.measureTime(() -> SelectionSort.sort(array7), "Selection sort");
        Utils.measureTime(() -> CombSort.sort(array8), "Comb sort");

        Utils.assertTrue(Arrays.equals(array6, array7));
        Utils.assertTrue(Arrays.equals(array7, array8));
    }
}
