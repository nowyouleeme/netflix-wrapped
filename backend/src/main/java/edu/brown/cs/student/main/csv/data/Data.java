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

  public record GenreCount(String genre, Integer count) {
    public boolean noNullInfo() {
      return(
              genre != null && count != null
              );
    }
  }

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

  public record TitleNEWImage(String title, Integer numEpWatched, String image) {
    public boolean noNullInfo() {
      return (title != null && numEpWatched != null && image != null);
    }
  }

  public record TitleImage(String title, String image) {
    public boolean noNullInfo() {
      return (title != null  && image != null);
    }
  }

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

  public record Personality(String title, String description){
    public boolean noNullInfo() {
      return (title != null && description != null);
    }
  }
}
