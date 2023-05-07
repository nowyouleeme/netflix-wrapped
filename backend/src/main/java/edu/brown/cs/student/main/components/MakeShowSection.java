package edu.brown.cs.student.main.components;

import edu.brown.cs.student.main.PosterImageGetting.PosterFetch;
import edu.brown.cs.student.main.components.JsonDataType.JSONShowSection;
import edu.brown.cs.student.main.components.JsonDataType.JSONShowSection.JSONShow;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MakeShowSection {

  public JSONShowSection getShowSection(
      String[][] userHistory,
      ArrayList<Map<String, ArrayList<ArrayList<String>>>> userHistoryMapList) {
    PosterFetch posterFetcher = new PosterFetch();
    JSONShowSection showSection = new JSONShowSection();

    ArrayList<Map<String, ArrayList<ArrayList<String>>>> showOnlyList =
        showOnly(userHistoryMapList);
    // System.out.println("movie only list size" + movieOnlyList.size());
    showSection.totalEpWatched = showOnlyList.size();

    Map<String, Integer> showCountMap = getShowCountMap(getShowTitleList(showOnlyList));

    // although a for loop, this is adding only one movie title.
    ArrayList<String> showTitles = new ArrayList<String>();
    for (int i = 0; i < showOnlyList.size(); i++) {
      Set<String> showSet = showOnlyList.get(i).keySet();
      for (String element : showSet) {
        JSONShow show = new JSONShow();
        show.title = element;
        show.image = posterFetcher.getPosterLink(element);
        show.numEpWatched = showCountMap.get(element);
        if (!showTitles.contains(element)) {
          showSection.allShows.add(show);
        }
        showTitles.add(element);
      }
    }
    // showSection.topShows.above50_3
    ArrayList<String> topShows = getTopThree(showOnlyList);
    ArrayList<String> bottomShows = getBottomThree(showOnlyList);

    for (int i = 0; i < topShows.size(); i++) {
      if (i % 2 == 0) {
        JSONShow mostShow = new JSONShow();
        mostShow.title = topShows.get(i);
        mostShow.image = posterFetcher.getPosterLink(topShows.get(i));
        mostShow.numEpWatched = Integer.parseInt(topShows.get(i + 1));
        showSection.topShows.mostWatched.add(mostShow);
      }
    }
    for (int i = 0; i < bottomShows.size(); i++) {
      if (i % 2 == 0) {
        JSONShow mostShow = new JSONShow();
        mostShow.title = bottomShows.get(i);
        mostShow.image = posterFetcher.getPosterLink(bottomShows.get(i));
        mostShow.numEpWatched = Integer.parseInt(bottomShows.get(i + 1));
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

  public Map<String, Integer> getShowCountMap(ArrayList<String> showTitleList) {
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

  public ArrayList<String> getShowTitleList(
      ArrayList<Map<String, ArrayList<ArrayList<String>>>> showList) {
    ArrayList<String> showTitleList = new ArrayList<String>();
    for (Map<String, ArrayList<ArrayList<String>>> map : showList) {
      for (Map.Entry<String, ArrayList<ArrayList<String>>> entry : map.entrySet()) {
        showTitleList.add(entry.getKey());
      }
    }
    return showTitleList;
  }

  public ArrayList<String> getTopThree(
      ArrayList<Map<String, ArrayList<ArrayList<String>>>> showList) {
    ArrayList<String> topShowList = new ArrayList<String>();
    ArrayList<String> showTitleList = getShowTitleList(showList);

    Map<String, Integer> showCountMap = getShowCountMap(showTitleList);
    List<Map.Entry<String, Integer>> showCountList = new ArrayList<>(showCountMap.entrySet());
    Collections.sort(showCountList, (a, b) -> b.getValue().compareTo(a.getValue()));
    try {
      topShowList.add(showCountList.get(0).getKey());
      String mostWatchedvalueString = Integer.toString(showCountList.get(0).getValue());
      topShowList.add(mostWatchedvalueString);
      topShowList.add(showCountList.get(1).getKey());
      String secondMostWatchedvalueString = Integer.toString(showCountList.get(1).getValue());
      topShowList.add(secondMostWatchedvalueString);
      topShowList.add(showCountList.get(2).getKey());
      String thirdMostWatchedvalueString = Integer.toString(showCountList.get(2).getValue());
      topShowList.add(thirdMostWatchedvalueString);
    } catch (Exception e) {
      return topShowList;
    }
    return topShowList;
  }

  public ArrayList<String> getBottomThree(
      ArrayList<Map<String, ArrayList<ArrayList<String>>>> showList) {
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

      topShowList.add(showCountList.get(sizeNum - 1).getKey());
      String secondMostWatchedvalueString =
          Integer.toString(showCountList.get(sizeNum - 1).getValue());
      topShowList.add(secondMostWatchedvalueString);

      topShowList.add(showCountList.get(sizeNum - 2).getKey());
      String thirdMostWatchedvalueString =
          Integer.toString(showCountList.get(sizeNum - 2).getValue());
      topShowList.add(thirdMostWatchedvalueString);
    } catch (Exception e) {
      return topShowList;
    }
    return topShowList;
  }

  // returns top three or less actors
  public ArrayList<String> actorInfo(
      ArrayList<Map<String, ArrayList<ArrayList<String>>>> showList) {
    Map<String, Integer> actorCountMap = new HashMap<String, Integer>();
    ArrayList<String> finalActorList = new ArrayList<String>();
    if (showList.size() == 0) {
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

    // Print the top 5 genres with their counts
    int num = Math.min(3, actorCountlist.size());
    for (int i = 0; i < num; i++) {
      finalActorList.add(actorCountlist.get(i).getKey());
    }
    return finalActorList;
  }

  public String actorFeaturedShows(
      String actor, ArrayList<Map<String, ArrayList<ArrayList<String>>>> showList) {

    for (Map<String, ArrayList<ArrayList<String>>> map : showList) {
      for (Map.Entry<String, ArrayList<ArrayList<String>>> entry : map.entrySet()) {
        if (entry.getValue().get(4).contains(actor)) {
          return entry.getKey();
        }
      }
    }
    return "";
  }

  public ArrayList<Map<String, ArrayList<ArrayList<String>>>> showOnly(
      ArrayList<Map<String, ArrayList<ArrayList<String>>>> fullList) {

    ArrayList<Map<String, ArrayList<ArrayList<String>>>> showArray =
        new ArrayList<Map<String, ArrayList<ArrayList<String>>>>();

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
