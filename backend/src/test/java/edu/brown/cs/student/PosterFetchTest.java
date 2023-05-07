package edu.brown.cs.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.junit.jupiter.api.Test;

import edu.brown.cs.student.main.PosterImageGetting.PosterFetch;
import edu.brown.cs.student.main.components.helpers.MapCreator;

public class PosterFetchTest {

    
     @Test
    public void testFileWorking() throws IOException {
        assertEquals(1+1, 2);
    }

    @Test
    public void fuzzTestPosterFetchRandom() throws IOException {
        FuzzTestingHelpers fuzzHelper = new FuzzTestingHelpers();
        PosterFetch posterFetch = new PosterFetch();
        for(int i = 0; i < 100; i++){

            String randomTitle = fuzzHelper.generateRandomString(20);
            String posterLink = posterFetch.getPosterLink(randomTitle);

            Integer linkLength = posterLink.length();
            //length of imgur link that outputs when no movie found.
            assertEquals(linkLength, 31);
        }
    }

    @Test
    public void fuzzTestPosterFetchFromList() throws IOException {
        PosterFetch posterFetch = new PosterFetch();
        MapCreator mapCreator = new MapCreator();
        Map<String, ArrayList<ArrayList<String>>> movieMap = mapCreator.createMovieMap();
        ArrayList<String> titleList = new ArrayList<String>();

        //fill titlelist
        for (String element : movieMap.keySet()){
            titleList.add(element);
        }


        for(int i = 0; i < 100; i++){
            Integer randomIndex = FuzzTestingHelpers.rng(0, titleList.size()-1);
            String randomTitle = titleList.get(randomIndex);
            String posterLink = posterFetch.getPosterLink(randomTitle);
            Integer linkLength = posterLink.length();
            if(posterLink != "https://i.imgur.com/mgzENYv.jpg"){
                assertEquals(posterLink.substring(0, 23), "https://image.tmdb.org/");
                assertTrue(posterLink != "https://image.tmdb.org/t/p/original/null");
            }
        }
    }

    @Test
    public void posterTestBugNullPosterPath() throws IOException {
        FuzzTestingHelpers fuzzHelper = new FuzzTestingHelpers();
        PosterFetch posterFetch = new PosterFetch();

            String randomTitle = "Age gap love";
            String posterLink = posterFetch.getPosterLink(randomTitle);

            //length of imgur link that outputs when no movie found.
            assertEquals(posterLink, "https://i.imgur.com/mgzENYv.jpg");
    }
    
}
