package edu.brown.cs.student.server;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import edu.brown.cs.student.main.reports.MockRGenerator;
import edu.brown.cs.student.main.reports.ReportGenerator;
import edu.brown.cs.student.main.server.ServerInfo;
import edu.brown.cs.student.main.server.handlers.SaveDataHandler;
import okio.Buffer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spark.Spark;



import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * class to test save data endpoint
 */
public class SaveDataHandlerTest {
    /** Method that sets up the port and logger at the start of the testing suite execution. */
    @BeforeAll
    public static void beforeAll() {
        Spark.port(0);
        Logger.getLogger("").setLevel(Level.WARNING);
    }

    /**
     * Method that initalizes all the global variables and sets up the route mapping for the endpoints
     * before every test.
     */
    @BeforeEach
    public void setup() {
        MockRGenerator mockGenerator = new MockRGenerator();
        ServerInfo serverInfo = new ServerInfo(mockGenerator);
        // In fact, restart the entire Spark server for every test!
        Spark.post("/saveData", new SaveDataHandler(serverInfo));
        Spark.init();
        Spark.awaitInitialization(); // don't continue until the server is listening
    }

    /** The method that unmaps the routes for each endpoint and stops the server gracefully. */
    @AfterEach
    public void teardown() {
        // Gracefully stop Spark listening on both endpoints
        Spark.unmap("/saveData");
        Spark.stop();
        Spark.awaitStop(); // don't proceed until the server is stopped
    }

    /**
     * Method that starts a connection to a given API endpoint
     *
     * @param apiCall the request being made
     * @return the connection for the given URL, just after connecting
     * @throws IOException when the connection fails or cannot be made
     */
    private static HttpURLConnection tryRequest(String apiCall, String requestBody0) throws IOException {
        String requestBody = requestBody0.substring(1, requestBody0.length() - 1).replace("\\", "");

        // Configure the connection (but don't actually send the request yet)
        URL requestURL = new URL("http://localhost:" + Spark.port() + "/" + apiCall);
        HttpURLConnection clientConnection = (HttpURLConnection) requestURL.openConnection();
        System.out.println("bruh moment1");
        clientConnection.setRequestMethod("POST");
        clientConnection.setRequestProperty("Content-Type", "application/json");
        clientConnection.setRequestProperty("Accept", "application/json");
        System.out.println("bruh moment2");
        clientConnection.setDoOutput(true);
        OutputStream outputStream = clientConnection.getOutputStream();
        byte[] input = requestBody.getBytes("utf-8");
        outputStream.write(input, 0, input.length);
//        outputStream.write(requestBody.getBytes());
        outputStream.flush();
        outputStream.close();
        System.out.println("bruh moment3");


        clientConnection.connect();
        return clientConnection;
    }

    /**
     * turns http url connection intoa map representing the contents of the http url connection
     * @param clientConnection http url connection to convert
     * @return map representing the contents of the http url connection
     * @throws IOException
     */
    public Map clientConnectToMap(HttpURLConnection clientConnection) throws IOException {
        Moshi moshi = new Moshi.Builder().build();
        JsonAdapter<Map<String, Object>> adapter =
                moshi.adapter(Types.newParameterizedType(Map.class, String.class, Object.class));

        Map<String, Object> resp = adapter.fromJson(new Buffer().readFrom(clientConnection.getInputStream()));
//        System.out.print(resp.toString());
        return resp;
    }


    /** tests saveData Handler without providing saveData returns bad json response */
    @Test
    public void testBadJSON() throws IOException {
        //test for no parameters.
            //get client connection
        HttpURLConnection clientConnection = tryRequest("saveData", "{{{}}}");
        assertEquals(200, clientConnection.getResponseCode());
            //get response
        Map resp = clientConnectToMap(clientConnection);
        assertTrue(resp.get("result") instanceof Map);
        if (resp.get("result") instanceof Map result) {
            assertEquals("usercsv not provided", result.get("error_bad_json"));
        }


        //test for wrong queryParam name (diff word)
        String query = "{{{\"bahaha\":[[\"Title\",\"Date\"],[\"Crash%20Landing%20on%20You:%20Episode16\",\"3/26/23\"]]}}}";
        clientConnection = tryRequest("saveData", query);
        assertEquals(200, clientConnection.getResponseCode());

        resp = clientConnectToMap(clientConnection);
        assertTrue(resp.get("result") instanceof Map);
        if (resp.get("result") instanceof Map result) {
            assertEquals("usercsv not provided", result.get("error_bad_json"));
        }

        //test for wrong queryParam name (diff capitalisation)
        query = "{{{\"userCSV\":[[\"Title\",\"Date\"],[\"Crash%20Landing%20on%20You:%20Episode16\",\"3/26/23\"]]}}}";
        clientConnection = tryRequest("saveData", query);
        assertEquals(200, clientConnection.getResponseCode());

        resp = clientConnectToMap(clientConnection);
        assertTrue(resp.get("result") instanceof Map);
        if (resp.get("result") instanceof Map result) {
            assertEquals("usercsv not provided", result.get("error_bad_json"));
        }


        clientConnection.disconnect();
    }



    /** tests saveDataHandler without providing saveData returns bad request response */
    @Test
    public void testBadRequest() throws IOException {
        //test for too few rows [[title, date]]
        String query = "{{{\"usercsv\":[[\"Title\",\"Date\"]]}}}";
        HttpURLConnection clientConnection = tryRequest("saveData", query);
        assertEquals(200, clientConnection.getResponseCode());

        Map resp = clientConnectToMap(clientConnection);
        assertTrue(resp.get("result") instanceof Map);
        if (resp.get("result") instanceof Map result) {
            assertEquals("invalid netflix history csv", result.get("error_bad_request"));
        }

        //test for too few rows [[]]
        query = "{{{\"usercsv\":[[]]}}}";
        clientConnection = tryRequest("saveData", query);
        assertEquals(200, clientConnection.getResponseCode());

        resp = clientConnectToMap(clientConnection);
        assertTrue(resp.get("result") instanceof Map);
        if (resp.get("result") instanceof Map result) {
            assertEquals("invalid netflix history csv", result.get("error_bad_request"));
        }

        //test for 1 column
        query = "{{{\"usercsv\":[[\"Title\"],[\"Crash%20Landing%20on%20You:%20Episode16\"]," +
                "[\"Crash%20Landing%20on%20You:%20Episode15\"]]}}}";
        clientConnection = tryRequest("saveData", query);
        assertEquals(200, clientConnection.getResponseCode());

        resp = clientConnectToMap(clientConnection);
        assertTrue(resp.get("result") instanceof Map);
        if (resp.get("result") instanceof Map result) {
            assertEquals("invalid netflix history csv", result.get("error_bad_request"));
        }

        //test for 3 columns
        query = "{{{\"usercsv\":[[\"Title\",\"Date\",\"Director\"]," +
                "[\"Crash%20Landing%20on%20You:%20Episode16\",\"3/26/23\",\"Lee%20Jeong-hyo\"]]}}}";
        clientConnection = tryRequest("saveData", query);
        assertEquals(200, clientConnection.getResponseCode());

        resp = clientConnectToMap(clientConnection);
        assertTrue(resp.get("result") instanceof Map);
        if (resp.get("result") instanceof Map result) {
            assertEquals("invalid netflix history csv", result.get("error_bad_request"));
        }

        //test for 5 columns
        query = "{{{\"usercsv\":[[\"Title\",\"Date\"],[\"Crash%20Landing%20on%20You:%20Episode16\",\"3/26/23\"]," +
                "[\"Title\",\"Date\",\"Title\",\"Date\",\"Title\"]]}}}";
        clientConnection = tryRequest("saveData", query);
        assertEquals(200, clientConnection.getResponseCode());

        resp = clientConnectToMap(clientConnection);
        assertTrue(resp.get("result") instanceof Map);
        if (resp.get("result") instanceof Map result) {
            assertEquals("invalid netflix history csv", result.get("error_bad_request"));
        }

        //test for not title date
        query = "{{{\"usercsv\":[[\"Title\",\"Time\"],[\"Crash%20Landing%20on%20You:%20Episode16\",\"3/26/23\"]," +
                "[\"Crash%20Landing%20on%20You:%20Episode15\",\"3/26/23\"]," +
                "[\"Crash%20Landing%20on%20You:%20Episode14\",\"3/26/23\"]]}}}";
        clientConnection = tryRequest("saveData", query);
        assertEquals(200, clientConnection.getResponseCode());

        resp = clientConnectToMap(clientConnection);
        assertTrue(resp.get("result") instanceof Map);
        if (resp.get("result") instanceof Map result) {
            assertEquals("invalid netflix history csv", result.get("error_bad_request"));
        }

        //test for not title date
        query = "{{{\"usercsv\":[[\"Media\",\"Time\"],[\"Crash%20Landing%20on%20You:%20Episode16\",\"3/26/23\"]," +
                "[\"Crash%20Landing%20on%20You:%20Episode15\",\"3/26/23\"]," +
                "[\"Crash%20Landing%20on%20You:%20Episode14\",\"3/26/23\"]]}}}";
        clientConnection = tryRequest("saveData", query);
        assertEquals(200, clientConnection.getResponseCode());

        resp = clientConnectToMap(clientConnection);
        assertTrue(resp.get("result") instanceof Map);
        if (resp.get("result") instanceof Map result) {
            assertEquals("invalid netflix history csv", result.get("error_bad_request"));
        }


        clientConnection.disconnect();
    }


    /** tests saveData Handler without providing saveData returns bad request response */
    @Test
    public void testSuccess() throws IOException {

        //test that a 2row csv is successful
        String query = "{{{\"usercsv\":[[\"Title\",\"Date\"],[\"Crash%20Landing%20on%20You:%20Episode16\",\"3/26/23\"]]}}}";
        HttpURLConnection clientConnection = tryRequest("saveData", query);
        assertEquals(200, clientConnection.getResponseCode());

        Map resp = clientConnectToMap(clientConnection);
        assertEquals("success", resp.get("result"));

        //tests that a 3row csv is successful
        query = "{{{\"usercsv\":[[\"Title\",\"Date\"],[\"Crash%20Landing%20on%20You:%20Episode16\",\"3/26/23\"]," +
                "[\"One%20More%20Time:%20Episode%201\",\"3/18/2020\"]]}}}";
        clientConnection = tryRequest("saveData", query);
        assertEquals(200, clientConnection.getResponseCode());

        resp = clientConnectToMap(clientConnection);
        assertEquals("success", resp.get("result"));

        //tests that a 4row csv is successful
        query = "{{{\"usercsv\":[[\"Title\",\"Date\"],[\"Crash%20Landing%20on%20You:%20Episode16\",\"3/26/23\"]," +
                "[\"One%20More%20Time:%20Episode%201\",\"3/18/2020\"]," +
                "[\"The%20Garden%20of%20Words\",\"3/18/2020\"]]}}}";
        clientConnection = tryRequest("saveData", query);
        assertEquals(200, clientConnection.getResponseCode());

        resp = clientConnectToMap(clientConnection);
        assertEquals("success", resp.get("result"));

        //tests that a 10row csv is successful
        query = "{{{\"usercsv\":[[\"Title\",\"Date\"]," +
                "[\"Crash%20Landing%20on%20You:%20Episode16\",\"3/26/23\"]," +
                "[\"Crash%20Landing%20on%20You:%20Episode15\",\"3/26/23\"]," +
                "[\"Crash%20Landing%20on%20You:%20Episode14\",\"3/26/23\"]," +
                "[\"Crash%20Landing%20on%20You:%20Episode13\",\"3/26/23\"]," +
                "[\"Crash%20Landing%20on%20You:%20Episode12\",\"3/26/23\"]," +
                "[\"Crash%20Landing%20on%20You:%20Episode11\",\"3/26/23\"]," +
                "[\"Crash%20Landing%20on%20You:%20Episode10\",\"3/26/23\"]," +
                "[\"One%20More%20Time:%20Episode%201\",\"3/18/2020\"]," +
                "[\"The%20Garden%20of%20Words\",\"3/18/2020\"]]}}}";
        clientConnection = tryRequest("saveData", query);
        assertEquals(200, clientConnection.getResponseCode());

        resp = clientConnectToMap(clientConnection);
        assertEquals("success", resp.get("result"));

        //test success for extra queryParam name
        query = "{{{\"usercsv\":[[\"Title\",\"Date\"],[\"Crash%20Landing%20on%20You:%20Episode16\",\"3/26/23\"]]," +
                "\"userhistory\":[[]]}}}";
        clientConnection = tryRequest("saveData", query);
        assertEquals(200, clientConnection.getResponseCode());

        resp = clientConnectToMap(clientConnection);
        assertEquals("success", resp.get("result"));


        clientConnection.disconnect();
    }




}
