package edu.brown.cs.student.main.reports;

import edu.brown.cs.student.main.components.*;
import edu.brown.cs.student.main.components.JsonDataType.JSONFinalFetch;
import edu.brown.cs.student.main.components.JsonDataType.movieData;
import edu.brown.cs.student.main.components.helpers.JsonReader;
import edu.brown.cs.student.main.components.helpers.MapCreator;
import edu.brown.cs.student.main.csv.data.Data;
import edu.brown.cs.student.main.csv.data.Data.ShowActors;
import edu.brown.cs.student.main.csv.data.Data.TitleNEWImage;
import edu.brown.cs.student.main.csv.data.Data.WrappedData;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.xml.datatype.DatatypeConfigurationException;

public class MLGenerator implements ReportGenerator {
    public WrappedData generateReportJSON(String[][] userCSVData) throws IOException {



        JsonReader<movieData.movieJson> jsonReader = new JsonReader<>(movieData.movieJson.class);
        // movieJson result = jsonReader.fromJson("backend/data/netflix_titles.json");
        // Map<String, String> cast = result.cast();
        MapCreator mapCreator = new MapCreator();
        MakeTopGenres topGenre = new MakeTopGenres();
        MakeBingeData bingeData = new MakeBingeData();
        System.out.print(1);
        MakeTotalMin totalMin = new MakeTotalMin();
        System.out.print(2);
        MakeMovieSection movieSection = new MakeMovieSection();
        System.out.print(3);
        MakeShowSection showSection = new MakeShowSection();
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

       MakePersonality personality = new MakePersonality();
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

        return converterToData(finalFetchJson);

    }
    public WrappedData converterToData(JSONFinalFetch dataToConvert){


        //List<>

        Data.GenreCount[] genreCountArr = new Data.GenreCount[dataToConvert.top5Genres.size()];
        for (int i= 0; i < dataToConvert.top5Genres.size(); i++){
            Data.GenreCount genreCount = new Data.GenreCount(dataToConvert.top5Genres.get(i).genre,
            dataToConvert.top5Genres.get(i).count);
            genreCountArr[i] = (genreCount);
        }
        Data.TitleImage[] bingeDataMovies = new Data.TitleImage[dataToConvert.bingeData.movies.size()];
        for (int i = 0; i < dataToConvert.bingeData.movies.size(); i++){
            Data.TitleImage titleImage = new Data.TitleImage(dataToConvert.bingeData.movies.get(i).title, 
            dataToConvert.bingeData.movies.get(i).image);
            bingeDataMovies[i] = titleImage;
        }

        Data.TitleNEWImage[] bingeDataShows = new Data.TitleNEWImage[dataToConvert.bingeData.shows.size()];
        for (int i = 0; i < dataToConvert.bingeData.shows.size(); i++){
            Data.TitleNEWImage titleNEWImage = new Data.TitleNEWImage(dataToConvert.bingeData.shows.get(i).title,
            dataToConvert.bingeData.shows.get(i).numEpWatched, 
            dataToConvert.bingeData.shows.get(i).image);
            bingeDataShows[i] = titleNEWImage;
        }

        Data.BingeData bingeData = new Data.BingeData(dataToConvert.bingeData.date, 
        bingeDataShows, bingeDataMovies);


        Data.TitleNEWImage[] allShows = new Data.TitleNEWImage[dataToConvert.shows.allShows.size()];
        for (int i = 0; i < allShows.length; i++){
            TitleNEWImage show = new Data.TitleNEWImage(dataToConvert.shows.allShows.get(i).title,
            dataToConvert.shows.allShows.get(i).numEpWatched,
            dataToConvert.shows.allShows.get(i).image);

            allShows[i] = show;
        }
        Data.TitleNEWImage[] mostWatchedShows = new Data.TitleNEWImage[dataToConvert.shows.topShows.mostWatched.size()];
        
        for(int i = 0; i < mostWatchedShows.length; i++){
            TitleNEWImage show = new Data.TitleNEWImage(dataToConvert.shows.topShows.mostWatched.get(i).title,
            dataToConvert.shows.topShows.mostWatched.get(i).numEpWatched,
            dataToConvert.shows.topShows.mostWatched.get(i).image);
            mostWatchedShows[i] = show;
        }

        Data.TitleNEWImage[] leastWatchedShows = new Data.TitleNEWImage[dataToConvert.shows.topShows.leastWatched.size()];     
        
        for(int i = 0; i < leastWatchedShows.length; i++){
            TitleNEWImage show = new Data.TitleNEWImage(dataToConvert.shows.topShows.leastWatched.get(i).title,
            dataToConvert.shows.topShows.leastWatched.get(i).numEpWatched,
            dataToConvert.shows.topShows.leastWatched.get(i).image);
            leastWatchedShows[i] = show;
        }
        Data.TopShows topShows = new Data.TopShows(mostWatchedShows, leastWatchedShows);

        String[] mostWatchedShowActors = new String[dataToConvert.shows.showActors.mostWatchedActors.size()];
        for(int i = 0; i < dataToConvert.shows.showActors.mostWatchedActors.size(); i++){
            mostWatchedShowActors[i] = dataToConvert.shows.showActors.mostWatchedActors.get(i);
        }

        Data.TitleImage[] actorShows = new Data.TitleImage[dataToConvert.shows.showActors.actorFeaturedShows.size()];
        for(int i = 0; i < dataToConvert.shows.showActors.actorFeaturedShows.size(); i++){
            Data.TitleImage show = new Data.TitleImage(dataToConvert.shows.showActors.actorFeaturedShows.get(i).title,
            dataToConvert.shows.showActors.actorFeaturedShows.get(i).image);

            actorShows[i] = show;
        }
        Data.ShowActors showActors = new Data.ShowActors(mostWatchedShowActors, actorShows);


        Data.Shows shows = new Data.Shows(dataToConvert.shows.totalEpWatched,
        allShows, topShows, showActors);




        Data.TitleNEWImage[] allMovies = new Data.TitleNEWImage[dataToConvert.movie.allMovies.size()];
        for (int i = 0; i < allMovies.length; i++) {
            Data.TitleNEWImage movie = new Data.TitleNEWImage(dataToConvert.movie.allMovies.get(i).title,
                    dataToConvert.movie.allMovies.get(i).numEpWatched,
                    dataToConvert.movie.allMovies.get(i).image);
            allMovies[i] = movie;
        }
        Data.TitleNEWImage[] top5Movies = new Data.TitleNEWImage[dataToConvert.movie.top5Movies.size()];
        for (int i = 0; i < top5Movies.length; i++) {
            Data.TitleNEWImage movie = new Data.TitleNEWImage(dataToConvert.movie.top5Movies.get(i).title,
                    dataToConvert.movie.top5Movies.get(i).numEpWatched,
                    dataToConvert.movie.top5Movies.get(i).image);
            top5Movies[i] = movie;
        }
        
        String[] mostWatchedActors = new String[dataToConvert.movie.movieActors.mostWatchedActors.size()];
        dataToConvert.movie.movieActors.mostWatchedActors.toArray(mostWatchedActors);
        Data.TitleImage[] actorFeaturedMovies = new Data.TitleImage[dataToConvert.movie.movieActors.actorFeaturedMovies.size()];
        for (int i = 0; i < actorFeaturedMovies.length; i++) {
            Data.TitleImage movie = new Data.TitleImage(dataToConvert.movie.movieActors.actorFeaturedMovies.get(i).title,
                    dataToConvert.movie.movieActors.actorFeaturedMovies.get(i).image);
            actorFeaturedMovies[i] = movie;
        }
        Data.MovieActors movieActors = new Data.MovieActors(mostWatchedActors, actorFeaturedMovies);
        
        Data.Movie movie = new Data.Movie(allMovies, top5Movies, movieActors);





        Data.Personality personality = new Data.Personality(
            dataToConvert.personality.title, "backend personality");


        

        Data.WrappedData wrappedData = new WrappedData(genreCountArr, dataToConvert.totalMin, 
        bingeData, shows, movie, personality);

        return wrappedData;
        
    }
}
