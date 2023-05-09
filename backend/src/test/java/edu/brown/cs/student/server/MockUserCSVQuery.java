package edu.brown.cs.student.server;

import edu.brown.cs.student.main.reports.MockRGenerator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * class to represent mock user csv queries to the saveData enpoint
 */
public class MockUserCSVQuery {
    String mock1;

    /**
     * constructor that creates 1 mockuser csv query to the saveData enpoint
     * @throws IOException
     */
    public MockUserCSVQuery() throws IOException {
        mock1 = new String(Files.readAllBytes(Paths.get("backend-ml/data/finalTestData.csv")));
    }
}
