import { Button, TextField } from "@mui/material";
import { Link } from "react-router-dom";
import "../App.css";
import Uploader from "../components/uploader/Uploader"
import { useState } from "react";

function Landing() {
const [contents, setContents] = useState("");
  return (
    <div className="LandingBackground">
      <div className="center Landing">
        <div className="LandingHero">
          <h1>NETFLIX WRAPPED</h1>
          <sub>A lookback on your time with Netflix.</sub>
        </div>
        <TextField
          id="outlined-basic"
          label="What's your name?"
          sx={{ input: { color: "white", fontFamily: "Metropolis-Medium" } }}
          variant="outlined"
          onChange={(e) => setContents(e.target.value)} // this will actually store the content inside the input box
          value={contents} // NEED TO SPECIFY THIS IN ORDER FOR TEXT BOX TO CLEAR
        />
        <Uploader />

        <Link
          style={{ textDecoration: "none" }}
          to={`/Report`}
          state={{ name: contents }}
        >
          <label className="Upload" htmlFor="netflix-file">
            <Button
              style={{
                padding: "0.75em 1.5em",
                fontFamily: "Metropolis-Black",
                color: "white",
                backgroundColor: "#D92929",
                marginTop: "1em",
              }}
              variant="contained"
              color="primary"
              component="span"
            >
              go
            </Button>
          </label>
        </Link>
        <div className="center Help">
          <label>How do I get my ViewingHistory.csv?</label>
            <svg
              width="30"
              height="18"
              viewBox="0 0 30 18"
              fill="none"
              xmlns="http://www.w3.org/2000/svg"
            >
              <path
                d="M3.42048 0L14.5553 11.1348L25.69 0L29.1105 3.44474L14.5553 18L0 3.44474L3.42048 0Z"
                fill="rgb(193, 193, 193"
              />
            </svg>
        </div>
      </div>
      <div className="Instructions">
        <div>
          <h2>How our application works</h2>
        </div>
        <h3>1. Find your Netflix Viewing History</h3>
        <h3>2. Download your Netflix Viewing History</h3>
        <h3>3. Upload your Netflix Viewing History</h3>
      </div>
    </div>
  );
}

export default Landing;
