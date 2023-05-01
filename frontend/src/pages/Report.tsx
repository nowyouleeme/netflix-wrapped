import "../App.css";
import { WrappedGenres } from "../components/report/WrappedGenres";
import { WrappedMinutes } from "../components/report/WrappedMinutes";
import { WrappedEpisodes } from "../components/report/WrappedEpisodes";
import { WrappedMoviesOverview } from "../components/report/WrappedMoviesOverview";
import { NVTIPersonality } from "../components/report/NVTIPersonality";
import { ReportIntro } from "../components/report/ReportIntro";
import { ReportWelcome } from "../components/report/ReportWelcome";
import { WrappedOverview } from "../components/report/WrappedOverview";
import mockAll from "../assets/mocks/mockActor.json";
import { BingeData } from "../components/report/BingeData";
import { useLocation } from "react-router-dom";
import { WrappedFavActors } from "../components/report/WrappedFavActors";
import { motion } from "framer-motion";
import { Button } from '@mui/material';
import {useState} from 'react'


// mockShowData.forEach((item) => {
//   item.image = process.env.PUBLIC_URL + "/" + item.image;
// });

export const wipe_data = "Wipe your personal csv data from our server"

function Report() {
  const location = useLocation();
  const state = location.state;
  const [wipeDataStatus, setWipeDataStatus] = useState("")

  return (
    <motion.div
    
      className="Report"
      initial={{ opacity: 0 }}
      animate={{ opacity: 1 }}
      exit={{ opacity: 0 }}
      transition={{ duration: 2 }}
    >
      <ReportIntro year={2022} />
      <ReportWelcome name={state.name} />
      <WrappedGenres genres={mockAll.top5Genres} />
      <WrappedMinutes totalMin={mockAll.totalMin} />
      <WrappedOverview
        shows={mockAll.shows.allShows}
        movies={mockAll.movie.allMovies}
      />
      <WrappedEpisodes
        totalEps={mockAll.shows.totalEpWatched}
        totalShows={mockAll.shows.allShows.length}
        above50={mockAll.shows.topShows.above50_3}
        below50={mockAll.shows.topShows.below50_3}
      />
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
            Looks like you have some favorite folks... but we <b>all</b> do.
          </p>
        }
        color="#F5EC72"
        bg="#7E18A1"
      />
      <BingeData bingeData={mockAll.bingeData} />
      <NVTIPersonality personality={mockAll.personality} />

      {/* Button to wipe data */}
      <Button
        aria-label={wipe_data}
        onClick={() => {
          //call backend API
          const url = "http://localhost:6969/wipeData"
          fetch(url)
            .then((response) => response.json())
            .then((responseJSON) => {
              if (responseJSON.result === "success") {
                //'success' dialog
                setWipeDataStatus("your netflix viewing history data has been successfully removed")
                console.log("successfully sent to backend"); // it's working
              } else {
                //FIXME: 'fail' dialog based on backend error thrown
                setWipeDataStatus("failed to wipe netflix viewing history data")
              }
            })          
        }}
        style={{
          padding: "0.75em 1.5em",
          fontFamily: "Metropolis-Black",
          color: "#D92929",
          backgroundColor: "white",
          marginTop: "1em",
        }}
        variant="contained"
        color="primary"
        component="span">
        Remove your NetflixViewingHistory.CSV File from our memory
      </Button>
      <div className = "csv-upload-status"> 
        {wipeDataStatus}
      </div>
    </motion.div>
  );
}

export default Report;
