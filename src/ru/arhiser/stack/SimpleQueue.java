package ru.arhiser.stack;

import java.util.ArrayList;

public class SimpleQueue<T> implements Queue<T> {

    private ArrayList<T> list = new ArrayList<>();

    @Override
    public void add(T item) {
        list.add(item);
    }

    @Override
    public T remove() {
        return list.remove(0);
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    public boolean contains(T item) {
        return list.contains(item);
    }
}
