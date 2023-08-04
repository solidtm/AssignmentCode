package com.solid.class_notes;

public class TestClass {
    public static void main(String[] args) {
        Car car = Car.getInstance();
        String textToPrint = car.toString();

        System.out.println(textToPrint);

        //Comparing Objects
        //Using  == or equals() method or compareTo() method

       String x = new String("6");
       String y = "6";

        System.out.println("Is x == y? : " + (x == y));  //reference equality
        System.out.println("Is x.equals(y)? : " + (x.equals(y))); //value equality
    }
}
