package edu.brown.cs.student.main.reports;

import com.squareup.moshi.Moshi;
import edu.brown.cs.student.main.csv.data.Data;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * class that is used as a mock wrapped report data generator
 */
public class MockRGenerator implements ReportGenerator{
    public MockRGenerator(){}

    /**
     * mock generateReportJSON function that generates a WrappedData datum using a mock file
     * @param csvData unused
     * @return the WrappedData datum
     */
    public Data.WrappedData generateReportJSON(String[][] csvData) {
        try {
            String stringJSON = new String(Files.readAllBytes(Paths.get("src/main/java/edu/brown/cs/student/main/reports/MockWrappedJSON.json")));
            Moshi moshi = new Moshi.Builder().build();
            Data.WrappedData wrappedData = moshi.adapter(Data.WrappedData.class).fromJson(stringJSON);
            return wrappedData;
        } catch (Exception e) {
            return null;
        }
    }
}
