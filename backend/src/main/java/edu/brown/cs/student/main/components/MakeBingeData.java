package edu.brown.cs.student.main.components;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.brown.cs.student.main.PosterImageGetting.PosterFetch;
import edu.brown.cs.student.main.components.JsonDataType.JSONBingeData;
import edu.brown.cs.student.main.components.JsonDataType.JSONBingeData.JSONBingeMovie;
import edu.brown.cs.student.main.components.JsonDataType.JSONBingeData.JSONBingeShow;

public class MakeBingeData {




    /**
    A function to create the bingedata using many helpers.
    */
    public JSONBingeData getBingeData(String[][] userHistory,
            ArrayList<Map<String, ArrayList<ArrayList<String>>>> userHistoryMapList) {
        PosterFetch posterFetcher = new PosterFetch();
        Map<String, Integer> dateCountMap = new HashMap<>();
        JSONBingeData bingeData = new JSONBingeData();

        //loop through the user history
        for (Map<String, ArrayList<ArrayList<String>>> map : userHistoryMapList) {
            for (Map.Entry<String, ArrayList<ArrayList<String>>> entry : map.entrySet()) {
                ArrayList<ArrayList<String>> value = entry.getValue();
                // if value is 1 it means we dont have genre info
                if (value.size() != 1) {
                    if (dateCountMap.containsKey(value.get(5).get(0))) {
                        int count = dateCountMap.get(value.get(5).get(0));
                        dateCountMap.put(value.get(5).get(0), count + 1);

                    } else {
                        dateCountMap.put(value.get(5).get(0), 1);
                    }
                } else {
                    if (dateCountMap.containsKey(value.get(0).get(0))) {
                        int count = dateCountMap.get(value.get(0).get(0));
                        dateCountMap.put(value.get(0).get(0), count + 1);
                    } else {
                        dateCountMap.put(value.get(0).get(0), 1);
                    }
                }
            }
        }
        List<Map.Entry<String, Integer>> dateCountList = new ArrayList<>(dateCountMap.entrySet());
        Collections.sort(dateCountList, (a, b) -> b.getValue().compareTo(a.getValue()));

        bingeData.date = dateCountList.get(0).getKey();

        List<Map.Entry<String, Integer>> showsInDate = getShowsInDate(dateCountList.get(0).getKey(),
                userHistoryMapList);

        if (showsInDate != null) {
            for (Map.Entry<String, Integer> entry : showsInDate) {
                JSONBingeShow show = new JSONBingeShow();

                show.title = entry.getKey();
                show.numEpWatched = entry.getValue();
                show.image = posterFetcher.getPosterLink(entry.getKey());
                bingeData.shows.add(show);
            }
        }

        ArrayList<String> movieList = getMoviesInDate(dateCountList.get(0).getKey(), userHistoryMapList);
        for (int i = 0; i < movieList.size(); i++) {
            JSONBingeMovie movie = new JSONBingeMovie();
            movie.title = movieList.get(i);
            movie.image = posterFetcher.getPosterLink(movieList.get(i));
            bingeData.movies.add(movie);
        }
        return bingeData;

    }





    /**
    function to create a list of a map, to find the shows found in the date recieved.
    @param date the date to search
    @param userHistoryMapList the map list of the user history.
    @return the list of shows in date.
    */  
    public List<Map.Entry<String, Integer>> getShowsInDate(String date,
            ArrayList<Map<String, ArrayList<ArrayList<String>>>> userHistoryMapList) {
        Map<String, Integer> showCountMap = new HashMap<>();

        for (Map<String, ArrayList<ArrayList<String>>> map : userHistoryMapList) {
            for (Map.Entry<String, ArrayList<ArrayList<String>>> entry : map.entrySet()) {
                if (entry.getValue().size() != 1) {
                    if (entry.getValue().get(5).get(0).equals(date)) {
                        if (entry.getValue().get(1).get(0).equals("TV Show")) {
                            if (showCountMap.containsKey(entry.getKey())) {
                                showCountMap.put(entry.getKey(), showCountMap.get(entry.getKey()) + 1);
                            } else {
                                showCountMap.put(entry.getKey(), 1);
                            }
                        }

                    }
                }
            }
        }
        List<Map.Entry<String, Integer>> showCountList = new ArrayList<>(showCountMap.entrySet());
        Collections.sort(showCountList, (a, b) -> b.getValue().compareTo(a.getValue()));

        if (showCountList.size() > 0) {
            return showCountList;
        } else {
            return null;
        }
    }



    /**
    function to create a a list of movies in a specified date.
    @param date the date to search
    @param userHistoryMapList the map list of the user history.
    @return the list of movies in the date.
    */  
    public ArrayList<String> getMoviesInDate(String date,
            ArrayList<Map<String, ArrayList<ArrayList<String>>>> userHistoryMapList) {
        Set<String> movies = new HashSet<String>();

        for (Map<String, ArrayList<ArrayList<String>>> map : userHistoryMapList) {
            for (Map.Entry<String, ArrayList<ArrayList<String>>> entry : map.entrySet()) {
                if (entry.getValue().size() != 1) {
                    if (entry.getValue().get(5).get(0).equals(date)) {
                        if (entry.getValue().get(1).get(0).equals("Movie")) {
                            movies.add(entry.getKey());
                        }
                    }
                } else {
                    if (entry.getValue().get(0).get(0).equals(date)) {
                        movies.add(entry.getKey());
                    }
                }
            }
        }
        ArrayList<String> movieList = new ArrayList<String>(movies);
        return movieList;

    }

}
