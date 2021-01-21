package ru.arhiser.sort.count;

import java.util.ArrayList;
import java.util.List;

public class CountSortObjects {
    public static void main(String[] params) {

        ArrayList<Worker> workerList = new ArrayList<>();

        workerList.add(new Worker(123, "Васильев Евстахий Борисович", "+129381832", 5));
        workerList.add(new Worker(151, "Коновалов Степан Петрович", "+234432334", 7));
        workerList.add(new Worker(332, "Калинин Артём Валериевич", "+2234234423", 2));
        workerList.add(new Worker(432, "Предыбайло Григорий Анатолиевич", "+2342344234", 5));
        workerList.add(new Worker(556, "Степанов Мирослав Андреевич", "+6678877777", 3));
        workerList.add(new Worker(556, "Пупкин Василий Степанович", "+6678877777", 2));

        for(Worker worker: countSortObject(workerList)) {
            System.out.println(worker);
        }
    }

    public static Worker[] countSortObject(List<Worker> workers) {

        final int MAX_VALUE = 10;

        int[] count = new int[MAX_VALUE];

        for (int i = 0; i < workers.size(); i++) {
            count[workers.get(i).qualification] = count[workers.get(i).qualification] + 1;
        }

        Worker[] out = new Worker[workers.size()];

        for (int i = 1; i < count.length; i++) {
            count[i] = count[i] + count[i - 1];
        }

        for (int i = workers.size() - 1; i >= 0 ; i--) {
            Worker worker = workers.get(i);
            out[count[worker.qualification] - 1] = worker;
            count[worker.qualification]--;
        }

        return out;
    }

    static class Worker {
        int id;
        String name;
        String phone;
        int qualification;

        public Worker(int id, String name, String phone, int qualification) {
            this.id = id;
            this.name = name;
            this.phone = phone;
            this.qualification = qualification;
        }

        @Override
        public String toString() {
            return "Worker{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", phone='" + phone + '\'' +
                    ", qualification=" + qualification +
                    '}';
        }
    }
}
