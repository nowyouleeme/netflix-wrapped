interface WrappedWorstProps {
    name: string;
    type: string;
    rating: number;
}

export function WrappedWorst(props:WrappedWorstProps) {
    return (
      <div className="WrappedRatingShow worst">
        <div className="ratedShow">
            {/* image url would be props probably */}
          <img src={require("../assets/images/poster-780.jpg")} />
        </div>
        <div className="ratingRight">
          <h2>
            The worst rated {props.type} you watched was <i>{props.name}</i>,
            rated {props.rating} on IMDb.
          </h2>
          <sub>
            {/* this will probably be props but maybe would have to convert string to element bc include other html tags */}
            But that doesn't matter, as long as <b>you</b> enjoyed it.
          </sub>
        </div>
      </div>
    );
}