package com.solid.class_notes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
public class ArrayListDemo{
    public static void main(String[] args) {
//        System.out.println(Arrays.toString(sumRows(new int[][]{{1,2,3,4}, {5,6,7,8}, {9,10,11,12}})));
//        System.out.println(GupaNumber(5));
//        extractInfo("/Users/richardmmakuaebo/IdeaProjects/AssignmentCode/src/com/solid/class_notes/demo.txt");
        zeroGreater(new int[]{2,6,2,2,1,5,3,9,4});
    }

    public static int[] sumRows (int[][] matrix) {  //[[1,2,3,4], [5,6,7,8], [9,10,11,12]] -> [10, 26, 42]
        int[] result = new int[matrix.length];

        for(int i = 0; i < matrix.length; i++){
            int sum = 0;
            for(int value : matrix[i]){
                sum += value;
            }
            result[i] = sum;
        }

        return result;
    }

    //Gupa numbers = G(n) = G(n - 1) + G(n + 1) - 4
    //if n = 1, G(n) = 1
    //if n = 0, G(n) = 0

    //Base case and the recursive case

    public static int GupaNumber (int n) {
        //base case
        if(n == 0 || n == 1){
            return n;
        }

        System.out.println((n - 1) + (n + 1) - 4);

        //recursive case
        return GupaNumber(n - 1);
    }


    // [5 6 7 8 10 cat bat mat more words]
    //1 find sum = Ist (5) + 5th(10) => 15
    //2. get first 3 words: cat bat mat
    //3. print => 15 cat bat mat

    public static void extractInfo (String fileName) {
       try{
           BufferedReader br = new BufferedReader(new FileReader(fileName));
           String line = "";
           int sum = 0;
           String firstThreeWords = "";

           while((line = br.readLine()) != null){
               String[] lineValues = line.split(" ");
               //get sum of the first and 5th numbers
               sum = Integer.parseInt(lineValues[0]) + Integer.parseInt(lineValues[4]);
               firstThreeWords = lineValues[5] + " " + lineValues[6] + " " + lineValues[7];
           }

           System.out.println(sum + " " + firstThreeWords);
       }catch (IOException e){
           System.out.println("An error occurred while reading the file");
       }
    }

    public static void zeroGreater (int[] a){
        int[] result = zeroGreaterHelper(a, 0);
        System.out.println(Arrays.toString(result));
    }

    private static int[] zeroGreaterHelper(int[] arr, int index) {
        //base case
        if(index == arr.length) return arr;

        if(arr[index] > index) arr[index] = 0;

        return zeroGreaterHelper(arr, index + 1);
    }
}

class Art{
    String artist;
    double value;

    public Art(String artist, double value) {
        this.artist = artist;
        this.value = value;
    }

    public void increaseValue(double percentage){       // 5/100 * value?
        double actualValueAdded = percentage * value;
        this.value += actualValueAdded;
    }
}
class Sculpture extends Art {

    public Sculpture(String artist, double value) {
        super(artist, value);
    }


    public void increaseValue() {
        super.increaseValue(0.05);
    }
}
class Painting extends Art{
    int width;
    int height;

    public Painting(String artist, double value, int width, int height) {
        super(artist, value);
        this.width = width;
        this.height = height;
    }


    public void increaseValue() {
        super.increaseValue(0.1);
    }
}
