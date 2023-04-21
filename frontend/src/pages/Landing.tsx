import React, { useState } from "react";
import logo from "./logo.svg";
import { Button } from "@mui/material";
import { InputLabel } from "@mui/material";
import "./App.css";

function Landing() {
  const [fileName, setFileName] = useState<string | undefined>("");
  const [file, setFile] = useState<File>();

  function handleChange(event: React.ChangeEvent<HTMLInputElement>) {
    setFile(event.target.files?.[0]);
    setFileName(event.target.files?.[0].name);
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
          style={{ visibility: "hidden" }}
          accept=".csv"
          onChange={handleChange}
        />
        <label className="Upload" htmlFor="netflix-file">
          <span>UPLOAD VIEWING HISTORY CSV</span>
        </label>
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
