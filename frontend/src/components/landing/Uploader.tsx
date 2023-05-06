import { useState } from "react";
import { Button } from "@mui/material";
import { Link } from "react-router-dom";
import { convertCSV } from "./Parsing";
import { ReportJSON } from "../../assets/data/ReportJSON";

export const load_csv = "Select CSV file here"; //TODO: fix any testing that depended on the specific text.. if there's errors
export const generate_wrapped = "Generate your Wrapped Report";
export const get_wrapped = "Go see the Netflix Wrapped Report";
export const upload_csv = "Upload the selected CSV file";

/**
 * An interface containing all of the properties expected of the props that are passed into the Uploader component.
 */
export interface UploaderProps {
  name: String;
}

/**
 * Function that returns a Uploader component,
 * which displays information and interactive components for the user to select and upload their NetflixViewingHistory.csv.
 * @param props all of the information needed to select and upload their viewing history.
 * @returns a Uploader component
 */
function Uploader(props: UploaderProps) {
  const [file, setFile] = useState<File>();
  const [fileSelected, setFileSelected] = useState<boolean>(false);
  const [reportJSON, setReportJSON] = useState<ReportJSON>();
  const [status, setStatus] = useState(<></>);
  const [showUpload, setShowUpload] = useState(false);
  const [showWrapped, setShowWrapped] = useState(false);
  const name = props.name;

  return (
    <div className="Uploader">
      <form>
        {/* input for the user to put in their csv */}
        <input
          id="netflix-file"
          type="file"
          accept=".csv"
          style={{ visibility: "hidden", height: "0" }}
          className="input-field"
          onChange={({ target: { files } }) => {
            //set the file to new one
            if (files) {
              setFile(files[0]);
              setFileSelected(true);
            }
          }}
          onClick={() => {
            setShowWrapped(false);
            setShowUpload(false);
          }}
        />
        {/* this will upload to say file selected / whether or not upload was successfull */}
        {fileSelected ? (
          <p style={{ marginBottom: "1em" }}>File selected: '{file?.name}'</p>
        ) : (
          status
        )}

        {/* button UI for the input field for the user's csv */}
        <label className="Upload" htmlFor="netflix-file">
          <Button
            aria-label={load_csv}
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
            Select Your NetflixViewingHistory.CSV File
          </Button>
        </label>

        {/* button UI for the uploading the user's csv */}
        {fileSelected ? (
          <Button
            aria-label={upload_csv}
            onClick={() => {
              //when user submits,
              if (file) {
                console.log("hello hello file file");
                //parse the file
                convertCSV(file).then((jsonstring: String) => {
                  //making a post...
                  const queryString = "usercsv=" + jsonstring;
                  console.log("bello" + jsonstring)

                  const url = "http://localhost:6969/saveData?" + queryString;
                  fetch("http://localhost:6969/saveData", {
                    mode: "no-cors",
                    method: "POST",
                    body: JSON.stringify(jsonstring),
                    headers: {
                      "Content-Type": "application/json",
                      Accept: "application/json",
                    },
                  })
                    // fetch(url)
                    .then((response) => {
                      
                      if (response.status === 0) {
                        //'success' dialog

                        // setCsvUploadStatus(
                        //   "netflix viewing history csv successfully uploaded"
                        // );
                        setStatus(
                          <p style={{ marginBottom: "1em" }}>
                            '{file?.name}' uploaded successfully!
                          </p>
                        );
                        
                        setShowUpload(true);
                        setFileSelected(false);
                        console.log("successfully sent to backend");
                      } else {
                        // setCsvUploadStatus(
                        //   "failed to upload netflix viewing history csv"
                        // );
                        setStatus(
                          <p style={{ marginBottom: "1em" }}>
                            '{file?.name}' upload failed, please try again
                            later.
                          </p>
                        );
                      }
                    })
                    
                  // .catch((error) => {
                  //   setStatus(<p style={{ marginBottom: "1em" }}>
                  //   '{file?.name}' upload failed, please try again
                  //   later.
                  //   </p>);
                  //   console.log("hi")
                  // });
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
            Upload the CSV
          </Button>
        ) : (
          <></>
        )}
        {/* update when the csv has been uploaded or failed uploading */}
        {/* TODO: aria label for this */}
        {/* <div className="csv-upload-status">{csvUploadStatus}</div> */}
        {showUpload ? (
          <Button
            aria-label={generate_wrapped}
            onClick={() => {
              //make call to the backend endpoint for generating a report...
              const url = "http://localhost:6969/wrapped";
              fetch(url)
                .then((response) => {
                  console.log(response);
                  return response.json();
                })
                .then((responseJSON) => {
                  setStatus(
                    <p style={{ marginBottom: "1em" }}>
                      Loading your Netflix Wrapped...
                    </p>
                  );
                  if (responseJSON.result === "success") {
                    //'success' dialog
                    // setReportStatus("report successfully generated");
                    setStatus(
                      <p style={{ marginBottom: "1em" }}>
                        Your Netflix Wrapped was generated successfully!
                      </p>
                    );
                    setShowWrapped(true);
                    setShowUpload(false);
                    console.log(
                      "sent wrapped request to backend, responseJSON : " +
                        responseJSON
                    );
                    //parse the backend response into typescript object
                    console.log("responseJSON.report : " + responseJSON.report);
                    let parsedReportJSON: ReportJSON = JSON.parse(
                      JSON.stringify(responseJSON.report)
                    );
                    console.log("parsedReportJSON: " + parsedReportJSON);
                    setReportJSON(parsedReportJSON);
                  } else {
                    //'fail' dialog based on backend error thrown
                    setStatus(
                      <p style={{ marginBottom: "1em" }}>
                        Failed to generate your Netflix Wrapped, please try
                        again later.
                      </p>
                    );
                    // setReportStatus("failed to generate report");
                  }
                });
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
            Generate Report
          </Button>
        ) : (
          <></>
        )}
        {/* make so that this button will only show when user uploads valid file */}
        {showWrapped ? (
          <Link
            style={{ textDecoration: "none" }}
            to={`/Report`}
            state={{ name: name, reportJSON: reportJSON }}
          >
            <label className="Upload" htmlFor="netflix-file">
              <Button
                aria-label={get_wrapped} //no onclick,it should go to link
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
                Get your wrapped!
              </Button>
            </label>
          </Link>
        ) : (
          <></>
        )}
      </form>
    </div>
  );
}

export default Uploader;
