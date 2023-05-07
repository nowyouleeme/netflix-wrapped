package edu.brown.cs.student.main.components.JsonDataType;

import java.util.ArrayList;
import java.util.List;

public class JSONTopGenres {
    public List<JSONGenre> top5Genres = new ArrayList<>();
    
    public static class JSONGenre {
        public String genre;
        public int count;
    }
    
}
