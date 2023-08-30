package com.solid.assignment_five;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * COMP 1020 SECTION A5Q2
 * INSTRUCTOR    Heather C. Matheson
 * ASSIGNMENT    Assignment 5, Question 2
 * @author       Abiodun Oluwatobi, 7897247
 * @version      11th August, 2023
 *
 * PURPOSE: Program that simulates a checkout scenario
 */
public class AbiodunOluwatobiA5Q2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String fileName;
        int numCashiers;
        CheckoutLine[] checkoutLines;

        //accept user inputs
        System.out.print("Please enter the name of a data file: ");
        fileName = scanner.nextLine();
        System.out.print("Please enter the number of cashiers available: ");
        numCashiers = scanner.nextInt();
        scanner.nextLine();

        try(BufferedReader br = new BufferedReader(new FileReader(fileName))){ //read file line by line
            checkoutLines = new CheckoutLine[numCashiers];
            for (int i = 0; i < numCashiers; i++) {  //initialize checkoutLines array based on available cashiers
                checkoutLines[i] = new CheckoutLine();
            }

            String line;
            int currentCashier = 0;
            while ((line = br.readLine()) != null) {
                if (line.equals("CART")) { //start of customer item
                    Cart cart = new Cart();
                    while (!(line = br.readLine()).equals("CART")) {
                        String[] parts = line.split(",");
                        cart.add(new FoodItem(parts[0], Double.parseDouble(parts[1]))); //add food item to the cart
                    }
                    checkoutLines[currentCashier].enqueue(cart); //enqueue cart to  current cashier
                    System.out.println("A cart with " + cart.numItems() + " items has been added to cashier " + (currentCashier + 1));
                    currentCashier = (currentCashier + 1) % numCashiers;
                }
            }

            //Print summary of checkout lines for each cashier.
            System.out.println("Summary of checkout lines:");
            for (int i = 0; i < numCashiers; i++) {
                System.out.println("Cashier " + (i + 1) + " has " + checkoutLines[i].numCarts() + " customers waiting, with total estimated time " + checkoutLines[i].estimatedTime() + " seconds.");
            }
            System.out.println("Program terminated normally");
        }catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
    }
}

/**
 * CartQueue interface defines methods to implement a Queue for teh CheckoutLine
 */
interface CartQueue{
    void enqueue(Cart newCart);  //add Cart to the queue
    Cart peek();    //get front Cart in the queue
    Cart dequeue();   //remove Cart from the queue
    boolean isEmpty(); //check if Queue is empty
}

/**
 * CartQueueNode class implementing the CartQueueNode object to define a Checkout Cart in the queue
 */
class CartQueueNode {
    Cart cart;
    CartQueueNode next;

    public CartQueueNode(Cart cart) {
        this.cart = cart;
        this.next = null;
    }
}

/**
 * Cart class implementing the FoodList interface to define the Cart object
 */
class Cart implements FoodList{
    /**
     * FoodNode class to define a Food item (as a node) in the List.
     */
    private class FoodNode{
        FoodItem item;
        FoodNode next;

        FoodNode(FoodItem item) {
            this.item = item;
            this.next = null;
        }
    }

    private FoodNode head;  //first item in the linked list

    //Constructor
    public Cart(){
        head = null;
    }

    /**
     * @param foodItem FoodItem object to be added to the list
     * This method adds a value to the List.
     */
    public void add(FoodItem foodItem) {
        FoodNode newNode = new FoodNode(foodItem); //create a node for the item
        if (head == null) { //linked list is empty
            head = newNode;
        } else {  //linked list is not empty
            FoodNode current = head;
            while (current.next != null) { //loop through to the end of the list
                current = current.next;
            }
            current.next = newNode; //add the food item as the next on the list.
        }
    }

    /**
     * @return int value representing the number of items in the list
     * This method returns the number of items in the list.
     */
    public int numItems() {
        int count = 0;
        FoodNode current = head;
        while (current != null) { //loop through the list
            count++; //increment count of nodes
            current = current.next; //go to next node
        }
        return count;
    }

    /**
     * @return double value representing the sum of prices of all items in the list
     * This method computes and returns the total cost of food items in the list.
     */
    public double totalCost() {
        double sum = 0; //variable to hold the sum of item costs
        FoodNode current = head;
        while (current != null) { //loop through each item in the list
            sum += current.item.getPrice(); //add each item prices
            current = current.next; //move to next item
        }
        return sum;
    }

    /**
     * @return int value representing the total number of items in the list
     * This method calls numItems() and returns the total number of items in the list.
     */
    public int getTotalItems() {
        return numItems();
    }
}

/**
 * CheckoutLine class implementing the CartQueue interface to provide implementations for a Checkout queue
 */
class CheckoutLine implements CartQueue{
    private CartQueueNode head;  //variable pointing to the front of queue
    private CartQueueNode tail;  //variable pointing to th end of queue

    //Constructor
    public CheckoutLine() {
        head = null;
        tail = null;
    }

    /**
     * @return Cart value representing the value of the Cart at the front of the queue
     * This method does not remove the front Cart from the queue
     */
    @Override
    public Cart peek() {
        return isEmpty() ? null : head.cart;
    }

    /**
     * @param newCart Cart object to be added to back of the queue
     *
     */
    @Override
    public void enqueue(Cart newCart) {
        CartQueueNode newNode = new CartQueueNode(newCart);  //create node to be added
        if (isEmpty()) {  //no items in queue, let the new node be both the head and tail
            head = newNode;
            tail = newNode;
        } else {  //there is an item in the queue
            tail.next = newNode; //set next item in the queue to the newNode
            tail = newNode;   // newNode becomes the tail.
        }
    }

    /**
     * @return Cart value representing the value of the Cart at the front of the queue
     * This method removes the front Cart from the queue
     */
    @Override
    public Cart dequeue() {
        if (isEmpty()) return null;  //return null if  no value in the queue

        //queue has items
        Cart cart = head.cart; //get Cart in front of queue
        head = head.next; //next value becomes the front of queue
        if (head == null) tail = null; //if there is no next value in queue, set tail to null
        return cart;
    }

    /**
     * @return boolean value representing if the queue is empty or not
     */
    @Override
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * @return int value representing the number of Carts in the checkout line
     */
    public int numCarts() {
        int count = 0;
        CartQueueNode current = head;
        while (current != null) { //loop through all items in the queue
            count++; //increment count for each item
            current = current.next;  //move to the next item
        }
        return count;
    }

    /**
     * @return int value representing the estimates time to process all carts in a queue
     */
    public int estimatedTime() {
        int totalSeconds = 0;
        CartQueueNode current = head;
        while (current != null) { //go through each item in the queue
            totalSeconds += current.cart.getTotalItems() * 5; // Time to scan items
            totalSeconds += 30; // Time to process payment
            current = current.next;
        }
        return totalSeconds;
    }
}

/**
 * FoodList interface to define the checkout cart methods
 */
interface FoodList{
    void add(FoodItem f); //add an item to the (unordered) list
    double totalCost(); //get the sum of all prices
    int numItems(); //get the number of items in the list
}

/**
 * FoodItem class to define the FoodItem object and provide implementations for a FoodItem object
 */
class FoodItem{
    private String description;
    private double price;

    public FoodItem(String d, double p){
        description = d;
        price = p;
    }

    public String getDescription(){
        return description;
    }

    public double getPrice(){
        return price;
    }

    public String toString(){
        return String.format("(%s, $%4.2f)", description, price);
    }
}
