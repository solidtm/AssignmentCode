package com.solid.class_notes;

public class ErrorHandling {
    public static void main(String[] args) {
        /*  Exceptions in java are seen as objects of Throwable
        *
        *  1. Checked Exceptions
        *  2. Unchecked Exceptions
        *
        * Exception is handled in 2 ways:
        *  1. Using the try-catch block
        *
        * How u add exceptions to your code
        *  1. Using throws keyword.
        *
        * */

//        int num = divideNum(40);
//        System.out.println(num);
        try{
            validateNum(-2);
        }catch (CustomException ex){
            System.out.println("Custom exception caught: " + ex.getMessage());
        }
    }

    public static int divideNum(int num) throws ArithmeticException{
        return num / 10;
    }

    public static void validateNum (int number) throws CustomException{
        if(number < 0){
            throw new CustomException("Number cannot be negative");
        }
    }
}

class CustomException extends Exception{
    public CustomException(String message){
        super(message);
    }
}
