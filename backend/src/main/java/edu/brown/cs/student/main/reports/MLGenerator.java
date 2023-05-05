package edu.brown.cs.student.main.reports;

import edu.brown.cs.student.main.components.*;
import edu.brown.cs.student.main.components.JsonDataType.JSONFinalFetch;
import edu.brown.cs.student.main.components.JsonDataType.movieData;
import edu.brown.cs.student.main.components.helpers.JsonReader;
import edu.brown.cs.student.main.components.helpers.MapCreator;
import edu.brown.cs.student.main.csv.data.Data;
import edu.brown.cs.student.main.csv.data.Data.WrappedData;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class MLGenerator implements ReportGenerator {
    public JSONFinalFetch generateReportJSON(String[][] userCSVData) throws IOException {
        JsonReader<movieData.movieJson> jsonReader = new JsonReader<>(movieData.movieJson.class);
        // movieJson result = jsonReader.fromJson("backend/data/netflix_titles.json");
        // Map<String, String> cast = result.cast();
        MapCreator mapCreator = new MapCreator();
        TopGenres topGenre = new TopGenres();
        BingeData bingeData = new BingeData();
        System.out.print(1);
        TotalMin totalMin = new TotalMin();
        System.out.print(2);
        MovieSection movieSection = new MovieSection();
        System.out.print(3);
        ShowSection showSection = new ShowSection();
        System.out.print(4);

        ArrayList<Map<String, ArrayList<ArrayList<String>>>> userHistoryMapList = mapCreator
                .createWatchedMovieMap(userCSVData);
        JSONFinalFetch finalFetchJson = new JSONFinalFetch();
        System.out.print(5);




       try (FileWriter writer = new FileWriter("backend/backend-ml/data/viewhist2.csv")) {
           for (int j = 0; j < userCSVData.length; j++) {
               writer.append("\""+userCSVData[j][0]+"\"");
               writer.append(",");
               writer.append("\""+userCSVData[j][1]+"\"");
               writer.append("\n");
           }
           writer.close();
       } catch (IOException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       }

        Personality personality = new Personality();
        System.out.print(6);

        finalFetchJson.personality.title = personality.getPersonality(userCSVData, userHistoryMapList);
        //finalFetchJson.personality.title = "ml gen mock";
        System.out.print(7);


        finalFetchJson.top5Genres = topGenre.getTopGenres(userCSVData, userHistoryMapList).top5Genres;
        System.out.print(8);
        finalFetchJson.bingeData = bingeData.getBingeData(userCSVData, userHistoryMapList);
        System.out.print(9);
        finalFetchJson.totalMin = totalMin.getTotalMin(userCSVData, userHistoryMapList).totalMin;
        System.out.print(10);
        finalFetchJson.movie = movieSection.getMovieSection(userCSVData, userHistoryMapList);
        System.out.print(11);
        finalFetchJson.shows = showSection.getShowSection(userCSVData, userHistoryMapList);
        System.out.print(12);

        // System.out.println("TotalTime: "+totalMin.getTotalMin(userCSVData));
        // System.out.println("bingeData: " + bingeData.getBingeData(userCSVData));
        // System.out.println("movieSection: "+ movieSection.getMovieSection(userCSVData));
        // System.out.println("showSection: "+ showSection.getMovieSection(userCSVData));

        // for (Map<String, ArrayList<ArrayList<String>>> element :
        // mapCreator.createWatchedMovieMap(userCSVData)){
        // mapCreator.printMapWithArray(element);
        // }

        return finalFetchJson;

    }
    public WrappedData converterToData(JSONFinalFetch dataToConvert){


        //List<>

        Data.GenreCount[] genreCountArr = new Data.GenreCount[dataToConvert.top5Genres.size()];
        for (int i= 0; i < dataToConvert.top5Genres.size(); i++){
            Data.GenreCount genreCount = new Data.GenreCount(dataToConvert.top5Genres.get(i).genre,
            dataToConvert.top5Genres.get(i).count);
            genreCountArr[i] = (genreCount);
        }
        Data.BingeData bingeData = new Data.BingeData(dataToConvert.bingeData.date, );
        









        WrappedData = new WrappedData(genreCountArr, dataToConvert.totalMin, 
        null, null, null, null);
        
    }
}
