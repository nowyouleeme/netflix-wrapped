package edu.brown.cs.student.main.csv.data;

/** Class for converting the GeoJSON to a Data object. */
public class Data {
  /**
   * Record for one user's csv
   * @param usercsv the csv of the user, represented as a 2D Array of Strings
   */
  public record UserCSV(String[][] usercsv) {}


  public record WrappedData(
          GenreCount[] top5Genres,
          Integer totalMin,
          BingeData bingeData,

          Shows shows,

          Movie movie,

          Personality personality
  ) {}

  public record GenreCount(String genre, Integer count) {}

  public record BingeData(String date, TitleNEWImage[] shows, TitleImage[] movies) {}

  public record TitleNEWImage(String title, Integer numEpWatched, String image) {}

  public record TitleImage(String title, String image) {}

  //note: allShows is TitleNEWImage[] but some in the array might have no NEW
  public record Shows(Integer totalEpWatched, TitleNEWImage[] allShows, TopShows topShows, ShowActors showActors) {}

  public record TopShows(TitleNEWImage[] mostWatched, TitleNEWImage[] leastWatched) {}

  public record ShowActors(String[] mostWatchedActors, TitleImage[] actorFeaturedShows) {}

  //note: allMovies is TitleNEWImage[] but some in the array might have no NEW
  public record Movie(TitleNEWImage[] allMovies, TitleNEWImage[] top5Movies, MovieActors movieActors) {}

  public record MovieActors(String[] mostWatchedActors, TitleImage[] actorFeaturedMovies) {}

  public record Personality(String title, String description){}
}
