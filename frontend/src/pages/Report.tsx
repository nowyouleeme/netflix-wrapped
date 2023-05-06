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
import { useLocation, useNavigate} from "react-router-dom";
import { WrappedFavActors } from "../components/report/WrappedFavActors";
import { motion } from "framer-motion";
import { Button } from '@mui/material';
import {useState} from 'react'
import { Console } from "console";
import ReportError from "./ReportError";


export const wipe_data = "Wipe your personal csv data from our server"


/**
 * Function that returns the Netflix Wrapped Report React component, containing information and visualizing the data on a user's
 * watching and viewing behavior on Netflix
 * @returns the Netflix Wrapped Report React component
 */
function Report() {
  const location = useLocation();
  const state = location.state;
  const initLocationState = (state!= null)
  const [showReport, setShowReport] = useState(initLocationState)
  const navigate = useNavigate();


  
  //TODO: uncomment below
  // const [mockAll, setMockAll] = useState(state.reportJSON)

  // if (wipeDataStatus === "backend wiping...") {
  //   return (
  //     <div>
  //       backend wiping...
  //     </div>
  //   )
  // } else if (wipeDataStatus === "frontend non-stateful wiping...") {
  //   return (
  //     <div>
  //       frontend non-stateful wiping...
  //     </div>
  //   )
  // } else if (wipeDataStatus === "frontend stateful wiping...") {
  //   return (
  //     <div>
  //       frontend non-stateful wiping...
  //     </div>
  //   )
  // } else if (wipeDataStatus === "done") {
  //   return (
  //     <div>
  //       done
  //     </div>
  //   )
  // }


  //the below is code that will run, assuming that we are not in the process of
  //wiping data
  return (
    <motion.div
      className="Report"
      initial={{ opacity: 0 }}
      animate={{ opacity: 1 }}
      exit={{ opacity: 0 }}
      transition={{ duration: 2 }}
    >
      {showReport && (<ReportIntro />)}
      {showReport && (<ReportWelcome name={state.name} />)}
      {showReport && (<WrappedGenres genres={state.reportJSON.top5Genres} />)}
      {showReport && (<WrappedMinutes totalMin={state.reportJSON.totalMin} />)}
      {showReport && (<WrappedOverview
        shows={state.reportJSON.shows.allShows}
        movies={state.reportJSON.movie.allMovies}
      />)}
      {showReport && (<WrappedEpisodes
        totalEps={state.reportJSON.shows.totalEpWatched}
        totalShows={state.reportJSON.shows.allShows.length}
        mostWatched={state.reportJSON.shows.topShows.mostWatched}
        leastWatched={state.reportJSON.shows.topShows.leastWatched}
      />)}
      {showReport && (<WrappedFavActors
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
      />)}
      {showReport && (<WrappedMoviesOverview year={2022} movies={state.reportJSON.movie.top5Movies} />)}
      {showReport && (<WrappedFavActors
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
      />)}
      {showReport && (<BingeData bingeData={state.reportJSON.bingeData} />)}
      {showReport && (<NVTIPersonality personality={state.reportJSON.personality} />)}

      {!showReport && (<ReportError />)}

      {/* Button to wipe data */}
      {showReport && (<div className="WrappedWipe">
        <p className="WrappedWipeP">
          Finished with your report and want to wipe your data from our servers?
        </p>
        <sub className="subReroute">
          Clicking this button will also re-route you to the landing page.
        </sub>
        <Button
          aria-label={wipe_data}
          onClick={() => {
            handleWipingData()         
          }}
          style={{
            padding: "0.75em 1.5em",
            fontFamily: "Metropolis-Black",
            color: "white",
            backgroundColor: "#D92929",
            marginTop: "1.5em",
            marginBottom: "8em"
          }}
          variant="contained"
          color="primary"
          component="span"
          size="large"
        >
          Clear my history
        </Button>
        <p className="cute">Made with ❤️ by Kathryn, Karen, CJ, and Brian</p>
      </div>)}
    </motion.div>
  );

  function handleWipingData() {
    //hide the report
    setShowReport(false);
    console.log("report hidden")
    //wipe the backend info TODO: ask cj and brian if they save the user data anywhere...
    const url = "http://localhost:6969/wipeData";
    fetch(url)
      .then((response) => response.json())
      .then((responseJSON) => {
        if (responseJSON.result === "success") {
          console.log("successfully wiped data from backend"); // it's working
        } else {
          //FIXME: 'fail' dialog based on backend error thrown
          console.log("failed to wipe data from backend");
        }
      });
    //wipe the data associated with the location /Report
    navigate('/', { replace: true });
    // navigate('/Report');
    console.log("/Report name and reportJSON deleted")
    //TODO: check if we wiped csv data from frontend (landing/Uploader)
     
    //TODO: reroute to the landing page

  }
}
  


export default Report;
