package edu.brown.cs.student.main.server;
import edu.brown.cs.student.main.csv.data.Data;
import edu.brown.cs.student.main.reports.ReportGenerator;

import java.io.IOException;

/** Class for our ServerInfo object initialized upon starting the Server. */
public class ServerInfo {
  //has one field storing all the users' csvs
  private Data.UserCSV currentUserCSV;
  private ReportGenerator reportGenerator;

  /**
   * Constructor for our ServerInfo object, that is responsible for creating an empty userCSVs map
   * @param reportGenerator the ReportGenerator that will be used to generate user's wrapped reports given the
   *                        current csv in the server.
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
  }

  /**
   * function to wipe the user csv data in our server. If the current user csv is already null, no error is thrown but
   * rather the field is set to null redundantly.
   */
  public void wipeUserData() {
    this.currentUserCSV = null;
  }

  /**
   * function that generates the json with all the information contained in the wrapped report.
   * @return a String representing the json
   */
  public Data.WrappedData generateReportJSON() throws IOException {
    return reportGenerator.generateReportJSON(currentUserCSV.usercsv());
  }

  /**
   * function that gets the user csv data stored in the server, this function is only used mostly for testing purposes
   * @return the Data.UserCSV object stored in the server
   */
  public Data.UserCSV getUserData() {
    return this.currentUserCSV;
  }



}
