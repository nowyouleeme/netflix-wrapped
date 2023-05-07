package edu.brown.cs.student.main.components.JsonDataType;

import java.util.ArrayList;
import java.util.List;

public class JSONShowSection {
  public Integer totalEpWatched = 0;
  public List<JSONShow> allShows = new ArrayList<>();
  public JSONTopShows topShows = new JSONTopShows();
  public JSONShowActors showActors = new JSONShowActors();

  public static class JSONShow {
    public String title = new String();
    public int numEpWatched = 0;
    public String image = new String();
  }

  public static class JSONTopShows {
    public List<JSONShow> mostWatched = new ArrayList<JSONShow>();
    public List<JSONShow> leastWatched = new ArrayList<JSONShow>();
  }

  public static class JSONShowActors {
    public List<String> mostWatchedActors = new ArrayList<String>();
    public List<JSONShow> actorFeaturedShows = new ArrayList<JSONShow>();
  }
}
