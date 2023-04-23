interface WrappedMoviesProps {
  year: number;
  movies: {
    title: string;
    numTimesWatched: number;
    image: string;
  }[];
}

export function WrappedMoviesOverview(props: WrappedMoviesProps) {
  // receive map of other favorites

  function showOtherFavs(
    movies: {
      title: string;
      numTimesWatched: number;
      image: string;
    }[]
  ) {

    let favMovies: JSX.Element[] = []

    for (let i=0; i < movies.length; i++) {
      if (i!==0) {
        favMovies.push(
          <div className="Episode">
            <div className="EpisodeImg">
              <img
                src={require("../" + movies[i].image)}
                alt={`Poster for the movie '${movies[i].title}'`}
              />
            </div>
            <div className="EpisodeDesc">
              <div className="DescContentMovie">
                <p>
                  You watched <i>{movies[i].title}</i>
                </p>
                <h2>{movies[i].numTimesWatched} times</h2>
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
      <div className="FavoriteMovie">
        <h3>When it comes to movies,</h3>
        <p>
          you were a number one fan for <i>{props.movies[0].title}</i>:
        </p>
        <div className="center">
          <div className="favoriteMovieImg">
            <img
              src={require("../" + props.movies[0].image)}
              alt={`Poster for the movie '${props.movies[0].title}'`}
            />
          </div>
          <p>
            which you watched <b>{props.movies[0].numTimesWatched} times</b> in{" "}
            {props.year}.
          </p>
        </div>
      </div>
      <div className="center OtherMovieFavorites">
        <h4>Some of your other favorites included:</h4>
        <div className="Episodes">{showOtherFavs(props.movies)}</div>
      </div>
    </div>
  );
}
