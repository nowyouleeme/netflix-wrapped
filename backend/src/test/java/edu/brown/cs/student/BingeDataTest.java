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
    public void getTopGenresBasic2() throws IOException {
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
    
    
    
}
