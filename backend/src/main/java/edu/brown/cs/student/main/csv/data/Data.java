package edu.brown.cs.student.main.csv.data;

/** Class for data representing user's csv and wrapped data */
public class Data {
  /**
   * Record for one user's csv
   * @param usercsv the csv of the user, represented as a 2D Array of Strings
   */
  public record UserCSV(String[][] usercsv) {}


  /**
   * represents user's wrapped report data
   *
   * @param top5Genres  List representing the user's top 5 genres watched
   * @param totalMin    Integer representing user's total watch time
   * @param bingeData   Object representing user's binge watching
   * @param shows       Object representing user's show watching report data
   * @param movie       Object representing user's movie watching report data
   * @param personality Object representing user's personality, determined by ml
   */
  public record WrappedData(
          GenreCount[] top5Genres,
          Integer totalMin,
          BingeData bingeData,

          Shows shows,

          Movie movie,

          Personality personality
  ) {
    /**
     * function that returns whether the wrapped data has any null fields
     * @return true, if the wrapped's fields are all filled. False, if any field is null.
     */
    public boolean noNullInfo() {
      //top layer
      if (top5Genres != null && totalMin != null && bingeData != null && shows != null && movie != null && personality != null) {
        for (GenreCount top5Genre : top5Genres) {
          if (!top5Genre.noNullInfo()) {
            return false;
          };
        }
        if (!bingeData.noNullInfo()) {
          return false;
        }
        if (!shows.noNullInfo()) {
          return false;
        }
        if (!movie.noNullInfo()) {
          return false;
        }
        if (!personality.noNullInfo()) {
          return false;
        }
      } else {
        return false;
      }
      return true;
    }
  }

  /**
   * represents the genre and number of media in that genre that the user watched
   *
   * @param genre the genre
   * @param count the number of media in that genre that the user watched
   */
  public record GenreCount(String genre, Integer count) {
    public boolean noNullInfo() {
      return(
              genre != null && count != null
              );
    }
  }

  /**
   * represents user's binge watching
   *
   * @param date   date that they watched most media
   * @param shows  list of shows and info related to those shows, watched on date
   * @param movies list of movies and info related to those movies, watched on
   *               date
   */
  public record BingeData(String date, TitleNEWImage[] shows, TitleImage[] movies) {
    public boolean noNullInfo() {
      if (date != null && shows != null && movies != null) {
        for (TitleNEWImage show: shows) {
          if (!show.noNullInfo()) {
            return false;
          }
        }
        for (TitleImage movie: movies) {
          if (!movie.noNullInfo()) {
            return false;
          }
        }
      } else {
        return false;
      }
      return true;
    }
  }

  /**
   * represents a piece of media's metadata
   *
   * @param title        title of the media
   * @param numEpWatched episodes (or times) watched of that media
   * @param image        image url for that media
   */
  public record TitleNEWImage(String title, Integer numEpWatched, String image) {
    public boolean noNullInfo() {
      return (title != null && numEpWatched != null && image != null);
    }
  }

  /**
   * represents a piece of media's metadata
   *
   * @param title title of the media
   * @param image image url for that media
   */
  public record TitleImage(String title, String image) {
    public boolean noNullInfo() {
      return (title != null  && image != null);
    }
  }

  /**
   * user's show report info
   *
   * @param totalEpWatched total episodes watched
   * @param allShows       list of all shows watched and show metadata
   * @param topShows       object representing user's top shows
   * @param showActors     object representing user's actor preferences for show
   *                       actors
   */
  //note: allShows is TitleNEWImage[] but some in the array might have no NEW
  public record Shows(Integer totalEpWatched, TitleNEWImage[] allShows, TopShows topShows, ShowActors showActors) {
    public boolean noNullInfo() {
      if (totalEpWatched != null && allShows != null && topShows != null && showActors != null) {
        for (TitleNEWImage show : allShows) {
          if (!show.noNullInfo()) {
            return false;
          }
        }
        if (!topShows.noNullInfo() && showActors.noNullInfo()) {
          return false;
        }

      } else {
        return false;
      }
      return true;
    }
  }

  /**
   * represents users top shows
   *
   * @param mostWatched  list of shows and their metadata that user has watched
   *                     most of
   * @param leastWatched list of shows and their metadata that user has watched
   *                     least of
   */
  public record TopShows(TitleNEWImage[] mostWatched, TitleNEWImage[] leastWatched) {
    public boolean noNullInfo() {
      if (mostWatched != null && leastWatched != null) {
        for (TitleNEWImage show: mostWatched) {
          if (!show.noNullInfo()) {
            return false;
          }
        }
        for (TitleNEWImage show: leastWatched) {
          if (!show.noNullInfo()) {
            return false;
          }
        }
      } else {
        return false;
      }
      return true;
    }
  }

  /**
   * object representing user's actor preferences for shows
   *
   * @param mostWatchedActors  list of actors users watched the most of
   * @param actorFeaturedShows shows that the most watched actors are in
   */
  public record ShowActors(String[] mostWatchedActors, TitleImage[] actorFeaturedShows) {
    public boolean noNullInfo() {
      if (mostWatchedActors != null && actorFeaturedShows != null) {
        for (String actor: mostWatchedActors) {
          if (actor == null) {
            return false;
          }
        }
        for (TitleImage show: actorFeaturedShows) {
          if (!show.noNullInfo()) {
            return false;
          }
        }
      } else {
        return false;
      }
      return true;
    }
  }

  /**
   * Object representing user's movie watching report data
   *
   * @param allMovies   list of all movies watched and show metadata
   * @param top5Movies  list of user's top 5 movies and movie metadata
   * @param movieActors object representing user's actor preferences for movie
   *                    actors
   */
  //note: allMovies is TitleNEWImage[] but some in the array might have no NEW
  public record Movie(TitleNEWImage[] allMovies, TitleNEWImage[] top5Movies, MovieActors movieActors) {
    public boolean noNullInfo() {
      if (allMovies != null && top5Movies != null && movieActors != null) {
        for (TitleNEWImage movie: allMovies) {
          if (!movie.noNullInfo()) {
            return false;
          }
        }
        for (TitleNEWImage movie: top5Movies) {
          if (!movie.noNullInfo()) {
            return false;
          }
        }
        return movieActors().noNullInfo();
      } else {
        return false;
      }
    }
  }

  /**
   * object representing user's preference for movie actors
   *
   * @param mostWatchedActors   list of actors users watched the most of
   * @param actorFeaturedMovies movies that the most watched actors are in
   *
   */
  public record MovieActors(String[] mostWatchedActors, TitleImage[] actorFeaturedMovies) {
    public boolean noNullInfo() {
      if (mostWatchedActors != null && actorFeaturedMovies != null) {
        for (String actor: mostWatchedActors) {
          if (actor == null) {
            return false;
          }
        }
        for (TitleImage movie: actorFeaturedMovies) {
          if (!movie.noNullInfo()) {
            return false;
          }
        }
      } else {
        return false;
      }
      return true;
    }
  }

  /**
   * object representing user's personality, determined by ml
   *
   * @param title       title of the user's personality type
   * @param description
   */
  public record Personality(String title, String description){
    public boolean noNullInfo() {
      return (title != null && description != null);
    }
  }
}
