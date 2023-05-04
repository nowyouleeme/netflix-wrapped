import "./App.css";
import { WrappedGenres } from "./components/report/WrappedGenres";
import { WrappedMinutes } from "./components/report/WrappedMinutes";
import { WrappedEpisodes } from "./components/report/WrappedEpisodes";
import { WrappedMoviesOverview } from "./components/report/WrappedMoviesOverview";
import { NVTIPersonality } from "./components/report/NVTIPersonality";
import { ReportIntro } from "./components/report/ReportIntro";
import { ReportWelcome } from "./components/report/ReportWelcome";
import { WrappedOverview } from "./components/report/WrappedOverview";
import mockAll from "./assets/mocks/mockActor.json";
import { BingeData } from "./components/report/BingeData";
import { useLocation} from "react-router-dom";
import { WrappedFavActors } from "./components/report/WrappedFavActors";

function App() {


  return (
    <div className="Report">
      <ReportIntro year={2022} />
      <WrappedGenres genres={mockAll.top5Genres} />
      <WrappedMinutes totalMin={mockAll.totalMin} />
      <WrappedOverview
        shows={mockAll.shows.allShows}
        movies={mockAll.movie.allMovies}
      />
      {/* <WrappedEpisodes
        totalEps={mockAll.shows.totalEpWatched}
        totalShows={mockAll.shows.allShows.length}
        above50={mockAll.shows.topShows.above50_3}
        below50={mockAll.shows.topShows.below50_3}
      /> */}
      <WrappedFavActors
        type="show"
        actors={mockAll.shows.showActors.mostWatchedActors}
        media={mockAll.shows.showActors.actorFeaturedShows}
        saying={
          <p>
            You've got <b>amazing</b> taste in entertainers!
          </p>
        }
        color="#EEFFE7"
        bg="#EE9021"
      />
      <WrappedMoviesOverview year={2022} movies={mockAll.movie.top5Movies} />
      <WrappedFavActors
        type="movie"
        actors={mockAll.movie.movieActors.mostWatchedActors}
        media={mockAll.movie.movieActors.actorFeaturedMovies}
        saying={
          <p>
            Looks like you have some favorites... but we <b>all</b> do.
          </p>
        }
        color="#F5EC72"
        bg="#7E18A1"
      />
      <BingeData bingeData={mockAll.bingeData} />
      <NVTIPersonality personality={mockAll.personality} />
    </div>
  );
}

export default App;
