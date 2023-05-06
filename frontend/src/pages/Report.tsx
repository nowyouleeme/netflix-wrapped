import "../App.css";
import { WrappedGenres } from "../components/report/WrappedGenres";
import { WrappedMinutes } from "../components/report/WrappedMinutes";
import { WrappedEpisodes } from "../components/report/WrappedEpisodes";
import { WrappedMoviesOverview } from "../components/report/WrappedMoviesOverview";
import { NVTIPersonality } from "../components/report/NVTIPersonality";
import { ReportIntro } from "../components/report/ReportIntro";
import { ReportWelcome } from "../components/report/ReportWelcome";
import { WrappedOverview } from "../components/report/WrappedOverview";
import { BingeData } from "../components/report/BingeData";
import { useLocation, useNavigate } from "react-router-dom";
import { WrappedFavActors } from "../components/report/WrappedFavActors";
import { motion } from "framer-motion";
import { useState } from "react";
import ReportError from "./ReportError";
import { Wipe } from "../components/report/Wipe";

export const wipe_data = "Wipe your personal csv data from our server";

/**
 * Function that returns the Netflix Wrapped Report React component, containing information and visualizing the data on a user's
 * watching and viewing behavior on Netflix
 * @returns the Netflix Wrapped Report React component
 */
function Report() {
  const location = useLocation();
  const state = location.state;
  const initLocationState = state != null;
  const [showReport, setShowReport] = useState(initLocationState);
  const navigate = useNavigate();

  return (
    <>
      <motion.div
        className="Report"
        initial={{ opacity: 0 }}
        animate={{ opacity: 1 }}
        exit={{ opacity: 0 }}
        transition={{ duration: 2 }}
      >
        {showReport && <ReportIntro />}
        {showReport && <ReportWelcome name={state.name} />}
        {showReport && <WrappedGenres genres={state.reportJSON.top5Genres} />}
        {showReport && <WrappedMinutes totalMin={state.reportJSON.totalMin} />}
        {showReport && (
          <WrappedOverview
            shows={state.reportJSON.shows.allShows}
            movies={state.reportJSON.movie.allMovies}
          />
        )}
        {showReport && (
          <WrappedEpisodes
            totalEps={state.reportJSON.shows.totalEpWatched}
            totalShows={state.reportJSON.shows.allShows.length}
            mostWatched={state.reportJSON.shows.topShows.mostWatched}
            leastWatched={state.reportJSON.shows.topShows.leastWatched}
          />
        )}
        {showReport && (
          <WrappedFavActors
            type="show"
            actors={state.reportJSON.shows.showActors.mostWatchedActors}
            media={state.reportJSON.shows.showActors.actorFeaturedShows}
            saying={
              <p className="actorsDescP">
                You've got <b>amazing</b> taste in entertainers!
              </p>
            }
            color="#EEFFE7"
            bg="#EE9021"
          />
        )}
        {showReport && (
          <WrappedMoviesOverview
            year={2022}
            movies={state.reportJSON.movie.top5Movies}
          />
        )}
        {showReport && (
          <WrappedFavActors
            type="movie"
            actors={state.reportJSON.movie.movieActors.mostWatchedActors}
            media={state.reportJSON.movie.movieActors.actorFeaturedMovies}
            saying={
              <p className="actorsDescP">
                Looks like you have some favorite folks!
              </p>
            }
            color="#F5EC72"
            bg="#7E18A1"
          />
        )}
        {showReport && <BingeData bingeData={state.reportJSON.bingeData} />}
        {showReport && (
          <NVTIPersonality personality={state.reportJSON.personality} />
        )}

        {/* Button to wipe data */}
        {showReport && (
          <Wipe setShowReport={setShowReport} navigate={navigate} />
        )}
      </motion.div>
      <div>{!showReport && <ReportError />}</div>
    </>
  );
}

export default Report;
