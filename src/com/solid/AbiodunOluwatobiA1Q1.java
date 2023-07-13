package com.solid;

import java.util.*;

/**
 *
 * COMP 1020 SECTION A1Q1
 * INSTRUCTOR    Heather C. Matheson
 * ASSIGNMENT    Assignment 1, question 1
 * @author       Abiodun Oluwatobi, 7897247
 * @version      13th July, 2023
 *
 * PURPOSE: Simulate playing a lottery, to determine the chance of winning.
 */
public class AbiodunOluwatobiA1Q1 {

    private static int winnerCount = 0;
    static Map<Integer, Long> winningNumberMap = new HashMap<>();
    static Set<Integer> playerSet = new HashSet<>();
    static Set<Integer> winningSet = new HashSet<>();

    public static void main(String[] args) {
        long noOfTrials = 50000000;
        for (int i = 1; i <= noOfTrials; i++) {
            simulate();
        }

        printTable();
        System.out.println("In " + noOfTrials + " trials, there were" + winnerCount + " winners. " +
                "The chance of winning Lotto Max is " + printWinEstimate(noOfTrials) + " percent.");
    }

    /**
     *
     * @return  nothing is returned by this function and is used to simulate
     *          the lottery choice to be played by the user
     */
    public static void simulate() {
        //Ask the user to pick a choice for simulation
        System.out.println("Please input an option to simulate: ");
        System.out.println("1. LOTTO 6/49");
        System.out.println("2. LOTTO MAX");

        //Accept the imputed choice from the user
        Scanner scanner = new Scanner(System.in);
        int playerChoice = scanner.nextInt();

        if (playerChoice == 1) {
            System.out.println("Simulating LOTTO 6/49...");
            handleCase(6, 49);
        } else if (playerChoice == 2) {
            System.out.println("Simulating LOTTO MAX...");
            handleCase(7, 50);
        } else {
            System.out.println("You have imputed a wrong option, please input correct choice to continue");
        }
    }

    /**
     * @param setSize integer value representing the lottery choice set size (LOTTO 6/49 or LOTTO MAX)
     * @param maxChoice integer value representing the maximum inclusivity for random values to be picked.
     * @return  nothing is returned by this function and is used to handle picking set of random numbers
     *          based on the lottery choice picked by the user.
     */
    public static void handleCase(int setSize, int maxChoice) {
        //choose randomly setSize player numbers in playerSet from 1-maxChoice
        Random random = new Random();
        while (playerSet.size() <= setSize) {
            int randomNumber = random.nextInt(maxChoice) + 1;
            playerSet.add(randomNumber);
        }

        //choose randomly setSize winning numbers in winningSet from 1-maxChoice
        while (winningSet.size() <= setSize) {
            int randomNumber = random.nextInt(maxChoice) + 1;
            winningSet.add(randomNumber);
        }

        //compare the two sets, if same, then player won, increment winnerCount
        for (int num : winningSet) {
            //add winning numbers to our winningNumberMap
            winningNumberMap.put(num, winningNumberMap.getOrDefault(num, 0L) + 1);

            if (playerSet.contains(num)) {
                playerSet.remove(num);
            }
        }

        if (playerSet.isEmpty()) { //player has won
            // increment each winning number's count in the map
            winnerCount++;
        }

        playerSet.clear();
        winningSet.clear();
    }

    /**
     * @return  nothing is returned by this function and is used to print the number of
     *          occurrences of a number in the winning set.
     */
    public static void printTable() {
        System.out.println("Number:\tTimes Seen:\n");
        for (Map.Entry<Integer, Long> entry : winningNumberMap.entrySet()) {
            System.out.println("\t\t" + entry.getKey() + "\t" + entry.getValue() + "\n");
        }
    }

    /**
     * @param numberOfTrials long variable which represents the number of times
     *                       the user tries to win a lottery
     * @return  double value is returned which is the result of the winnerCount divided by the numberOfTrials
     */
    public static double printWinEstimate(long numberOfTrials) {
        return (double) winnerCount / numberOfTrials;
    }
}




















