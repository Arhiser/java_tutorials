package ru.arhiser.sort;

public class SelectionSort {

    public static void main(String[] args) {
        int [] array = new int[] {64, 42, 73, 41, 32, 53, 16, 24, 57, 42, 74, 55, 36};

        for (int i = 0; i < array.length; i++) {
            System.out.println(arrayToString(array));
            int minIndex = min(array, i, array.length);

            int tmp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = tmp;
        }

        System.out.println(arrayToString(array));
        System.out.println("min value index: " + min(array, 0, array.length));
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

    private static String arrayToString(int[] array) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < array.length; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(array[i]);
        }
        sb.append("]");
        return sb.toString();
    }
}
