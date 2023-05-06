package edu.brown.cs.student.main.reports;

import edu.brown.cs.student.main.csv.data.Data;

import java.io.IOException;

/**
 * interface that represents any report generator, which must share the property that
 * they can take in a userCSV represented by a 2D string array, and return a wrapped report datum
 */
public interface ReportGenerator {
    /**
     * function that generates a wrapped data object (with all the information we need to display the wrapped report on
     * frontend) using a 2D array that represents a user's watching history
     * @param userCSVData the 2D string array that represents a user's watching history
     * @return a wrapped data object (with all the information we need to display the wrapped report on
     *       frontend)
     */
    Data.WrappedData generateReportJSON(String[][] userCSVData) throws IOException;
}
