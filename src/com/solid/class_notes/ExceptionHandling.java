package com.solid.class_notes;

public class ExceptionHandling {
    public static void main(String[] args) {

        System.out.println(divide(6, 2));
        System.out.println(divide(5, 3));
        System.out.println(divide(5, 0));
    }

    private static int divide(int numerator, int denominator){
        int result = 0;
        try{
            result = numerator / denominator;
        }catch(ArithmeticException ex){
            //handle exception
            System.out.println("You tried to perform an invalid arithmetic operation, division by zero is not allowed.");
        }

        return  result;
    }
}
