package edu.brown.cs.student.main.components.JsonDataType;

import java.util.ArrayList;
import java.util.List;

public class JSONTopGenres {
    public List<Genre> top5Genres = new ArrayList<>();
    
    public static class Genre {
        public String genre;
        public int count;
    }
    
}
