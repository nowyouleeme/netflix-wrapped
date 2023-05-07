package edu.brown.cs.student.main.components.JsonDataType;

import java.util.ArrayList;
import java.util.List;

public class JSONBingeData {
  public String date;
  public List<JSONBingeShow> shows = new ArrayList<>();
  public List<JSONBingeMovie> movies = new ArrayList<>();

  public static class JSONBingeShow {
    public String title;
    public int numEpWatched;
    public String image;
  }

  public static class JSONBingeMovie {
    public String title;
    public String image;
  }
}
