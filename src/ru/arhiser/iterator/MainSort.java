package ru.arhiser.iterator;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class MainSort {

    public static void main(String[] args) {

        ArrayList<Integer> arrayListTest1 = new ArrayList<>();
        fillListWithRandoms(arrayListTest1);
        ArrayList<Integer> arrayListTest2 = new ArrayList<>(arrayListTest1);

        LinkedList<Integer> linkedListTest1 = new LinkedList<>(arrayListTest1);
        LinkedList<Integer> linkedListTest2 = new LinkedList<>(arrayListTest1);

        System.out.println("Array list naive");
        measureTime(() -> {
            sortNaive(arrayListTest1);
        });
        System.out.println("Array list iterator");
        measureTime(() -> {
            sortListIterator(arrayListTest2);
        });
        System.out.println("Linked list naive");
        measureTime(() -> {
            sortNaive(linkedListTest1);
        });
        System.out.println("Linked list iterator");
        measureTime(() -> {
            sortListIterator(linkedListTest2);
        });
    }

    private static void sortNaive(List<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            int current = list.get(i);
            int min = current;
            int minIndex = i;

            for (int j = i; j < list.size(); j++) {
                if (list.get(j) < min) {
                    min = list.get(j);
                    minIndex = j;
                }
            }

            list.set(i, min);
            list.set(minIndex, current);
        }
    }

    private static void sortListIterator(List<Integer> list) {
        ListIterator<Integer> iterator = list.listIterator();
        while (iterator.hasNext()) {
            ListIterator<Integer> minimal = getMinIterator(list, list.listIterator(iterator.nextIndex()));
            int current = iterator.next();
            iterator.set(minimal.next());
            minimal.set(current);
        }
    }

    private static ListIterator<Integer> getMinIterator(List<Integer> list, ListIterator<Integer> iterator) {
        ListIterator<Integer> minPosition = list.listIterator(iterator.nextIndex());
        int min = iterator.next();
        while (iterator.hasNext()) {
            int current = iterator.next();
            if (current < min) {
                min = current;
                while (minPosition.nextIndex() < iterator.nextIndex() - 1) {
                    minPosition.next();
                }
            }
        }
        return minPosition;
    }

    public static void fillListWithRandoms(List<Integer> list) {
        for (int i = 0; i < 5000; i++) {
            list.add((int) Math.round(Math.random() * 256));
        }
    }

    public static void measureTime(Runnable task) {
        long startTime = System.currentTimeMillis();
        task.run();
        long elapsed = System.currentTimeMillis() - startTime;
        System.out.println("Затраченное время: " + elapsed + " ms");
    }
}
