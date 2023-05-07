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

    @Test
    public void testFileWorking() throws IOException {
        assertEquals(1 + 1, 2);
    }

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
                .showOnly(mapCreator.createWatchedMovieMap(movieData1));
        assertEquals(showList.size(), 1);
        assertTrue(showList.get(0).keySet().contains("Squid Game"));
    }

    @Test
    public void testShowOnlyNoShows() {
        String[][] movieData1 = {
                { "adlfnp", "2/14/21" },
        };
        MapCreator mapCreator = new MapCreator();
        MakeShowSection showSection = new MakeShowSection();
        ArrayList<Map<String, ArrayList<ArrayList<String>>>> showList = showSection
                .showOnly(mapCreator.createWatchedMovieMap(movieData1));
        assertEquals(showList.size(), 0);
    }

    @Test
    public void actorInfoTest() {
        String[][] movieData1 = {
            { "Squid Game: level2", "2/14/21" }

        };
        MapCreator mapCreator = new MapCreator();
        MakeShowSection showSection = new MakeShowSection();
        ArrayList<Map<String, ArrayList<ArrayList<String>>>> showList = showSection
        .showOnly(mapCreator.createWatchedMovieMap(movieData1));
        ArrayList<String> actorList = showSection.actorInfo(showList);

        assertEquals(actorList.size(), 3);
        assertEquals(actorList.get(0), "Park Hae-soo");
        

    }

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
        .showOnly(mapCreator.createWatchedMovieMap(movieData1));
        ArrayList<String> actorList = showSection.actorInfo(showList);

        assertEquals(actorList.size(), 3);
        assertEquals(actorList.get(0), "Hwangwoo Seul-hye");
        assertEquals(actorList.get(1), "Lee You-mi");
        assertEquals(actorList.get(2), "You Seong-joo");

    }

    @Test
    public void actorInfoTestEmpty() {
        String[][] movieData1 = {
            { "arsoufhl", "2/14/21" }

        };
        MapCreator mapCreator = new MapCreator();
        MakeShowSection showSection = new MakeShowSection();
        ArrayList<Map<String, ArrayList<ArrayList<String>>>> showList = showSection
        .showOnly(mapCreator.createWatchedMovieMap(movieData1));
        ArrayList<String> actorList = showSection.actorInfo(showList);

        assertEquals(actorList.size(),0);
    }

}
