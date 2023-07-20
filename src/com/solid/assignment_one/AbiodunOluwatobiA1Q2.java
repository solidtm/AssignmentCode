package com.solid.assignment_one;

import java.util.*;

/**
 *
 * COMP 1020 SECTION A1Q2
 * INSTRUCTOR    Heather C. Matheson
 * ASSIGNMENT    Assignment 1, question 2
 * @author       Abiodun Oluwatobi, 7897247
 * @version      13th July, 2023
 *
 * PURPOSE: Store a list of clients, and allow the user to create new clients and make bookings to use the fitness facilities.
 */

public class AbiodunOluwatobiA1Q2 {

    static List<Client> clients = new ArrayList();
    static List<Booking> bookings = new ArrayList();
    static Map<String, Integer> bookingMap = new HashMap();
    static final double PI = 3.142;

    public static void main(String[] args) {

        while(true){
            System.out.println("Would you like to make a client (c), booking (b), or quit (q)? ");
            Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine();

            if(choice.equals("c")){
                createClient(scanner);
            }else if(choice.equals("b")){
                createBooking();
            }else if(choice.equals("q")){
                System.out.println("Program terminated successfully...");
                break;
            }else {
                System.out.println("You have entered an invalid option, " +
                        "\n please enter a valid option to continue, client (c), booking (b), or quit (q)...");
            }
        }
    }

    /**
     *
     * @param scanner  Scanner class object to receive user input
     *
     * @return  nothing is returned by this function and is used to create a client
     */

    private static void createClient(Scanner scanner){
        //Prompt the client's information:
        String name;
        int age;
        Boolean[] accessPaid = new Boolean[3]; // [true, false, false]
        String bookingAnswers;

        System.out.println("Please enter the client's name: ");
        name = scanner.nextLine();
        System.out.println("Please enter the client’s age: ");
        age = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Will the client have access to the track?");
        bookingAnswers = scanner.nextLine();
        accessPaid[0] = bookingAnswers.equals("y");
        System.out.println("Will the client have access to the pool?");
        bookingAnswers = scanner.nextLine();
        accessPaid[1] = bookingAnswers.equals("y");
        System.out.println("Will the client have access to the weight room?");
        bookingAnswers = scanner.nextLine();
        accessPaid[2] = bookingAnswers.equals("y");

        Client client = new Client(name, age, accessPaid);
        clients.add(client);
    }

    /**
     *
     * This function takes in no parameter and returns no value
     * This function creates a booking for a client
     */
    private static void createBooking(){
        Scanner scanner = new Scanner(System.in);
        String clientName;
        String bookingType;
        Client client = null;

        System.out.println("Enter the client's name: ");
        clientName = scanner.nextLine();
        System.out.println("Enter the type of booking, track (t), pool (p), weight room (w): ");
        bookingType = scanner.nextLine();
        for(Client value : clients){
            if(value.getName().equals(clientName)) client = value;
        }

        if(client != null){
            if(bookingType.equals("t")){
                if(client.getAccessPaid()[0]){
                    registerBooking(scanner, "track");
                }else{
                    System.out.println("Booking denied. The client does not have access.");
                }
            }else if(bookingType.equals("p")){
                if(client.getAccessPaid()[1]){
                    registerBooking(scanner, "pool");
                }else{
                    System.out.println("Booking denied. The client does not have access.");
                }
            }else if(bookingType.equals("w")){
                if(client.getAccessPaid()[2]){
                    registerBooking(scanner, "weight room");
                }else{
                    System.out.println("Booking denied. The client does not have access.");
                }
            }else{
                System.out.println("Enter a valid option to continue... track (t), pool (p), weight room (w):");
            }
        }else{
            System.out.println("Client does not exist, create a client first...");
        }
    }

    /**
     *
     * @param scanner  Scanner class object to receive user input
     * @param facility String representing the facility to be booked
     *
     * @return    nothing is returned by this function, used to register a client's booking for a specific facility
     */
    private static void registerBooking(Scanner scanner, String facility){
        String dateAndTime;
        int year;
        int month;
        int day;
        int hour;

        System.out.println("Enter the date and time (year month day hour):");
        dateAndTime = scanner.nextLine();
        String[] dateAndTImeArray = dateAndTime.split(" ");
        year = Integer.parseInt(dateAndTImeArray[0]);
        month = Integer.parseInt(dateAndTImeArray[1]);
        day = Integer.parseInt(dateAndTImeArray[2]);
        hour = Integer.parseInt(dateAndTImeArray[3]);

        String capacityKey = dateAndTime + facility;
        Integer capacity = bookingMap.get(capacityKey);

        if(bookingMap.isEmpty() || (capacity != null && capacity < 5)){
            bookingMap.put(capacityKey, bookingMap.getOrDefault(capacityKey, 0) + 1);
            Booking booking = new Booking(year, month, day, hour, facility);
            bookings.add(booking);
            System.out.println("Booking successful.");
        }else{
            System.out.println("Booking denied. Capacity exceeded.");
        }
    }


    /**
     * Client class to define the client object
     */
    public static class Client{
        private String name;
        private int age;
        private Boolean[] accessPaid;

        public Client(String name, int age, Boolean[] accessPaid){
            this.name = name;
            this.age = age;
            this.accessPaid = accessPaid;
        }

        public String getName(){return name;}
        public int getAge(){return age;}
        public Boolean[] getAccessPaid(){return accessPaid;}
    }

    /**
     * Booking class to define the Booking object
     */
    public static class Booking{
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
            return (booking1.getFacility().equals(booking2.getFacility())) && (booking1.getHour() == booking2.getHour())
                    && (booking1.getYear() == booking2.getYear()) && (booking1.getMonth() == booking2.getMonth()) && (booking1.getDay() == booking2.getDay());
        }
    }
}






















