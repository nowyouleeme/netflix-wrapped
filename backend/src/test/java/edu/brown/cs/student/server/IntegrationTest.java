package edu.brown.cs.student.server;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import edu.brown.cs.student.main.csv.data.Data;
import edu.brown.cs.student.main.reports.MLGenerator;
import edu.brown.cs.student.main.reports.MockRGenerator;
import edu.brown.cs.student.main.server.ServerInfo;
import edu.brown.cs.student.main.server.handlers.SaveDataHandler;
import edu.brown.cs.student.main.server.handlers.WipeDataHandler;
import edu.brown.cs.student.main.server.handlers.WrappedHandler;
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
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

/**
 * class to hold integration tests for endpoints (saveData, wrapped, wipeData)
 */
public class IntegrationTest {
    /** Method that sets up the port and logger at the start of the testing suite execution. */
    @BeforeAll
    public static void beforeAll() {
        Spark.port(0);
        Logger.getLogger("").setLevel(Level.WARNING);
    }

    ServerInfo serverInfo;

    /**
     * Method that initializes all the global variables and sets up the route mapping for the endpoints
     * before every test.
     */
    @BeforeEach
    public void setup() {
        MLGenerator mlGenerator = new MLGenerator();
        serverInfo = new ServerInfo(mlGenerator);
        // In fact, restart the entire Spark server for every test!
        Spark.post("/saveData", new SaveDataHandler(serverInfo));
        Spark.get("/wrapped", new WrappedHandler(serverInfo));
        Spark.get("/wipeData", new WipeDataHandler(serverInfo));
        Spark.init();
        Spark.awaitInitialization(); // don't continue until the server is listening
    }

    /** The method that unmaps the routes for each endpoint and stops the server gracefully. */
    @AfterEach
    public void teardown() {
        // Gracefully stop Spark listening on both endpoints
        Spark.unmap("/saveData");
        Spark.unmap("/wrapped");
        Spark.unmap("/wipeData");
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
    private static HttpURLConnection tryGetRequest(String apiCall) throws IOException {
        // Configure the connection (but don't actually send the request yet)
        URL requestURL = new URL("http://localhost:" + Spark.port() + "/" + apiCall);
        HttpURLConnection clientConnection = (HttpURLConnection) requestURL.openConnection();

        clientConnection.connect();
        return clientConnection;
    }

    /**
     * Method that starts a connection to a given API endpoint
     *
     * @param apiCall the request being made
     * @param requestBody0 the body of the post request
     * @return the connection for the given URL, just after connecting
     * @throws IOException when the connection fails or cannot be made
     */
    private static HttpURLConnection tryPostRequest(String apiCall, String requestBody0) throws IOException {
        String requestBody = requestBody0.substring(1, requestBody0.length() - 1).replace("\\", "");

        // Configure the connection (but don't actually send the request yet)
        URL requestURL = new URL("http://localhost:" + Spark.port() + "/" + apiCall);
        HttpURLConnection clientConnection = (HttpURLConnection) requestURL.openConnection();
        clientConnection.setRequestMethod("POST");
        clientConnection.setRequestProperty("Content-Type", "application/json");
        clientConnection.setRequestProperty("Accept", "application/json");
        clientConnection.setDoOutput(true);
        OutputStream outputStream = clientConnection.getOutputStream();
        byte[] input = requestBody.getBytes("utf-8");
        outputStream.write(input, 0, input.length);
//        outputStream.write(requestBody.getBytes());
        outputStream.flush();
        outputStream.close();


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
                moshi.adapter(Types.newParameterizedType(Map.class, String.class, Object.class, Data.WrappedData.class));

        Map<String, Object> resp = adapter.fromJson(new Buffer().readFrom(clientConnection.getInputStream()));
//        System.out.print(resp.toString());
        return resp;
    }

    public WrappedResp clientConnectToWrappedResp(HttpURLConnection clientConnection) throws IOException {
        Moshi moshi = new Moshi.Builder().build();
        JsonAdapter<WrappedResp> adapter = moshi.adapter(WrappedResp.class);

        WrappedResp resp = adapter.fromJson(new Buffer().readFrom(clientConnection.getInputStream()));
//        System.out.print(resp.toString());
        return resp;
    }


    /** tests wipeData Handler integrates with save data and wrapped generating endpoints
     */
    @Test
    public void testWipeSuccess() throws IOException {
        //test that save (correctly) -> wipe works
        assertNull(serverInfo.getUserData());
        String query = "{{{\"usercsv\":[[\"Title\",\"Date\"],[\"Crash%20Landing%20on%20You:%20Episode16\",\"3/26/23\"]]}}}";
        HttpURLConnection clientConnection = tryPostRequest("saveData", query);
        assertEquals(200, clientConnection.getResponseCode());
        assertNotNull(serverInfo.getUserData());
        clientConnection = tryGetRequest("wipeData");
        assertEquals(200, clientConnection.getResponseCode());
        Map resp = clientConnectToMap(clientConnection);
        assertEquals("success", resp.get("result"));
        assertNull(serverInfo.getUserData());

        //tests that save (incorrectly) -> wipe also works
        assertNull(serverInfo.getUserData());
        query = "{{{\"usercsv\":[[\"Title\"],[\"Crash%20Landing%20on%20You:%20Episode16\",\"3/26/23\"]]}}}";
        clientConnection = tryPostRequest("saveData", query);
        assertEquals(200, clientConnection.getResponseCode());
        assertNull(serverInfo.getUserData());
        clientConnection = tryGetRequest("wipeData");
        assertEquals(200, clientConnection.getResponseCode());
        resp = clientConnectToMap(clientConnection);
        assertEquals("success", resp.get("result"));
        assertNull(serverInfo.getUserData());

        //tests that save (correctly) -> generate report -> wipe also works
        assertNull(serverInfo.getUserData());
        query = "{{{\"usercsv\":[[\"Title\",\"Date\"],[\"Crash%20Landing%20on%20You:%20Episode16\",\"3/26/23\"]]}}}";
        clientConnection = tryPostRequest("saveData", query);
        assertEquals(200, clientConnection.getResponseCode());
        assertNotNull(serverInfo.getUserData());
        assertEquals(2, serverInfo.getUserData().usercsv().length);
        clientConnection = tryGetRequest("wrapped");
        assertEquals(200, clientConnection.getResponseCode());
        assertNotNull(serverInfo.getUserData());
        clientConnection = tryGetRequest("wipeData");
        assertEquals(200, clientConnection.getResponseCode());
        resp = clientConnectToMap(clientConnection);
        assertEquals("success", resp.get("result"));
        assertNull(serverInfo.getUserData());

        clientConnection.disconnect();
    }

    /** tests wrapped Handler integrates with save data and wipe data endpoints
     */
    @Test
    public void testWrapped() throws IOException {
        //test that save (with not enough info..) -> generate works
        assertNull(serverInfo.getUserData());
        String query = "{{{\"usercsv\":[[\"Title\",\"Date\"],[\"Crash%20Landing%20on%20You:%20Episode16\",\"3/26/23\"]]}}}";
        HttpURLConnection clientConnection = tryPostRequest("saveData", query);
        assertEquals(200, clientConnection.getResponseCode());
        assertNotNull(serverInfo.getUserData());

        clientConnection = tryGetRequest("wrapped");
        assertEquals(200, clientConnection.getResponseCode());
        WrappedResp resp = clientConnectToWrappedResp(clientConnection);
        assertEquals("success", resp.result());
        assertNotNull(resp.report());
        System.out.println("This is result");
        System.out.println(resp.report());
        assertTrue(resp.report().noNullInfo());

        //test that save (with new set of info that's enough info..) -> generate works.
        MockUserCSVQuery m = new MockUserCSVQuery();
        query = m.mock1;
        clientConnection = tryPostRequest("saveData", query);
        assertEquals(200, clientConnection.getResponseCode());
        assertNotNull(serverInfo.getUserData());

        clientConnection = tryGetRequest("wrapped");
        assertEquals(200, clientConnection.getResponseCode());
        resp = clientConnectToWrappedResp(clientConnection);
        assertEquals("success", resp.result());
        assertNotNull(resp.report());
        assertTrue(resp.report().noNullInfo());

        //tests that wiping and then generating lead to an error
        clientConnection = tryGetRequest("wipeData");
        assertEquals(200, clientConnection.getResponseCode());
        Map respMap = clientConnectToMap(clientConnection);
        assertEquals("success", resp.result());
        assertNull(serverInfo.getUserData());
        clientConnection = tryGetRequest("wrapped");
        assertEquals(200, clientConnection.getResponseCode());
        respMap = clientConnectToMap(clientConnection);
        if (respMap.get("result") instanceof Map result) {
            assertEquals("no user csv in server to create wrapped with", result.get("error_bad_request"));
        }


        clientConnection.disconnect();
    }





}