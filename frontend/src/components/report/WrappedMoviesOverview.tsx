/**
 * An interface containing all of the properties expected of the props that are passed into the WrappedMoviesOverview component.
 */
interface WrappedMoviesProps {
  year: number;
  movies: {
    title: string;
    numEpWatched: number;
    image: string;
  }[];
}
export const remaining_movies = "Remaining top 4 movies watched";
export const favorite_movie = "Most watched movie";

/**
 * Function that returns a WrappedMoviesOverview component, 
 * which displays information on all of the movies a user has watched in their NetflixViewingHistory.csv and 
 * how many times they watched it.
 * @param props all of the information needed to display the movies a user has watched
 * @returns a WrappedMoviesOverview component
 */
export function WrappedMoviesOverview(props: WrappedMoviesProps) {
  // receive map of other favorites

  /**
   * Function that generates JSX.Elements for the remaining movies that are not the top most watched.
   * @param movies the movies that are not the top most watched, but are part of the movies the user most frequently watched.
   * @returns an array of JSX.Elements visualizing the movies
   */
  function showOtherFavs(
    movies: {
      title: string;
      numEpWatched: number;
      image: string;
    }[]
  ) {
    let favMovies: JSX.Element[] = [];

    for (let i = 0; i < movies.length; i++) {
      if (i !== 0) {
        favMovies.push(
          <div className="Episode" key={`Poster for '${movies[i].title}'"`}>
            <div className="EpisodeImg">
              <a
                rel="noreferrer"
                href={"https://www.google.com/search?q=" + movies[i].title}
                target="_blank"
              >
                <img
                  src={movies[i].image}
                  alt={`Poster for the movie '${movies[i].title}'`}
                />
              </a>
            </div>
            <div className="EpisodeDesc">
              <div className="DescContentMovie">
                <p className="moviesDescP">
                  You watched <i>{movies[i].title}</i>
                </p>
                <p className="moviesDisplayP">
                  {movies[i].numEpWatched}{" "}
                  {movies[i].numEpWatched > 1 ? "times" : "time"}
                </p>
              </div>
            </div>
          </div>
        );
      }
    }

    return favMovies;
  }

  return (
    <div className="center WrappedMoviesOverview">
      <div role="figure" aria-label={favorite_movie} className="FavoriteMovie">
        <p className="moviesBigP">When it came to movies,</p>
        <p className="moviesDescP">
          you were a number one fan for <i>{props.movies[0].title}</i>:
        </p>
        <div className="center">
          <div className="favoriteMovieImg">
            <a
              rel="noreferrer"
              href={"https://www.google.com/search?q=" + props.movies[0].title}
              target="_blank"
            >
              <img
                src={props.movies[0].image}
                alt={`Poster for the movie '${props.movies[0].title}'`}
              />
            </a>
          </div>
          <p className="moviesDescP">
            which you watched{" "}
            <b>
              {props.movies[0].numEpWatched}{" "}
              {props.movies[0].numEpWatched > 1 ? "times" : "time"}
            </b>
            .
          </p>
        </div>
      </div>
      <div className="center OtherMovieFavorites">
        <p className="moviesSubP">Some of your other favorites included:</p>
        <div role="figure" aria-label={remaining_movies} className="Episodes">
          {showOtherFavs(props.movies)}
        </div>
      </div>
    </div>
  );
}
