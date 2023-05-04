package edu.brown.cs.student.main.components.JsonDataType;

import java.util.ArrayList;
import java.util.List;

public class JSONShowSection {
    public Integer totalEpWatched = 0;
    public List<Show> allShows = new ArrayList<>();
    public TopShows topShows = new TopShows();
    public ShowActors showActors = new ShowActors();
  
    public static class Show {
        public String title = new String();
        public int numEpWatched = 0;
        public String image = new String();
    }

    public static class TopShows {
        public List<Show> above50_3 = new ArrayList<Show>();
        public List<Show> below50_3 = new ArrayList<Show>();
    }

    public static class ShowActors {
        public List<String> mostWatchedActors = new ArrayList<String>();
        public List<Show> actorFeaturedShows = new ArrayList<Show>();
    }
}
