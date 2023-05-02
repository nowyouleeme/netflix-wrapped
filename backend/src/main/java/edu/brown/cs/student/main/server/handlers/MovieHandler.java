package edu.brown.cs.student.main.server.handlers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.squareup.moshi.JsonDataException;
import com.squareup.moshi.Moshi;

import spark.Route;
import edu.brown.cs.student.main.components.TopGenres;
import edu.brown.cs.student.main.components.helpers.CreateMap;
import edu.brown.cs.student.main.components.helpers.JsonReader;
import edu.brown.cs.student.main.movieData.PosterData;
import edu.brown.cs.student.main.movieData.PosterFetch;
import edu.brown.cs.student.main.movieData.movieData;
import edu.brown.cs.student.main.movieData.movieData.movieJson;

import spark.Request;
import spark.Response;

/**
 * Handler class for the filtered map
 *
 */
public class MovieHandler implements Route {
  public String[][] history;

  /**
   * Constructor accepts some shared csv
   */
  public MovieHandler(String[][] userData) {

    history = userData;
  }

  /**
   * Filters GeoJsonData objects based on query parameters and returns a success
   * or failure response
   * 
   * @param request  the HTTP request
   * @param response the HTTP response
   * @return a success response with the filtered GeoJsonData object, or a failure
   *         response
   * @throws Exception if something goes wrong while filtering the GeoJsonData
   *                   object
   */
  @Override
  public Object handle(Request request, Response response) throws Exception {
    try {
      JsonReader<movieJson> jsonReader = new JsonReader<>(movieJson.class);
      movieJson result = jsonReader.fromJson("backend/data/netflix_titles.json");
      Map<String, String> cast = result.cast();
      CreateMap mapCreator = new CreateMap();
      TopGenres topGenre = new TopGenres();
      topGenre.getTopGenres(history);


      return new MovieSuccessResponse(cast).serialize();
    } catch (IOException | JsonDataException e) {
      System.out.println(e.getMessage());
      return new MapFailureResponse("error_bad_request", e.getMessage()).serialize();
    }
  }

  // Success response record
  public record MovieSuccessResponse(String result, Map<String, String> movieJson) {
    public MovieSuccessResponse(Map<String, String> movieJson) {
      this("success", movieJson);
    }

    /**
     * @return this response, serialized as Json
     */
    public String serialize() {
      LinkedHashMap<String, Object> responseMap = new LinkedHashMap<>();
      responseMap.put("result", this.result);
      responseMap.put("geoJson", this.movieJson);
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
