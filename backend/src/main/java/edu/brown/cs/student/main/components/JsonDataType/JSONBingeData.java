package edu.brown.cs.student.main.components.JsonDataType;

import java.util.ArrayList;
import java.util.List;

public class JSONBingeData {
    public String date;
    public List<BingeShow> shows = new ArrayList<>();
    public List<BingeMovie> movies = new ArrayList<>();
    
    public static class BingeShow {
        public String title;
        public int numEpWatched;
        public String image;
        
    }
    
    public static class BingeMovie {
        public String title;
        public String image;
        
    }
    

}
    