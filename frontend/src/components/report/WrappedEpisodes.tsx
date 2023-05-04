interface WrappedEpisodesProps {
  totalEps: number;
  totalShows: number;
  mostWatched: {
    title: string;
    numEpWatched: number;
    image: string;
  }[];
  leastWatched: {
    title: string;
    numEpWatched: number;
    image: string;
  }[];
};

export const episode_display = "Episodes watched by user with episode count and percentage finished"

export function WrappedEpisodes(props: WrappedEpisodesProps) {
  // pass in array of different processed show objects with how many episodes of tht show u watched + percentage
  return (
    <div className="two-row WrappedShowEpisodes">
      <div className="EpisodesMany">
        <div style={{ textAlign: "center" }}>
          <p className="episodesBigP">
            You watched a total of {props.totalEps} episodes of{" "}
            {props.totalShows} different shows,
          </p>
          <p className="episodesDescP">
            with some you just couldn't get enough of:
          </p>
        </div>
        <div role="figure" aria-label={episode_display} className="Episodes">
          {props.mostWatched.map((value, index) => (
            <div className="Episode" key={index}>
              <div className="EpisodeImg">
                <a
                  rel="noreferrer"
                  target="_blank"
                  href={"https://www.google.com/search?q=" + value.title}
                >
                  <img
                    src={value.image}
                    alt={`Poster for the show '${value.title}'`}
                  />
                </a>
              </div>
              <div className="EpisodeDesc">
                <div className="DescContent">
                  <p className="DescContentSmallP">You watched</p>
                  <p className="DescContentBigP">
                    {value.numEpWatched} episodes
                  </p>
                  <p className="DescContentSmallP">
                    of <i>{value.title}</i>
                  </p>
                </div>
              </div>
            </div>
          ))}
        </div>
      </div>

      <div className="EpisodesLess">
        <p className="episodesDescP" style={{ textAlign: "center" }}>
          And others that you started but <b>never finished:</b>
        </p>
        <div className="Episodes">
          {props.leastWatched.map((value, index) => (
            <div className="Episode" key={index}>
              <div className="EpisodeImg">
                <a
                  rel="noreferrer"
                  target="_blank"
                  href={"https://www.google.com/search?q=" + value.title}
                >
                  <img
                    src={value.image}
                    alt={`Poster for the show '${value.title}'`}
                  />
                </a>
              </div>
              <div className="EpisodeDesc">
                <div className="DescContent">
                  <p className="DescContentSmallP">You watched</p>
                  <p className="DescContentBigP">
                    {value.numEpWatched} episodes
                  </p>
                  <p className="DescContentSmallP">
                    of <i>{value.title}</i>
                  </p>
                </div>
              </div>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
}
