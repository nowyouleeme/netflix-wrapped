package edu.brown.cs.student.main.server;

import static spark.Spark.after;

import edu.brown.cs.student.main.server.handlers.MovieHandler;
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

    // serverInfo contains the shared states, including the 'database' of user csvs
    ServerInfo serverInfo = new ServerInfo();
    // System.out.println("server info's redlining geojson is ccurently initalizing as " +
    // serverInfo.getFullRedliningGeoJSON());

    // set up loadData and wipeData endpoints

    String[][] movieData1 = { 
      {"Seinfeld: Season 8: The Little Kicks", "4/14/23"},
      {"The Net", "5/5/22"},
      {"Valentine's Day", "2/14/21"},
      {"Rurouni Kenshin: The Beginning", "6/4/22"},
      {"Transformers: War for Cybertron: Kingdom", "7/29/21"},
      {"Yowamushi Pedal", "7/29/21"},
      {"Pok\u00e9mon Master Journeys: The Series", "7/29/21"},
      {"The Net", "5/5/22"},
      {"The Net", "5/5/22"},
      {"The Net", "5/5/22"},
      {"The Net", "5/5/22"},
      {"Valentine's Day", "2/14/21"},
      {"Valentine's Day", "2/14/21"},
      {"Valentine's Day", "2/14/21"},
      {"Valentine's Day", "2/14/21"},
      {"Valentine's Day", "2/14/21"},
      {"Valentine's Day", "2/14/21"},
      {"Valentine's Day", "2/14/21"},
  };
    Spark.get("saveData", new SaveDataHandler(serverInfo));
    // System.out.print(serverInfo.getFullRedliningGeoJSON().keySet());
    Spark.get("wipeData", new WipeDataHandler(serverInfo));
    Spark.get("wrapped", new WrappedHandler(serverInfo));
    Spark.get("movies", new MovieHandler(movieData1));

    Spark.init();
    Spark.awaitInitialization();
    System.out.println("Server started.: http://localhost:6969");
  }
}
