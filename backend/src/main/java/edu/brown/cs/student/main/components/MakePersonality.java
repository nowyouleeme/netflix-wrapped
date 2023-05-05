package edu.brown.cs.student.main.components;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

import com.squareup.moshi.Moshi;

import edu.brown.cs.student.main.components.helpers.MapCreator;
import okio.Buffer;

public class MakePersonality {
    public MakePersonality(){

    }

    public String getPersonality(String[][] userHistory,
    ArrayList<Map<String, ArrayList<ArrayList<String>>>> userHistoryMapList){

        String posterJson = "";
        try {
            posterJson = requestFromAPI("http://127.0.0.1:5000/filepath/", String.class);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return posterJson;
    }

    public <T> T requestFromAPI(String url, Class<T> targetClass) throws IOException {
        HttpURLConnection connection = tryRequest(url);
        T apiResponse = null;
        if (connection.getResponseCode() == 200) {
            Moshi moshi = new Moshi.Builder().build();
            try (Buffer buffer = new Buffer()) {
                apiResponse = moshi.adapter(targetClass).fromJson(buffer.readFrom(connection.getInputStream()));
            }
        }
        connection.disconnect();
        return apiResponse;
    }
        static private HttpURLConnection tryRequest(String url) throws IOException {
        // Configure the connection (but don't actually send the request yet)
        URL requestURL = new URL(url);
        HttpURLConnection clientConnection = (HttpURLConnection) requestURL.openConnection();


        clientConnection.connect();
        return clientConnection;
    }
}
