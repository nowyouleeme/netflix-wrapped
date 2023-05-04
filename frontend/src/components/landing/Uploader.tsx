import {useState} from 'react'
import Papa from "papaparse";
import { Button} from '@mui/material';
import { Link } from "react-router-dom";
import mockAll from "../../assets/mocks/mockActor.json";
import { convertCSV } from './Parsing';
import { ReportJSON } from '../../assets/data/ReportJSON';
export const load_csv = "Select CSV file here"; //TODO: fix any testing that depended on the specific text.. if there's errors
export const generate_wrapped = "Generate your wrapped report";
export const upload_csv = "Upload the selected CSV file";

export interface UploaderProps {
  name: String;
}

function Uploader(props: UploaderProps) {
    const [file, setFile] = useState<File>();
    const [fileSelected, setFileSelected] = useState<boolean>(false);
    const [confirmUpload, setConfirmUpload] = useState<boolean>(false);
    const [csvUploadStatus, setCsvUploadStatus] = useState("");
    const [reportStatus, setReportStatus] = useState("");
    const [reportJSON, setReportJSON] = useState<ReportJSON>();
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
          />
          {/* this will upload to say file selected / whether or not upload was successfull */}
          {confirmUpload ? (
            <p style={{ marginBottom: "1em" }}>File Uploaded Successfully {file?.name}</p>
          ) : fileSelected ? (
            <p style={{ marginBottom: "1em" }}>File selected: {file?.name}</p>
          ) : (
            <></>
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
          <Button
            aria-label={upload_csv}
            onClick={() => {
              //when user submits,
              if (file) {
                console.log("hello hello file file");
                //parse the file
                convertCSV(file)
                  .then((jsonstring: String) => {
                    console.log("inside .then jsonstring is :" + jsonstring)
                    const url =
                      "http://localhost:6969/saveData?usercsv=" + jsonstring;
                      console.log("url is " + url);
                      fetch(url)
                        .then((response) => response.json())
                        .then((responseJSON) => {
                          setConfirmUpload(true);
                          if (responseJSON.result === "success") {
                            //'success' dialog
                            setCsvUploadStatus(
                              "netflix viewing history csv successfully uploaded"
                            );
                            console.log("successfully sent to backend"); 
                          } else {
                            setCsvUploadStatus(
                              "failed to upload netflix viewing history csv"
                            );
                          }
                        });
                  })
                

                
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

          {/* update when the csv has been uploaded or failed uploading */}
          {/* TODO: aria label for this */}
          <div className = "csv-upload-status"> 
            {csvUploadStatus}
          </div>

          {/* TODO: meeds fixing*/}
          {reportStatus}
          <Button
            aria-label={generate_wrapped}
            onClick={() => {
              //make call to the backend endpoint for generating a report...
              const url = "http://localhost:6969/wrapped"
              fetch(url)
                .then((response) => {
                  console.log(response);
                  return response.json()
                })
                .then((responseJSON) => {
                  if (responseJSON.result === "success") {
                    //'success' dialog
                    setReportStatus("report successfully generated")
                    console.log("sent wrapped request to backend, responseJSON : " + responseJSON)
                    //parse the backend response into typescript object
                    console.log("responseJSON.report : " + responseJSON.report)
                    let parsedReportJSON: ReportJSON = JSON.parse(JSON.stringify(responseJSON.report));
                    console.log("parsedReportJSON: " + parsedReportJSON)
                    setReportJSON(parsedReportJSON)
                  } else {
                    //'fail' dialog based on backend error thrown
                    setReportStatus("failed to generate report")
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
            Generate Report
          </Button>



          {/* make so that this button will only show when user uploads valid file */}
          {csvUploadStatus ===
          "netflix viewing history csv successfully uploaded" ? (
            <Link
              style={{ textDecoration: "none" }}
              to={`/Report`}
              state={{ name: name, reportJSON: reportJSON}} 
            >
              <label className="Upload" htmlFor="netflix-file">
              <Button
                aria-label={generate_wrapped} //no onclick,it should go to link
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

export default Uploader