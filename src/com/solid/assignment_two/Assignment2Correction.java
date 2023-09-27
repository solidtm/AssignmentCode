package com.solid.assignment_two;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Assignment2Correction {
    static Word[] wordsArray = new Word[26];
    public static void main(String[] args) {
        // accept the file names from user
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the name of the book file:");
        String bookFileName = scanner.nextLine();
        System.out.println("Please enter the name of the file with words to find:");
        String wordsFileName = scanner.nextLine();

        //read the book
        readFile(bookFileName);


        // wordsMap.clear();
        wordsArray = new Word[26];
        System.out.println();

        //read the words to find
        readFile(wordsFileName);
        scanner.close();

        calculateStatistics();
    }

    private static void readFile(String fileName) {
        String line;
        boolean containsWord = false;
        int currIndex = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            while ((line = br.readLine()) != null){
                line = line.replaceAll("\\p{Punct}", "");
                line = line.toLowerCase();
                String[] words = line.split(" ");

                for(String word: words){
                    //1. check the array if the word exists in the array
                    //if it exists, then increment it's number of occurrence in the array
                    //if not add it to the array with a number of occurrence of 1.

                    if(wordsArray != null){
                        for(Word value: wordsArray){
                            if(value != null){
                                if(value.getWord().equals(word)){
                                    containsWord = true;
                                    value.setNoOfOccurrence(value.getNoOfOccurrence() + 1);
                                }
                            }
                        }
                    }

                    if(!containsWord){
                        Word newWord = new Word(word, 1);

                        if(currIndex >= wordsArray.length){
                            //expand the words array
                            Word[] temp = wordsArray;
                            wordsArray = new Word[wordsArray.length * 2];
                            System.arraycopy(temp, 0, wordsArray, 0, temp.length);
                        }
                        wordsArray[currIndex++] = newWord;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("The file you are trying to access was not found...");
        } catch (IOException e) {
            System.out.println("Unexpected error occurred...");
        }
    }

    private static void calculateStatistics(){
        int sum = 0;
        int numerator = 0;
        int denominator = 0;

        for(Word word : wordsArray){
            if(word != null){
                sum += word.getWord().length();
            }
        }

        for(Word word : wordsArray){
            if(word != null){
                numerator += (word.getWord().length() * word.getNoOfOccurrence());
                denominator += word.getNoOfOccurrence();
            }
        }


        System.out.println("The number of words is: " + wordsArray.length);
        System.out.println("The number of unique words is: " + wordsArray.length);
        System.out.println("The average word length is: " + (double) (sum / wordsArray.length));
        System.out.println("The weighted average word length is: " + (double) (numerator / denominator));
    }
}

class Word {
    private String word;
    private int noOfOccurrence;

    //constructor
    public Word(String word, int noOfOccurrence) {
        this.word = word;
        this.noOfOccurrence = noOfOccurrence;
    }

    //getters
    public String getWord() {
        return word;
    }

    public int getNoOfOccurrence() {
        return noOfOccurrence;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setNoOfOccurrence(int noOfOccurrence) {
        this.noOfOccurrence = noOfOccurrence;
    }

    @Override
    public String toString() {
        return "Word: " + word + ", Occurrence: " + noOfOccurrence;
    }
}
