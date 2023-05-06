package edu.brown.cs.student.main.components;

import java.util.ArrayList;
import java.util.Map;

import edu.brown.cs.student.main.components.JsonDataType.JSONTotalMin;

public class MakeTotalMin {
    public MakeTotalMin() {
    }

    public JSONTotalMin getTotalMin(String[][] userHistory,
    ArrayList<Map<String, ArrayList<ArrayList<String>>>> userHistoryMapList) {
        JSONTotalMin finalToJsonTotalMin = new JSONTotalMin();

        int totalMin = 0;

        for (Map<String, ArrayList<ArrayList<String>>> map : userHistoryMapList) {
            for (Map.Entry<String, ArrayList<ArrayList<String>>> entry : map.entrySet()) {
                // if movie (clear length of film)
                if (entry.getValue().size() != 1) {
                    if (entry.getValue().get(1).get(0).equals("Movie")) {
                        String intString = entry.getValue().get(3).get(0).split(" ")[0];

                        totalMin = totalMin + Integer.parseInt(intString);
                    }
                    // its a series, not a clear length of film
                    else {
                        // for now, each episode is 30 min
                        totalMin = totalMin + 30;
                    }
                } else {
                    totalMin = totalMin + 30;
                }
            }
        }
        finalToJsonTotalMin.totalMin = totalMin;
        return finalToJsonTotalMin;
    }
}
