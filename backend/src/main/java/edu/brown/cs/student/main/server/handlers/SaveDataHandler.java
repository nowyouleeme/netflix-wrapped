package edu.brown.cs.student.main.server.handlers;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import edu.brown.cs.student.main.csv.data.Data;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.brown.cs.student.main.server.Server;
import edu.brown.cs.student.main.server.ServerInfo;
import edu.brown.cs.student.main.user.UserID;
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
    QueryParamsMap queryMap = request.queryMap();
    String userCSVQuery = queryMap.value("usercsv");
    HashMap<String, String> errorMessages = new HashMap<>();

    if (queryMap.toMap().size() != 1) {
      errorMessages.put("error_bad_json", "expected 1 query parameters but received" + queryMap.toMap().size());
      return serialize(fail(errorMessages));
    }

    if (userCSVQuery == null) {
      errorMessages.put("error_bad_json", "need user's csv to save data");
      return serialize(fail(errorMessages));
    }


    try {
      Moshi moshi = new Moshi.Builder().build();
      System.out.print("usercsvquery\n" + userCSVQuery + "\n");
      Data.UserCSV parsedUserCSV = moshi.adapter(Data.UserCSV.class).fromJson(userCSVQuery);
      System.out.print("parsedUserCSV\n" + Arrays.toString(parsedUserCSV.usercsv()[0])  + "\n");

      //TODO: make sure the user csv is shaped right (2 columns, headers being title and date)

      //save the userCSV into the serverInfo
      serverInfo.saveUserData(parsedUserCSV);
      System.out.print("user data in server\n" + Arrays.toString(serverInfo.getUserData().usercsv()[1]) + "\n");

      //return serialized success response
      return serialize(success());
    } catch (Exception e) {
      System.out.println(e.getMessage()); // TODO: better error handling
      errorMessages.put("error_bad_json", e.getMessage());
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
