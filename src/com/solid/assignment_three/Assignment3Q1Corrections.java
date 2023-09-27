package com.solid.assignment_three;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Assignment3Q1Corrections {
    public static void main(String[] args) {
        //Prompt the user for a filename containing race results.
        String fileName;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the name of the results file: ");
        fileName = scanner.nextLine();

        //Load the data into a RaceResultList.
        RaceResultList resultList = readFile(fileName);

        //Sort the list, in increasing order of finish time. Use an insertion sort.
        resultList.sortResults();

       // Print the top 20 finishers, with their finish time and pace per mile
        System.out.println("The top 20 finishers are: ");
        System.out.println("Place\tName\t\tFinish Time \tPace Per Mile");
        List<RaceResult> top20Finishers = resultList.getTopFinishers(20);
        for(int i = 0; i < top20Finishers.size(); i++){
            System.out.println(i + 1 + "\t" + top20Finishers.get(i).getName() + "\t\t"
                    + top20Finishers.get(i).getFinishTime() + "\t" + top20Finishers.get(i).getPacePerMile());
        }
    }

    private static RaceResultList readFile(String fileName) {
        RaceResultList raceResultList = new RaceResultList();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            // Read the race results from the CSV file and store them in the RaceResultList
            String line;
            while ((line = reader.readLine()) != null) {
                String[] columns = line.split(",");
                if (columns.length == 3) {
                    int bibNumber = Integer.parseInt(columns[0]);
                    String name = columns[1];
                    String finishTime = columns[2];
                    RaceResult raceResult = new RaceResult(bibNumber, name, finishTime);
                    raceResultList.addRaceResult(raceResult);
                }
            }
        } catch (IOException e){
            throw new RuntimeException(e);
        }
        return raceResultList;
    }
}

class RaceResult implements Comparable<RaceResult> {
    private int bibNumber;
    private String name;
    private String finishTime; // Finish time in seconds

    public RaceResult(int bibNumber, String name, String finishTime) {
        this.bibNumber = bibNumber;
        this.name = name;
        this.finishTime = finishTime;
    }

    public String getName() {
        return name;
    }

    public String getFinishTime() {
        return finishTime;
    }

    private double convertToSeconds(String timeString) {
        String[] timeValues = timeString.split(":");
        int hours = Integer.parseInt(timeValues[0]);
        int minutes = Integer.parseInt(timeValues[1]);
        double seconds = Double.parseDouble(timeValues[2]);
        return (hours * 3600) + (minutes * 60) + seconds;
    }

    private String convertToTimeStr(double seconds){
        int hours = (int) (seconds / 3600);
        int minutes = (int) ((seconds % 3600) / 60);
        double remainingSeconds = seconds % 60;

        return String.format("%d:%2d%.1f", hours, minutes, remainingSeconds);
    }

    @Override
    public int compareTo(RaceResult other) {
        // Compare finish times to sort in increasing order
        return Double.compare(convertToSeconds(this.finishTime), convertToSeconds(other.finishTime));
    }

    // Calculate pace per mile (in seconds)
    public String getPacePerMile() {
        double marathonDistance = 26.22; // miles
        double secondsValue = convertToSeconds(finishTime) / marathonDistance;
        return convertToTimeStr(secondsValue);
    }
    @Override
    public String toString() {
        return bibNumber + " " + name + " " + finishTime + " " + getPacePerMile();
    }
}

class RaceResultList {
    private List<RaceResult> raceResults;

    public RaceResultList() {
        raceResults = new ArrayList<>();
    }

    public void addRaceResult(RaceResult result) {
        raceResults.add(result);
    }

    public void sortResults() {
        int n = raceResults.size();
        for (int i = 1; i < n; i++) {
            RaceResult key = raceResults.get(i);
            int j = i - 1;

            // Move elements of raceResults[0..i-1], that are greater than key.finishTime,
            // to one position ahead of their current position
            while (j >= 0 && raceResults.get(j).compareTo(key) > 0) {
                raceResults.set(j + 1, raceResults.get(j));
                j = j - 1;
            }
            raceResults.set(j + 1, key);
        }
    }

    public List<RaceResult> getTopFinishers(int n) {
        if (n <= 0 || n > raceResults.size()) {
            throw new IllegalArgumentException("Invalid value for N");
        }
        return raceResults.subList(0, n);
    }

    @Override
    public String toString() {
        return "RaceResultList{" +
                "raceResults=" + raceResults +
                '}';
    }
}
