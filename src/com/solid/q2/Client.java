package com.solid.q2;

public class Client{
    private String name;
    private int age;
    private Boolean[] accessPaid;
    private Booking[] bookings;

    public Client(String name, int age, Boolean[] accessPaid){
        this.name = name;
        this.age = age;
        this.accessPaid = accessPaid;
        this.bookings = new Booking[3];
    }

    public String getName(){return name;}
    public int getAge(){return age;}
    public Boolean[] getAccessPaid(){return accessPaid;}


}
