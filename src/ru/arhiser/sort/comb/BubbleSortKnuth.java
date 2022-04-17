package ru.arhiser.sort.comb;

public class BubbleSortKnuth {

    public static int sort(int[] array) {
        int count = 0;
        int k = 1;
        int j = array.length;
        while (k > 0) {
            k = 0;
            for (int i = 1; i < j; i++) {
                if (array[i - 1] > array[i]) {
                    int tmp = array[i];
                    array[i] = array[i - 1];
                    array[i - 1] = tmp;
                    k = i;
                }
                count++;
            }
            j = k;
        }
        return count;
    }
}
