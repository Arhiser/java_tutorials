package ru.arhiser.time;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Calendars {

    public static void main(String[] args) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSSS X");

        long millis = System.currentTimeMillis();
        Calendar calendar = Calendar.getInstance();

        calendar.setTimeInMillis(millis);
        System.out.println("minutes: " + calendar.get(Calendar.MINUTE));
        System.out.println("hours: " + calendar.get(Calendar.HOUR_OF_DAY));

        System.out.println("\nday of week: " + calendar.get(Calendar.DAY_OF_WEEK));
        System.out.println("day of month: " + calendar.get(Calendar.DAY_OF_MONTH));
        System.out.println("day of year: " + calendar.get(Calendar.DAY_OF_YEAR));

        calendar.add(Calendar.DAY_OF_YEAR, -1);
        System.out.println("yesterday: " + format.format(calendar.getTime()));

        System.out.println("\nis leap year 2019: " + isLeapYear(2019));
        System.out.println("is leap year 2020: " + isLeapYear(2020));

        System.out.println("\nstart of week day: " + calendar.getFirstDayOfWeek());
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
        System.out.println("start of week date: " + format.format(calendar.getTime()));

        System.out.println("\nis dates of the same day: " + dateOfSameDay(Calendar.getInstance(),
                Calendar.getInstance()));

        System.out.println("\nstart of day: " + format.format(calendarStartOfDay(calendar).getTime()));

        System.out.println("\nstart of month: " + format.format(calendarStartOfMonth(calendar).getTime()));
    }

    public static boolean isLeapYear(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, Calendar.FEBRUARY, 1);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH) == 29;
    }

    public static boolean dateOfSameDay(Calendar calendar1, Calendar calendar2) {
        return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR)
                && calendar1.get(Calendar.DAY_OF_YEAR) == calendar2.get(Calendar.DAY_OF_YEAR);
    }

    public static Calendar calendarStartOfDay(Calendar dateTime) {
        Calendar calendar = (Calendar) dateTime.clone();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }

    public static Calendar calendarStartOfMonth(Calendar dateTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateTime.getTime());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar;
    }
}
