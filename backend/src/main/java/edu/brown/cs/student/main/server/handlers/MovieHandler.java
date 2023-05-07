package edu.brown.cs.student.main.server.handlers;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import com.squareup.moshi.JsonDataException;
import com.squareup.moshi.Moshi;

import spark.Route;
import edu.brown.cs.student.main.components.MakeBingeData;
import edu.brown.cs.student.main.components.MakeMovieSection;
import edu.brown.cs.student.main.components.MakePersonality;
import edu.brown.cs.student.main.components.MakeShowSection;
import edu.brown.cs.student.main.components.MakeTopGenres;
import edu.brown.cs.student.main.components.MakeTotalMin;
import edu.brown.cs.student.main.components.JsonDataType.JSONFinalFetch;
import edu.brown.cs.student.main.components.helpers.MapCreator;
import spark.Request;
import spark.Response;



/**
  * THIS CLASS IS NOT USED BY THE ACTUAL PROGRAM, BUT RATHER USED TO MAKE SURE THE DATA
  IS BEING PROCESSED. THE ENDPOINT IS USED TO VISUALIZE DATA FOR THE DEVELOPERS.
  *THE CODE IS NEARLY IDENTICAL TO MLGenerator.
  */
public class MovieHandler implements Route {
  public String[][] history;


  public MovieHandler(String[][] userData) {
    history = userData;
  }


  @Override
  public Object handle(Request request, Response response) throws Exception {
    try {


      MapCreator mapCreator = new MapCreator();
      MakeTopGenres topGenre = new MakeTopGenres();
      MakeBingeData bingeData = new MakeBingeData();
      MakeTotalMin totalMin = new MakeTotalMin();
      MakeMovieSection movieSection = new MakeMovieSection();
      MakeShowSection showSection = new MakeShowSection();

      ArrayList<Map<String, ArrayList<ArrayList<String>>>> userHistoryMapList = mapCreator
          .createWatchedMediaMap(history);
      JSONFinalFetch finalFetchJson = new JSONFinalFetch();




      try (FileWriter writer = new FileWriter("backend/backend-ml/data/viewhist.csv")) {
        for (int j = 0; j < history.length; j++) {
          writer.append("\""+history[j][0]+"\"");
          writer.append(",");
          writer.append("\""+history[j][1]+"\"");
          writer.append("\n");
        }
        writer.close();
      } catch (IOException e) {

        e.printStackTrace();
      }

      MakePersonality personality = new MakePersonality();

      finalFetchJson.personality.title = personality.getPersonality(history, userHistoryMapList);
      finalFetchJson.top5Genres = topGenre.getTopGenres(history, userHistoryMapList).top5Genres;
      finalFetchJson.bingeData = bingeData.getBingeData(history, userHistoryMapList);
      finalFetchJson.totalMin = totalMin.getTotalMin(history, userHistoryMapList).totalMin;
      finalFetchJson.movie = movieSection.getMovieSection(history, userHistoryMapList);
      finalFetchJson.shows = showSection.getShowSection(history, userHistoryMapList);

      try (FileWriter writer = new FileWriter("backend/backend-ml/data/viewhist.csv")) {
        for (int j = 0; j < history.length; j++) {
          writer.flush();
        }
        writer.close();
      } catch (IOException e) {

        e.printStackTrace();
      }

      return new MovieSuccessResponse(finalFetchJson).serialize();
      

    } catch (JsonDataException e) {
      System.out.println(e.getMessage());
      return new MapFailureResponse("error_bad_request", e.getMessage()).serialize();
    }
  }


  public record MovieSuccessResponse(String result, Object json) {
    public MovieSuccessResponse(Object json) {
      this("success", json);
    }

    /**
     * @return this response, serialized as Json
     */
    public String serialize() {
      LinkedHashMap<String, Object> responseMap = new LinkedHashMap<>();
      responseMap.put("result", this.result);
      responseMap.put("movieJson", this.json);
      try {
        Moshi moshi = new Moshi.Builder()
            .build();
        return moshi.adapter(Map.class).toJson(responseMap);
      } catch (Exception e) {
        e.printStackTrace();
        throw e;
      }
    }
  }

  // failure response record
  public record MapFailureResponse(String result, String error_message) {
    /**
     * @return this response, serialized as Json
     */
    public String serialize() {
      LinkedHashMap<String, Object> responseMap = new LinkedHashMap<>();
      responseMap.put("result", this.result);
      responseMap.put("error_message", this.error_message);
      try {
        Moshi moshi = new Moshi.Builder()
            .build();
        return moshi.adapter(Map.class).toJson(responseMap);
      } catch (Exception e) {
        e.printStackTrace();
        throw e;
      }
    }
  }
}
