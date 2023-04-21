interface WrappedShowOverviewProps {
    numShows: number;
    numMovies: number;
}

export function WrappedShowOverview(props: WrappedShowOverviewProps) {
    // also pass in array of movie image urls for fetching from a given api
    return (
      <div className="WrappedOverview">
        <div className="OverviewDesc">
          <h3>Out of that time you spent with us in 2022,</h3>
          <p>
            you watched <b>{props.numShows} shows</b>...
          </p>
        </div>

        {/* map list of shows to make a card component */}
        <div className="carousel">
          <div className="firstCard">
            <img src={require("../assets/poster-780.jpg")} />
          </div>
          <div className="carouselCard">
            <img src={require("../assets/poster-780.jpg")} />
          </div>
          <div className="carouselCard">
            <img src={require("../assets/poster-780.jpg")} />
          </div>
          <div className="lastCard">
            <img src={require("../assets/poster-780.jpg")} />
          </div>
        </div>

        <div className="OverviewDesc">
          <p>
            and <b>{props.numMovies} movies</b>!
          </p>
        </div>

        <div className="carousel">
          <div className="firstCard">
            <img src={require("../assets/poster-780.jpg")} />
          </div>
          <div className="carouselCard">
            <img src={require("../assets/poster-780.jpg")} />
          </div>
          <div className="carouselCard">
            <img src={require("../assets/poster-780.jpg")} />
          </div>
          <div className="lastCard">
            <img src={require("../assets/poster-780.jpg")} />
          </div>
        </div>
      </div>
    );
}