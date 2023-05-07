package edu.brown.cs.student.main.components.JsonDataType;

import java.util.ArrayList;
import java.util.List;



/**
class to create the top genres without typecasting
*/
public class JSONTopGenres {
  public List<JSONGenre> top5Genres = new ArrayList<>();

  public static class JSONGenre {
    public String genre;
    public int count;
  }
}
