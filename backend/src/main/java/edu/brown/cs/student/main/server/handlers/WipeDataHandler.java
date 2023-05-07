package edu.brown.cs.student.main.server.handlers;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import edu.brown.cs.student.main.csv.data.Data;
import edu.brown.cs.student.main.server.ServerInfo;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import edu.brown.cs.student.main.user.UserID;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import spark.Route;


/** Class that holds the handler for the "loadRLTData" server endpoint. */
public class WipeDataHandler implements Route {
  private final ServerInfo serverInfo;

  /**
   * Constructor for a WipeDataHandler object.
   *
   * @param serverInfo - ServerInfo object that holds the currently loaded GeoJSON file.
   */
  public WipeDataHandler(ServerInfo serverInfo) {
    this.serverInfo = serverInfo;
  }

  @Override
  public Object handle(Request request, Response response) throws Exception {
    QueryParamsMap queryMap = request.queryMap();
    HashMap<String, String> errorMessages = new HashMap<>();

    if (queryMap.toMap().size() != 0) {
      errorMessages.put("error_bad_json", "expected 0 query parameters but received " + queryMap.toMap().size());
      return serialize(fail(errorMessages));
    }

    try {
      //wipe userCSV from the serverInfo
      serverInfo.wipeUserData();
      System.out.print("user data in server info\n" + serverInfo.getUserData() + "\n");
      return serialize(success());
    } catch (Exception e) {
      System.out.println(e.getMessage());
      errorMessages.put("error_bad_request", "unexpected error occured trying to wipe data");
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
