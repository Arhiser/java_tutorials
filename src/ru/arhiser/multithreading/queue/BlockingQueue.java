package ru.arhiser.multithreading.queue;

import java.util.ArrayList;

public class BlockingQueue {

    ArrayList<Runnable> tasks = new ArrayList<>();

    synchronized void put(Runnable task) {
        tasks.add(task);
        notify();
    }

    synchronized Runnable get() {
        while (tasks.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Runnable task = tasks.get(0);
        tasks.remove(task);
        return task;
    }
}
