package edu.brown.cs.student.main.csv.data;

import java.util.List;
import java.util.Map;

/** Class for converting the GeoJSON to a Data object. */
public class Data {
  /**
   * Record for one user's csv
   * @param filler
   */
  public record UserCSV(String filler) {} //TODO: adjust these records to actually match what the user csv data looks like

//  /**
//   * Record for the overall GeoJSON data structure, which contains a type and features field
//   *
//   * @param type the GeoJSON type name
//   * @param features the List of Feature objects making up the features of the map
//   */
//  public record GeoJSON(String type, List<Feature> features) {}
//
//  /**
//   * Record for an individual Feature object, which contains a type, geometry, and properties field
//   *
//   * @param type the Feature type
//   * @param geometry the shape of the Feature
//   * @param properties the properties of the Feature
//   */
//  public record Feature(String type, FeatureGeometry geometry, Map<String, Object> properties) {}
//
//  /**
//   * Record for a FeatureGeometry object, which contains a type and coordinates of the geometry
//   * (make up the polygon points)
//   *
//   * @param type the FeatureGeometry type
//   * @param coordinates the coordinates making up the polygon shape
//   */
//  public record FeatureGeometry(String type, List<List<List<List<Double>>>> coordinates) {}
}
