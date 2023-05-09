package edu.brown.cs.student.main.server.handlers;


import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import edu.brown.cs.student.main.components.JsonDataType.JSONFinalFetch;
import edu.brown.cs.student.main.csv.data.Data;
import edu.brown.cs.student.main.server.ServerInfo;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import spark.Route;

// the below imports and where they are used, which turns the json file into a json string, is taken
// from
// https://www.javatpoint.com/convert-json-file-to-string-in-java#:~:text=In%20Java%2C%20we%20can%20easily,intensive%20I%2FO%20operations).

/** Class that holds the handler for the "wrapped" server endpoint. */
public class WrappedHandler implements Route {
    private final ServerInfo serverInfo;

    /**
     * Constructor for a WipeDataHandler object.
     *
     * @param serverInfo - ServerInfo object that holds the currently loaded usercsv
     */
    public WrappedHandler(ServerInfo serverInfo) {
        this.serverInfo = serverInfo;
    }

    @Override
    public Object handle(Request request, Response response) {
        QueryParamsMap queryMap = request.queryMap();
        HashMap<String, String> errorMessages = new HashMap<>();

        if (queryMap.toMap().size() != 0) {
            errorMessages.put("error_bad_json", "expected 0 query parameters but received" + queryMap.toMap().size());
            return serialize(fail(errorMessages));
        }

        try {
            if (serverInfo.getUserData() ==  null) {
                errorMessages.put("error_bad_request", "no user csv in server to create wrapped with");
                return serialize(fail(errorMessages));
            }
            //  1. we use the generator in server info to generate the report.
            Data.WrappedData reportJSON = serverInfo.generateReportJSON();
            //  2. we send this report into the frontend.
            return serialize(success(reportJSON));


        } catch (Exception e) {
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
    public Map<String, Object> success(Data.WrappedData reportJSON) {
        Map<String, Object> successful = new HashMap<>();
        successful.put("result", "success");
        successful.put("report", reportJSON);
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
                Types.newParameterizedType(Map.class, String.class, Object.class, Data.WrappedData.class);
        JsonAdapter<Map<String, Object>> adapter = moshi.adapter(mapOfJSONResponse);
        return adapter.toJson(response);
    }
}
