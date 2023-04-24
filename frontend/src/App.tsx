import "./App.css";
import { WrappedGenres } from "./components/WrappedGenres";
import { WrappedMinutes } from "./components/WrappedMinutes";
import { WrappedEpisodes } from "./components/WrappedEpisodes";
import { WrappedBest } from "./components/WrappedBest";
import { WrappedWorst } from "./components/WrappedWorst";
import { WrappedMoviesOverview } from "./components/WrappedMoviesOverview";
import { NVTIPersonality } from "./components/NVTIPersonality";
import { ReportIntro } from "./components/ReportIntro";
import { ReportWelcome } from "./components/ReportWelcome";
import { WrappedOverview } from "./components/WrappedOverview";
import mockAll from "./assets/mocks/mockAll.json";
import { BingeData } from "./components/BingeData";
import { useLocation} from "react-router-dom";
import { useEffect, useState } from "react";
import { key } from "./private/key";

// mockShowData.forEach((item) => {
//   item.image = process.env.PUBLIC_URL + "/" + item.image;
// });

function App() {
  const location = useLocation();
  const state = location.state;

  return (
    <div className="Report">
      <ReportIntro year={2022} />
      <ReportWelcome name={state.name} />
      <WrappedGenres genres={mockAll.topGenres} />
      <WrappedMinutes totalMin={mockAll.totalMin} />
      <WrappedOverview
        shows={mockAll.shows.allShows}
        movies={mockAll.movie.allMovies}
      />
      <WrappedEpisodes
        totalEps={mockAll.shows.totalEpWatched}
        totalShows={mockAll.shows.allShows.length}
        above50={mockAll.shows.topShows.above50}
        below50={mockAll.shows.topShows.below50}
      />
      <WrappedBest
        type="show"
        bestRated={mockAll.shows.bestRated}
        color="#EEFFE7"
        backgroundColor="#EE9021"
      />
      <WrappedWorst
        color="#F5EC72"
        backgroundColor="#C72B68"
        worstRated={mockAll.shows.worstRated}
        type="show"
      />
      <WrappedMoviesOverview year={2022} movies={mockAll.movie.topMovies} />
      <WrappedBest
        color="#FEFFB9"
        backgroundColor="#95C12B"
        type="movie"
        bestRated={mockAll.movie.bestRated}
      />
      <WrappedWorst
        color="#EEFFE7"
        backgroundColor="#EE9021"
        worstRated={mockAll.movie.worstRated}
        type="movie"
      />
      <BingeData bingeData={mockAll.bingeData} />
      <NVTIPersonality personality={mockAll.personality} />
    </div>
  );
}

export default App;
