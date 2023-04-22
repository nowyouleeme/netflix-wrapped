package edu.brown.cs.student.main.server;

import edu.brown.cs.student.main.csv.data.Data;
import edu.brown.cs.student.main.user.UserID;

import java.util.HashMap;
import java.util.Map;

/** Class for our ServerInfo object initialized upon starting the Server. */
public class ServerInfo {
  //has one field storing all the users' csvs
  private Map<UserID, Data.UserCSV> userCSVs;

  /**
   * Constructor for our ServerInfo object, that is responsible for creating an empty userCSVs map
   */
  public ServerInfo() {
    // new map
    this.userCSVs = new HashMap<>();
  }

  public void saveUserData(UserID userID, Data.UserCSV userCSV) {
    userCSVs.put(userID, userCSV);
    //TODO: maybe might need something like 'checking if the password is correct'..
    //  it's okay to replace a user's old csv with new csv, as long as the username and password are right
  }

  public void wipeUserData(UserID userID) {
    userCSVs.remove(userID);
    //TODO: maybe might need something like 'checking if the password is correct'..
    //  maybe try catch in case data was unsaved (therefore not in map)
  }



}
