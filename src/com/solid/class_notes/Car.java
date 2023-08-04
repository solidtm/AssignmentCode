package com.solid.class_notes;

public class Car{
    private int speed;
    private int noOfDoors;
    private static Car instance;

    //constructor
    private Car(){
    }

    public static Car getInstance(){
        if(instance == null){
            instance = new Car();
            instance.speed = 200;
            instance.noOfDoors = 4;
        }

        return instance;
    }

    public int getSpeed(){return speed;}

    @Override
    public String toString() {
        return "Car has speed of " + this.speed + " and " + this.noOfDoors + " number of doors";
    }
}
