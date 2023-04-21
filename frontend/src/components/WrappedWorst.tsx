interface WrappedWorstProps {
    name: string;
    rating: number;
}

export function WrappedWorst(props:WrappedWorstProps) {
    return (
      <div className="WrappedRatingShow worst">
        <div className="ratedShow">
          <img src={require("../assets/poster-780.jpg")} />
        </div>
        <div className="ratingRight">
          <h2>
            The worst rated show you watched was <i>{props.name}</i>, rated
            {props.rating} on IMDb.
          </h2>
          <sub>
            But that doesn't matter, as long as <b>you</b> enjoyed it.
          </sub>
        </div>
      </div>
    );
}