package edu.brown.cs.student;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.junit.jupiter.api.Test;

import edu.brown.cs.student.main.components.MakeTopGenres;
import edu.brown.cs.student.main.components.JsonDataType.JSONTopGenres;
import edu.brown.cs.student.main.components.helpers.MapCreator;

public class TopGenresTest {
    
    @Test
    public void testFileWorking() throws IOException {
        assertEquals(1+1, 2);
    }

        
    @Test
    public void getTopGenresBasic() throws IOException {
        MapCreator mapCreator = new MapCreator();

        String[][] movieData1 = {
            { "Valentine's Day", "2/14/21" },
            { "The Golden Child", "10/21/21" },
            { "The Guns of Navarone", "10/21/21" },
            { "akljr", "0/21/21" },
            { "Elizabeth at 90: A Family Tribute", "0/21/21" },
            { "Elizabeth Harvest", "0/21/21" },
            { "Squid Game: level2", "0/14/21" },
            { "Dear White People", "0/21/21" },
            { "Euphoria: level1", "9/21/21" },
            { "asflnv", "2/14/21" },
        };
        MakeTopGenres topGenresMaker = new MakeTopGenres();
        ArrayList<Map<String, ArrayList<ArrayList<String>>>> userHistoryMap = mapCreator.createWatchedMovieMap(movieData1);
        JSONTopGenres topGenres = topGenresMaker.getTopGenres(movieData1, userHistoryMap);
        for (Map<String, ArrayList<ArrayList<String>>> element : userHistoryMap){
            mapCreator.printMapWithArray(element);
        }


        assertEquals(topGenres.top5Genres.get(0).genre, "Dramas");
        assertEquals(topGenres.top5Genres.get(0).count, 2);

        assertEquals(topGenres.top5Genres.get(1).genre, "TV Dramas");
        assertEquals(topGenres.top5Genres.get(1).count, 2);

        assertEquals(topGenres.top5Genres.get(2).genre, "Comedies");
        assertEquals(topGenres.top5Genres.get(2).count, 2);
        
        assertEquals(topGenres.top5Genres.get(3).genre, "Action & Adventure");
        assertEquals(topGenres.top5Genres.get(3).count, 2);

        assertEquals(topGenres.top5Genres.get(4).genre, "Sci-Fi & Fantasy");
        assertEquals(topGenres.top5Genres.get(4).count, 1);
    }




    @Test
    public void getTopGenresBasic2() throws IOException {
        MapCreator mapCreator = new MapCreator();

        String[][] movieData1 = {
            { "Valentine's Day", "2/14/21" },
            { "Valentine's Day", "2/14/21" },
            { "Valentine's Day", "2/14/21" },
            { "Valentine's Day", "2/14/21" },
            { "Valentine's Day", "2/14/21" },
            { "The Golden Child", "10/21/21" },
            { "The Guns of Navarone", "10/21/21" },
            { "akljr", "0/21/21" },
        };
        MakeTopGenres topGenresMaker = new MakeTopGenres();
        ArrayList<Map<String, ArrayList<ArrayList<String>>>> userHistoryMap = mapCreator.createWatchedMovieMap(movieData1);
        JSONTopGenres topGenres = topGenresMaker.getTopGenres(movieData1, userHistoryMap);
        for (Map<String, ArrayList<ArrayList<String>>> element : userHistoryMap){
            mapCreator.printMapWithArray(element);
        }
        assertEquals(topGenres.top5Genres.get(0).genre, "Comedies");
        assertEquals(topGenres.top5Genres.get(0).count, 6);

        assertEquals(topGenres.top5Genres.get(1).genre, "Romantic Movies");
        assertEquals(topGenres.top5Genres.get(1).count, 5);

        assertEquals(topGenres.top5Genres.get(2).genre, "Action & Adventure");
        assertEquals(topGenres.top5Genres.get(2).count, 2);

        assertEquals(topGenres.top5Genres.get(3).genre, "Classic Movies");
        assertEquals(topGenres.top5Genres.get(3).count, 1);
    }

    @Test
    public void getTopGenresBasicEmpty() throws IOException {
        MapCreator mapCreator = new MapCreator();

        String[][] movieData1 = {
            { "akljr", "0/21/21" },
        };
        MakeTopGenres topGenresMaker = new MakeTopGenres();
        ArrayList<Map<String, ArrayList<ArrayList<String>>>> userHistoryMap = mapCreator.createWatchedMovieMap(movieData1);
        JSONTopGenres topGenres = topGenresMaker.getTopGenres(movieData1, userHistoryMap);
        for (Map<String, ArrayList<ArrayList<String>>> element : userHistoryMap){
            mapCreator.printMapWithArray(element);
        }
        assertEquals(topGenres.top5Genres.size(), 0);

    }

    @Test
    public void getTopGenresBasicOne() throws IOException {
        MapCreator mapCreator = new MapCreator();

        String[][] movieData1 = {
            { "Dear White People", "0/21/21" }
        };
        MakeTopGenres topGenresMaker = new MakeTopGenres();
        ArrayList<Map<String, ArrayList<ArrayList<String>>>> userHistoryMap = mapCreator.createWatchedMovieMap(movieData1);
        JSONTopGenres topGenres = topGenresMaker.getTopGenres(movieData1, userHistoryMap);
        for (Map<String, ArrayList<ArrayList<String>>> element : userHistoryMap){
            mapCreator.printMapWithArray(element);
        }
        assertEquals(topGenres.top5Genres.size(), 2);

        assertEquals(topGenres.top5Genres.get(0).genre, "TV Comedies");
        assertEquals(topGenres.top5Genres.get(0).count, 1);

        assertEquals(topGenres.top5Genres.get(1).genre, "TV Dramas");
        assertEquals(topGenres.top5Genres.get(1).count, 1);

    }
    
}
