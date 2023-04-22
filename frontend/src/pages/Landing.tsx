import React, { useState, useRef } from "react";
import { Button } from "@mui/material";
import { Outlet, Link } from "react-router-dom";
import "../App.css";

function Landing() {
  const [fileName, setFileName] = useState<string | undefined>("");
  const [file, setFile] = useState<File>();
  const [uploadState, setUploadState] = useState<string>(
    "Upload ViewingHistory.csv File"
  );

  function handleChange(event: React.ChangeEvent<HTMLInputElement>) {
    setFile(event.target.files?.[0]);
    setFileName(event.target.files?.[0].name);
  }
  const hiddenFileInput = useRef<HTMLInputElement>(null);

  function handleClick() {
    // ideally you'd set the file object, get the info, send it to backend
    setUploadState("Generating your Wrapped...");
    // then wait till you get info back, trigger statechange of page/navigate to new?
    // create new app component and navigate to that? is that even possible
    //  https://bobbyhadz.com/blog/react-onclick-redirect
    // https://stackoverflow.com/questions/49840197/how-can-i-set-delay-function-in-react-routing
    // https://reactrouter.com/en/main/start/tutorial
    // idk which to do
  }

  return (
    <div className="LandingBackground">
      <div className="Landing">
        <div className="LandingHero">
          <h1>NETFLIX WRAPPED</h1>
          <sub>A lookback on your time with Netflix.</sub>
        </div>
        <input
          id="netflix-file"
          type="file"
          ref={hiddenFileInput}
          style={{ visibility: "hidden" }}
          accept=".csv"
          onChange={handleChange}
        />
        
        <Link style={{textDecoration: "none"}} to={`/Report`}>
          <label className="Upload" htmlFor="netflix-file">
            <Button
              onClick={handleClick}
              style={{
                padding: "0.75em 1.5em",
                fontFamily: "Metropolis-Black",
                color: "white",
                backgroundColor: "#D92929",
              }}
              variant="contained"
              color="primary"
              component="span"
            >
              {uploadState}
            </Button>
          </label>
        </Link>
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
