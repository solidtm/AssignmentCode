package com.solid.class_notes;

public class ClassesAndObjects {

    private String name = "Richard";
    public static void main(String[] args) {
        Car lorry1 = new Car("Mercedes M1", 2, 4);
        Car lorry2 = new Car("Mercedes M2", 2, 4);
        Car lorry3 = lorry1.clone();

        System.out.println(lorry3);

        Car vehicle = new Car("Bugatti", 4, 4);
        System.out.println(vehicle.returnCarType());
        vehicle.printCarType(vehicle);


//        Lorry ferrari1 = new Ferrari("Mercedes M2", 2, 4, "2023 Model");
//        Ferrari ferrari2 = new Ferrari("Mercedes M2", 2, 4, "2023 Model");
//
//
//        Vehicle vehicle = new Vehicle();
//        vehicle.brake();
//        vehicle.brake(180);
//        vehicle.brake(180, "Circular disc");
    }


    static class Car{
        int noOfWheels;
        String name;
        int noOfDoors;

        public Car(String name, int noOfDoors, int noOfWheels){
            this.name = name;
            this.noOfDoors = noOfDoors;
            this.noOfWheels = noOfWheels;
        }

        public void move(){
            System.out.println("Car is moving...");
        }

        public void speed(int speed){
            System.out.println("Speed of car is " + speed + "km/hr");
        }

        @Override
        public String toString() {
            return "Car: name = " + name + "\t noOfWheels = " + noOfWheels + "\t noOfDoors = " + noOfDoors;
        }

        public Car clone() {
            return new Car(name, noOfDoors, noOfWheels);
        }

        public void printCarType(Car car){
            System.out.println("The car type is: " + car.name);
        }

        public Car returnCarType(){
           return new Car(name, noOfDoors, noOfWheels);
        }
    }


    static class Lorry extends Car{
        String size;
        String make;

        public Lorry(String name, int noOfDoors, int noOfWheels, String size, String make) {
            super(name, noOfDoors, noOfWheels);
            this.make = make;
            this.size = size;
        }
        @Override
        public void move(){
            System.out.println("Lorry is moving...");
        }

        @Override
        public void speed(int speed){
            System.out.println("Speed of lorry is " + speed + "km/hr");
        }

        public void brake(){
            System.out.println("Lorry is stopping...");
        }
    }

    static class Ferrari extends Lorry{
        String make;

        public Ferrari(String name, int noOfDoors, int noOfWheels, String make) {
            super(name, noOfDoors, noOfWheels, "Small", make);
            this.make = make;
        }

        @Override
        public void move(){
            System.out.println("Ferrari is moving...");
        }

        @Override
        public void speed(int speed){
            System.out.println("Speed of lorry is " + speed + "km/hr");
        }
    }

    static class Vehicle {
        public void brake(){
            System.out.println("Vehicle is stopping...");
        }

        public int brakeTyres(){return 2;}

        public void brake(int brakeLimit){
            System.out.println("Vehicle is stopping... with limit " + brakeLimit);
        }

        public void brake(int brakeLimit, String brakeType){
            System.out.println("Vehicle is stopping... with limit " + brakeLimit + " and brake type of " + brakeType);
        }
    }

    static class FileIO implements ReadWriteToFile{
        ClassesAndObjects instance = new ClassesAndObjects();
        TestClass test = new TestClass();
        String fileName = instance.name;
        @Override
        public void readFile() {
            System.out.println("Reading from file...");

        }

        @Override
        public String writeToFile(String fileName) {
            return "Successfully written to file " + fileName;
        }
    }

    abstract static class NetworkIO implements ReadWriteToFile{

        @Override
        public String writeToFile(String fileName) {
            return "Successfully written to network file " + fileName;
        }
    }

    interface ReadWriteToFile{
        void readFile();

        String writeToFile(String fileName);
    }
}
