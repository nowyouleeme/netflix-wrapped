package edu.brown.cs.student.server;

import edu.brown.cs.student.main.reports.MockRGenerator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MockUserCSVQuery {
    String mock1;
    public MockUserCSVQuery() throws IOException {
        mock1 = new String(Files.readAllBytes(Paths.get("backend-ml/data/finalTestData.csv")));
    }
}
