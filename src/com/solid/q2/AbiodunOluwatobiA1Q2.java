package com.solid.q2;


import java.util.*;

//* WHAT AM I DOING? - Simulate playing a lottery, to determine the chane of winning
// * HOW AM I DOING IT? -
public class AbiodunOluwatobiA1Q2 {

    static List<Client> clients = new ArrayList();
    static List<Booking> bookings = new ArrayList();
    static Map<String, Integer> bookingMap = new HashMap();

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
}



















