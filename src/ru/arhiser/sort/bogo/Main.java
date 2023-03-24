package ru.arhiser.sort.bogo;

import ru.arhiser.Utils;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] array = new int[] {64, 42, 73, 41, 32, 53, 16, 24, 57, 42, 74, 55, 36};

        Utils.measureTime(() -> {
            while (!isSorted(array)) {
                shuffle(array);
            }
        }, "bogosort");

        System.out.println(Arrays.toString(array));

    }

    static boolean isSorted(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] > arr[i]) {
                return false;
            }
        }
        return true;
    }

    static void shuffle(int[] arr) {
        int temp;
        int index;
        for (int i = 0; i < arr.length; i++) {
            index = (int)(Math.random() * arr.length);

            temp = arr[i];
            arr[i] = arr[index];
            arr[index] = temp;
        }
    }
}
