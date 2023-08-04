package com.solid.assignment_three;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * COMP 1020 SECTION A1Q1
 * INSTRUCTOR    Heather C. Matheson
 * ASSIGNMENT    Assignment 3, Question 2
 * @author       Abiodun Oluwatobi, 7897247
 * @version      27th July, 2023
 *
 * PURPOSE: Writing a pattern matching function.
 */
public class AbiodunOluwatobiA3Q2 {
    public static boolean matchPattern(String pattern, String line) {
        return matchPatternHelper(pattern, 0, line, 0);
    }

    private static boolean matchPatternHelper(String pattern, int index, String line, int lineIndex) {
        // Base case 1: If both pattern and line are fully processed, it's a match
        if (index == pattern.length() && lineIndex == line.length()) {
            return true;
        }

        // Base case 2: If only the pattern is fully processed, but not the line, it's not a match
        if (index == pattern.length()) {
            return false;
        }

        // Check if the next character in the pattern is an asterisk
        if (index + 1 < pattern.length() && pattern.charAt(index + 1) == '*') {
            // Match zero or more characters with '*'
            if(matchPatternHelper(pattern, index + 2, line, lineIndex)){
                return true;
            }

            while(lineIndex < line.length() && (pattern.charAt(index) == line.charAt(lineIndex) || pattern.charAt(index) == '.')){
                if(matchPatternHelper(pattern, index + 2, line, lineIndex)){
                    return true;
                }
                lineIndex++;
            }

            return false;
        } else if (pattern.charAt(index) == '[') {
            // Find the closing bracket for the character set
            int closingBracketIndex = pattern.indexOf(']', index);
            if(closingBracketIndex == -1) return false;
            // Extract the characters between brackets
            String stringBetweenBraces = pattern.substring(index + 1, closingBracketIndex);
            // Check if the line's character at the current index matches any character in the character set
            if (stringBetweenBraces.indexOf(line.charAt(lineIndex)) >= 0) {
                return matchPatternHelper(pattern, closingBracketIndex + 1, line, lineIndex + 1);
            } else {
                return false;
            }
        } else {
            // Check if the current character in the pattern matches the corresponding character in the line
            if (lineIndex < line.length() && pattern.charAt(index) == line.charAt(lineIndex)) {
                return matchPatternHelper(pattern, index + 1, line, lineIndex + 1);
            } else {
                return false;
            }
        }
    }

    public static void main(String[] args) {
        //Accept fileName from the user.
        String fileName;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the name of the data file: ");
        fileName = scanner.nextLine();

        File directory = new File("");
        String filePath = directory.getAbsolutePath() + fileName;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String pattern = reader.readLine();  //reading the first line
            String line;                        //other strings to match the pattern
            while ((line = reader.readLine()) != null) {
                boolean isMatch = matchPattern(pattern, line);
                System.out.println("The pattern '" + pattern + "' " + (isMatch ? "MATCHES" : "DOES NOT MATCH") + " the line '" + line + "'");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

