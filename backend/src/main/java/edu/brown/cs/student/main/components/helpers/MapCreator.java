package edu.brown.cs.student.main.components.helpers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.squareup.moshi.JsonDataException;

import edu.brown.cs.student.main.components.JsonDataType.movieData.movieJson;

public class MapCreator {

  public MapCreator() {

  }

  public ArrayList<Map<String, ArrayList<ArrayList<String>>>> createWatchedMovieMap(String[][] history) {
    //fix show issue


    ArrayList<Map<String, ArrayList<ArrayList<String>>>> historyMapList = new ArrayList<>();


    for (int i = 0; i < history.length; i++) {
      //fill up historyMapList.
      final Map<String, ArrayList<ArrayList<String>>> movieMap = createMovieMap();
      Map<String, ArrayList<ArrayList<String>>> mapToAdd = new HashMap<String, ArrayList<ArrayList<String>>>();
      //System.out.println(mapToAdd.get(history[i][0]).size());

      //check if a title is a show: search and not found-> split by :, and search again. 

      if(movieMap.containsKey(history[i][0])){
        ArrayList<ArrayList<String>> movieInfo = new ArrayList<ArrayList<String>>();
        movieInfo = movieMap.get(history[i][0]);
        mapToAdd.put(history[i][0], movieInfo);
        ArrayList<String> stringList = new ArrayList<String>();
        stringList.add(history[i][1]);
        //this line ic changing movemap somehow
        mapToAdd.get(history[i][0]).add(stringList);
      }
      else{
        if (movieMap.containsKey(history[i][0].split(":")[0])){
          String showTitle = history[i][0].split(":")[0];
          ArrayList<ArrayList<String>> movieInfo = new ArrayList<ArrayList<String>>();
          movieInfo = movieMap.get(showTitle);
          mapToAdd.put(showTitle, movieInfo);
          ArrayList<String> stringList = new ArrayList<String>();
          stringList.add(history[i][1]);
          //this line ic changing movemap somehow
          mapToAdd.get(showTitle).add(stringList);
        }
        else{
          ArrayList<ArrayList<String>> largeStringArray = new ArrayList<ArrayList<String>>();
          ArrayList<String> stringList = new ArrayList<String>();
          stringList.add(history[i][1]);
          largeStringArray.add(stringList);
          //this line ic changing movemap somehow
          mapToAdd.put(history[i][0], largeStringArray);
        }
      }
      historyMapList.add(mapToAdd);
      //printMapWithArray(mapToAdd);
    }
    return historyMapList;
  }

// //loop through the history length
// for (int i = 0; i < history.length; i++) {
//   //make new variables, movieInfo is the movie information array and mapToAdd is the map we add to the final array
//   ArrayList<ArrayList<String>> movieInfo = new ArrayList<ArrayList<String>>();
//   Map<String, ArrayList<ArrayList<String>>> mapToAdd = new HashMap<>();

//   //if movieMap actually has information on the movie title,
//   if (movieMap.containsKey(history[i][0])) {
//     //movieinfo is the information in the movieMap. 

//     System.out.println(history[i][0] + ": " + movieMap.get(history[i][0]).size());
//     movieInfo = movieMap.get(history[i][0]);
//   }
//   // gets null if not found
//   ArrayList<String> watchDateList = new ArrayList<>();
//   watchDateList.add(history[i][1]);
//   // prevent adding multiple versions of view dates.

//   movieInfo.add(watchDateList);
  


//   mapToAdd.put(history[i][0], movieInfo);
//   historyMapList.add(mapToAdd);
// }
// System.out.println(historyMapList.size());
// for (Map<String, ArrayList<ArrayList<String>>> entry : historyMapList) {
//   printMapWithArray(entry);
// }
// return historyMapList;







  public Map<String, ArrayList<ArrayList<String>>> createMovieMap() {
    Map<String, ArrayList<ArrayList<String>>> finalMap = new HashMap<>();
    try {
      JsonReader<movieJson> jsonReader = new JsonReader<>(movieJson.class);
      movieJson result = jsonReader.fromJson("data/netflix_titles.json");

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
