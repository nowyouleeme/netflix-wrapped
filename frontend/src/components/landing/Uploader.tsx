import {useState} from 'react'
import Papa from "papaparse";
import { Button } from '@mui/material';

export const load_csv = "Select CSV file here"; //TODO: fix any testing that depended on the specific text.. if there's errors
export const fetch_wrapped = "Fetch your wrapped report";
export const upload_csv = "Upload the selected CSV file";


function Uploader() {
    const [file, setFile] = useState<File>();
    const [csvUploadStatus, setCsvUploadStatus] = useState("");

    return (
      <div className="Uploader">
        <form>

          {/* input for the user to put in their csv */}
          <input
            id="netflix-file"
            type="file"
            accept=".csv"
            style={{ visibility: "hidden" , height: "0"}}
            className="input-field"
            onChange={({ target: { files } }) => {
              //set the file to new one
              if (files) {
                setFile(files[0]);
              }
            }}
          />


          {/* button UI for the input field for the user's csv */}
          <label className="Upload" htmlFor="netflix-file">
            <Button
            aria-label={upload_csv}
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
              Select NetflixViewingHistory.CSV File
            </Button>
          </label>

          
          

          <Button
            aria-label={upload_csv}
            onClick={() => {
              //when user submits,
              if (file) {
                console.log("hello hello file file");
                //parse the file
                Papa.parse(file, { 
                  complete: function (results) {
                    //upon parsing completion
                    //1. send to backend
                    console.log(results)
                    const url =
                      "http://localhost:6969/saveData?usercsv={usercsv:" +
                      results.data + "}"
                    console.log("url is " + url)
                    fetch(url)
                      .then((response) => response.json())
                      .then((responseJSON) => {
                        if (responseJSON.result === "success") {
                          //'success' dialog
                          setCsvUploadStatus("netflix viewing history csv successfully uploaded")
                          console.log("successfully sent to backend"); // it's working
                        } else {
                          //FIXME: 'fail' dialog based on backend error thrown
                          setCsvUploadStatus("failed to upload netflix viewing history csv")
                        }
                      })
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
            component="span">
            Upload NetflixViewingHistory.CSV File
          </Button>

          {/* update when the csv has been uploaded or failed uploading */}
          {/* TODO: aria label for this */}
          <div className = "csv-upload-status"> 
            {csvUploadStatus}
          </div>


          <Button
            aria-label={fetch_wrapped}
            onClick={() => {
              //make call to the backend endpoint for retrieving a report...
              
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
            Get your wrapped
          </Button>



        </form>
      </div>
    );
}

export default Uploader