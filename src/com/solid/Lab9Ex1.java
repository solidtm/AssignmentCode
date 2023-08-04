package com.solid;

import java.util.Scanner;
import java.io.FileReader;
import java.io.FileNotFoundException;

public class Lab9Ex1 {
  public static final String INPUT_FILE = "magicsquares.txt";
  
  public static void main(String args[]) {
    Scanner in;
    Square sq1, sq2, sq3, sq4, sq5, sqIn;

    // Create some possible magic squares
    sq1 = new Square(3);
    sq1.fill(new int[] { 8, 1, 6, 3, 5, 7, 4, 9, 2 });
    System.out.println("Square 1:\n" + sq1);

    sq2 = new Square(3);
    sq2.fill(new int[] { 1, 1, 1, 5, 5, 5, 0, 9, 9 });
    System.out.println("Square 2:\n" + sq2);

    sq3 = new Square(4);
    sq3.fill(new int[] { 10, 3, 13, 8, 5, 16, 2, 11, 4, 9, 7, 14, 15, 6, 12, 1 });
    System.out.println("Square 3:\n" + sq3);

    sq4 = new Square(5);
    sq4.fill(new int[] { 21, 3, 4, 12, 25, 15, 17, 6, 19, 8, 10, 24, 13, 2, 16, 18, 7, 20, 9, 11, 1, 14, 22, 5, 23 });
    System.out.println("Square 4:\n" + sq4);

    sq5 = new Square(3);
    sq5.fill(new int[] { 1, 2, 3, 3, 2, 1, 1, 1, 11 });
    System.out.println("Square 5:\n" + sq5);

    // Test if Exercise 1 code works
//     System.out.println("Testing checkRows():");
     verify(3, sq1.checkRows(), "sq1.checkRows()");
     verify(1, sq2.checkRows(), "sq2.checkRows()");
     verify(4, sq3.checkRows(), "sq3.checkRows()");
     verify(5, sq4.checkRows(), "sq4.checkRows()");
     verify(0, sq5.checkRows(), "sq5.checkRows()");

    // Test if Exercise 2 code works
     System.out.println("\nTesting checkColumns():");
     verify(3, sq1.checkColumns(), "sq1.checkColumns()");
     verify(2, sq2.checkColumns(), "sq2.checkColumns()");
     verify(4, sq3.checkColumns(), "sq3.checkColumns()");
     verify(3, sq4.checkColumns(), "sq4.checkColumns()");
     verify(1, sq5.checkColumns(), "sq5.checkColumns()");

    // Test if Exercise 3 code works
     System.out.println("\nTesting checkDiagonals():");
     verify(2, sq1.checkDiagonals(), "sq1.checkDiagonals()");
     verify(1, sq2.checkDiagonals(), "sq2.checkDiagonals()");
     verify(2, sq3.checkDiagonals(), "sq3.checkDiagonals()");
     verify(1, sq4.checkDiagonals(), "sq4.checkDiagonals()");
     verify(0, sq5.checkDiagonals(), "sq5.checkDiagonals()");

    // Test if Exercise 4 code works
     System.out.println("\nTesting checkValues():");
     verify(0, sq1.checkValues(), "sq1.checkValues()");
     verify(5, sq2.checkValues(), "sq2.checkValues()");
     verify(0, sq3.checkValues(), "sq3.checkValues()");
     verify(0, sq4.checkValues(), "sq4.checkValues()");
     verify(6, sq5.checkValues(), "sq5.checkValues()");
    
    // Run Exercise 5 code
//    /*
    System.out.println("\nReading squares from " + INPUT_FILE + ":");
    try {
      sqIn = new Square(0); // will be overwritten anyway
      in = new Scanner(new FileReader(INPUT_FILE));
      while (sqIn.read(in)) {
        System.out.println("\nInput square:");
        System.out.println(sqIn);
        if (sqIn.isMagic()) {
          System.out.println("IS a magic square.");
        } else {
          System.out.println("is NOT a magic square.");
        }
      }
    } catch (FileNotFoundException fnf) {
      System.out.println("File not found: " + INPUT_FILE);
    }
//    */

    System.out.println("\nEnd of processing.");
  }
  
  public static void verify(int expected, int result, String action) {
    if (expected == result) {
      System.out.printf("Test succeeded: %s\n", action);
    } else {
      System.out.printf("Test FAILED (expected %d, got %d): %s\n", expected, result, action);
    }
  }
}

class Square {
  private int[][] array;
  
  public Square(int dim) {
    this.array = new int[dim][dim];
  }
  
  // This method is used as a quick way to get data into the square for testing
  public void fill(int[] data) {
    int pos = 0;
    for (int r = 0; r < array.length; r++) {
      for (int c = 0; c < array[r].length; c++) {
        if (pos < data.length) {
          array[r][c] = data[pos];
          pos++;
        }
      }
    }
  }
  
  public boolean isMagic() {
    boolean rows = checkRows() == array.length;
    boolean columns = checkColumns() == array[0].length;
    boolean diagonals = checkDiagonals() == 2;
    boolean values = checkValues() == 0;

    return rows && columns && diagonals && values;
  }
  
  public int magicConstant() {
    int squareSize = array.length;
    return (squareSize * (squareSize * squareSize + 1))/2;
  }
  
  public int checkRows() {
    // Exercise 1:
    //  count the number of rows that add up to the magic constant
    int result = 0;
    int magicConstant = magicConstant();
    int sum = 0;

    for(int[] row : array) {
      for (int index = 0; index < row.length; index++) {
        sum += row[index];
        if (sum == magicConstant) {
          result++;
        }
      }
      sum = 0;
    }
    
    return result;
  }

  public int checkColumns() {
    // Exercise 2:
    //  count the number of columns that add up to the magic constant
    int result = 0;
    int magicConstant = magicConstant();
    int sum = 0;
    int columnLength = array[0].length;
    int rowLength = array.length;

    for(int i = 0; i < columnLength; i++){
      for(int j = 0; j < rowLength; j++){
        sum += array[j][i];
      }
      if (sum == magicConstant) result++;
      sum = 0;
    }
    
    return result;
  }

  public int checkDiagonals() {
    // Exercise 3:
    //  count the number of diagonals that add up to the magic constant
    int result = 0;
    int magicConstant = magicConstant();
    int diagonalSum = 0;
    int antiDiagonalSum = 0;
    int arrayLength = array.length;

    for (int i = 0; i < arrayLength; i++){
      diagonalSum += array[i][i];
      antiDiagonalSum += array[i][arrayLength - i - 1];
    }

    if(diagonalSum == magicConstant) result++;
    if(antiDiagonalSum == magicConstant) result++;

    return result;
  }
  
  public int checkValues() {
    // Exercise 4:
    //   count the number of duplicate values or values out of range
    int wrong = 0;
    int squareSize = array.length;
    int sizeSquared = array.length * array.length;
    int[] counts = new int[sizeSquared];

    for (int row  = 0; row < squareSize; row++){
      for(int col = 0; col < array[0].length; col++){
        int value = array[row][col];
        if(value > sizeSquared || value < 1){
          wrong++;
        }else if(value < sizeSquared){
          if(counts[value] < 1){
            counts[value] += 1;
          }else{
            wrong++;
          }
        }
      }
    }

    return wrong;
  }

  public boolean read(Scanner in) {
    boolean read = false;
    String[] tokens;
    String line = "";
    int squareSize;

    if (in.hasNextLine()) {
      try {
        line = in.nextLine();
        squareSize = Integer.parseInt(line);
        // System.out.println("\nSize: " + squareSize);

        // Exercise 5: add code here...
        int[][] square = new int[squareSize][squareSize];
        int readIndex = 0;
        while(readIndex < squareSize){
          line = in.nextLine();
          String[] lineArray = line.split(" ");
          int[] currentRow = square[readIndex];

          for(int col = 0; col < currentRow.length; col++){
            currentRow[col] = Integer.parseInt(lineArray[col]);
          }

          readIndex++;
        }
        read = true;
      } catch (NumberFormatException nfe) {
        System.out.println("Invalid data in input line: " + line);
      }
    }

    return read;
  }
  
  
  public String toString() {
    String result = "";
    for (int r = 0; r < array.length; r++) {
      for (int c = 0; c < array[r].length; c++) {
        result += String.format("%4d", array[r][c]);
      }
      result += "\n";
    }
    return result;
  }
}