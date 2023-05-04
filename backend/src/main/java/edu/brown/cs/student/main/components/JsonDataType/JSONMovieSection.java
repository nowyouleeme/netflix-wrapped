package edu.brown.cs.student.main.components.JsonDataType;

import java.util.ArrayList;
import java.util.List;

public class JSONMovieSection {
    public Integer totalMoviesWatched = 0;
    public List<Movie> allMovies = new ArrayList<Movie>();
    public List<Movie> top5Movies = new ArrayList<Movie>();
    public MovieActors movieActors = new MovieActors();
  
    public static class Movie {
        public String title = new String();
        public int numEpWatched = 0;
        public String image = new String();
    }

    public static class MovieActors {
        public List<String> mostWatchedActors = new ArrayList<String>();
        public List<Movie> actorFeaturedMovies = new ArrayList<Movie>();
    }
    
}
