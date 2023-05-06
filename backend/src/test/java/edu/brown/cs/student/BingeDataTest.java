package edu.brown.cs.student;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.junit.jupiter.api.Test;

import edu.brown.cs.student.main.components.MakeBingeData;
import edu.brown.cs.student.main.components.JsonDataType.JSONBingeData;
import edu.brown.cs.student.main.components.helpers.MapCreator;

public class BingeDataTest {
    

    @Test
    public void testFileWorking() throws IOException {
        assertEquals(1+1, 2);
    }


    @Test
    public void BingeDataBasicTest() throws IOException {
        MapCreator mapCreator = new MapCreator();

        String[][] movieData1 = {
            { "Valentine's Day", "2/14/21" },
            { "Valentine's Day", "2/14/21" },
            { "Valentine's Day", "3/14/21" },
            { "Valentine's Day", "3/14/21" },
            { "Valentine's Day", "3/14/21" },
        };
        
        MakeBingeData bingeDataMaker = new MakeBingeData();
        ArrayList<Map<String, ArrayList<ArrayList<String>>>> userHistoryMap = mapCreator.createWatchedMovieMap(movieData1);

        JSONBingeData bingeData = bingeDataMaker.getBingeData(movieData1, userHistoryMap);

        assertEquals(bingeData.date, "3/14/21");
        assertEquals(bingeData.movies.size(), 1);

        assertEquals(bingeData.movies.get(0).title, "Valentine's Day");

        assertEquals(bingeData.shows.size(), 0);
        
    }
    

    @Test
    public void BingeDataBasicTestWithShow() throws IOException {
        MapCreator mapCreator = new MapCreator();

        String[][] movieData1 = {
            { "Valentine's Day", "2/14/21" },
            { "Valentine's Day", "2/14/21" },
            { "Valentine's Day", "3/14/21" },
            { "Valentine's Day", "3/14/21" },
            { "Valentine's Day", "3/14/21" },
            { "Dear White People", "3/14/21" },
            { "Squid Game", "3/14/21" },
            { "Squid Game", "3/14/21" },
            { "Squid Game", "3/1/21" },
            { "Euphoria: level1", "1/21/21" },
            { "asflnv", "3/14/21" }
        };
        
        MakeBingeData bingeDataMaker = new MakeBingeData();
        ArrayList<Map<String, ArrayList<ArrayList<String>>>> userHistoryMap = mapCreator.createWatchedMovieMap(movieData1);

        JSONBingeData bingeData = bingeDataMaker.getBingeData(movieData1, userHistoryMap);

        assertEquals(bingeData.date, "3/14/21");
        assertEquals(bingeData.movies.size(), 2);
        assertEquals(bingeData.movies.get(0).title, "asflnv");
        assertEquals(bingeData.movies.get(1).title, "Valentine's Day");

        assertEquals(bingeData.shows.size(), 2);
        assertEquals(bingeData.shows.get(1).title, "Dear White People");
        assertEquals(bingeData.shows.get(1).numEpWatched, 1);

        assertEquals(bingeData.shows.get(0).title, "Squid Game");
        assertEquals(bingeData.shows.get(0).numEpWatched, 2);
        
    }


    @Test
    public void BingeDataBasicTestEmpty() throws IOException {
        MapCreator mapCreator = new MapCreator();

        String[][] movieData1 = {
            { "asflnv", "3/14/21" }
        };
        
        MakeBingeData bingeDataMaker = new MakeBingeData();
        ArrayList<Map<String, ArrayList<ArrayList<String>>>> userHistoryMap = mapCreator.createWatchedMovieMap(movieData1);

        JSONBingeData bingeData = bingeDataMaker.getBingeData(movieData1, userHistoryMap);

        assertEquals(bingeData.date, "3/14/21");
        assertEquals(bingeData.movies.size(), 1);
        assertEquals(bingeData.movies.get(0).title, "asflnv");

        assertEquals(bingeData.shows.size(), 0);
        
    }

}
