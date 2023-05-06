package edu.brown.cs.student.main.server.handlers;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import edu.brown.cs.student.main.csv.data.Data;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import edu.brown.cs.student.main.server.ServerInfo;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import spark.Route;

/** Class that holds the handler for the "saveAnnotations" server endpoint. */
public class SaveDataHandler implements Route {
  private ServerInfo serverInfo;

  public SaveDataHandler(ServerInfo serverInfo) {
    this.serverInfo = serverInfo;
  }

  @Override
  public Object handle(Request request, Response response) {
    // QueryParamsMap queryMap = request.queryMap();
    // String userCSVQuery = queryMap.value("usercsv");
    String userCSVQuery0 = request.body();
    String userCSVQuery = userCSVQuery0.substring(1, userCSVQuery0.length() - 1).replace("\\", "");
    System.out.println(userCSVQuery);
    HashMap<String, String> errorMessages = new HashMap<>();

<<<<<<< Updated upstream
    if (queryMap.toMap().size() != 1) {
      errorMessages.put("error_bad_json", "expected 1 query parameters but received " + queryMap.toMap().size());
      return serialize(fail(errorMessages));
    }

    if (userCSVQuery == null) {
      errorMessages.put("error_bad_json", "need usercsv query parameter in order to save data");
=======
    // if (queryMap.toMap().size() != 1) {
    //   errorMessages.put("error_bad_json", "expected 1 query parameters but received" + queryMap.toMap().size());
    //   System.out.println(1);
    //   return serialize(fail(errorMessages));
    // }

    if (userCSVQuery == null) {
      errorMessages.put("error_bad_json", "need user's csv to save data");
>>>>>>> Stashed changes
      return serialize(fail(errorMessages));
    }


    try {
      Moshi moshi = new Moshi.Builder().build();
      System.out.println("hello");
      Data.UserCSV parsedUserCSV = moshi.adapter(Data.UserCSV.class).fromJson(userCSVQuery);
      //must be at least 2 rows
      System.out.println(parsedUserCSV);
      if (parsedUserCSV.usercsv().length < 2) {
<<<<<<< Updated upstream
        System.out.println(parsedUserCSV.usercsv().length);
        errorMessages.put("error_bad_request", "invalid netflix history csv");
=======
        errorMessages.put("error_bad_json", "invalid csv");
>>>>>>> Stashed changes
        return serialize(fail(errorMessages));
      }

      //each row must be 2 columns
      for (int i = 0; i < parsedUserCSV.usercsv().length; i++) {
        if (parsedUserCSV.usercsv()[i].length != 2) {
<<<<<<< Updated upstream
          System.out.println(4);
          errorMessages.put("error_bad_request", "invalid netflix history csv");
=======
          errorMessages.put("error_bad_json", "invalid csv");
>>>>>>> Stashed changes
          return serialize(fail(errorMessages));
        }
      }

//      headers must be title and date
      if (!(parsedUserCSV.usercsv()[0][0].equals("Title") && parsedUserCSV.usercsv()[0][1].equals("Date"))) {
        errorMessages.put("error_bad_request", "invalid netflix history csv");
        return serialize(fail(errorMessages));
      }

      //save the userCSV into the serverInfo
      serverInfo.saveUserData(parsedUserCSV);
      // for (int i = 0; i < serverInfo.getUserData().usercsv().length; i++) {
      //   System.out.print(Arrays.toString(serverInfo.getUserData().usercsv()[i])  + "\n");
      // }

      //return serialized success response
      return serialize(success());
    } catch (Exception e) {
<<<<<<< Updated upstream
      System.out.println(e.getMessage());
      errorMessages.put("error_bad_request", "unexpected error occured trying to save csv");
=======
      // System.out.println(e.getMessage()); // TODO: better error handling
      errorMessages.put("error_bad_json", "unexpected error");
>>>>>>> Stashed changes
      return serialize(fail(errorMessages));
    }
  }

  /**
   * Method that returns a Map mapping the response for a failed request.
   *
   * @param err - the error messages we are returning
   * @return hashmap mapping the response for a failed request
   */
  public Map<String, Object> fail(Map err) {
    Map<String, Object> failed = new HashMap<>();
    failed.put("result", err);
    return failed;
  }

  /**
   * Method that returns a Map mapping the response for a successful request.
   *
   * @return the hashmap mapping the response for a successful request.
   */
  public Map<String, Object> success() {
    Map<String, Object> successful = new HashMap<>();
    successful.put("result", "success");
    return successful;
  }

  /**
   * Method that serializes a HashMap mapping strings to objects (also known as the response
   * objects) as a Json Object
   *
   * @param response - the response that is being converted to a json
   * @return the json object representing the response
   */
  public static String serialize(Map<String, Object> response) {
    Moshi moshi = new Moshi.Builder().build();
    Type mapOfJSONResponse =
        Types.newParameterizedType(Map.class, String.class, Object.class, Data.UserCSV.class);
    JsonAdapter<Map<String, Object>> adapter = moshi.adapter(mapOfJSONResponse);
    return adapter.toJson(response);
  }
}
