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
    let posters: string[] = [];
    for (let i = 0; i < shows.length; i++) {
        posters.push(shows[i].image)
    }
    for (let i = 0; i < movies.length; i++) {
      posters.push(movies[i].image);
    }
    return posters;
  }

  return (
    <div className="center WrappedBingeShow">
      <h1>
        {date.toLocaleString("default", { month: "long" })} {date.getDate()}
        {nth(date.getDate())}, {date.getFullYear()}
      </h1>
      <sub>was quite the special day.</sub>
      <div className="bingeDesc">
        <p>
          On this day, you managed to watch{" "}
          {writeOutBinge(props.bingeData.shows, props.bingeData.movies)}, making
          it{" "}
          <a
            target="_blank"
            rel="noreferrer"
            href="https://www.youtube.com/watch?v=StTqXEQ2l-Y"
          >
            your most bingeful show day
          </a>{" "}
          of the year.
        </p>
      </div>
      <div className="BingeGrid">
        {bingeImages(props.bingeData.shows, props.bingeData.movies).map(
          (value, index) => {
            return (
              <div className="BingeShow">
                <img src={require("../" + value)}/>
              </div>
            );
          }
        )}
      </div>

      <p>Pretty productive, if we say so ourselves!</p>
    </div>
  );
}
