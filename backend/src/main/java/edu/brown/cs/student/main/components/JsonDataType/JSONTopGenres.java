package edu.brown.cs.student.main.components.JsonDataType;

import java.util.ArrayList;
import java.util.List;



  /**
    class for creating the top 5 genres to avoid typecasting issues
 */
public class JSONTopGenres {
    public List<JSONGenre> top5Genres = new ArrayList<>();
    
    public static class JSONGenre {
        public String genre;
        public int count;
    }
    
}
