package edu.brown.cs.student.main.components.JsonDataType;

import java.util.ArrayList;
import java.util.List;

import edu.brown.cs.student.main.components.JsonDataType.JSONTopGenres.JSONGenre;


  /**
    class for creating the final data to avoid typecasting issues
 */
public class JSONFinalFetch {
    public List<JSONGenre> top5Genres = new ArrayList<>();
    public int totalMin = 0;
    public JSONBingeData bingeData = new JSONBingeData();
    public JSONShowSection shows = new JSONShowSection();
    public JSONMovieSection movie = new JSONMovieSection();
    public JSONPersonality personality = new JSONPersonality();
}