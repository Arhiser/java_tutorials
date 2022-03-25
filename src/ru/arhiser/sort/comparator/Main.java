//package ru.arhiser.sort.comparator;
//
//import ru.arhiser.sort.count.CountSortObjects;
//
//import java.util.*;
//
//public class Main {
//    public static void main(String[] args) {
//        example2();
//    }
//
//    private static void example1() {
//        Integer[] array = new Integer[] {64, 42, 73, 41, 32, 53, 16, 24, 57, 42, 74, 55, 36};
//        Arrays.sort(array, new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return o1 - o2;
//            }
//        });
//        System.out.println(Arrays.toString(array));
//    }
//
//    private static void  example2() {
//        ArrayList<CountSortObjects.Worker> workerList = new ArrayList<>();
//
//        workerList.add(new CountSortObjects.Worker(123, "Васильев Евстахий Борисович", "+129381832", 5));
//        workerList.add(new CountSortObjects.Worker(151, "Коновалов Степан Петрович", "+234432334", 7));
//        workerList.add(new CountSortObjects.Worker(332, "Калинин Артём Валериевич", "+2234234423", 2));
//        workerList.add(new CountSortObjects.Worker(432, "Предыбайло Григорий Анатолиевич", "+2342344234", 5));
//        workerList.add(new CountSortObjects.Worker(556, "Степанов Мирослав Андреевич", "+6678877777", 3));
//        workerList.add(new CountSortObjects.Worker(556, "Пупкин Василий Степанович", "+6678877777", 2));
//
//        bubbleSortKnuth(workerList, new Comparator<CountSortObjects.Worker>() {
//            @Override
//            public int compare(CountSortObjects.Worker o1, CountSortObjects.Worker o2) {
//                int result = o1.qualification - o2.qualification;
//                if (result == 0) {
//                    return o1.name.compareTo(o2.name);
//                }
//                return result;
//            }
//        });
//        /*
//        Collections.sort(workerList, new Comparator<CountSortObjects.Worker>() {
//            @Override
//            public int compare(CountSortObjects.Worker o1, CountSortObjects.Worker o2) {
//                int result = o1.qualification - o2.qualification;
//                if (result == 0) {
//                    return o1.name.compareTo(o2.name);
//                }
//                return result;
//            }
//        });
//         */
//
//        for(CountSortObjects.Worker worker: workerList) {
//            System.out.println(worker);
//        }
//    }
//
//    public static <T> int bubbleSortKnuth(List<T> list, Comparator<T> comparator) {
//        int count = 0;
//        int k = 1;
//        int j = list.size();
//        while (k > 0) {
//            k = 0;
//            for (int i = 1; i < j; i++) {
//                if (comparator.compare(list.get(i - 1), list.get(i)) > 0) {
//                    T tmp = list.get(i);
//                    list.set(i, list.get(i - 1));
//                    list.set(i - 1, tmp);
//                    k = i;
//                }
//                count++;
//            }
//            j = k;
//        }
//        return count;
//    }
//}
