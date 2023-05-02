package edu.brown.cs.student.main.components;

import java.util.ArrayList;
import java.util.Map;

import edu.brown.cs.student.main.components.helpers.CreateMap;

public class Personality {
    public Personality(){

    }

    public String getPersonality(String[][] userHistory){
        
        ArrayList<Map<String, ArrayList<ArrayList<String>>>> userHistoryMapList = new ArrayList<Map<String, ArrayList<ArrayList<String>>>>();
        CreateMap mapCreator = new CreateMap();
        userHistoryMapList = mapCreator.createWatchedMovieMap(userHistory);


        //user userHistoryMapList to get the personality type, return string or whatever datatype you prefer, preferably 
        //arraylist of string
        

        return null;
    }
}
