package edu.brown.cs.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.junit.jupiter.api.Test;

import edu.brown.cs.student.main.components.MakeMovieSection;
import edu.brown.cs.student.main.components.helpers.MapCreator;

public class MovieSectionTest {

        /**
     * Basic test to see if the test in this file are working
     */
    @Test
    public void testFileWorking() throws IOException {
        assertEquals(1 + 1, 2);
    }


    /**
     * Basic test to see if the filter for the moviesOnly is working
     */
    @Test
    public void testMovieOnly() {
        String[][] movieData1 = {
                { "Valentine's Day", "2/14/21" },
                { "akljr", "0/21/21" },
                { "Elizabeth at 90: A Family Tribute", "0/21/21" },
                { "Squid Game: level2", "0/14/21" },
                { "asflnv", "2/14/21" },
        };
        MapCreator mapCreator = new MapCreator();
        MakeMovieSection movieSection = new MakeMovieSection();
        ArrayList<Map<String, ArrayList<ArrayList<String>>>> movieList = movieSection
        .movieOnly(mapCreator.createWatchedMediaMap(movieData1));
        
        assertEquals(movieList.size(), 2);
        assertTrue(movieList.get(1).keySet().contains("Elizabeth at 90: A Family Tribute"));
        assertTrue(movieList.get(0).keySet().contains("Valentine's Day"));

    }


    /**
     * Integration test to see if getting the top five movies watched works, using helpers.
     * Checks if shows are being filtered.
     */
    @Test
    public void testTopFiveMovies() {
        String[][] movieData1 = {
                { "Valentine's Day", "2/14/21" },
                { "Valentine's Day", "2/14/21" },
                { "Valentine's Day", "2/14/21" },
                { "Valentine's Day", "2/14/21" },
                { "Valentine's Day", "2/14/21" },
                { "akljr", "0/21/21" },
                { "Elizabeth at 90: A Family Tribute", "0/21/21" },
                { "Elizabeth at 90: A Family Tribute", "0/21/21" },
                { "Elizabeth at 90: A Family Tribute", "0/21/21" },
                { "Squid Game: level2", "0/14/21" },
                { "asflnv", "2/14/21" },
                { "Squid Game: level2", "0/14/21" },
                { "The Golden Child", "10/21/21" },
                { "The Golden Child", "10/21/21" },
                { "The Guns of Navarone", "10/21/21" },
                { "The Guns of Navarone", "13/21/21" },
                { "The Guns of Navarone", "13/21/21" },
                { "Superbad", "13/21/21" },
                { "Superbad", "13/21/21" },
                { "Valentine's Day", "2/14/21" },
                { "Superman Returns", "2/14/21" },
        };
        MapCreator mapCreator = new MapCreator();
        MakeMovieSection movieSection = new MakeMovieSection();
        ArrayList<Map<String, ArrayList<ArrayList<String>>>> movieList = movieSection
        .movieOnly(mapCreator.createWatchedMediaMap(movieData1));
        ArrayList<String> topFive = movieSection.getTopFiveMovies(movieList);
        
        assertEquals(topFive.size(), 5);
        assertEquals(topFive.get(0), ("Valentine's Day"));
        assertEquals(topFive.get(1), ("Elizabeth at 90: A Family Tribute"));
        assertEquals(topFive.get(2), ("The Guns of Navarone"));
        assertEquals(topFive.get(3), ("The Golden Child"));
        assertEquals(topFive.get(4), ("Superbad"));
    }

    /**
     * Integration test to see if getting the top five movies watched works, 
     * even if there is less than five variety.
     * Checks if shows are being filtered.
     */
    @Test
    public void testTopFiveMoviesLessThanFive() {
        String[][] movieData1 = {
                { "Valentine's Day", "2/14/21" },
                { "Valentine's Day", "2/14/21" },
                { "Valentine's Day", "2/14/21" },
                { "Valentine's Day", "2/14/21" },
                { "Valentine's Day", "2/14/21" },
                { "akljr", "0/21/21" },
                { "Elizabeth at 90: A Family Tribute", "0/21/21" },
                { "Elizabeth at 90: A Family Tribute", "0/21/21" },
                { "Elizabeth at 90: A Family Tribute", "0/21/21" },
                { "Squid Game: level2", "0/14/21" },
                { "asflnv", "2/14/21" },
                { "Squid Game: level2", "0/14/21" },
                { "The Golden Child", "10/21/21" },
                { "The Golden Child", "10/21/21" },
                { "The Guns of Navarone", "10/21/21" },
                { "The Guns of Navarone", "13/21/21" },
                { "The Guns of Navarone", "13/21/21" },
                { "Valentine's Day", "2/14/21" }
        };
        MapCreator mapCreator = new MapCreator();
        MakeMovieSection movieSection = new MakeMovieSection();
        ArrayList<Map<String, ArrayList<ArrayList<String>>>> movieList = movieSection
        .movieOnly(mapCreator.createWatchedMediaMap(movieData1));
        ArrayList<String> topFive = movieSection.getTopFiveMovies(movieList);
        
        assertEquals(topFive.size(), 4);
        assertEquals(topFive.get(0), ("Valentine's Day"));
        assertEquals(topFive.get(1), ("Elizabeth at 90: A Family Tribute"));
        assertEquals(topFive.get(2), ("The Guns of Navarone"));
        assertEquals(topFive.get(3), ("The Golden Child"));
    }




    /**
     * Integration test to see if getting the top five movies watched works, even if the only movie
     * in there is a movie not in data.
     * Checks if shows are being filtered.
     */
    @Test
    public void testTopFiveMoviesEmpty() {
        String[][] movieData1 = {
                { "asfoh", "2/14/21" }
        };
        MapCreator mapCreator = new MapCreator();
        MakeMovieSection movieSection = new MakeMovieSection();
        ArrayList<Map<String, ArrayList<ArrayList<String>>>> movieList = movieSection
        .movieOnly(mapCreator.createWatchedMediaMap(movieData1));
        ArrayList<String> topFive = movieSection.getTopFiveMovies(movieList);
        
        assertEquals(topFive.size(), 0);
    }


    /**
     * Integration test for checking for the top actors and movies they are in, using a variety of movies
     */
    @Test
    public void topActorsAndMoviesTest(){
        String[][] movieData1 = {
            { "Valentine's Day", "2/14/21" },
            { "Valentine's Day", "2/14/21" },
            { "Valentine's Day", "2/14/21" },
            { "akljr", "0/21/21" },
            { "Elizabeth at 90: A Family Tribute", "0/21/21" },
            { "Elizabeth at 90: A Family Tribute", "0/21/21" },
            { "Elizabeth at 90: A Family Tribute", "0/21/21" },
            { "Squid Game: level2", "0/14/21" },
            { "asflnv", "2/14/21" },
            { "Squid Game: level2", "0/14/21" },
            { "The Golden Child", "10/21/21" },
            { "The Golden Child", "10/21/21" },
            { "The Guns of Navarone", "10/21/21" },
            { "The Guns of Navarone", "13/21/21" },
            { "The Guns of Navarone", "13/21/21" },
            { "Superbad", "13/21/21" },
            { "Superbad", "13/21/21" },
            { "Valentine's Day", "2/14/21" },
            { "Superman Returns", "2/14/21" },
    };
    MapCreator mapCreator = new MapCreator();
    MakeMovieSection movieSection = new MakeMovieSection();
    ArrayList<Map<String, ArrayList<ArrayList<String>>>> movieList = movieSection
    .movieOnly(mapCreator.createWatchedMediaMap(movieData1));
    ArrayList<String> actors = movieSection.actorInfo(movieList);

   
    assertEquals(actors.size(), 3);
    assertEquals(actors.get(0), "Ashton Kutcher");
    assertEquals(actors.get(1), ("Eric Dane"));
    assertEquals(actors.get(2), ("Emma Roberts"));


    assertEquals(movieSection.actorFeaturedMovies("Ashton Kutcher", movieList), "Valentine's Day");
    assertEquals(movieSection.actorFeaturedMovies("Eric Dane", movieList), "Valentine's Day");
    assertEquals(movieSection.actorFeaturedMovies("Emma Roberts", movieList), "Valentine's Day");
    assertEquals(movieSection.actorFeaturedMovies("Michael Cera", movieList), "Superbad");

    assertEquals(movieSection.actorFeaturedMovies("Doomfenshmirtz", movieList), "");

    }



        /**
     * checking for the top actors and movies they are in, even if the movie in it is not in our
     * data.
     */
    @Test
    public void topActorsAndMoviesTestEmpty(){
        String[][] movieData1 = {
            
            { "akljr", "0/21/21" }
    };
    MapCreator mapCreator = new MapCreator();
    MakeMovieSection movieSection = new MakeMovieSection();
    ArrayList<Map<String, ArrayList<ArrayList<String>>>> movieList = movieSection
    .movieOnly(mapCreator.createWatchedMediaMap(movieData1));
    ArrayList<String> actors = movieSection.actorInfo(movieList);

   
    assertEquals(actors.size(), 0);
    


    assertEquals(movieSection.actorFeaturedMovies("Ashton Kutcher", movieList), "");
    }


            /**
     * checking for the top actors and movies they are in, and checking that our list
     * can hold people from different movies.
     */
    @Test
    public void topActorsAndMoviesDifferentMovies(){
        String[][] movieData1 = {
            { "Superbad", "13/21/21" },
            { "Valentine's Day", "2/14/21" }

    };
    MapCreator mapCreator = new MapCreator();
    MakeMovieSection movieSection = new MakeMovieSection();
    ArrayList<Map<String, ArrayList<ArrayList<String>>>> movieList = movieSection
    .movieOnly(mapCreator.createWatchedMediaMap(movieData1));
    ArrayList<String> actors = movieSection.actorInfo(movieList);

   
    assertEquals(actors.size(), 3);
    assertEquals(actors.get(0), "Bill Hader");
    assertEquals(actors.get(1), ("Topher Grace"));
    assertEquals(actors.get(2), ("Jamie Foxx"));

    assertEquals(movieSection.actorFeaturedMovies("Bill Hader", movieList), "Superbad");
    assertEquals(movieSection.actorFeaturedMovies("Topher Grace", movieList), "Valentine's Day");
    }







    
    
    
}
