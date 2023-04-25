import {useState} from 'react'
import Papa from "papaparse";
import { Button } from '@mui/material';



function Uploader() {
    const [file, setFile] = useState<File>();

    return (
      <div className="Uploader">
        <form>
          <input
            id="netflix-file"
            type="file"
            accept=".csv"
            style={{ visibility: "hidden" }}
            className="input-field"
            onChange={({ target: { files } }) => {
              //set the file to new one
              if (files) {
                setFile(files[0]);
              }
            }}
          />
          <label className="Upload" htmlFor="netflix-file">
            <Button
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
              Upload NetflixViewingHistory.CSV File
            </Button>
          </label>

          <Button
            onClick={() => {
              //when user submits,
              if (file) {
                console.log("hello hello file file");
                Papa.parse(file, {
                  //parse the file
                  complete: function (results) {
                    //upon parsing completion
                    //send to backend
                    const url =
                      "http://localhost:6969/saveData?usercsv=" +
                      results.data.toString();
                    fetch(url)
                      .then((response) => response.json())
                      .then((responseJSON) => {
                        if (responseJSON.result === "success") {
                          //TODO: maybe some 'success' screen
                          console.log("successfully sent to backend"); // it's working
                        } else {
                          //TODO: maybe some 'fail' screen
                        }
                      })
                      //FIXME: get information back from backend algorithm
                      .then(() => {
                        fetch("http://localhost:6969/wrapped").then();
                      });
                    //TODO: display their wrapped
                    console.log("Finished:", results.data);
                  },
                });
              }
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
            component="span"
          >
            Get your wrapped
          </Button>
        </form>
      </div>
    );
}

export default Uploader