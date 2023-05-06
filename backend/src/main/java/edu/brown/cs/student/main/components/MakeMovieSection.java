package edu.brown.cs.student.main.components;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.brown.cs.student.main.components.JsonDataType.JSONMovieSection;
import edu.brown.cs.student.main.components.JsonDataType.JSONMovieSection.JSONMovie;
import edu.brown.cs.student.main.movieData.PosterFetch;

public class MakeMovieSection {

    public MakeMovieSection() {

    }

    public JSONMovieSection getMovieSection(String[][] userHistory,
    ArrayList<Map<String, ArrayList<ArrayList<String>>>> userHistoryMapList) {
        JSONMovieSection jsonMovieSection = new JSONMovieSection();
        PosterFetch posterFetcher = new PosterFetch();

        ArrayList<Map<String, ArrayList<ArrayList<String>>>> movieOnlyList = movieOnly(userHistoryMapList);
        jsonMovieSection.totalMoviesWatched = movieOnlyList.size();

        Map<String, Integer> movieCountMap = getMovieCountMap(getMovieTitleList(movieOnlyList));
        ArrayList<String> movieTitles = new ArrayList<String>();


        for (int i = 0; i < movieOnlyList.size(); i++) {
            Set<String> filmSet = movieOnlyList.get(i).keySet();
            for (String element : filmSet) {
                JSONMovie movie = new JSONMovie();
                movie.title = element;
                movie.image = posterFetcher.getPosterLink(element);
                movie.numEpWatched = movieCountMap.get(element);
                if(!movieTitles.contains(element)){
                    jsonMovieSection.allMovies.add(movie);
                }
                movieTitles.add(element); 
            }
        }
        // although a for loop, this is adding only one movie title.
        for (String element : getTopFiveMovies(movieOnlyList)) {
            JSONMovie movie = new JSONMovie();
            movie.title = element;
            movie.image = posterFetcher.getPosterLink(element);
            movie.numEpWatched = movieCountMap.get(element);
            jsonMovieSection.top5Movies.add(movie);
        }


        for (String element : actorInfo(movieOnlyList)){
            jsonMovieSection.movieActors.mostWatchedActors.add(element);
        }
        for (String element : actorInfo(movieOnlyList)){
            String actorMovie = actorFeaturedMovies(element, movieOnlyList);
            JSONMovie movie = new JSONMovie();
            movie.title = actorMovie;
            movie.image = posterFetcher.getPosterLink(actorMovie);
            movie.numEpWatched = movieCountMap.get(actorMovie);
            jsonMovieSection.movieActors.actorFeaturedMovies.add(movie);
        }
        return jsonMovieSection;
    }





    public Map<String, Integer> getMovieCountMap(ArrayList<String> movieTitleList){
        Map<String, Integer> movieCountMap = new HashMap<String, Integer>();
        for (String title : movieTitleList) {
            if (movieCountMap.containsKey(title)) {
                movieCountMap.put(title, movieCountMap.get(title) + 1);
            } else {
                movieCountMap.put(title, 1);
            }
        }
        return movieCountMap;

    }

    public ArrayList<String> getMovieTitleList(ArrayList<Map<String, ArrayList<ArrayList<String>>>> movieList){
        ArrayList<String> movieTitleList = new ArrayList<String>();
        for (Map<String, ArrayList<ArrayList<String>>> map : movieList) {
            for (Map.Entry<String, ArrayList<ArrayList<String>>> entry : map.entrySet()) {
                movieTitleList.add(entry.getKey());
            }
        }
        return movieTitleList;

    }

    public ArrayList<String> getTopFiveMovies(ArrayList<Map<String, ArrayList<ArrayList<String>>>> movieList) {
        ArrayList<String> topMoviesList = new ArrayList<String>();
        ArrayList<String> movieTitleList = getMovieTitleList(movieList);
        Map<String, Integer> movieCountMap = getMovieCountMap(movieTitleList);

        List<Map.Entry<String, Integer>> movieCountList = new ArrayList<>(movieCountMap.entrySet());
        Collections.sort(movieCountList, (a, b) -> b.getValue().compareTo(a.getValue()));

        // Print the top 5 genres with their counts
        int numGenres = Math.min(5, movieCountList.size());
        for (int i = 0; i < numGenres; i++) {
            topMoviesList.add(movieCountList.get(i).getKey());
        }
        return topMoviesList;

    }

    public ArrayList<String> actorInfo(ArrayList<Map<String, ArrayList<ArrayList<String>>>> movieList) {
        Map<String, Integer> actorCountMap = new HashMap<String, Integer>();
        ArrayList<String> finalActorList = new ArrayList<String>();

        for (Map<String, ArrayList<ArrayList<String>>> map : movieList) {
            for (Map.Entry<String, ArrayList<ArrayList<String>>> entry : map.entrySet()) {
                ArrayList<String> actorList = entry.getValue().get(4);
                for (String actor : actorList) {
                    if (actorCountMap.containsKey(actor)) {
                        actorCountMap.put(actor, actorCountMap.get(actor) + 1);
                    } else {
                        actorCountMap.put(actor, 1);
                    }
                }
            }
        }
        List<Map.Entry<String, Integer>> actorCountlist = new ArrayList<>(actorCountMap.entrySet());
        Collections.sort(actorCountlist, (a, b) -> b.getValue().compareTo(a.getValue()));

        // Print the top 5 genres with their counts
        int num = Math.min(3, actorCountlist.size());
        for (int i = 0; i < num; i++) {
            finalActorList.add(actorCountlist.get(i).getKey());
        }
        return finalActorList;
    }

    public String actorFeaturedMovies(String actor,
            ArrayList<Map<String, ArrayList<ArrayList<String>>>> movieList) {


        for (Map<String, ArrayList<ArrayList<String>>> map : movieList) {
            for (Map.Entry<String, ArrayList<ArrayList<String>>> entry : map.entrySet()) {
                if (entry.getValue().get(4).contains(actor)) {
                    return entry.getKey();
                }

            }

        }
        return "no movie found";

    }

    public ArrayList<Map<String, ArrayList<ArrayList<String>>>> movieOnly(
            ArrayList<Map<String, ArrayList<ArrayList<String>>>> fullList) {

        ArrayList<Map<String, ArrayList<ArrayList<String>>>> movieArray = new ArrayList<Map<String, ArrayList<ArrayList<String>>>>();

        for (Map<String, ArrayList<ArrayList<String>>> map : fullList) {
            for (Map.Entry<String, ArrayList<ArrayList<String>>> entry : map.entrySet()) {
                if (entry.getValue().size() != 1) {
                    if (entry.getValue().get(1).get(0).equals("Movie")) {
                        movieArray.add(map);
                    }
                }

            }
        }

        return movieArray;
    }
}
