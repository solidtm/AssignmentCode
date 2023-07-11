package com.solid.q2;

public class Booking{
    private int year;
    private int month;
    private int day;
    private int hour;
    private String facility;


    public Booking(int year, int month, int day, int hour, String facility) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.facility = facility;
    }

    public int getYear() {return year;}

    public int getMonth() {return month;}

    public int getDay() {return day;}

    public int getHour() {return hour;}

    public String getFacility() {return facility;}

    public boolean equals(Booking booking1, Booking booking2) {
        return (booking1.getFacility().equals(booking2.getFacility())) && (booking1.getHour() == booking2.getHour());
    }
}
