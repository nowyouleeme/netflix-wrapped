package edu.brown.cs.student.main.server.handlers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class CSVReader {
    public static String[][] readCSV(String filename) {
        String line = "";
        String delimiter = ",";
        String[][] movieData = null;
        int row = 0;

        try  {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            // Count the number of rows in the CSV file
            int numRows = (int) br.lines().count();

            // Reset the reader
            br.close();
            br = new BufferedReader(new FileReader(filename));

            // Initialize the movieData array
            movieData = new String[numRows][2];

            // Read each line of the CSV file and add it to the movieData array
            while ((line = br.readLine()) != null) {
                String[] movieLine = line.split(delimiter);
                movieData[row][0] = movieLine[0];
                movieData[row][1] = movieLine[1];
                row++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return movieData;
    }

    public static void main(String[] args) {
        String filename = "movies.csv";
        String[][] movieData = readCSV(filename);

        // Print the movie data array
        for (int i = 0; i < movieData.length; i++) {
            System.out.println(movieData[i][0] + ", " + movieData[i][1]);
        }
    }
    
}
