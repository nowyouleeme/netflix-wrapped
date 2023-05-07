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


        /**
     * Basic test to see if the test in this file are working
     */
    @Test
    public void testFileWorking() throws IOException {
        assertEquals(1+1, 2);
    }
    
    /**
     * Unit testing to see if the total minutes watched works with shows and movies
     */
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

        /**
     * Unit testing to see if the total minutes watched works with shows and movies,
     * when the one movie is not found in our data
     */
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


        /**
     * Unit testing to see if the total minutes watched works with shows in paticular,
     * and it adds 30. 
     */
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
