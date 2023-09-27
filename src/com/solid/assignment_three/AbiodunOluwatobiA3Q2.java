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
    public static boolean matchPattern(String pattern, String str) {
        return matchPatternHelper(pattern, 0, str, 0);
    }

    private static boolean matchPatternHelper(String pattern, int patternIndex, String str, int strIndex) {
        // Base case 1: If both pattern and str are fully processed, it's a match
        if (patternIndex == pattern.length() && strIndex == str.length()) {
            return true;
        }

        // Base case 2: If only the pattern is fully processed, but not the str, it's not a match
        if (patternIndex == pattern.length()) {
            return false;
        }

        //Recursive case
        //“[afc]x” matches the line “I like fax lots”.
        // Check if the next character in the pattern is an asterisk
        if (patternIndex + 1 < pattern.length() && pattern.charAt(patternIndex + 1) == '*') {
            // Match zero or more characters with '*'
            if(matchPatternHelper(pattern, patternIndex + 2, str, strIndex)){
                return true;
            }

            while(strIndex < str.length() && (pattern.charAt(patternIndex) == str.charAt(strIndex) || pattern.charAt(patternIndex) == '*')){
                if(matchPatternHelper(pattern, patternIndex + 2, str, strIndex)){
                    return true;
                }
                strIndex++;
            }

            return false;
        } else if (pattern.charAt(patternIndex) == '[') {
            // Find the closing bracket for the character set
            int closingBracketIndex = pattern.indexOf(']', patternIndex);
            if(closingBracketIndex == -1) return false;

            //“[afc]x”   string =  “I like fax lots”.
            // Extract the characters between brackets
            String stringBetweenBraces = pattern.substring(patternIndex + 1, closingBracketIndex);
            // Check if the line's character at the current index matches any character in the character set
            if (stringBetweenBraces.indexOf(str.charAt(strIndex)) >= 0) {
                return matchPatternHelper(pattern, closingBracketIndex + 1, str, strIndex + 1);
            } else {
                return false;
            }
        } else {
            // Check if the current character in the pattern matches the corresponding character in the line
            if (strIndex < str.length() && pattern.charAt(patternIndex) == str.charAt(strIndex)) {
                return matchPatternHelper(pattern, patternIndex + 1, str, strIndex + 1);
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

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
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

