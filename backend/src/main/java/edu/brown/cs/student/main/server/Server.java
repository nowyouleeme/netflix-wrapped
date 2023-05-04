package edu.brown.cs.student.main.server;

import static spark.Spark.after;

import java.io.FileWriter;
import java.io.IOException;

import edu.brown.cs.student.main.reports.MLGenerator;
import edu.brown.cs.student.main.server.handlers.MovieHandler;

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

    // serverInfo contains the shared states, including the 'database' of user csvs
    // ServerInfo serverInfo = new ServerInfo();
    // System.out.println("server info's redlining geojson is ccurently initalizing
    // as " +
    // serverInfo.getFullRedliningGeoJSON());

    // set up loadData and wipeData endpoints
    System.out.println("getting printed");

    String[][] movieData1 = {
        { "Valentine's Day", "2/14/21" },
        { "Valentine's Day", "9/21/21" },
        { "Valentine's Day", "9/21/21" },

        { "The Golden Child", "10/21/21" },
        { "The Golden Child", "10/21/21" },
        { "The Golden Child", "10/21/21" },

        { "The Guns of Navarone", "10/21/21" },
        { "The Guns of Navarone", "13/21/21" },
        { "The Guns of Navarone", "10/22/21" },
        { "The Guns of Navarone", "11/21/21" },
        { "The Guns of Navarone", "10/21/21" },
        { "The Guns of Navarone", "0/21/21" },

        { "Elephants Dream 4 Hour", "0/21/21" },
        { "Elizabeth at 90: A Family Tribute", "0/21/21" },
        { "Elizabeth at 90: A Family Tribute", "0/21/21" },
        { "Elizabeth Harvest", "0/21/21" },

        { "Elizabeth Harvest", "0/21/21" },
        { "Elizabeth Harvest", "0/21/21" },
        { "Elizabeth Harvest", "0/21/21" },
        { "Elizabeth Harvest", "0/21/21" },

        { "Squid Game: level2", "0/14/21" },
        { "Squid Game: level1", "0/21/21" },
        { "Squid Game: level1", "9/21/21" },

        { "Dear White People", "0/21/21" },
        { "Euphoria: level1", "9/21/21" },

        { "asflnv", "2/14/21" },
        { "asflnv", "9/21/21" },
    };
    String[][] movieData2 = {

        { "Community: Season 1: Advanced Criminal Law", "4/10/20" },
        { "The Golden Child", "10/21/21" },

        { "The Guns of Navarone", "10/21/21" },
        { "The Guns of Navarone", "13/21/21" },
        { "The Guns of Navarone", "10/22/21" },
        { "The Guns of Navarone", "11/21/21" },
        { "The Guns of Navarone", "10/21/21" },
        { "The Guns of Navarone", "0/21/21" },

        { "Elephants Dream 4 Hour", "0/21/21" },
        { "Elizabeth at 90: A Family Tribute", "0/21/21" },
        { "Elizabeth at 90: A Family Tribute", "0/21/21" },
        { "Elizabeth Harvest", "0/21/21" },

        { "Elizabeth Harvest", "0/21/21" },
        { "Elizabeth Harvest", "0/21/21" },
        { "Elizabeth Harvest", "0/21/21" },
        { "Elizabeth Harvest", "0/21/21" },

        { "Squid Game: level2", "0/14/21" },
        { "Squid Game: level1", "0/21/21" },
        { "Squid Game: level1", "9/21/21" },

        { "Dear White People", "0/21/21" },
        { "Euphoria: level1", "9/21/21" },

        { "asflnv", "2/14/21" },
        { "asflnv", "9/21/21" },
    };
    String[][] movieData3 = {
        { "Parks and Recreation: Season 2: Episode 5", "4/12/20" },
        { "Parks and Recreation: Season 2: Episode 4", "4/12/20" },
        { "Parks and Recreation: Episode 1", "4/12/20" },
        { "Greenhouse Academy: Season 2: Escape Mechanism", "4/12/20" },
        { "Community: Season 1: Home Economics", "4/12/20" },
        { "The Office (U.S.): Season 2: Episode 10", "4/11/20" },
        { "The Office (U.S.): Season 2: Episode 9", "4/11/20" },
        { "Gilmore Girls: Episode 6", "4/11/20" },
        { "Gilmore Girls: Episode 5", "4/11/20" },
        { "Gilmore Girls: Episode 4", "4/11/20" },
        { "Gilmore Girls: Episode 3", "4/10/20" },
        { "Gilmore Girls: Episode 2", "4/10/20" },
        { "Community: Season 1: Introduction to Statistics", "4/10/20" },
        { "Community: Season 1: Football, Feminism and You", "4/10/20" },
        { "Community: Season 1: Advanced Criminal Law", "4/10/20" },
        { "Community: Season 1: Social Psychology", "4/10/20" },
        { "Community: Season 1: Introduction to Film", "4/10/20" },
        { "Community: Season 1: Spanish 101", "4/10/20" },
        { "Valentine's Day", "2/14/21" },
        { "Valentine's Day", "9/21/21" },
        { "Valentine's Day", "9/21/21" },

        { "The Golden Child", "10/21/21" },
        { "The Golden Child", "10/21/21" },
        { "The Golden Child", "10/21/21" },

        { "The Guns of Navarone", "10/21/21" },
        { "The Guns of Navarone", "10/21/21" },
        { "The Guns of Navarone", "0/21/21" },
        { "Elizabeth at 90: A Family Tribute", "0/21/21" },
        { "Elizabeth Harvest", "0/21/21" },
        { "asflnv", "2/14/21" },
        { "asflnv", "9/21/21" },
    };

    // try (FileWriter writer = new FileWriter("backend/backend-ml/data/viewhist.csv")) {
    //   for (int j = 0; j < movieData3.length; j++) {
    //     writer.append("\""+movieData3[j][0]+"\"");
    //     writer.append(",");
    //     writer.append("\""+movieData3[j][1]+"\"");
    //     writer.append("\n");
    //   }
    //   writer.close();
    // } catch (IOException e) {
    //   // TODO Auto-generated catch block
    //   e.printStackTrace();
    // }

    // TODO: initialize with real generator!!
    ReportGenerator generator = new MLGenerator();
    // serverInfo contains the shared states, including the current user csv and the
    // report generator of choice
    ServerInfo serverInfo = new ServerInfo(generator);

    // set up loadData and wipeData and wrapped endpoints
    Spark.get("saveData", new SaveDataHandler(serverInfo));
    // System.out.print(serverInfo.getFullRedliningGeoJSON().keySet());
    Spark.get("wipeData", new WipeDataHandler(serverInfo));
    Spark.get("wrapped", new WrappedHandler(serverInfo));

    Spark.get("movies", new MovieHandler(movieData2));

    Spark.init();
    Spark.awaitInitialization();
    System.out.println("Server started.: http://localhost:6969");
  }
}
