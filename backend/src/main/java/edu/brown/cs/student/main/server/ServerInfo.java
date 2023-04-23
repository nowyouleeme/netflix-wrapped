package edu.brown.cs.student.main.server;

import edu.brown.cs.student.main.csv.data.Data;
import edu.brown.cs.student.main.user.UserID;

import java.util.HashMap;
import java.util.Map;

/** Class for our ServerInfo object initialized upon starting the Server. */
public class ServerInfo {
  //has one field storing all the users' csvs
  private Data.UserCSV currentUserCSV;

  /**
   * Constructor for our ServerInfo object, that is responsible for creating an empty userCSVs map
   */
  public ServerInfo() {
    // usercsv initialized as null
  }

  public void saveUserData(Data.UserCSV userCSV) {
    this.currentUserCSV = userCSV;
    //TODO: maybe might need something like 'checking if the password is correct'..
    //  it's okay to replace a user's old csv with new csv, as long as the username and password are right
  }

  public void wipeUserData() {
    this.currentUserCSV = null; //TODO: null handling
    //TODO: maybe might need something like 'checking if the password is correct'..
    //  maybe try catch in case data was unsaved (therefore not in map)
  }



}
