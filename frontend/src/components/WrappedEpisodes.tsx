interface WrappedEpisodesProps {
  totalEps: number;
  totalShows: number;
  above50: {
    title: string;
    numEpWatched: number;
    percentageFinished: number;
    image: string;
  }[];
  below50: {
    title: string;
    numEpWatched: number;
    percentageFinished: number;
    image: string;
  }[];
};

export function WrappedEpisodes(props: WrappedEpisodesProps) {
  // pass in array of different processed show objects with how many episodes of tht show u watched + percentage
  return (
    <div className="two-row WrappedShowEpisodes">
      <div className="EpisodesMany">
        <div style={{ textAlign: "center" }}>
          <h4>
            You watched a total of {props.totalEps} episodes of{" "}
            {props.totalShows} different shows,
          </h4>
          <p>with some you just couldn't get enough of:</p>
        </div>
        <div className="Episodes">
          <div className="Episode">
            <div className="EpisodeImg">
              <img src={require("../assets/images/poster-780.jpg")} />
            </div>
            <div className="EpisodeDesc">
              <div className="DescContent">
                <h2>24 episodes</h2>
                <p>
                  You watched through 50% of the episodes for <i>Dynasty</i>
                </p>
              </div>
            </div>
          </div>
          <div className="Episode">
            <div className="EpisodeImg">
              <img src={require("../assets/images/poster-780.jpg")} />
            </div>
            <div className="EpisodeDesc">
              <div className="DescContent">
                <h2>24 episodes</h2>
                <p>
                  You watched through 50% of the episodes for <i>Dynasty</i>
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>

      <div className="EpisodesLess">
        <p style={{ textAlign: "center" }}>
          And others that you started but <b>never finished:</b>
        </p>
        <div className="Episodes">
          <div className="Episode">
            <div className="EpisodeImg">
              <img src={require("../assets/images/poster-780.jpg")} />
            </div>
            <div className="EpisodeDesc">
              <div className="DescContent">
                <h2>24 episodes</h2>
                <p>
                  You watched through 50% of the episodes for <i>Dynasty</i>
                </p>
              </div>
            </div>
          </div>
          <div className="Episode">
            <div className="EpisodeImg">
              <img src={require("../assets/images/poster-780.jpg")} />
            </div>
            <div className="EpisodeDesc">
              <div className="DescContent">
                <h2>24 episodes</h2>
                <p>
                  You watched through 50% of the episodes for <i>Dynasty</i>
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
