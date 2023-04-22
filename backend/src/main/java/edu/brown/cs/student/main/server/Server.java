package edu.brown.cs.student.main.server;

import static spark.Spark.after;

import edu.brown.cs.student.main.server.handlers.SaveDataHandler;
import edu.brown.cs.student.main.server.handlers.WipeDataHandler;
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

    // serverInfo contains the shared states, including the 'database' of user csvs
    ServerInfo serverInfo = new ServerInfo();
    // System.out.println("server info's redlining geojson is ccurently initalizing as " +
    // serverInfo.getFullRedliningGeoJSON());

    // set up loadData and wipeData endpoints
    Spark.get("saveData", new SaveDataHandler(serverInfo));
    // System.out.print(serverInfo.getFullRedliningGeoJSON().keySet());
    Spark.get("wipeData", new WipeDataHandler(serverInfo));

    Spark.init();
    Spark.awaitInitialization();
    System.out.println("Server started.");
  }
}
