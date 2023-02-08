package com.sayfr.intervjuprosjekt;

public class Booking {
    private Room room;
    private int days;
    private String startDate;
    private String endDate;
    private String startDay;
    private String endDay;
    private CalendarMechanism calendar = new CalendarMechanism(1, 1, 2023);

    public Booking(Room room, int days){
        this.room = room;
        if(days < 1){
            throw new IllegalArgumentException("Days must be greater than 0");
        }
        this.days = days;
        this.startDay = calendar.getWeekDayInAmountOfDays(0);
        this.endDay = calendar.getWeekDayInAmountOfDays(days);
        this.startDate = calendar.getDateInAmountOfDays(0);
        this.endDate = calendar.getDateInAmountOfDays(days);
    }
    @Override
    public String toString() {
        return "Booked room " + this.room.getRoomNumber() + " for " + this.days + " days. " + this.startDay + " " + this.startDate + " to " + this.endDay + " " + this.endDate + ".";
    }
}
