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

    @Test
    public void testFileWorking() throws IOException {
        assertEquals(1 + 1, 2);
    }


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
