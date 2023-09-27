package com.solid.assignment_one;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class AssignmentOneCorrection {
    static ArrayList<Integer> playerList = new ArrayList<>();
    static ArrayList<Integer> winningList = new ArrayList<>();
    static int winnerCount = 0;
    static int[] winningNumberArray = new int[51];
    public static void main(String[] args) {
        long noOfTrials = 5000;
        //Ask the user to pick a choice for simulation
        System.out.println("Please input an option to simulate: ");
        System.out.println("1. LOTTO 6/49");
        System.out.println("2. LOTTO MAX");

        //Accept the imputed choice from the user
        Scanner scanner = new Scanner(System.in);
        int playerChoice = scanner.nextInt();
        if (playerChoice == 1) {
            System.out.println("Simulating LOTTO 6/49...");
        } else if (playerChoice == 2) {
            System.out.println("Simulating LOTTO MAX...");
        } else {
            System.out.println("You have imputed a wrong option, please input correct choice to continue...");
        }

        for (int i = 1; i <= noOfTrials; i++) {
            simulate(playerChoice);
        }

        printTable(playerChoice);
        System.out.println("In " + noOfTrials + " trials, there were" + winnerCount + " winners. " +
                "The chance of winning Lotto Max is " + printWinEstimate(noOfTrials) + " percent.");

        System.out.println("Program terminated normally.");
    }

    public static void simulate(int playerChoice) {
        if (playerChoice == 1) {
            handleCase(6, 49);
        } else if (playerChoice == 2) {
            handleCase(7, 50);
        }
    }

    public static void handleCase(int noOfChoices, int maxChoice) {
        //choose randomly noOfChoices player numbers in playerSet from 1-maxChoice
        Random random = new Random();

        while (playerList.size() <= noOfChoices) {
            int randomNumber = random.nextInt(maxChoice) + 1;
            if(!playerList.contains(randomNumber)){
                playerList.add(randomNumber);
            }
        }

        //choose randomly noOfChoices winning numbers in winningSet from 1-maxChoice
        while (winningList.size() <= noOfChoices) {
            int randomNumber = random.nextInt(maxChoice) + 1;
            if(!winningList.contains(randomNumber)){
                winningList.add(randomNumber);
            }
        }

        //compare the two sets, if same, then player won, increment winnerCount
        for (int num : winningList) {   //==
            //add winning numbers to our winningNumberMap
            winningNumberArray[num] += 1;

            if (playerList.contains(num)) {
                playerList.remove((Integer) num);
            }
        }

        if (playerList.isEmpty()) { //player has won
            // increment each winning number's count in the map
            winnerCount++;
        }

        playerList.clear();
        winningList.clear();
    }

    public static void printTable(int playerChoice) {
        System.out.println("Number:\tTimes Seen:\n");
        int length;

        if(playerChoice == 1){
            length = winningNumberArray.length - 1;
        }else{
            length = winningNumberArray.length;
        }

        for (int index = 1; index < length; index++) {
            System.out.println("\t\t" + index + "\t" + winningNumberArray[index] + "\n");
        }
    }

    public static double printWinEstimate(long numberOfTrials) {
        return (double) winnerCount / numberOfTrials;
    }
}
