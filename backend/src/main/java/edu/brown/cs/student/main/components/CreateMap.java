package edu.brown.cs.student.main.components;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.squareup.moshi.JsonDataException;

import edu.brown.cs.student.main.movieData.movieData.movieJson;

public class CreateMap {
  String[][] history;

  public CreateMap(String[][] userData) {
    history = userData;

  }

  public ArrayList<Map<String, ArrayList<ArrayList<String>>>> createWatchedMovieMap() {

    Map<String, ArrayList<ArrayList<String>>> movieMap = new HashMap<>();
    ArrayList<Map<String, ArrayList<ArrayList<String>>>> historyMapList = new ArrayList<>();
    movieMap = createMovieMap();
    for (int i = 0; i < history.length; i++) {
      ArrayList<ArrayList<String>> movieInfo = new ArrayList<ArrayList<String>>();
      Map<String, ArrayList<ArrayList<String>>> mapToAdd = new HashMap<>();

      if (movieMap.get(history[i][0]) != null) {
        movieInfo = movieMap.get(history[i][0]);
      }
      // gets null if not found
      ArrayList<String> watchDateList = new ArrayList<>();
      watchDateList.add(history[i][1]);
      movieInfo.add(watchDateList);

      mapToAdd.put(history[i][0], movieInfo);
      historyMapList.add(mapToAdd);
    }
    return historyMapList;
  }


  public Map<String, ArrayList<ArrayList<String>>> createMovieMap() {
    Map<String, ArrayList<ArrayList<String>>> finalMap = new HashMap<>();
    try {
      JsonReader<movieJson> jsonReader = new JsonReader<>(movieJson.class);
      movieJson result = jsonReader.fromJson("backend/data/netflix_titles.json");

      Map<String, String> listed_in = result.listed_in();
      Map<String, Integer> release_year = result.release_year();
      Map<String, String> type = result.type();
      Map<String, String> title = result.title();
      Map<String, String> cast = result.cast();
      Map<String, String> duration = result.duration();

      Map<String, ArrayList<String>> listed_inArr = new HashMap<>();
      Map<String, ArrayList<String>> release_yearArr = new HashMap<>();
      Map<String, ArrayList<String>> newTypeArr = new HashMap<>();
      Map<String, ArrayList<String>> castArr = new HashMap<>();
      Map<String, ArrayList<String>> durationArr = new HashMap<>();

      for (Map.Entry<String, String> entry : type.entrySet()) {
        String key = entry.getKey();
        String value = entry.getValue();
        ArrayList<String> valueList = new ArrayList<>();
        valueList.add(value);
        newTypeArr.put(key, valueList);
      }

      for (Map.Entry<String, String> entry : listed_in.entrySet()) {
        String key = entry.getKey();
        String value = entry.getValue();
        String[] valuesArray = value.split(", ");
        ArrayList<String> valuesList = new ArrayList<String>(Arrays.asList(valuesArray));
        listed_inArr.put(key, valuesList);
      }
      for (Map.Entry<String, Integer> entry : release_year.entrySet()) {
        String key = entry.getKey();
        Integer value = entry.getValue();
        ArrayList<String> valueList = new ArrayList<>();
        valueList.add(value.toString());
        release_yearArr.put(key, valueList);
      }

      for (Map.Entry<String, String> entry : cast.entrySet()) {
        String key = entry.getKey();
        String value = entry.getValue();
        String[] valuesArray;
        if (value != null) {
          valuesArray = value.split(", ");
        } else {
          valuesArray = new String[0];
        }
        ArrayList<String> valuesList = new ArrayList<String>(Arrays.asList(valuesArray));
        castArr.put(key, valuesList);
      }

      for (Map.Entry<String, String> entry : duration.entrySet()) {
        String key = entry.getKey();
        String value = entry.getValue();
        ArrayList<String> valueList = new ArrayList<>();
        valueList.add(value);
        durationArr.put(key, valueList);
      }

      for (int i = 0; i < listed_in.size(); i++) {
        // String[][] individualMovieData = new String[3][listed_in.size()];
        ArrayList<ArrayList<String>> individualMovieData = new ArrayList<ArrayList<String>>();
        individualMovieData.add(listed_inArr.get(Integer.toString(i)));
        individualMovieData.add(newTypeArr.get(Integer.toString(i)));
        individualMovieData.add(release_yearArr.get(Integer.toString(i)));
        individualMovieData.add(durationArr.get(Integer.toString(i)));
        individualMovieData.add(castArr.get(Integer.toString(i)));
        finalMap.put(title.get(Integer.toString(i)), individualMovieData);

      }
      ;
      return finalMap;
    } catch (IOException | JsonDataException e) {
      return finalMap;
    }
  }

  // only for testing purposes
  public void printMapWithArray(Map<String, ArrayList<ArrayList<String>>> input) {
    for (Map.Entry<String, ArrayList<ArrayList<String>>> entry : input.entrySet()) {
      String key = entry.getKey();
      System.out.println("key: " + key);
      // key printed
      ArrayList<ArrayList<String>> values = entry.getValue();
      String valueString = new String();
      for (ArrayList<String> innerList : values) {
        valueString = valueString + innerList.toString();
      }
      System.out.println("val: " + valueString);
    }
  }

}
