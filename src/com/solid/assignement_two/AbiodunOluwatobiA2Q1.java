package com.solid.assignement_two;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class AbiodunOluwatobiA2Q1{
    static Word[] wordArray;
    static String line = "";
    static Map<String, Integer> wordsMap = new HashMap<>();

    public static void main(String[] args){
        // accept the file names from user
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the name of the book file:");
        String bookFileName = scanner.nextLine();
        System.out.println("Please enter the name of the file with words to find:");
        String wordsFileName = scanner.nextLine();

        //read the book
        readFile(bookFileName, true);

        wordsMap.clear();
        System.out.println();

        //read the words to find
        readFile(wordsFileName, false);
        scanner.close();
    }

    private static void readFile(String fileName, boolean flag) {
        // File directory = new File("../");
  		String filePath = "//Users//richardmmakuaebo//AssignmentCode//src//com//solid//assignment_two//" + fileName;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            while ((line = br.readLine()) != null){
                line = line.replaceAll("\\p{Punct}", "");   //remove punctuations
                line = line.trim();                                           //remove whitespaces
                line = line.toLowerCase();                                    // convert to lowercase
                String[] words = line.split(" ");                       // split into an array

                for (String value : words){                                   // loop through the array and pass each word into map
                    //put the value in the wordsMap and track it's number occurence
                    wordsMap.put(value, wordsMap.getOrDefault(value, 0) + 1); 
                }
            }

            wordArray = new Word[wordsMap.size()];
            int index = 0;

            //Loop through the map and print it's key - value pairs.
            for(Map.Entry<String, Integer> entry : wordsMap.entrySet()) {   
                Word word = new Word(entry.getKey(), entry.getValue());     
                wordArray[index] = word;
                index++;
            }

            if(flag) calculateStatistics(wordArray);
            else {
                for(Map.Entry<String, Integer> entry : wordsMap.entrySet()){
                    System.out.println("Word: " + entry.getKey() + " Count: " + entry.getValue());
                }
                System.out.println("Program terminated successfully");
            }

		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    private static void calculateStatistics(Word[] wordArray){
        int noOfUniqueWords = 0;
        int sum = 0;
        int numerator = 0;
        int denominator = 0;

        for(Word word : wordArray){
            if(word.getNoOfOccurence() == 1){noOfUniqueWords++;}
        }

        for(Word word : wordArray){
            if(word.getNoOfOccurence() == 1){
                sum += word.getWord().length();
            }
        }

        for(Word word : wordArray){
            numerator += (word.getWord().length() * word.getNoOfOccurence());
            denominator += word.getNoOfOccurence();
        }


        System.out.println("The number of words is: " + wordArray.length);
        System.out.println("The number of unique words is: " + noOfUniqueWords);
        System.out.println("The average word length is: " + (double) (sum / noOfUniqueWords));
        System.out.println("The weighted average word length is: " + (double) (numerator / denominator));
    }

   static class Word{
        private String word;
        private int noOfOccurence;

        //constructor
        public Word(String word, int noOfOccurence){
            this.word = word;
            this.noOfOccurence = noOfOccurence;
        }

        //getters 
        public String getWord() {
            return word;
        }

        public int getNoOfOccurence() {
            return noOfOccurence;
        }

        @Override
        public String toString(){
            return "Word: " + word + ", Occurence: " + noOfOccurence;
        }
    }
}