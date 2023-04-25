interface WrappedBestProps {
  type: string;
  bestRated: { title: string; rating: number; image: string };
  backgroundColor: string;
  color: string;
}
export function WrappedBest(props: WrappedBestProps) {
  let altText = `Poster for the ${props.type} '${props.bestRated.title}'`
    return (
      <div
        className="WrappedRatingShow"
        style={{ backgroundColor: props.backgroundColor, color: props.color }}
      >
        <div className="ratingLeft">
          <h2>
            The most critically acclaimed {props.type} you watched was{" "}
            <i>{props.bestRated.title}</i>, rated{" "}
            {props.bestRated.rating.toFixed(1)} on IMDb.
          </h2>
          <sub>You have some great taste!</sub>
        </div>
        <div className="ratedShow">
          <a
            rel="noreferrer"
            target="_blank"
            href={"https://www.google.com/search?q=" + props.bestRated.title}
          >
            <img src={props.bestRated.image} alt={altText} />
          </a>
        </div>
      </div>
    );
}