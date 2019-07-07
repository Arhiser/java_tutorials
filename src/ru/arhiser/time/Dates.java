package ru.arhiser.time;

import java.util.Date;

public class Dates {

    public static void main(String[] params) {

        //Объектная обертка, более удобно чем просто long

        Date date1 = new Date();
        Date date2 = new Date(System.currentTimeMillis());

        date2.setTime(System.currentTimeMillis());

        System.out.println(date1);

        System.out.println("Время в миллисекундах: " + date1.getTime());

        System.out.println("Сравнение: " + date1.after(date2));

        System.out.println("Еще сравнение: " + (date1.compareTo(date2) > 0));
    }
}
