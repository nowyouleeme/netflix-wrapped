package edu.brown.cs.student.main.server;

import com.squareup.moshi.Json;
import edu.brown.cs.student.main.components.JsonDataType.JSONFinalFetch;
import edu.brown.cs.student.main.csv.data.Data;
import edu.brown.cs.student.main.reports.ReportGenerator;
import edu.brown.cs.student.main.user.UserID;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/** Class for our ServerInfo object initialized upon starting the Server. */
public class ServerInfo {
  //has one field storing all the users' csvs
  private Data.UserCSV currentUserCSV;
  private ReportGenerator reportGenerator;

  /**
   * Constructor for our ServerInfo object, that is responsible for creating an empty userCSVs map
   */
  public ServerInfo(ReportGenerator reportGenerator) {
    // note thatusercsv initialized as null
    this.reportGenerator = reportGenerator;
  }

  /**
   * function to save the user csv data in our server
   * @param userCSV the user csv that we are saving in our server
   */
  public void saveUserData(Data.UserCSV userCSV) {
    this.currentUserCSV = userCSV;
    //TODO: maybe might need something like 'checking if the password is correct'..
    //  it's okay to replace a user's old csv with new csv, as long as the username and password are right
  }

  /**
   * function to wipe the user csv data in our server
   */
  public void wipeUserData() {
    this.currentUserCSV = null; //TODO: null handling
    //TODO: maybe might need something like 'checking if the password is correct'..
    //  maybe try catch in case data was unsaved (therefore not in map)
  }

  /**
   * function that generates the json with all the information contained in the wrapped report.
   * @return a String representing the json
   */
  public Data.WrappedData generateReportJSON() throws IOException {
    return reportGenerator.generateReportJSON(currentUserCSV.usercsv());
  }

  /**
   * function that gets the user csv data stored in the server, this function is only used for testing purposes
   * @return the Data.UserCSV object stored in the server
   */
  public Data.UserCSV getUserData() {
    return this.currentUserCSV;
  }



}
