package com.solid.assignment_five;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * COMP 1020 SECTION A5Q1
 * INSTRUCTOR    Heather C. Matheson
 * ASSIGNMENT    Assignment 5, Question 1
 * @author       Abiodun Oluwatobi, 7897247
 * @version      11th August, 2023
 *
 * PURPOSE: Program that matches delimiters (should be one of {, }, [, ], (, or )) in a given file
 */
public class AbiodunOluwatobiA5Q1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TokenStack stack = new TokenStack();
        String fileName;
        System.out.println("Please enter the name of a code file: ");
        fileName = scanner.nextLine(); //read the name of the file

        try(BufferedReader br = new BufferedReader(new FileReader(fileName))){
            String line;     // line[] = ['c', 'l', 'a', 's', 's', 'T', 'o', 'k', 'e', 'n', 'S', 't', 'a', 'c', 'k', '{']
            int lineNumber = 1;

            while ((line = br.readLine()) != null){ //read lines of file
                for(char ch : line.toCharArray()){ // convert the file line string to a character array and loop through it
                    if(ch == '(' || ch == '[' || ch == '{'){ //if is an open delimiter
                        stack.push(new Token(ch, lineNumber)); //push the token to the stack
                    }else if(ch == ')' || ch == ']' || ch == '}'){  //if is a closing delimiter
                        if (stack.peek() == null || stack.isEmpty()) { //if stack is empty, we did not find a matching pair
                            System.out.println("The delimiter '" + ch + "' in line " + lineNumber +
                                    " does not correctly match the delimiter 'none' in line none.");
                            return;
                        }

                        //stack is not empty
                        Token topToken = stack.pop();
                        if(!areMatching(topToken.getDelimiter(), ch)){ //value at top of stack isn't matching with closing delimiter
                            System.out.println("The delimiter '" + ch + "' in line " + lineNumber +
                                    " does not correctly match the delimiter '" + topToken.getDelimiter() + "' in line " +
                                    topToken.getLineNum() + ".");
                            return;
                        }
                    }
                }
                lineNumber++; //go to next line
            }

            //if we have unprocessed tokens in the stack
            if (!stack.isEmpty()){
                Token unmatchedToken = stack.pop();
                System.out.println("End of file reached without finding a match for the delimiter '" + unmatchedToken.getDelimiter() +
                        "' in line " + unmatchedToken.getLineNum() + ".");
            }else{
                System.out.println("All delimiters in the file are correctly paired.");
            }
            System.out.println("Program terminated normally");
        }catch(IOException e){
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
    }

    /**
     * @param open char value of opening delimiter
     * @param close char value of closing delimiter
     * @return boolean value representing if an open delimiter matches the closing delimiter
     */
    private static boolean areMatching(char open, char close) {
        return (open == '(' && close == ')') || (open == '[' && close == ']') || (open == '{' && close == '}');
    }
}

/**
 * TokenStack class to define the TokenStack object and provide implementations for each of the Stack methods
 */
class TokenStack implements Stack{
    /**
     * Node class to define the Node object representing a node in a linkedList
     */
    private class Node {
        Token data;
        Node next;

        Node(Token data) { //constructor
            this.data = data;
            this.next = null;
        }
    }

    private Node top; //field representing top of the stack

    //Constructor
    public TokenStack(){
        top = null;
    }

    /**
     * @param token Token object to be pushed to the stack
     *
     */
    @Override
    public void push(Token token) {
        Node newNode = new Node(token); //create a new LinkedList node using the token
        newNode.next = top;  //assign previous top to the next pointer of the new node
        top = newNode;      // set node to top
    }

    /**
     * @return Token value representing the value at the top of the stack
     * This method does not remove the top value from the stack
     */
    @Override
    public Token peek() {
        return top != null ? top.data : null;
    }

    /**
     * @return Token value representing the value at the top of the stack
     *  This method removes the top value from the stack
     */
    @Override
    public Token pop() {
        if(top == null) return null; // return null if there is no value in the stack
        Token poppedToken = top.data; //get value at top of stack
        top = top.next;   //set top to the next value in the LinkedList
        return poppedToken; //return the top value
    }

    /**
     * @return boolean value representing if the stack is empty or not
     */
    @Override
    public boolean isEmpty() {
        return top == null;
    }
}

/**
 * Stack Interface to define the various Stack operations
 */
interface Stack {
    void push(Token t);
    Token peek();
    Token pop();
    boolean isEmpty();
}

/**
 * Token class to define the Token object
 */
class Token{
    private char delimiter;  //should be one of {, }, [, ], (, or )
    private int lineNum;     //line number in code file where the delimiter was found

    public Token(char d, int l){
        delimiter = d;
        lineNum = l;
    }

    public char getDelimiter(){
        return delimiter;
    }

    public int getLineNum(){
        return lineNum;
    }

    public String toString(){
        return "" + delimiter + " on line " + lineNum;
    }
}























