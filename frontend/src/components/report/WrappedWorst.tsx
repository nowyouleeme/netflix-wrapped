interface WrappedWorstProps {
  type: string;
  worstRated: { title: string; rating: number; image: string };
  backgroundColor: string;
  color: string;
}

export function WrappedWorst(props:WrappedWorstProps) {
  let altText =
    `Poster for the ${props.type} '${props.worstRated.title}'`;
    return (
      <div
        className="WrappedRatingShow worst"
        style={{ backgroundColor: props.backgroundColor, color: props.color }}
      >
        <div className="ratedShow">
          {/* image url would be props probably */}
          <a
            rel="noreferrer"
            target="_blank"
            href={"https://www.google.com/search?q=" + props.worstRated.title}
          >
            <img src={props.worstRated.image} alt={altText} />
          </a>
        </div>
        <div className="ratingRight">
          <h2>
            The worst rated {props.type} you watched was{" "}
            <i>{props.worstRated.title}</i>, rated{" "}
            {props.worstRated.rating.toFixed(1)} on IMDb.
          </h2>
          <sub>
            {/* this will probably be props but maybe would have to convert string to element bc include other html tags */}
            But that doesn't matter, as long as <b>you</b> enjoyed it.
          </sub>
        </div>
      </div>
    );
}