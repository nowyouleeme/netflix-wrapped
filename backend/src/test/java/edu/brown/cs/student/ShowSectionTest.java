package edu.brown.cs.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.testng.annotations.Test;

import edu.brown.cs.student.main.components.MakeShowSection;
import edu.brown.cs.student.main.components.helpers.MapCreator;

public class ShowSectionTest {

        /**
     * Basic test to see if the test in this file are working
     */
    @Test
    public void testFileWorking() throws IOException {
        assertEquals(1 + 1, 2);
    }


    /**
     * Unit testing to check is show filters are working.
     */
    @Test
    public void testShowOnly() {
        String[][] movieData1 = {
                { "Valentine's Day", "2/14/21" },
                { "The Golden Child", "10/21/21" },
                { "The Guns of Navarone", "10/21/21" },
                { "akljr", "0/21/21" },
                { "Elizabeth at 90: A Family Tribute", "0/21/21" },
                { "Elizabeth Harvest", "0/21/21" },
                { "Squid Game: level2", "0/14/21" },
                { "Euphoria: level1", "9/21/21" },
                { "asflnv", "2/14/21" },
        };
        MapCreator mapCreator = new MapCreator();
        MakeShowSection showSection = new MakeShowSection();
        ArrayList<Map<String, ArrayList<ArrayList<String>>>> showList = showSection
                .showOnly(mapCreator.createWatchedMediaMap(movieData1));
        assertEquals(showList.size(), 1);
        assertTrue(showList.get(0).keySet().contains("Squid Game"));
    }

    /**
     * Unit testing to check is show filters are working, even when inputted a show with no 
     * data.
     */
    @Test
    public void testShowOnlyNoShows() {
        String[][] movieData1 = {
                { "adlfnp", "2/14/21" },
        };
        MapCreator mapCreator = new MapCreator();
        MakeShowSection showSection = new MakeShowSection();
        ArrayList<Map<String, ArrayList<ArrayList<String>>>> showList = showSection
                .showOnly(mapCreator.createWatchedMediaMap(movieData1));
        assertEquals(showList.size(), 0);
    }

    /**
     * Integration testing to check is if we can get the top three actors in the history.
     */
    @Test
    public void actorInfoTest() {
        String[][] movieData1 = {
            { "Squid Game: level2", "2/14/21" }

        };
        MapCreator mapCreator = new MapCreator();
        MakeShowSection showSection = new MakeShowSection();
        ArrayList<Map<String, ArrayList<ArrayList<String>>>> showList = showSection
        .showOnly(mapCreator.createWatchedMediaMap(movieData1));
        ArrayList<String> actorList = showSection.actorInfo(showList);

        assertEquals(actorList.size(), 3);
        assertEquals(actorList.get(0), "Park Hae-soo");
        

    }

    /**
     * Integration testing to check is if we can get the top three actors in the history,
     * with a larger history.
     */
    @Test
    public void actorInfoTestLarge() {
        String[][] movieData1 = {
            { "Itaewon Class", "2/14/21"},
            { "Squid Game: level2", "2/14/21"},
            {"Crash Landing on You", "2/14/21" },
            { "Squid Game: level2", "2/14/21"},
            {"Crash Landing on You", "2/14/21" },
            { "Squid Game: level2", "2/14/21"},
            {"Crash Landing on You", "2/14/21" },
            { "Squid Game: level2", "2/14/21"},
            {"Crash Landing on You", "2/14/21" },
            { "Squid Game: level2", "2/14/21"},
            {"Crash Landing on You", "2/14/21" }

        };
        MapCreator mapCreator = new MapCreator();
        MakeShowSection showSection = new MakeShowSection();
        ArrayList<Map<String, ArrayList<ArrayList<String>>>> showList = showSection
        .showOnly(mapCreator.createWatchedMediaMap(movieData1));
        ArrayList<String> actorList = showSection.actorInfo(showList);

        assertEquals(actorList.size(), 3);
        assertEquals(actorList.get(0), "Hwangwoo Seul-hye");
        assertEquals(actorList.get(1), "Lee You-mi");
        assertEquals(actorList.get(2), "You Seong-joo");

    }


    /**
     * Integration testing to check is if we can get the top three actors in the history,
     * even if the show is not in data
     */
    @Test
    public void actorInfoTestEmpty() {
        String[][] movieData1 = {
            { "arsoufhl", "2/14/21" }

        };
        MapCreator mapCreator = new MapCreator();
        MakeShowSection showSection = new MakeShowSection();
        ArrayList<Map<String, ArrayList<ArrayList<String>>>> showList = showSection
        .showOnly(mapCreator.createWatchedMediaMap(movieData1));
        ArrayList<String> actorList = showSection.actorInfo(showList);

        assertEquals(actorList.size(),0);
    }

    /**
     * Integration testing to check is if we can get the top three actors in the history,
     * and find the movie the actor is in.
     */
    @Test
    public void actorMoviesTest() {
        String[][] movieData1 = {
            { "Itaewon Class", "2/14/21"},
            { "Squid Game: level2", "2/14/21"},

        };
        MapCreator mapCreator = new MapCreator();
        MakeShowSection showSection = new MakeShowSection();
        ArrayList<Map<String, ArrayList<ArrayList<String>>>> showList = showSection
        .showOnly(mapCreator.createWatchedMediaMap(movieData1));
        String actorMovie = showSection.actorFeaturedShows("Lee You-mi", showList);

        assertEquals(actorMovie, "Squid Game");
    }


        /**
     * Integration testing to check is if we can get the top three actors in the history,
     * , even if there are no movies, and find the movie the actor is in.
     */
    @Test
    public void actorMoviesUnfoundTest() {
        String[][] movieData1 = {
            { "Itaewon Class", "2/14/21"},

        };
        MapCreator mapCreator = new MapCreator();
        MakeShowSection showSection = new MakeShowSection();
        ArrayList<Map<String, ArrayList<ArrayList<String>>>> showList = showSection
        .showOnly(mapCreator.createWatchedMediaMap(movieData1));
        String actorMovie = showSection.actorFeaturedShows("Lee You-mi", showList);

        assertEquals(actorMovie, "");
    }



        /**
     * Integration testing to check if getting the top three most watched show works with one data.
     */
    @Test
    public void getTopThreeTestOneData(){

        String[][] movieData1 = {
            { "Itaewon Class", "2/14/21"},

        };
        MapCreator mapCreator = new MapCreator();
        MakeShowSection showSection = new MakeShowSection();
        ArrayList<Map<String, ArrayList<ArrayList<String>>>> showList = showSection
        .showOnly(mapCreator.createWatchedMediaMap(movieData1));
        ArrayList<String> topThree = showSection.getTopThree(showList);

        assertEquals(topThree.size(), 2);
        assertEquals(topThree.get(0), "Itaewon Class");
        assertEquals(topThree.get(1), "1");
    }


            /**
     * Integration testing to check if getting the top three most watched show works with no data.
     */
    @Test
    public void getTopThreeTestEmpty(){

        String[][] movieData1 = {
            { "SLKDF", "2/14/21"},

        };
        MapCreator mapCreator = new MapCreator();
        MakeShowSection showSection = new MakeShowSection();
        ArrayList<Map<String, ArrayList<ArrayList<String>>>> showList = showSection
        .showOnly(mapCreator.createWatchedMediaMap(movieData1));
        ArrayList<String> topThree = showSection.getTopThree(showList);

        assertEquals(topThree.size(), 0);
    }



/**
     * Integration testing to check if getting the top three most watched show works with
     * a larger mock.
     */
    @Test
    public void getTopThreeTestLarge(){

        String[][] movieData1 = {
            { "Squid Game: level2", "2/14/21"},
            {"Crash Landing on You", "2/14/21" },
            { "Squid Game: level2", "2/14/21"},
            {"Crash Landing on You", "2/14/21" },
            { "Squid Game: level2", "2/14/21"},
            {"Crash Landing on You", "2/14/21" },
            { "Squid Game: level2", "2/14/21"},
            {"Crash Landing on You", "2/14/21" },
            { "Squid Game: level2", "2/14/21"},
            {"Crash Landing on You", "2/14/21" },
            {"Itaewon Class", "2/14/21"},
            {"Itaewon Class", "2/14/21"},
            {"Itaewon Class", "2/14/21"},
            {"Dear White People", "2/14/21"},
            {"Dear White People", "2/14/21"},


        };
        MapCreator mapCreator = new MapCreator();
        MakeShowSection showSection = new MakeShowSection();
        ArrayList<Map<String, ArrayList<ArrayList<String>>>> showList = showSection
        .showOnly(mapCreator.createWatchedMediaMap(movieData1));
        ArrayList<String> topThree = showSection.getTopThree(showList);

        assertEquals(topThree.size(), 6);
        assertEquals(topThree.get(0), "Squid Game");
        assertEquals(topThree.get(1), "5");
        assertEquals(topThree.get(2), "Crash Landing on You");
        assertEquals(topThree.get(3), "5");
        assertEquals(topThree.get(4), "Itaewon Class");
        assertEquals(topThree.get(5), "3");

    }


    






/**
     * Integration testing to check if getting the bottom three most watched show works with
     * one data.
     */

    @Test
    public void getBottomThreeTestOneData(){

        String[][] movieData1 = {
            { "Itaewon Class", "2/14/21"},

        };
        MapCreator mapCreator = new MapCreator();
        MakeShowSection showSection = new MakeShowSection();
        ArrayList<Map<String, ArrayList<ArrayList<String>>>> showList = showSection
        .showOnly(mapCreator.createWatchedMediaMap(movieData1));
        ArrayList<String> bottomThree = showSection.getBottomThree(showList);

        assertEquals(bottomThree.size(), 2);
        assertEquals(bottomThree.get(0), "Itaewon Class");
        assertEquals(bottomThree.get(1), "1");
    }



/**
     * Integration testing to check if getting the bottom three most watched show works with
     * empty data.
     */
    @Test
    public void getBottomThreeTestEmpty(){

        String[][] movieData1 = {
            { "SLKDF", "2/14/21"},

        };
        MapCreator mapCreator = new MapCreator();
        MakeShowSection showSection = new MakeShowSection();
        ArrayList<Map<String, ArrayList<ArrayList<String>>>> showList = showSection
        .showOnly(mapCreator.createWatchedMediaMap(movieData1));
        ArrayList<String> bottomThree = showSection.getBottomThree(showList);

        assertEquals(bottomThree.size(), 0);
    }


    /**
     * Integration testing to check if getting the bottom three most watched show works with
     * a larger data.
     */
    @Test
    public void getBottomThreeTestLarge(){

        String[][] movieData1 = {
            { "Squid Game: level2", "2/14/21"},
            {"Crash Landing on You", "2/14/21" },
            { "Squid Game: level2", "2/14/21"},
            {"Crash Landing on You", "2/14/21" },
            { "Squid Game: level2", "2/14/21"},
            {"Crash Landing on You", "2/14/21" },
            { "Squid Game: level2", "2/14/21"},
            {"Crash Landing on You", "2/14/21" },
            { "Squid Game: level2", "2/14/21"},
            {"Crash Landing on You", "2/14/21" },
            {"Itaewon Class", "2/14/21"},
            {"Itaewon Class", "2/14/21"},
            {"Itaewon Class", "2/14/21"},
            {"Dear White People", "2/14/21"},
            {"Dear White People", "2/14/21"},


        };
        MapCreator mapCreator = new MapCreator();
        MakeShowSection showSection = new MakeShowSection();
        ArrayList<Map<String, ArrayList<ArrayList<String>>>> showList = showSection
        .showOnly(mapCreator.createWatchedMediaMap(movieData1));
        ArrayList<String> bottomThree = showSection.getBottomThree(showList);

        assertEquals(bottomThree.size(), 6);
        assertEquals(bottomThree.get(0), "Dear White People");
        assertEquals(bottomThree.get(1), "2");
        assertEquals(bottomThree.get(2), "Itaewon Class");
        assertEquals(bottomThree.get(3), "3");
        assertEquals(bottomThree.get(4), "Crash Landing on You");
        assertEquals(bottomThree.get(5), "5");


    }


}
