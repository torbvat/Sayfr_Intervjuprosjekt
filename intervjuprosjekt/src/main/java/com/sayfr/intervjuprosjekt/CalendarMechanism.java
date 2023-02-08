package com.sayfr.intervjuprosjekt;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CalendarMechanism {
    private int month;
    private int day;
    private int year;
    private final int[] monthDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
  
    public CalendarMechanism(int month, int day, int year) {
      this.month = month;
      this.day = day;
      this.year = year;
    }
  
    public String getWeekDayInAmountOfDays(int days) {
        if(days > 364 || days < 0){
            throw new IllegalArgumentException("Enter an amount of days from 1/1/2023 which will still be in the year 2023 (integer from 0-364))");
        }
        int daysFromStart = daysFromStartOfYear(month, day, year);
        int weekday = (days + daysFromStart-1) % 7;
        switch (weekday) {
            case 0: //(First day of the year is a monday.)
                return "Monday";
            case 1:
                return "Tuesday";
            case 2:
                return "Wednesday";
            case 3:
                return "Thursday";
            case 4:
                return "Friday";
            case 5:
                return "Saturday";
            case 6:
                return "Sunday";
            default:
                throw new IllegalArgumentException("Enter an integer between 0 and 364, representing the amount of days from 1/1/2023");
        }
    }

// Returns the amount of days from the first day of the year (restricted to the year 2023)
    private int daysFromStartOfYear(int month, int day, int year) { 
        int days = 0;

        for (int i = 0; i < month-1; i++) { //
            days += monthDays[i];
        }
        days += day;
        return days;
    }

    //Returns the date, in form of a list, after the specified amount of days from the current date.
    private ArrayList<Integer> getDateInAmountOfDays_List(int days){
        ArrayList<Integer> date = new ArrayList<Integer>();
        int totalDays = daysFromStartOfYear(month, day, year);
        totalDays += days;

        int year = 2023;
        int currentMonth = month;
        System.out.println(currentMonth);
        int currentDay = day;

        while (totalDays > 0) {
            if (totalDays > monthDays[currentMonth]) { // If the total days is greater than the days in the current month
                totalDays -= monthDays[currentMonth]; // Subtract the days in the current month from the total days
                currentMonth = (currentMonth + 1) % 12; // Increment the current month by 1.If it is greater than 12, set it to 0
                if (currentMonth == 0) {
                    year++;
                }
            } else {
                currentDay += totalDays;
                totalDays = 0;
            }
        }
        date.add(currentMonth);
        date.add(currentDay);
        date.add(year);
        return date;
    }
    
    //Formats the list returned by dateInAmountOfDays into a string of pattern dd.MM.yyyy.
    public String getDateInAmountOfDays(int days) {
        if(days<0){
            return "Enter a positive amount of days";
        }
        ArrayList<Integer> dateList = getDateInAmountOfDays_List(days);
        LocalDate date = LocalDate.of(dateList.get(2), dateList.get(0)+1, dateList.get(1));
        return date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static void main(String[] args) {
        CalendarMechanism calendar = new CalendarMechanism(4, 1, 2023);
        System.out.println(calendar.getWeekDayInAmountOfDays(1));
        System.out.println(calendar.getWeekDayInAmountOfDays(2));
        System.out.println(calendar.getWeekDayInAmountOfDays(3));
        System.out.println(calendar.getWeekDayInAmountOfDays(4));
        System.out.println(calendar.getWeekDayInAmountOfDays(5));
        System.out.println(calendar.getWeekDayInAmountOfDays(6));
        System.out.println(calendar.getWeekDayInAmountOfDays(0));
        System.out.println(calendar.getWeekDayInAmountOfDays(364));
        //System.out.println(calendar.getWeekDayInAmountOfDays(366));
        //System.out.println(calendar.getDateInAmountOfDays(365));
        System.out.println(calendar.getDateInAmountOfDays(31));
        System.out.println(calendar.getDateInAmountOfDays(59));
        System.out.println(calendar.getWeekDayInAmountOfDays(-1));
        System.out.println(calendar.getDateInAmountOfDays(-1));
    }
}
