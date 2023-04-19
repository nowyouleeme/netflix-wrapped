import React, { useState } from "react";
import logo from "./logo.svg";
import { Button } from "@mui/material";
import { InputLabel } from "@mui/material";
import "./App.css";

function App() {
  return (
    <div className="Report">
      <div className="center WrappedIntroduction">
        <div className="Here">
          <h2>Your Netflix 2022 Wrapped is finally here!</h2>
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
        <h2>Welcome to your Netflix Wrapped, Justin</h2>
      </div>
      <div className="center WrappedGenres">
        <div className="two-col genres">
          <div className="GenreDesc">
            <div className="DescMain">
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
            </div>

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
        <sub>
          That's about <b>1667 hours,</b> or <b>69 days!</b>
        </sub>
      </div>
      <div className="WrappedOverview">
        <div className="OverviewDesc">
          <h3>Out of that time you spent with us in 2022,</h3>
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
            <h4>You watched a total of 50 episodes of 5 different shows,</h4>
            <p>with some you just couldn't get enough of:</p>
          </div>
          <div className="Episodes">
            <div className="Episode">
              <div className="EpisodeImg">
                <img src={require("./assets/poster-780.jpg")} />
              </div>
              <div className="EpisodeDesc">
                <div className="DescContent">
                  <h2>24 episodes</h2>
                  <p>You watched through 50% of the episodes for <i>Dynasty</i></p>
                </div>
              </div>
            </div>
            <div className="Episode">
              <div className="EpisodeImg">
                <img src={require("./assets/poster-780.jpg")} />
              </div>
              <div className="EpisodeDesc">
                <div className="DescContent">
                  <h2>24 episodes</h2>
                  <p>You watched through 50% of the episodes for <i>Dynasty</i></p>
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
                <img src={require("./assets/poster-780.jpg")} />
              </div>
              <div className="EpisodeDesc">
                <div className="DescContent">
                  <h2>24 episodes</h2>
                  <p>You watched through 50% of the episodes for <i>Dynasty</i></p>
                </div>
              </div>
            </div>
            <div className="Episode">
              <div className="EpisodeImg">
                <img src={require("./assets/poster-780.jpg")} />
              </div>
              <div className="EpisodeDesc">
                <div className="DescContent">
                  <h2>24 episodes</h2>
                  <p>You watched through 50% of the episodes for <i>Dynasty</i></p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div className="center WrappedBingeShow">
        <h1>March 18th, 2022</h1>
        <sub>was quite the special day.</sub>
        <div className="bingeDesc">
          <p>
            On this day, you managed to watch <b>11 episodes</b> of{" "}
            <i>Street Food: USA</i>, making it your{" "}
            <b>most bingeful show day</b> of the year.
          </p>
        </div>

        <div className="BingeShow">
          <img src={require("./assets/poster-780.jpg")} />
        </div>
        <p>Pretty productive, if we say so ourselves!</p>
      </div>

      <div className="WrappedRatingShow best">
        <div className="ratingLeft">
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
      <div className="WrappedRatingShow worst">
        <div className="ratedShow">
          <img src={require("./assets/poster-780.jpg")} />
        </div>
        <div className="ratingRight">
          <h2>
            The worst rated show you watched was <i>Street Food: USA</i>, rated
            8.4 on IMDb.
          </h2>
          <sub>
            But that doesn't matter, as long as <b>you</b> enjoyed it.
          </sub>
        </div>
      </div>
      <div className="center WrappedMoviesOverview">
        <div className="FavoriteMovie">
          <h3>When it comes to movies,</h3>
          <p>
            you were a number one fan for{" "}
            <i>Spider-Man: Into the Spider-Verse</i>:
          </p>
          <div className="center">
            <div className="favoriteMovieImg">
              <img src={require("./assets/poster-780.jpg")} />
            </div>
            <p>
              which you watched <b>5 times</b> in 2022.
            </p>
          </div>
        </div>
        <div className="center OtherMovieFavorites">
          <h4>Some of your other favorites included:</h4>
          <div className="Episodes">
            <div className="Episode">
              <div className="EpisodeImg">
                <img src={require("./assets/poster-780.jpg")} />
              </div>
              <div className="EpisodeDesc">
                <div className="DescContent">
                  <p>
                    You watched <i>Spider-Man: Into the Spider-Verse</i>
                  </p>
                  <h2>4 times</h2>
                </div>
              </div>
            </div>
            <div className="Episode">
              <div className="EpisodeImg">
                <img src={require("./assets/poster-780.jpg")} />
              </div>
              <div className="EpisodeDesc">
                <div className="DescContent">
                  <p>
                    You watched <i>Spider-Man: Into the Spider-Verse</i>
                  </p>
                  <h2>4 times</h2>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div className="center WrappedPersonality">
        <div className="center PersonalityContainer">
          <h4>Meet your NVTI personality:</h4>
          <div className="personalityGraphic">
            <svg
              width="200"
              height="200"
              viewBox="0 0 200 200"
              fill="none"
              xmlns="http://www.w3.org/2000/svg"
            >
              <path
                d="M143.6 120C144.4 113.4 145 106.8 145 100C145 93.2 144.4 86.6 143.6 80H177.4C179 86.4 180 93.1 180 100C180 106.9 179 113.6 177.4 120M125.9 175.6C131.9 164.5 136.5 152.5 139.7 140H169.2C159.6 156.5 144.3 169.3 125.9 175.6ZM123.4 120H76.6C75.6 113.4 75 106.8 75 100C75 93.2 75.6 86.5 76.6 80H123.4C124.3 86.5 125 93.2 125 100C125 106.8 124.3 113.4 123.4 120ZM100 179.6C91.7 167.6 85 154.3 80.9 140H119.1C115 154.3 108.3 167.6 100 179.6ZM60 60H30.8C40.3 43.4 55.7 30.6 74 24.4C68 35.5 63.5 47.5 60 60ZM30.8 140H60C63.5 152.5 68 164.5 74 175.6C55.7 169.3 40.3 156.5 30.8 140ZM22.6 120C21 113.6 20 106.9 20 100C20 93.1 21 86.4 22.6 80H56.4C55.6 86.6 55 93.2 55 100C55 106.8 55.6 113.4 56.4 120M100 20.3C108.3 32.3 115 45.7 119.1 60H80.9C85 45.7 91.7 32.3 100 20.3ZM169.2 60H139.7C136.5 47.5 131.9 35.5 125.9 24.4C144.3 30.7 159.6 43.4 169.2 60ZM100 0C44.7 0 0 45 0 100C0 126.522 10.5357 151.957 29.2893 170.711C38.5752 179.997 49.5991 187.362 61.7317 192.388C73.8642 197.413 86.8678 200 100 200C126.522 200 151.957 189.464 170.711 170.711C189.464 151.957 200 126.522 200 100C200 86.8678 197.413 73.8642 192.388 61.7317C187.362 49.5991 179.997 38.5752 170.711 29.2893C161.425 20.0035 150.401 12.6375 138.268 7.61205C126.136 2.58658 113.132 0 100 0Z"
                fill="url(#paint0_linear_11_424)"
              />
              <defs>
                <linearGradient
                  id="paint0_linear_11_424"
                  x1="0"
                  y1="0"
                  x2="200"
                  y2="219"
                  gradientUnits="userSpaceOnUse"
                >
                  <stop stop-color="#95C12B" />
                  <stop offset="1" stop-color="#DB1864" />
                </linearGradient>
              </defs>
            </svg>
          </div>
          <h2>The Explorer</h2>
          <div className="personalityDesc">
            <p>
              <i>
                You’re an adventurous one. Not limiting yourself to either
                movies or shows, you delve into wide and varied range of genres
                to explore the unknown.
              </i>
            </p>
            <div className="NVTI">
              <p>✧ ENDP ✧</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default App;
