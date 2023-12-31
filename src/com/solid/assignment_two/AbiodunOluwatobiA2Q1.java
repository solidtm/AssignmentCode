package com.solid.assignment_two;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class AbiodunOluwatobiA2Q1{
    static Word[] wordArray;
    static String line = "";
    static ArrayList<String>[] wordFreqStore;

    public static void main(String[] args){
        // accept the file names from user
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the name of the book file:");
        String bookFileName = scanner.nextLine();
        System.out.println("Please enter the name of the file with words to find:");
        String wordsFileName = scanner.nextLine();

        //read the book
        readFile(bookFileName, true);

        // wordsMap.clear(); 
        wordFreqStore = null;
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

                    if(wordFreqStore == null){
                        ArrayList<String> list = new ArrayList<String>();
                        list.add(value);
                        wordFreqStore = new ArrayList[2];
                        wordFreqStore[1] = list; 
                    }else{
                        for(int index = 1; index < wordFreqStore.length; index++){
                            ArrayList<String> list = wordFreqStore[index];
                            ArrayList<String> prevList = new ArrayList<>();

                            if(list.contains(value)){
                                list.remove(value);
                                prevList = list;

                                if(index + 1 < wordFreqStore.length){
                                    wordFreqStore[index + 1].add(value);
                                    break;
                                }else{
                                    wordFreqStore = new ArrayList[wordFreqStore.length + 1];
                                    wordFreqStore[index] = prevList;

                                    ArrayList<String> newList = new ArrayList<>();
                                    newList.add(value);
                                    wordFreqStore[index + 1] = newList;
                                    break;
                                }
                            }else{
                                wordFreqStore[1].add(value);
                                break;
                            }
                        }
                    }
                }
            }

            wordArray = new Word[wordFreqStore.length];
            int index = 0;
        
            for(int i = 1; i < wordFreqStore.length; i++) {   
                ArrayList<String> list = wordFreqStore[i];

                for(String value : list){
                    Word word = new Word(value, i);  

                    if(index < wordArray.length){
                        wordArray[index++] = word;
                    }else{
                        Word[] prevArray = wordArray;
                        wordArray = new Word[wordArray.length + 1];
                        int j;

                        for(j = 0; j < prevArray.length; j++){
                            wordArray[j] = prevArray[j];
                        }
                        wordArray[index++] = word;
                    }
                }
            }

            if(flag) calculateStatistics(wordArray);
            else {
                for(int i = 1; i < wordFreqStore.length; i++){
                    ArrayList<String> list = wordFreqStore[i];

                    for(String value : list){
                        System.out.println("Word: " + value + " Count: " + i);
                    }
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
            if(word != null){
                if(word.getNoOfOccurrence() == 1){noOfUniqueWords++;}
            }
           
        }

        for(Word word : wordArray){
            if(word != null){
                if(word.getNoOfOccurrence() == 1){
                    sum += word.getWord().length();
                }
            }
        }

        for(Word word : wordArray){
            if(word != null){
                numerator += (word.getWord().length() * word.getNoOfOccurrence());
                denominator += word.getNoOfOccurrence();
            }
        }


        System.out.println("The number of words is: " + wordArray.length);
        System.out.println("The number of unique words is: " + noOfUniqueWords);
        System.out.println("The average word length is: " + (double) (sum / noOfUniqueWords));
        System.out.println("The weighted average word length is: " + (double) (numerator / denominator));
    }

   static class Word{
        private String word;
        private int noOfOccurrence;

        //constructor
        public Word(String word, int noOfOccurrence){
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

        @Override
        public String toString(){
            return "Word: " + word + ", Occurence: " + noOfOccurrence;
        }
    }
}