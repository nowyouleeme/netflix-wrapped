package edu.brown.cs.student.main.components.JsonDataType;

import edu.brown.cs.student.main.components.JsonDataType.JSONTopGenres.JSONGenre;
import java.util.ArrayList;
import java.util.List;

public class JSONFinalFetch {
  public List<JSONGenre> top5Genres = new ArrayList<>();
  public int totalMin = 0;
  public JSONBingeData bingeData = new JSONBingeData();
  public JSONShowSection shows = new JSONShowSection();
  public JSONMovieSection movie = new JSONMovieSection();
  public JSONPersonality personality = new JSONPersonality();
}
