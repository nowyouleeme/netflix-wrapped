package edu.brown.cs.student.server;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import edu.brown.cs.student.main.reports.MockRGenerator;
import edu.brown.cs.student.main.server.ServerInfo;
import edu.brown.cs.student.main.server.handlers.SaveDataHandler;
import edu.brown.cs.student.main.server.handlers.WipeDataHandler;
import okio.Buffer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spark.Spark;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

public class WipeDataHandlerTest {
    /** Method that sets up the port and logger at the start of the testing suite execution. */
    @BeforeAll
    public static void beforeAll() {
        Spark.port(0);
        Logger.getLogger("").setLevel(Level.WARNING);
    }

    ServerInfo serverInfo;

    /**
     * Method that initalizes all the global variables and sets up the route mapping for the endpoints
     * before every test.
     */
    @BeforeEach
    public void setup() {
        MockRGenerator mockGenerator = new MockRGenerator();
        serverInfo = new ServerInfo(mockGenerator);
        // In fact, restart the entire Spark server for every test!
        Spark.get("/saveData", new SaveDataHandler(serverInfo));
        Spark.get("/wrapped", new WipeDataHandler(serverInfo));
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
    private static HttpURLConnection tryRequest(String apiCall) throws IOException {
        // Configure the connection (but don't actually send the request yet)
        URL requestURL = new URL("http://localhost:" + Spark.port() + "/" + apiCall);
        HttpURLConnection clientConnection = (HttpURLConnection) requestURL.openConnection();

        clientConnection.connect();
        return clientConnection;
    }

    public Map clientConnectToMap(HttpURLConnection clientConnection) throws IOException {
        Moshi moshi = new Moshi.Builder().build();
        JsonAdapter<Map<String, Object>> adapter =
                moshi.adapter(Types.newParameterizedType(Map.class, String.class, Object.class));

        Map<String, Object> resp = adapter.fromJson(new Buffer().readFrom(clientConnection.getInputStream()));
//        System.out.print(resp.toString());
        return resp;
    }


    /** tests wipeData Handler without providing wipeData returns bad json response */
    @Test
    public void testBadJSON() throws IOException {
        //test for > 0 parameters. (1)
        HttpURLConnection clientConnection = tryRequest("wipeData?userName=karen");
        assertEquals(200, clientConnection.getResponseCode());
        Map resp = clientConnectToMap(clientConnection);
        assertTrue(resp.get("result") instanceof Map);
        if (resp.get("result") instanceof Map result) {
            assertEquals("expected 0 query parameters but received 1", result.get("error_bad_json"));
        }

        //test for too many parameters. (2)
        clientConnection = tryRequest("wipeData?userName=karen&password=123");
        assertEquals(200, clientConnection.getResponseCode());

        resp = clientConnectToMap(clientConnection);
        assertTrue(resp.get("result") instanceof Map);
        if (resp.get("result") instanceof Map result) {
            assertEquals("expected 0 query parameters but received 2", result.get("error_bad_json"));
        }

        clientConnection.disconnect();
    }


    /** tests wipeData Handler without providing wipeData returns bad request response,
     * includes integration tests dealing with wiping data.
     */
    @Test
    public void testSuccess() throws IOException {
        //test that wipe works
        assertNull(serverInfo.getUserData());
        HttpURLConnection clientConnection = tryRequest("wipeData");
        assertEquals(200, clientConnection.getResponseCode());
        Map resp = clientConnectToMap(clientConnection);
        assertEquals("success", resp.get("result"));
        assertNull(serverInfo.getUserData());

        //test that save (correctly) -> wipe works
        assertNull(serverInfo.getUserData());
        String query = "?usercsv={\"usercsv\":[[\"Title\",\"Date\"],[\"Crash%20Landing%20on%20You:%20Episode16\",\"3/26/23\"]]}";
        clientConnection = tryRequest("saveData" + query);
        assertEquals(200, clientConnection.getResponseCode());
        assertNotNull(serverInfo.getUserData());
        clientConnection = tryRequest("wipeData");
        assertEquals(200, clientConnection.getResponseCode());
        resp = clientConnectToMap(clientConnection);
        assertEquals("success", resp.get("result"));
        assertNull(serverInfo.getUserData());

        //tests that save (incorrectly) -> wipe also works
        assertNull(serverInfo.getUserData());
        query = "?usercsv={\"usercsv\":[[\"Title\"],[\"Crash%20Landing%20on%20You:%20Episode16\",\"3/26/23\"]]}";
        clientConnection = tryRequest("saveData" + query);
        assertEquals(200, clientConnection.getResponseCode());
        assertNull(serverInfo.getUserData());
        clientConnection = tryRequest("wipeData");
        assertEquals(200, clientConnection.getResponseCode());
        resp = clientConnectToMap(clientConnection);
        assertEquals("success", resp.get("result"));
        assertNull(serverInfo.getUserData());

        //tests that save (correctly) -> generate report -> wipe also works
        assertNull(serverInfo.getUserData());
        query = "?usercsv={\"usercsv\":[[\"Title\",\"Date\"],[\"Crash%20Landing%20on%20You:%20Episode16\",\"3/26/23\"]]}";
        clientConnection = tryRequest("saveData" + query);
        assertEquals(200, clientConnection.getResponseCode());
        assertNotNull(serverInfo.getUserData());
        clientConnection = tryRequest("wrapped" + query);
        assertEquals(200, clientConnection.getResponseCode());
        assertNotNull(serverInfo.getUserData());
        clientConnection = tryRequest("wipeData");
        assertEquals(200, clientConnection.getResponseCode());
        resp = clientConnectToMap(clientConnection);
        assertEquals("success", resp.get("result"));
        assertNull(serverInfo.getUserData());



        clientConnection.disconnect();
    }




}
