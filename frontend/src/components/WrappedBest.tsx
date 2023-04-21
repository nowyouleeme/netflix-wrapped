interface WrappedBest {
    name: string;
    rating: number;
}
export function WrappedBest(props: WrappedBest) {
    return (
      <div className="WrappedRatingShow best">
        <div className="ratingLeft">
          <h2>
            The most critically acclaimed show you watched was{" "}
            <i>{props.name}</i>, rated {props.rating} on IMDb.
          </h2>
          <sub>You have some great taste!</sub>
        </div>
        <div className="ratedShow">
          <img src={require("../assets/poster-780.jpg")} />
        </div>
      </div>
    );
}