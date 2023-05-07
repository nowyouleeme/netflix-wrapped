package edu.brown.cs.student.main.components;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.brown.cs.student.main.components.JsonDataType.JSONTopGenres;
import edu.brown.cs.student.main.components.JsonDataType.JSONTopGenres.JSONGenre;

public class MakeTopGenres {
    public MakeTopGenres() {
    }


    /**
     * Getting the top 5 or less genres you watched
     * @param userHistory the String array of user History
     * @return the top genres.
    */
    public JSONTopGenres getTopGenres(String[][] userHistory,
    ArrayList<Map<String, ArrayList<ArrayList<String>>>> userHistoryMapList) {
        JSONTopGenres topGenres = new JSONTopGenres();
        
        Map<String, Integer> genreCountMap = new HashMap<>();

        for (Map<String, ArrayList<ArrayList<String>>> map : userHistoryMapList) {
            for (Map.Entry<String, ArrayList<ArrayList<String>>> entry : map.entrySet()) {
            ArrayList<ArrayList<String>> value = entry.getValue();
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
        List<Map.Entry<String, Integer>> genreCountList = new ArrayList<>(genreCountMap.entrySet());
        Collections.sort(genreCountList, (a, b) -> b.getValue().compareTo(a.getValue()));

        int numGenres = Math.min(5, genreCountList.size());
        for (int i = 0; i < numGenres; i++) {
            JSONGenre genre = new JSONGenre();
            genre.genre = genreCountList.get(i).getKey();
            genre.count = genreCountList.get(i).getValue();
            topGenres.top5Genres.add(genre);
        }

        return topGenres;

    }
}
