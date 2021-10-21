package ru.arhiser.iterator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class MainIteration {
    public static void main(String[] args) {

        Integer[] array = new Integer[] {64, 42, 73, 41, 32, 53, 16, 24, 57, 42, 74, 55, 36};

        ArrayList<Integer> list = new ArrayList<>();

        Collections.addAll(list, array);

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        for (Integer integer: list) {
            System.out.println(integer);
        }
    }
}
