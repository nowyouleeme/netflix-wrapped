import React, { useState } from "react";
import logo from "./logo.svg";
import { Button } from "@mui/material";
import { InputLabel } from "@mui/material";
import "./App.css";

function App() {
  const [fileName, setFileName] = useState<string | undefined>("");
  const [file, setFile] = useState<File>();

  function handleChange(event: React.ChangeEvent<HTMLInputElement>) {
    setFile(event.target.files?.[0]);
    setFileName(event.target.files?.[0].name);
  }

  return (
    <div className="Report">
      <div className="center WrappedIntroduction">
        <div className="Here">
          <h1>Your Netflix 2022 Wrapped is finally here!</h1>
          <div className="HereSub">
            <sub>
              Take a glimpse at your watch time, top shows + movies, favorite
              genres—all in one place.
            </sub>
          </div>
        </div>
        <div className="center ScrollDown">
          <label>Scroll down to see more</label>
          <svg
            width="30"
            height="18"
            viewBox="0 0 30 18"
            fill="none"
            xmlns="http://www.w3.org/2000/svg"
          >
            <path
              d="M3.42048 0L14.5553 11.1348L25.69 0L29.1105 3.44474L14.5553 18L0 3.44474L3.42048 0Z"
              fill="#F5EC72"
            />
          </svg>
        </div>
      </div>
      <div className="center WrappedWelcome">
        <h1>Welcome to your Netflix Wrapped, Justin</h1>
      </div>
      <div className="center WrappedGenres">
        <div className="two-col genres">
          <div className="GenreDesc">
            <p>This year, you explored</p>
            <h1>
              4
              <br />
              different
              <br />
              genres,
            </h1>
            <p>
              your favorite being <b>Action.</b>
            </p>
            <sub>Look at you go, venturing into the unknown!</sub>
          </div>
          <div className="GenreVis"></div>
        </div>
      </div>
      <div className="center WrappedMinutes">
        <p>All that exploration added up to</p>
        <div>
          <h1>100,000</h1>
          <h1>100,000</h1>
          <h1>100,000</h1>
          <h1>100,000</h1>
        </div>
        <p>minutes on Netflix.</p>
        <sub>That's about 1667 hours, or 69 days!</sub>
      </div>
      <div className="WrappedShowOverview">
        <div className="OverviewDesc">
          <h2>Out of that time you spent with us in 2022,</h2>
          <p>
            you watched <b>5 shows</b>...
          </p>
        </div>

        {/* map list of shows to make a card component */}
        <div className="carousel">
          <div className="firstCard">
            <img src={require("./assets/poster-780.jpg")} />
          </div>
          <div className="carouselCard">
            <img src={require("./assets/poster-780.jpg")} />
          </div>
          <div className="carouselCard">
            <img src={require("./assets/poster-780.jpg")} />
          </div>
          <div className="carouselCard">
            <img src={require("./assets/poster-780.jpg")} />
          </div>
          <div className="carouselCard">
            <img src={require("./assets/poster-780.jpg")} />
          </div>
          <div className="lastCard">
            <img src={require("./assets/poster-780.jpg")} />
          </div>
        </div>
        <div className="OverviewDesc">
          <p>
            and <b>4 movies</b>!
          </p>
        </div>

        <div className="carousel">
          <div className="firstCard">
            <img src={require("./assets/poster-780.jpg")} />
          </div>
          <div className="carouselCard">
            <img src={require("./assets/poster-780.jpg")} />
          </div>
          <div className="carouselCard">
            <img src={require("./assets/poster-780.jpg")} />
          </div>
          <div className="carouselCard">
            <img src={require("./assets/poster-780.jpg")} />
          </div>
          <div className="carouselCard">
            <img src={require("./assets/poster-780.jpg")} />
          </div>
          <div className="lastCard">
            <img src={require("./assets/poster-780.jpg")} />
          </div>
        </div>
      </div>
      <div className="two-row WrappedShowEpisodes">
        <div className="EpisodesMany">
          <div style={{ textAlign: "center" }}>
            <h2>You watched a total of 50 episodes of 5 different shows,</h2>
            <p>with some you just couldn't get enough of:</p>
          </div>
          <div className="Episodes">
            <div className="two-col Episode">
              <div className="EpisodeImg">
                <img src={require("./assets/poster-780.jpg")} />
              </div>
              <div>
                <h1>24 episodes</h1>
                <p>You watched through 50% of the episodes for Dynasty</p>
              </div>
            </div>
          </div>
        </div>

        <div className="EpisodesLess">
          <p>And others that you started but never finished:</p>
        </div>
      </div>
      <div className="center WrappedBingeShow">
        <h1>March 18th, 2022</h1>
        <sub>was quite the special day.</sub>
        <p>
          On this day, you managed to watch <b>11 episodes</b> of{" "}
          <i>Street Food: USA</i>, making it your most bingeful show day of the
          year.
        </p>
        <div className="BingeShow">
          <img src={require("./assets/poster-780.jpg")} />
        </div>
        <sub>Pretty productive, if we say so ourselves!</sub>
      </div>

      <div className="two-col WrappedRatingShow best">
        <div>
          <h2>
            The most critically acclaimed show you watched was{" "}
            <i>Street Food: USA</i>, rated 8.4 on IMDb.
          </h2>
          <sub>You have some great taste!</sub>
        </div>
        <div className="ratedShow">
          <img src={require("./assets/poster-780.jpg")} />
        </div>
      </div>
      <div className="two-col WrappedRatingShow worst">
        <div className="ratedShow">
          <img src={require("./assets/poster-780.jpg")} />
        </div>
        <div>
          <h2>
            The worst rated show you watched was <i>Street Food: USA</i>, rated
            8.4 on IMDb.
          </h2>
          <sub>But that doesn't matter, as long as you enjoyed it.</sub>
        </div>
      </div>
      <div className="WrappedMoviesOverview">
        <div className="FavoriteMovie">
          <h2>When it comes to movies,</h2>
          <p>
            you were a number one fan for{" "}
            <i>Spider-Man: Into the Spider-Verse</i>:
          </p>
          <p>
            which you watched <b>5 times</b> in 2022.
          </p>
        </div>
        <div className="OtherMovieFavorites">
          <h2>Some of your other favorites included:</h2>
          <div>
            <p>
              You watched <i>Dynasty</i>
            </p>
            <h1>4 times</h1>
          </div>
        </div>
      </div>
      <div className="center WrappedPersonality">
        <div className="PersonalityContainer">
          <h2>Meet your NVTI personality:</h2>
          <h1>The Explorer</h1>
          <p>
            You’re an adventurous one. Not limiting yourself to either movies or
            shows, you delve into wide and varied range of genres to explore the
            unknown.
          </p>
          <p>✧ ENDP ✧</p>
        </div>
      </div>
    </div>
  );
}

export default App;
