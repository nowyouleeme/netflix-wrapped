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

/** Class that holds the handler for the "saveData" server endpoint. */
public class SaveDataHandler implements Route {
  private ServerInfo serverInfo;

  /**
   * constructor for savedatahandler object
   * @param serverInfo a ServerInfo object holding the data that our server maintains
   */
  public SaveDataHandler(ServerInfo serverInfo) {
    this.serverInfo = serverInfo;
  }

  @Override
  public Object handle(Request request, Response response) {
    HashMap<String, String> errorMessages = new HashMap<>();

    String userCSVQuery0 = request.body();
    if (userCSVQuery0 == null) {
      errorMessages.put("error_bad_json", "request body not provided");
      return serialize(fail(errorMessages));
    }


    String userCSVQuery = userCSVQuery0.substring(1, userCSVQuery0.length() - 1).replace("\\", "");

    try {
      Moshi moshi = new Moshi.Builder().build();
      Data.UserCSV parsedUserCSV = moshi.adapter(Data.UserCSV.class).fromJson(userCSVQuery);


      //this checks that the usercsv 2D array was actually created, aka checking query parameters
      if (parsedUserCSV.usercsv() == null) {
        errorMessages.put("error_bad_json", "usercsv not provided");
        return serialize(fail(errorMessages));
      }
      //must be at least 2 rows
      if (parsedUserCSV.usercsv().length < 2) {
        errorMessages.put("error_bad_request", "invalid netflix history csv");
        return serialize(fail(errorMessages));
      }

      //each row must be 2 columns
      for (int i = 0; i < parsedUserCSV.usercsv().length; i++) {
        if (parsedUserCSV.usercsv()[i].length != 2) {
          errorMessages.put("error_bad_request", "invalid netflix history csv");
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

      //return serialized success response
      return serialize(success());
    } catch (Exception e) {
      errorMessages.put("error_bad_request", "unexpected error occured trying to save csv");
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
