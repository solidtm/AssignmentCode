package com.solid.assignment_four;

import java.util.Scanner;

/**
 *
 * COMP 1020 SECTION A4Q2
 * INSTRUCTOR    Heather C. Matheson
 * ASSIGNMENT    Assignment 4, Question 2
 * @author       Abiodun Oluwatobi, 7897247
 * @version      3rd August, 2023
 *
 * PURPOSE: Validate a partially-filled or completely-filled Sudoku board.
 */
public class AbiodunOluwatobiA4Q2 {
    public static void main(String[] args) {
        System.out.format("Casting %f to int gives %d", 23.8, (int)23.8);

//        Scanner scanner = new Scanner(System.in);
//
//        System.out.println("Please enter a 9x9 Sudoku board:");
//        String input = scanner.nextLine();
//
//        Sudoku sudoku = new Sudoku(input);
//
//        System.out.println("The board is:");
//        sudoku.printBoard();
//
//        if (sudoku.validateBoard()) {
//            System.out.println("The board is VALID.");
//        } else {
//            System.out.println("The board is INVALID.");
//        }
//
//        System.out.println("Program terminated normally.");
    }

    /**
     * Sudoku class to define the Sudoku object
     */
    public static class Sudoku{
        private final int[][] board; //2D integer array board to store the Sudoku board.

        /**
         * @param input string input to initialize the board.
         *
         */
        public Sudoku(String input){
            board = new int[9][9];
            String[] tokens = input.split(" ");

            if (tokens.length != 81) {
                System.out.println("Invalid input: Board must contain 81 tokens.");
                return;
            }

            int index = 0;
            for(int row = 0; row < 9; row++){
                for(int col = 0; col < 9; col++){
                    if (tokens[index].equals("-")) {
                        board[row][col] = -1;
                    } else {
                        board[row][col] = Integer.parseInt(tokens[index]);
                    }
                    index++;
                }
            }
        }

        /**
         * @return boolean value representing if the board is valid or not
         * based on the rules
         */
        public boolean validateBoard(){
            // Check rows, columns, and 3x3 blocks
            // Return true if the board is valid, otherwise return false

            //check rows
            for (int row = 0; row < 9; row++) {
                if (!isValidGroup(row, 0, row, 8)) {
                    return false;
                }
            }

            //check columns
            for (int col = 0; col < 9; col++) {
                if (!isValidGroup(0, col, 8, col)) {
                    return false;
                }
            }

            //check 3x3 blocks
            for (int row = 0; row < 9; row += 3) {
                for (int col = 0; col < 9; col += 3) {
                    if (!isValidGroup(row, col, row + 2, col + 2)) {
                        return false;
                    }
                }
            }

            return true;
        }

        /**
         * @param startRow The starting row index of the group.
         * @param startCol The starting column index of the group.
         * @param endRow The ending row index of the group.
         * @param endCol The ending column index of the group.
         *
         * @return boolean value indicating that the individual rows, columns and 3x3 blocks is valid.
         */
        private boolean isValidGroup(int startRow, int startCol, int endRow, int endCol) {
            boolean[] seen = new boolean[10]; //keep track of numbers that have been seen within that group

            for (int row = startRow; row <= endRow; row++) {
                for (int col = startCol; col <= endCol; col++) {
                    int value = board[row][col];

                    if (value != -1) {
                        if (seen[value]) {
                            return false; // Duplicate value found
                        }
                        seen[value] = true;
                    }
                }
            }

            return true;
        }

        //Method to print the board.
        public void printBoard() {
            for (int row = 0; row < 9; row++) {
                for (int col = 0; col < 9; col++) {
                    int value = board[row][col];
                    System.out.print(value == -1 ? "-" : value);
                    if (col < 8) {
                        System.out.print(" ");
                    }
                }
                System.out.println();
            }
        }
    }
}
