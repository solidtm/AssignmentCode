package com.solid;

import java.util.*;

/*
 * Lottery Odds:
 * Players chooses a set of numbers
 * These numbers are compared against a random;y chosen set of winning numbers
 *
 * WHAT AM I DOING? - Simulate playing a lottery, to determine the chane of winning
 * HOW AM I DOING IT? -
 *
 *
 *
 *
 *     Player 1 -> Lotto 6/49
 *     Randomly chosen player numbers -> [2, 6, 7, 14, 18, 29] -> chosen from 1 - 49
 *     Randomly chosen winning numbers -> [14, 16, 38, 2, 29, 7] -> chosen from 1 - 49
 *     k   v  k   v
 *    [1 : 0, 2 : 0, 3 : 7, .... 49 -> 0] -> MAP
 *    [] -> SET
 *
 *    winnerCountVariable = 1
 *
 * */
public class AbiodunOluwatobiA1Q1 {

    private static int winnerCount = 0;
    static Map<Integer, Long> winningNumberMap = new HashMap<>();
    static Set<Integer> playerSet = new HashSet<>();
    static Set<Integer> winningSet = new HashSet<>();

    public static void main(String[] args) {
        long noOfTrials = 10;
        for (int i = 1; i <= noOfTrials; i++) {
            simulate();
        }

        printTable();
        System.out.println("In " + noOfTrials + " trials, there were" + winnerCount + " winners. " +
                "The chance of winning Lotto Max is " + printWinEstimate(noOfTrials) + " percent.");
    }

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

    public static void printTable() {
        System.out.println("Number:\tTimes Seen:\n");
        for (Map.Entry<Integer, Long> entry : winningNumberMap.entrySet()) {
            System.out.println(entry.getKey() + "\t" + entry.getValue() + "\n");
        }
    }

    public static double printWinEstimate(long numberOfTrials) {
        return (double) winnerCount / numberOfTrials;
    }
}




















