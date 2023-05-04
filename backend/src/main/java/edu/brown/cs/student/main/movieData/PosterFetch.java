package edu.brown.cs.student.main.movieData;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import edu.brown.cs.student.main.Token.PrivateToken;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import java.net.HttpURLConnection;
import okio.Buffer;

public class PosterFetch {
    public String getPosterLink(String title){
        PosterData.posterJson posterJson;
        try {
            PrivateToken privateToken = new PrivateToken();
            String userToken = privateToken.token;
            String filteredTitle = title.replace(" ", "-");

            
            posterJson = requestFromAPI(
            "https://api.themoviedb.org/3/search/multi?api_key=" 
            + userToken
            + "&query="
            + filteredTitle +
            "&page=1", 
            PosterData.posterJson.class);
            if (posterJson.results().length != 0){
                return "https://image.tmdb.org/t/p/original/" + posterJson.results()[0].poster_path();
            }
            else{
                return "https://i.imgur.com/mgzENYv.jpg";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "https://i.imgur.com/mgzENYv.jpg";
        }
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

    /**
     * Establish a connection with a url
     * @param url url to be connected
     * @return connection
     * @throws IOException thrown in case accessing url's connection has errors
     */
    static private HttpURLConnection tryRequest(String url) throws IOException {
        // Configure the connection (but don't actually send the request yet)
        URL requestURL = new URL(url);
        HttpURLConnection clientConnection = (HttpURLConnection) requestURL.openConnection();


        clientConnection.connect();
        return clientConnection;
    }
    

}
