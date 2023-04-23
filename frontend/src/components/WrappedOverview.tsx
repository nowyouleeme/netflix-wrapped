interface WrappedOverviewProps {
  shows: Array<string>;
  movies: Array<string>;
}

export function WrappedOverview(props: WrappedOverviewProps) {
    return(
        <div className="WrappedOverview">
        <div className="OverviewDesc">
          <h3>Out of that time you spent with us in 2022,</h3>
          <p>
            you watched <b>{props.shows.length} shows</b>...
          </p>
        </div>

        {/* map list of shows to make a card component */}
        <div className="carouselContainer">
          <div className="carousel">
            {props.shows.map((value, index) => (
              <div className="carouselCard">
                <img src={require("../" + value)} />
              </div>
            ))}
          </div>
        </div>

        <div className="OverviewDesc">
          <p>
            and <b>{props.movies.length} movies</b>!
          </p>
        </div>

        <div className="carouselContainer">
          <div className="carousel">
            {props.movies.map((value, index) => (
              <div className="carouselCard">
                <img src={require("../" + value)} />
              </div>
            ))}
          </div>
        </div>
      </div>
    )
}