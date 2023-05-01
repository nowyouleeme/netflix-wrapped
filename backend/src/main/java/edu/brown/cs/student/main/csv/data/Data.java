package edu.brown.cs.student.main.csv.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/** Class for converting the GeoJSON to a Data object. */
public class Data {
  /**
   * Record for one user's csv
   * @param usercsv the csv of the user
   */
  public record UserCSV(List<List<Object>> usercsv) {}
}
