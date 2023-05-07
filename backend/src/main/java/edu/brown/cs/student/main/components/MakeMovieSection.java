package edu.brown.cs.student.main.components;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.brown.cs.student.main.PosterImageGetting.PosterFetch;
import edu.brown.cs.student.main.components.JsonDataType.JSONMovieSection;
import edu.brown.cs.student.main.components.JsonDataType.JSONMovieSection.JSONMovie;

public class MakeMovieSection {

    public MakeMovieSection() {

    }


    /**
     * A function that gets the movie section to pass in the frontend
     * @param userHistory the string array of csv of watch history
     * @param userHistoryMapList is the arraylist  of maps of all the movies and shows watched
     * @return the moviesection
    */
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





    /**
     * getMovieCountMap the movieCount map to hold how many times you watched the movie.
     * @param movieTitleList is the list of movieTitles
     * @return the map 
    */
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

    /**
     * getMovieTitleList gets the list of movie titles.
     * @param movieList the list of movies
     * @return the list of just titles
    */
    public ArrayList<String> getMovieTitleList(ArrayList<Map<String, ArrayList<ArrayList<String>>>> movieList){
        ArrayList<String> movieTitleList = new ArrayList<String>();
        for (Map<String, ArrayList<ArrayList<String>>> map : movieList) {
            for (Map.Entry<String, ArrayList<ArrayList<String>>> entry : map.entrySet()) {
                movieTitleList.add(entry.getKey());
            }
        }
        return movieTitleList;

    }

    /**
     * we recieve the top five movies watched.
     * @param movieList the list of movies
     * @return the list of top five movies
    */
    public ArrayList<String> getTopFiveMovies(ArrayList<Map<String, ArrayList<ArrayList<String>>>> movieList) {
        ArrayList<String> topMoviesList = new ArrayList<String>();
        ArrayList<String> movieTitleList = getMovieTitleList(movieList);
        Map<String, Integer> movieCountMap = getMovieCountMap(movieTitleList);

        List<Map.Entry<String, Integer>> movieCountList = new ArrayList<>(movieCountMap.entrySet());
        Collections.sort(movieCountList, (a, b) -> b.getValue().compareTo(a.getValue()));

        int numGenres = Math.min(5, movieCountList.size());
        for (int i = 0; i < numGenres; i++) {
            topMoviesList.add(movieCountList.get(i).getKey());
        }
        return topMoviesList;

    }

    /**
     * getting the top three actors.
     * @param movieList the list of movies
     * @return returns the list of top actors.
    */
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

        int num = Math.min(3, actorCountlist.size());
        for (int i = 0; i < num; i++) {
            finalActorList.add(actorCountlist.get(i).getKey());
        }
        return finalActorList;
    }


    /**
     * getting the movie the actor in the input is feautred in. 
     * @param actor the actor to search
     * @param movieList the list of just movies
     * @return returns the movie the actor stars in 
    */
    public String actorFeaturedMovies(String actor,
            ArrayList<Map<String, ArrayList<ArrayList<String>>>> movieList) {


        for (Map<String, ArrayList<ArrayList<String>>> map : movieList) {
            for (Map.Entry<String, ArrayList<ArrayList<String>>> entry : map.entrySet()) {
                if (entry.getValue().get(4).contains(actor)) {
                    return entry.getKey();
                }

            }

        }
        return "";

    }


    /**
     * filtering the list of media into just movies
     * @param fullList the list of all movies and shows
     * @return the filtered list.
    */
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
