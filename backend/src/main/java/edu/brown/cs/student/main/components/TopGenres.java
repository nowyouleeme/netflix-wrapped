package edu.brown.cs.student.main.components;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.brown.cs.student.main.components.helpers.CreateMap;

public class TopGenres {
    public TopGenres() {
    }

    public ArrayList<String> getTopGenres(String[][] userHistory) {
        ArrayList<String> returnGenreList = new ArrayList<String>();
        Map<String, Integer> genreCountMap = new HashMap<>();
        
        ArrayList<Map<String, ArrayList<ArrayList<String>>>> userHistoryMapList = new ArrayList<Map<String, ArrayList<ArrayList<String>>>>();
        CreateMap mapCreator = new CreateMap();

        userHistoryMapList = mapCreator.createWatchedMovieMap(userHistory);
        
        for (Map<String, ArrayList<ArrayList<String>>> map : userHistoryMapList) {
            for (Map.Entry<String, ArrayList<ArrayList<String>>> entry : map.entrySet()) {
            String key = entry.getKey();
            ArrayList<ArrayList<String>> value = entry.getValue();
            // if value is 1 it means we dont have genre info
            if (value.size() != 1) {
                for (int i = 0; i < value.get(0).size(); i++) {
                    if (genreCountMap.containsKey(value.get(0).get(i))) {
                        int count = genreCountMap.get(value.get(0).get(i));
                        genreCountMap.put(value.get(0).get(i), count + 1);
                    } else {
                        genreCountMap.put(value.get(0).get(i), 1);
                    }
                }
            }
            }
        }
        //rewrite code for this
        List<Map.Entry<String, Integer>> genreCountList = new ArrayList<>(genreCountMap.entrySet());
        Collections.sort(genreCountList, (a, b) -> b.getValue().compareTo(a.getValue()));

        // Print the top 5 genres with their counts
        int numGenres = Math.min(5, genreCountList.size());
        for (int i = 0; i < numGenres; i++) {
            returnGenreList.add(genreCountList.get(i).getKey());
            System.out.println(genreCountList.get(i).getKey());
        }
        return returnGenreList;

    }
}
