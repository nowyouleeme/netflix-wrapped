import { Button, TextField} from "@mui/material";
import { Link } from "react-router-dom";
import "../App.css";
import Uploader from "../components/landing/Uploader";
import { useState } from "react";
import { AnimatePresence, motion } from "framer-motion";


/**
 * Styling prop for the input field on the landing page for the user to input their name. 
 */
const style = {
  input: {
    color: "white",
    fontFamily: "Metropolis-Medium",
  },

  "& label.Mui-focused": {
    color: "white",
  },

  "& .MuiOutlinedInput-root": {
    "& fieldset": {
      borderColor: "#8F8F8F",
    },
    "&:hover fieldset": {
      borderColor: "white",
    },
    "&.Mui-focused fieldset": {
      borderColor: "white",
    },
  },
};

/**
 * Aria labels for the button elements on the landing page.
 */
export const next_button_field = "Navigation to upload CSV";
export const back_button_field = "Navigation to text field";

/**
 * Function that returns the Netflix Wrapped Landing React component, where users will be able to input
 * their name, upload their NetflixViewingHistory.csv, figure out how to obtain their NetflixViewingHistory.csv, and generate
 * their report.
 * @returns the Netflix Wrapped Landing React component
 */
function Landing() {
  const [userName, setUserName] = useState<string>("");
  const [selectedTab, setSelectedTab] = useState("field");

  return (
    <div aria-live="polite" className="LandingBackground">
      <div className="center Landing">
        <div className="LandingHero">
          <h1>NETFLIX WRAPPED</h1>
          <sub>A lookback on your time with Netflix.</sub>
        </div>
        <AnimatePresence mode="wait">
          <motion.div
            className="center LandingInput"
            key={selectedTab === "field" ? "field" : "empty"}
            initial={{ opacity: 0 }}
            animate={{ opacity: 1 }}
            exit={{ opacity: 0 }}
            transition={{ duration: 0.5 }}
          >
            {selectedTab === "field" ? (
              <>
                <TextField
                  role="textbox"
                  color="primary"
                  style={{ marginRight: "1.5em" }}
                  id="outlined-basic"
                  label="What's your name?"
                  sx={style}
                  variant="outlined"
                  onChange={(e) => setUserName(e.target.value)} // this will actually store the content inside the input box
                />

                <Button
                  aria-label={next_button_field}
                  style={{
                    padding: "0.75em 1.5em",
                    fontFamily: "Metropolis-Black",
                    color: "white",
                    backgroundColor: "#D92929",
                  }}
                  variant="contained"
                  color="primary"
                  onClick={() => setSelectedTab("upload")}
                >
                  Next
                </Button>
              </>
            ) : (
              <div className="UploaderContainer">
                {/* //TODO: give uploader the prop of the name */}
                <Uploader name={userName} />
                <div>
                  <Button
                    aria-label={back_button_field}
                    style={{
                      padding: "0.75em 1.5em",
                      fontFamily: "Metropolis-Black",
                      color: "white",
                      backgroundColor: "transparent",
                      marginTop: "1em",
                      marginRight: "1em",
                      textDecoration: "underline",
                    }}
                    disableRipple
                    onClick={() => setSelectedTab("field")}
                  >
                    back
                  </Button>
                  <Link
                    style={{ textDecoration: "none" }}
                    to={`/Report`}
                    state={{ name: userName }}
                  ></Link>
                </div>
              </div>
            )}
          </motion.div>
          <motion.div
            className="center"
            initial={{ opacity: 0 }}
            animate={{ opacity: 1 }}
            exit={{ opacity: 0 }}
            transition={{ duration: 0.5 }}
          >
            {selectedTab === "upload" ? (
              <motion.div
                className="center Help"
                initial={{ opacity: 0 }}
                animate={{ opacity: 1 }}
                exit={{ opacity: 0 }}
                transition={{ duration: 0.5 }}
              >
                <label>How do I get my NetflixViewingHistory.csv?</label>
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
              </motion.div>
            ) : (
              <></>
            )}
          </motion.div>
        </AnimatePresence>
      </div>
      {selectedTab === "upload" ? (
        <div className="Instructions">
          <div>
            <h3>How to get your CSV:</h3>
          </div>
          <div className="instructionsSection">
            <div className="instructionsText">
              <h2>1. Find your Netflix Viewing History</h2>
              <p>
                Log into your Netflix account and navigate to your "Account"
                settings by clicking your account avatar in the upper-right
                corner of the screen.
              </p>
              <p>
                Navigate from "Profile & Parental Controls" &gt; "Viewing
                Activity" and click on the "View" button. You can also locate
                your viewing activity{" "}
                <a
                  rel="noreferrer"
                  href="https://www.netflix.com/viewingactivity"
                  target="_blank"
                >
                  HERE.
                </a>
              </p>
            </div>
            <div className="instructionsImg">
              <img
                src={require("../assets/images/findCSV.jpg")}
                alt="Netflix 'Account' Settings Page"
              />
            </div>
          </div>
          <div className="instructionsSection">
            <div className="instructionsText">
              <h2>2. Download your Netflix Viewing History</h2>
              <p>
                After clicking "View," you should be able to see a page that
                says "Activity for [your profile name]." Scroll down to the
                bottom of the screen where it says "Download All."
              </p>
              <p>
                Clicking that button will download your Netflix viewing activity
                in a .CSV format onto your device, which you can upload to our
                application along with your name above! The file should be
                titled "NetflixViewingHistory.csv."{" "}
              </p>
            </div>
            <div className="instructionsImg">
              <img
                src={require("../assets/images/downloadCSV.jpg")}
                alt="Netflix 'Viewing History' Page within 'Account'"
              />
            </div>
          </div>
        </div>
      ) : (
        <></>
      )}
    </div>
  );
}

export default Landing;
