package ru.arhiser.sort.comb;

import java.util.Arrays;

public class CombSort {

    public static void main(String[] args) {
        int[] array = new int[] {64, 73, 41, 32, 53, 16, 24, 57, 42, 74, 55, 36};
        sort(array);
        System.out.println(Arrays.toString(array));
    }

    public static void sort(int[] array) {
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
                if (array[i - gap] > array[i]) {
                    int tmp = array[i];
                    array[i] = array[i - gap];
                    array[i - gap] = tmp;
                    isSorted = false;
                }
            }
        }
    }
}
