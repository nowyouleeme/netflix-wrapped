package edu.brown.cs.student.main.components;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.brown.cs.student.main.PosterImageGetting.PosterFetch;
import edu.brown.cs.student.main.components.JsonDataType.JSONShowSection;
import edu.brown.cs.student.main.components.JsonDataType.JSONShowSection.JSONShow;

public class MakeShowSection {

    /**
     * get the showsection for the frontend
     * @param userHistory the string array of userHistory
     * @param userHistoryMapList is the users watched movies and shows
     * @return JSONShowSection to send in
    */
    public JSONShowSection getShowSection(String[][] userHistory,
    ArrayList<Map<String, ArrayList<ArrayList<String>>>> userHistoryMapList) {
        PosterFetch posterFetcher = new PosterFetch();
        JSONShowSection showSection = new JSONShowSection();

        ArrayList<Map<String, ArrayList<ArrayList<String>>>> showOnlyList = showOnly(userHistoryMapList);
    
        showSection.totalEpWatched = showOnlyList.size();

        Map<String, Integer> showCountMap = getShowCountMap(getShowTitleList(showOnlyList));
        


        ArrayList<String> showTitles = new ArrayList<String>();
        for (int i = 0; i < showOnlyList.size(); i++) {
            Set<String> showSet = showOnlyList.get(i).keySet();
            for (String element : showSet) {
                JSONShow show = new JSONShow();
                show.title = element;
                show.image = posterFetcher.getPosterLink(element);
                show.numEpWatched = showCountMap.get(element);
                if(!showTitles.contains(element)){
                    showSection.allShows.add(show);
                }
                showTitles.add(element);
                
            }

        }

       ArrayList<String> topShows = getTopThree(showOnlyList);
       ArrayList<String> bottomShows = getBottomThree(showOnlyList);

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

        showSection.showActors.mostWatchedActors = actorInfo(showOnlyList);
        showSection.showActors.mostWatchedActors = actorInfo(showOnlyList);

        for (String actor : actorInfo(showOnlyList)) {
            JSONShow show = new JSONShow();
            show.title = actorFeaturedShows(actor, showOnlyList);
            show.image = posterFetcher.getPosterLink(actorFeaturedShows(actor, showOnlyList));
            showSection.showActors.actorFeaturedShows.add(show);
        }

        return showSection;

    }


    /**
     * get the map of counting how many times you watched the show
     * @param showTitleList the string array of userHistory
     * @return the map of show title to count
    */
    public Map<String, Integer> getShowCountMap(ArrayList<String> showTitleList){
        Map<String, Integer> showCountMap = new HashMap<String, Integer>();
        for (String title : showTitleList) {
            if (showCountMap.containsKey(title)) {
                showCountMap.put(title, showCountMap.get(title) + 1);
            } else {
                showCountMap.put(title, 1);
            }
        }
        return showCountMap;

    }

    /**
     * get the title list of shows
     * @param showList the string array of shows
     * @return the arraylist of just show titles
    */
    public ArrayList<String> getShowTitleList(ArrayList<Map<String, ArrayList<ArrayList<String>>>> showList){
        ArrayList<String> showTitleList = new ArrayList<String>();
        for (Map<String, ArrayList<ArrayList<String>>> map : showList) {
            for (Map.Entry<String, ArrayList<ArrayList<String>>> entry : map.entrySet()) {
                showTitleList.add(entry.getKey());
            }
        }
        return showTitleList;

    }


    /**
     * use helpers to create the top three shows you watched
     * @param showList the string map array of shows
     * @return the list of top three shows.
    */
    public ArrayList<String> getTopThree(ArrayList<Map<String, ArrayList<ArrayList<String>>>> showList) {
        ArrayList<String> topShowList = new ArrayList<String>();
        ArrayList<String> showTitleList = getShowTitleList(showList);

        Map<String, Integer> showCountMap = getShowCountMap(showTitleList);
        List<Map.Entry<String, Integer>> showCountList = new ArrayList<>(showCountMap.entrySet());
        Collections.sort(showCountList, (a, b) -> b.getValue().compareTo(a.getValue()));
        try{
            topShowList.add(showCountList.get(0).getKey());
            String mostWatchedvalueString = Integer.toString(showCountList.get(0).getValue());
            topShowList.add(mostWatchedvalueString);
            topShowList.add(showCountList.get(1).getKey());
            String secondMostWatchedvalueString = Integer.toString(showCountList.get(1).getValue());
            topShowList.add(secondMostWatchedvalueString);
            topShowList.add(showCountList.get(2).getKey());
            String thirdMostWatchedvalueString = Integer.toString(showCountList.get(2).getValue());
            topShowList.add(thirdMostWatchedvalueString);
        } catch(Exception e) {
            return topShowList;
        }
        return topShowList;
    }




    /**
     * use helpers to create the bottom three shows you watched
     * @param showList the string map array of shows
     * @return the list of bottom three shows.
    */
    public ArrayList<String> getBottomThree(ArrayList<Map<String, ArrayList<ArrayList<String>>>> showList) {
        ArrayList<String> topShowList = new ArrayList<String>();
        ArrayList<String> showTitleList = getShowTitleList(showList);

        Map<String, Integer> showCountMap = getShowCountMap(showTitleList);
        List<Map.Entry<String, Integer>> showCountList = new ArrayList<>(showCountMap.entrySet());
        Collections.sort(showCountList, (a, b) -> b.getValue().compareTo(a.getValue()));

        int sizeNum = showCountList.size() - 1;
        try {
            topShowList.add(showCountList.get(sizeNum).getKey());
            String mostWatchedvalueString = Integer.toString(showCountList.get(sizeNum).getValue());
            topShowList.add(mostWatchedvalueString);

            topShowList.add(showCountList.get(sizeNum-1).getKey());
            String secondMostWatchedvalueString = Integer.toString(showCountList.get(sizeNum-1).getValue());
            topShowList.add(secondMostWatchedvalueString);

            topShowList.add(showCountList.get(sizeNum-2).getKey());
            String thirdMostWatchedvalueString = Integer.toString(showCountList.get(sizeNum-2).getValue());
            topShowList.add(thirdMostWatchedvalueString);
        } catch (Exception e) {
            return topShowList;
        }
        return topShowList;
    }


    /**
     * The list of top actors
     * @param showList the string map array of shows
     * @return the list of top actors
    */
    public ArrayList<String> actorInfo(ArrayList<Map<String, ArrayList<ArrayList<String>>>> showList) {
        Map<String, Integer> actorCountMap = new HashMap<String, Integer>();
        ArrayList<String> finalActorList = new ArrayList<String>();
        if (showList.size() == 0){
            return finalActorList;
        }

        for (Map<String, ArrayList<ArrayList<String>>> map : showList) {
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
     * we recieve the show the target actor is in.
     * @param actor the actor to search
     * @return the arraylist of show the actor is in. only one is returned.
    */
    public String actorFeaturedShows(String actor,
            ArrayList<Map<String, ArrayList<ArrayList<String>>>> showList) {

        for (Map<String, ArrayList<ArrayList<String>>> map : showList) {
            for (Map.Entry<String, ArrayList<ArrayList<String>>> entry : map.entrySet()) {
                if (entry.getValue().get(4).contains(actor)) {
                    return entry.getKey();
                }

            }

        }
        return "";

    }


    /**
     * filters the users data into only just shows.
     * @param fullList the list of all user data.
     * @return the arraylist of the filtered data.
    */
    public ArrayList<Map<String, ArrayList<ArrayList<String>>>> showOnly(
            ArrayList<Map<String, ArrayList<ArrayList<String>>>> fullList) {

        ArrayList<Map<String, ArrayList<ArrayList<String>>>> showArray = new ArrayList<Map<String, ArrayList<ArrayList<String>>>>();

        for (Map<String, ArrayList<ArrayList<String>>> map : fullList) {
            for (Map.Entry<String, ArrayList<ArrayList<String>>> entry : map.entrySet()) {
                if (entry.getValue().size() != 1) {
                    if (entry.getValue().get(1).get(0).equals("TV Show")) {
                        showArray.add(map);
                    }
                }

            }
        }

        return showArray;
    }

}
