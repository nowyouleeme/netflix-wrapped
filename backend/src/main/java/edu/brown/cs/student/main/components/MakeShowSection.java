package edu.brown.cs.student.main.components;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.brown.cs.student.main.components.JsonDataType.JSONShowSection;
import edu.brown.cs.student.main.components.JsonDataType.JSONShowSection.JSONShow;
import edu.brown.cs.student.main.movieData.PosterFetch;

public class MakeShowSection {

    public JSONShowSection getShowSection(String[][] userHistory,
    ArrayList<Map<String, ArrayList<ArrayList<String>>>> userHistoryMapList) {
        PosterFetch posterFetcher = new PosterFetch();
        JSONShowSection showSection = new JSONShowSection();

        ArrayList<Map<String, ArrayList<ArrayList<String>>>> movieOnlyList = showOnly(userHistoryMapList);
        //System.out.println("movie only list size" + movieOnlyList.size());
        showSection.totalEpWatched = movieOnlyList.size();

        Map<String, Integer> movieCountMap = getMovieCountMap(getShowTitleList(movieOnlyList));
        

        // although a for loop, this is adding only one movie title.
        ArrayList<String> movieTitles = new ArrayList<String>();
        for (int i = 0; i < movieOnlyList.size(); i++) {
            Set<String> filmSet = movieOnlyList.get(i).keySet();
            for (String element : filmSet) {
                JSONShow show = new JSONShow();
                show.title = element;
                show.image = posterFetcher.getPosterLink(element);
                show.numEpWatched = movieCountMap.get(element);
                if(!movieTitles.contains(element)){
                    showSection.allShows.add(show);
                }
                movieTitles.add(element);
                
            }

        }
       // showSection.topShows.above50_3
       ArrayList<String> topShows = getTopThree(movieOnlyList);
       ArrayList<String> bottomShows = getBottomThree(movieOnlyList);

       for(int i = 0; i < topShows.size(); i++){
        if(i % 2 == 0){
            JSONShow mostShow = new JSONShow();
            mostShow.title = topShows.get(i);
            mostShow.image = posterFetcher.getPosterLink(topShows.get(i));
            mostShow.numEpWatched = Integer.parseInt(topShows.get(i+1));
            showSection.topShows.mostWatched.add(mostShow);
        }
       }
       for(int i = 0; i < bottomShows.size(); i++){
        if(i % 2 == 0){
            JSONShow mostShow = new JSONShow();
            mostShow.title = bottomShows.get(i);
            mostShow.image = posterFetcher.getPosterLink(bottomShows.get(i));
            mostShow.numEpWatched = Integer.parseInt(bottomShows.get(i+1));
            showSection.topShows.leastWatched.add(mostShow);
        }
       }

        showSection.showActors.mostWatchedActors = actorInfo(movieOnlyList);
        showSection.showActors.mostWatchedActors = actorInfo(movieOnlyList);

        for (String actor : actorInfo(movieOnlyList)) {
            JSONShow show = new JSONShow();
            show.title = actorFeaturedMovies(actor, movieOnlyList);
            show.image = posterFetcher.getPosterLink(actorFeaturedMovies(actor, movieOnlyList));
            showSection.showActors.actorFeaturedShows.add(show);
        }

        return showSection;

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
    public ArrayList<String> getShowTitleList(ArrayList<Map<String, ArrayList<ArrayList<String>>>> movieList){
        ArrayList<String> movieTitleList = new ArrayList<String>();
        for (Map<String, ArrayList<ArrayList<String>>> map : movieList) {
            for (Map.Entry<String, ArrayList<ArrayList<String>>> entry : map.entrySet()) {
                movieTitleList.add(entry.getKey());
            }
        }
        return movieTitleList;

    }

    public ArrayList<String> getTopThree(ArrayList<Map<String, ArrayList<ArrayList<String>>>> movieList) {
        ArrayList<String> topMoviesList = new ArrayList<String>();
        ArrayList<String> movieTitleList = getShowTitleList(movieList);

        Map<String, Integer> movieCountMap = getMovieCountMap(movieTitleList);
        List<Map.Entry<String, Integer>> movieCountList = new ArrayList<>(movieCountMap.entrySet());
        Collections.sort(movieCountList, (a, b) -> b.getValue().compareTo(a.getValue()));
        try{
            topMoviesList.add(movieCountList.get(0).getKey());
            String mostWatchedvalueString = Integer.toString(movieCountList.get(0).getValue());
            topMoviesList.add(mostWatchedvalueString);
            topMoviesList.add(movieCountList.get(1).getKey());
            String secondMostWatchedvalueString = Integer.toString(movieCountList.get(1).getValue());
            topMoviesList.add(secondMostWatchedvalueString);
            topMoviesList.add(movieCountList.get(2).getKey());
            String thirdMostWatchedvalueString = Integer.toString(movieCountList.get(2).getValue());
            topMoviesList.add(thirdMostWatchedvalueString);
        } catch(Exception e) {
            return topMoviesList;
        }
        return topMoviesList;
    }

    public ArrayList<String> getBottomThree(ArrayList<Map<String, ArrayList<ArrayList<String>>>> movieList) {
        ArrayList<String> topMoviesList = new ArrayList<String>();
        ArrayList<String> movieTitleList = getShowTitleList(movieList);

        Map<String, Integer> movieCountMap = getMovieCountMap(movieTitleList);
        List<Map.Entry<String, Integer>> movieCountList = new ArrayList<>(movieCountMap.entrySet());
        Collections.sort(movieCountList, (a, b) -> b.getValue().compareTo(a.getValue()));

        int sizeNum = movieCountList.size() - 1;
        try {
            topMoviesList.add(movieCountList.get(sizeNum).getKey());
            String mostWatchedvalueString = Integer.toString(movieCountList.get(sizeNum).getValue());
            topMoviesList.add(mostWatchedvalueString);

            topMoviesList.add(movieCountList.get(sizeNum-1).getKey());
            String secondMostWatchedvalueString = Integer.toString(movieCountList.get(sizeNum-1).getValue());
            topMoviesList.add(secondMostWatchedvalueString);

            topMoviesList.add(movieCountList.get(sizeNum-2).getKey());
            String thirdMostWatchedvalueString = Integer.toString(movieCountList.get(sizeNum-2).getValue());
            topMoviesList.add(thirdMostWatchedvalueString);
        } catch (Exception e) {
            return topMoviesList;
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

        ArrayList<String> actorMovies = new ArrayList<String>();

        for (Map<String, ArrayList<ArrayList<String>>> map : movieList) {
            for (Map.Entry<String, ArrayList<ArrayList<String>>> entry : map.entrySet()) {
                if (entry.getValue().get(4).contains(actor)) {
                    return entry.getKey();
                }

            }

        }
        return "no movie found";

    }

    public ArrayList<Map<String, ArrayList<ArrayList<String>>>> showOnly(
            ArrayList<Map<String, ArrayList<ArrayList<String>>>> fullList) {

        ArrayList<Map<String, ArrayList<ArrayList<String>>>> movieArray = new ArrayList<Map<String, ArrayList<ArrayList<String>>>>();

        for (Map<String, ArrayList<ArrayList<String>>> map : fullList) {
            for (Map.Entry<String, ArrayList<ArrayList<String>>> entry : map.entrySet()) {
                if (entry.getValue().size() != 1) {
                    if (entry.getValue().get(1).get(0).equals("TV Show")) {
                        movieArray.add(map);
                    }
                }

            }
        }

        return movieArray;
    }

}
