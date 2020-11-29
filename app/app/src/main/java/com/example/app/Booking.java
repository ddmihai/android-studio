package com.example.app;

public class Booking {
    private int day;
    private int year;
    private int month;
    private double hour;
    private String eatery;
    private String address;
    public Booking(int year, int month, int day, double hour, String eatery,String address) {
        this.day = day;
        this.year = year;
        this.month = month;
        this.hour = hour;
        this.eatery = eatery;
        this.address=address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Booking() {
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public double getHour() {
        return hour;
    }

    public void setHour(double hour) {
        this.hour = hour;
    }

    public String getEatery() {
        return eatery;
    }

    public void setEatery(String eatery) {
        this.eatery = eatery;
    }
}

