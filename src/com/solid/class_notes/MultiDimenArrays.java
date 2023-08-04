package com.solid.class_notes;

import java.util.Arrays;

public class MultiDimenArrays {
    public static void main(String[] args) {
        //1D Arrays
        int[] array = new int[5];
//        System.out.println(Arrays.toString(array));
        //Array operations:
        //1. Write   => array[1] = 5;
        //2. Access  => //use for loop
        //3. Read   => int valueAtIndexOne = array[1];

        //2-D Array
        //1. Is represented as a matrix in Java
        //it has m rows and n columns
        //datatype [][] arrayName = new datatype [row][column];

        int[][] myArray = new int[3][3];
//        myArray[0] = new int[4];
//        myArray[1] = new int[3];
//        myArray[2] = new int[5];
//        int[][] newArray = {{2,3,4}, {3,4,5}};

        myArray[0][0] = 3;
        myArray[1][1] = 4;
        myArray[2][2] = 5;

        for (int[] rows : myArray) {  //row
            for (int row : rows) { //column
                System.out.print(row + " ");
            }
            System.out.println();
        }
//
//        System.out.println(Arrays.deepToString(myArray));

//        int[][][][] threeDArray = new int[2][2][3][3];

//        System.out.println(Arrays.deepToString(threeDArray));
        //Array operations:
        //1. Write   => myArray[0][0] = 5;  myArray[1][2] = 4;
        //2. Access  => //use for loop
        //3. Read   => int value = myArray[2][1];

//            column =  0  1  2  3
        //    row = 0  [5, 0, 0, 0]
        //          1  [0, 0, 4, 0]
        //          2  [0, 3, 0, 0]
    }
}
