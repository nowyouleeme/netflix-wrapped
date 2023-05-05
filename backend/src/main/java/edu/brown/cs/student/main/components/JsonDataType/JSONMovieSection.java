package edu.brown.cs.student.main.components.JsonDataType;

import java.util.ArrayList;
import java.util.List;

public class JSONMovieSection {
    public Integer totalMoviesWatched = 0;
    public List<JSONMovie> allMovies = new ArrayList<JSONMovie>();
    public List<JSONMovie> top5Movies = new ArrayList<JSONMovie>();
    public MovieActors movieActors = new MovieActors();
  
    public static class JSONMovie {
        public String title = new String();
        public int numEpWatched = 0;
        public String image = new String();
    }

    public static class MovieActors {
        public List<String> mostWatchedActors = new ArrayList<String>();
        public List<JSONMovie> actorFeaturedMovies = new ArrayList<JSONMovie>();
    }
    
}
