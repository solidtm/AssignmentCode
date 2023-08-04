package com.solid.assignment_four;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * COMP 1020 SECTION A4Q1
 * INSTRUCTOR    Heather C. Matheson
 * ASSIGNMENT    Assignment 4, Question 1
 * @author       Abiodun Oluwatobi, 7897247
 * @version      3rd August, 2023
 *
 * PURPOSE: Program that executes the game of life
 */
public class AbiodunOluwatobiA4Q1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Please enter the name of a file containing a grid: ");
        String fileName = scanner.nextLine();

        try{
            Grid grid = new Grid(fileName);
            System.out.println("Generation 0 is:");
            System.out.print(grid);

            System.out.print("How many generations would you like to simulate? ");
            int numGenerations = scanner.nextInt();

            for (int i = 1; i <= numGenerations; i++) {
                grid = grid.nextGeneration();
                System.out.println("Generation " + i + " is:");
                System.out.print(grid);
            }

            System.out.println("Program terminated normally.");
        }catch(IOException e){
            System.out.println("Error reading the file.");
        }
    }

    /**
     * Cell class to define the Cell object
     */
    public static class Cell{
        private boolean alive; //boolean variable to indicate whether the cell is alive or not.

        /**
         * Constructor
         */
        public Cell(){
            this.alive = false;
        }

        /**
         * @return boolean value representing status of the cell
         */
        public boolean isAlive(){
            return alive;
        }

        /**
         * Method to update the status of the cell
         */
        public void setAlive(boolean alive){
            this.alive = alive;
        }

        @Override
        public String toString() {
            return alive ? "*" : ".";   //"*" means cell is alive and "." means it is not
        }
    }

    /**
     * Grid class to define the Grid object
     */
    public static class Grid{
        private Cell[][] cells;
        private int numRows;
        private int numCols;

        /**
         * Constructor to construct an empty Grid of given dimensions constructs a grid from a file.
         */
        public Grid(int rmax, int cmax){
            this.numRows = rmax;
            this.numCols = cmax;
            this.cells = new Cell[numRows][numCols];

            for(int r = 0; r < numRows; r++){
                for(int c = 0; c < numCols; c++){
                    cells[r][c] = new Cell();
                }
            }
        }

        /**
         * Constructor to construct a grid from a file.
         */
        public Grid(String fileName) throws IOException {
            //read dimensions from file
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String[] dimensions = reader.readLine().split(" ");
            numRows = Integer.parseInt(dimensions[0]);
            numCols = Integer.parseInt(dimensions[1]);
            cells = new Cell[numRows][numCols];

            for(int r = 0; r < numRows; r++){
                for(int c = 0; c < numCols; c++){
                    cells[r][c] = new Cell();
                }
            }

            String line;
            while((line = reader.readLine()) != null){
                String[] coordinates = line.split(" ");
                int r = Integer.parseInt(coordinates[0]);
                int c = Integer.parseInt(coordinates[1]);
                cells[r][c].setAlive(true);
            }
            reader.close();
        }

        /**
         * @param r row index of cell
         * @param c column index of cell
         * @return boolean value representing if a cell is alive
         */
        public boolean isAlive(int r, int c){
            if(r < 0 || r >= numRows || c < 0 || c >= numCols){
                return false;
            }

            return cells[r][c].isAlive();
        }

        /**
         * @param r row index of cell
         * @param c column index of cell
         * Method to set a cell alive
         */
        public void set(int r, int c){
            if(r >= 0 || r < numRows || c >= 0 || c < numCols){
                cells[r][c].setAlive(true);
            }
        }

        /**
         * @param r row index of cell
         * @param c column index of cell
         * Method to clear a cell/reset a cell
         */
        public void clear(int r, int c){
            if(r >= 0 || r < numRows || c >= 0 || c < numCols){
                cells[r][c].setAlive(false);
            }
        }

        /**
         * @param r row index of cell
         * @param c column index of cell
         * @return int value representing the number of alive neighbours of a cell
         * Method to count the alive neighbors of a cell.
         */
        public int numNeighbours(int r, int c){
            int count = 0;
            for(int dr = -1; dr <= 1; dr++){
                for(int dc = -1; dc <= 1; dc++){
                    if(dr == 0 && dc == 0){
                        continue;
                    }
                    if(isAlive(r + dr, c + dc)){
                        count++;
                    }
                }
            }
            return count;
        }

        /**
         * @return Grid value representing the grid with the new generation
         * Method to calculate the next generation based on the rules.
         */
        public Grid nextGeneration(){
            Grid nextGen = new Grid(numRows, numCols);
            for (int r = 0; r < numRows; r++) {
                for (int c = 0; c < numCols; c++) {
                    int neighbors = numNeighbours(r, c); //calculate number of alive neighbours

                    if(isAlive(r, c)){
                        if (neighbors == 2 || neighbors == 3) {
                            nextGen.set(r, c);
                        }
                    }else{
                        if (neighbors == 3) {
                            nextGen.set(r, c);
                        }
                    }
                }
            }
            return nextGen;
        }

        /**
         * Method to print the grid.
         */
        @Override
        public String toString(){
            StringBuilder builder = new StringBuilder();
            for (int r = 0; r < numRows; r++) {
                for (int c = 0; c < numCols; c++) {
                    builder.append(cells[r][c]);
                    if (c < numCols - 1) {
                        builder.append(" ");
                    }
                }
                builder.append("\n");
            }
            return builder.toString();
        }
    }
}
















