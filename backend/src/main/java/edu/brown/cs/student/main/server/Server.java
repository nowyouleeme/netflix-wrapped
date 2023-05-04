package edu.brown.cs.student.main.server;

import static spark.Spark.after;

import edu.brown.cs.student.main.reports.MockRGenerator;
import edu.brown.cs.student.main.reports.ReportGenerator;
import edu.brown.cs.student.main.server.handlers.SaveDataHandler;
import edu.brown.cs.student.main.server.handlers.WipeDataHandler;
import edu.brown.cs.student.main.server.handlers.WrappedHandler;
import spark.Spark;

/** Class that holds our Server (entry point). */
public class Server {

  /**
   * The initial method called when execution begins.
   *
   * @param args An array of command line arguments
   */
  public static void main(String[] args) {
    Spark.port(6969);
    after(
        (request, response) -> {
          response.header("Access-Control-Allow-Origin", "*");
          response.header("Access-Control-Allow-Methods", "*");
        });

    //TODO: initialize with real generator!!
    ReportGenerator mockRGenerator = new MockRGenerator();
    // serverInfo contains the shared states, including the current user csv and the report generator of choice
    ServerInfo serverInfo = new ServerInfo(mockRGenerator);


    // set up loadData and wipeData and wrapped endpoints
    Spark.get("saveData", new SaveDataHandler(serverInfo));
    // System.out.print(serverInfo.getFullRedliningGeoJSON().keySet());
    Spark.get("wipeData", new WipeDataHandler(serverInfo));
    Spark.get("wrapped", new WrappedHandler(serverInfo));

    Spark.init();
    Spark.awaitInitialization();
    System.out.println("Server started.");
  }
}
