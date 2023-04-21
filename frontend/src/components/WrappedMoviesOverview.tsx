interface WrappedMoviesProps {
    nameMovie: string;
    favNum: number;
}

export function WrappedMoviesOverview(props: WrappedMoviesProps) {
    // receive map of other favorites
    return (
      <div className="center WrappedMoviesOverview">
        <div className="FavoriteMovie">
          <h3>When it comes to movies,</h3>
          <p>
            you were a number one fan for{" "}
            <i>{props.nameMovie}</i>:
          </p>
          <div className="center">
            <div className="favoriteMovieImg">
              <img src={require("../assets/poster-780.jpg")} />
            </div>
            <p>
              which you watched <b>{props.favNum} times</b> in 2022.
            </p>
          </div>
        </div>
        <div className="center OtherMovieFavorites">
          <h4>Some of your other favorites included:</h4>
          <div className="Episodes">
            <div className="Episode">
              <div className="EpisodeImg">
                <img src={require("../assets/poster-780.jpg")} />
              </div>
              <div className="EpisodeDesc">
                <div className="DescContentMovie">
                  <p>
                    You watched <i>Spider-Man: Into the Spider-Verse</i>
                  </p>
                  <h2>4 times</h2>
                </div>
              </div>
            </div>
            <div className="Episode">
              <div className="EpisodeImg">
                <img src={require("../assets/poster-780.jpg")} />
              </div>
              <div className="EpisodeDesc">
                <div className="DescContentMovie">
                  <p>
                    You watched <i>Spider-Man: Into the Spider-Verse</i>
                  </p>
                  <h2>4 times</h2>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
}