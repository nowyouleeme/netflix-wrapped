package edu.brown.cs.student;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.junit.jupiter.api.Test;

import edu.brown.cs.student.main.components.MakeTotalMin;
import edu.brown.cs.student.main.components.JsonDataType.JSONTotalMin;
import edu.brown.cs.student.main.components.helpers.MapCreator;

public class TotalMinTest {
    String[][] userHistory;
    ArrayList<Map<String, ArrayList<ArrayList<String>>>> userHistoryMapList;


    @Test
    public void testFileWorking() throws IOException {
        assertEquals(1+1, 2);
    }
    
    @Test
    public void totalMintestMainFunction(){
        String[][] movieData = {
            { "Community: Season 1: Advanced Criminal Law", "4/10/20" },
            { "The Golden Child", "10/21/21" }
        };
        MapCreator mapCreator = new MapCreator();
        userHistoryMapList = mapCreator.createWatchedMediaMap(movieData);
        MakeTotalMin makeTotalMin = new MakeTotalMin();

        JSONTotalMin minData = makeTotalMin.getTotalMin(movieData, userHistoryMapList);

        assertEquals(minData.totalMin, 124);
    }

    @Test
    public void totalMintestUnfoundMovie(){
        String[][] movieData = {
            { "aaaaaaaaaaaaaaaaaaa", "4/10/20" }
        };
        MapCreator mapCreator = new MapCreator();
        userHistoryMapList = mapCreator.createWatchedMediaMap(movieData);
        MakeTotalMin makeTotalMin = new MakeTotalMin();

        JSONTotalMin minData = makeTotalMin.getTotalMin(movieData, userHistoryMapList);
        assertEquals(minData.totalMin, 30);
    }

    @Test
    public void totalMintestUnfoundShow(){
        String[][] movieData = {
            { "Community: Season 1: Advanced Criminal Law", "4/10/20" }
        };
        MapCreator mapCreator = new MapCreator();
        userHistoryMapList = mapCreator.createWatchedMediaMap(movieData);
        MakeTotalMin makeTotalMin = new MakeTotalMin();

        JSONTotalMin minData = makeTotalMin.getTotalMin(movieData, userHistoryMapList);
        assertEquals(minData.totalMin, 30);
    }
    
}
