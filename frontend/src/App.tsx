import React, { useState } from "react";
import logo from "./logo.svg";
import { Button } from "@mui/material";
import { InputLabel } from "@mui/material";
import "./App.css";
import { WrappedGenres } from "./components/WrappedGenres";
import { WrappedMinutes } from "./components/WrappedMinutes";
import { WrappedShowOverview } from "./components/WrappedShowOverview";
import { WrappedEpisodes } from "./components/WrappedEpisodes";
import { WrappedBest } from "./components/WrappedBest";
import { WrappedWorst } from "./components/WrappedWorst";
import { WrappedMoviesOverview } from "./components/WrappedMoviesOverview";
import { NVTIPersonality } from "./components/NVTIPersonality";
import { ReportIntro } from "./components/ReportIntro";
import { ReportWelcome } from "./components/ReportWelcome";

function App() {
  return (
    <div className="Report">
      <ReportIntro year={2022}/>
      <ReportWelcome/>
      <WrappedGenres favGenre="Action" numGenres={4} />
      <WrappedMinutes totalMin={100000} />
      <WrappedShowOverview numMovies={5} numShows={5} />
      <WrappedEpisodes totalEps={24} totalShows={5} />

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

      <WrappedBest name="Street Food" rating={8.4} />
      <WrappedWorst name="Street Food" rating={8.4} />
      <WrappedMoviesOverview nameMovie="Spider-Man" favNum={5} />
      <NVTIPersonality
        desc="You’re an adventurous one. Not limiting yourself to either
                movies or shows, you delve into wide and varied range of genres
                to explore the unknown."
        title="The Explorer"
        NVTI="ENDP"
      />
    </div>
  );
}

export default App;
