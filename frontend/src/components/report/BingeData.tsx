interface BingeDataProps {
  bingeData: {
    date: string;
    shows: {
      title: string;
      numEpWatched: number;
      image: string;
    }[];
    movies: {
      title: string;
      image: string;
    }[];
  };
}

export const binge_grid = "Binge grid data with posters";

export function BingeData(props: BingeDataProps) {
  let date: Date = new Date(props.bingeData.date);
  const nth = function (d: number) {
    if (d > 3 && d < 21) return "th";
    switch (d % 10) {
      case 1:
        return "st";
      case 2:
        return "nd";
      case 3:
        return "rd";
      default:
        return "th";
    }
  };

  function writeOutBinge(
    shows: {
      title: string;
      numEpWatched: number;
      image: string;
    }[],
    movies: {
      title: string;
      image: string;
    }[]
  ) {
    let contentWatched: JSX.Element[] = [];

    for (let i = 0; i < shows.length; i++) {
      let stringElement: JSX.Element;
      stringElement = (
        <span>
          <b>{shows[i].numEpWatched} episodes</b> of <i>{shows[i].title}</i>
        </span>
      );
      contentWatched.push(stringElement);
    }

    for (let i = 0; i < movies.length; i++) {
      let stringElement: JSX.Element;
      stringElement = (
        <span>
          <i>{movies[i].title}</i>
        </span>
      );
      contentWatched.push(stringElement);
    }

    if (contentWatched.length === 2) {
      contentWatched[0] =
      (
        <>
          {contentWatched[0]} <span>and </span>
        </>
      );
      return contentWatched;
    } else if (contentWatched.length === 1) {
      return contentWatched;
    } else {
      for (let i = 0; i < contentWatched.length; i++) {
        if (i === contentWatched.length - 1) {
          contentWatched[i] = (
            <>
              <span>and</span> {contentWatched[i]}
            </>
          );
        } else {
          contentWatched[i] = (
            <>
              {contentWatched[i]}
              <span>, </span>
            </>
          );
        }
      }
      return contentWatched;
    }
  }

  function bingeImages(
    shows: {
      title: string;
      numEpWatched: number;
      image: string;
    }[],
    movies: {
      title: string;
      image: string;
    }[]
  ) {
    let posters: {title: string; image: string}[] = [];
    for (let i = 0; i < shows.length; i++) {
      let show = { title: shows[i].title, image: shows[i].image};
        posters.push(show)
    }
    for (let i = 0; i < movies.length; i++) {
      posters.push(movies[i]);
    }
    return posters;
  }

  return (
    <div className="center WrappedBingeShow">
      <p className="bingeDisplayP">
        {date.toLocaleString("default", { month: "long" })} {date.getDate()}
        {nth(date.getDate())}, {date.getFullYear()}
      </p>
      <sub>was quite the special day.</sub>
      <div className="bingeDesc">
        <p className="bingeDescP">
          On this day, you managed to watch {writeOutBinge(props.bingeData.shows, props.bingeData.movies)}, making
          it <a
            target="_blank"
            rel="noreferrer"
            href="https://www.youtube.com/watch?v=StTqXEQ2l-Y"
          >
            your most bingeful show day
          </a>{" "}
          of the year.
        </p>
      </div>
      <div role="figure" aria-label={binge_grid} className="BingeGrid">
        {bingeImages(props.bingeData.shows, props.bingeData.movies).map(
          (value, index) => {
            return (
              <div className="BingeShow" key={index}>
                <a
                  rel="noreferrer"
                  href={"https://www.google.com/search?q=" + value.title}
                  target="_blank"
                >
                  <img src={value.image} alt={"Poster for " + value.title} />
                </a>
              </div>
            );
          }
        )}
      </div>

      <p className="bingeDescP">Pretty productive, if we say so ourselves!</p>
    </div>
  );
}
