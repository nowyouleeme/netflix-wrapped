import { Button } from "@mui/material";
import { NavigateFunction } from "react-router-dom";

export const wipe_data = "Wipe your personal csv data from our server";

interface WipeProps {
  setShowReport: (bool: boolean) => void;
  navigate: NavigateFunction;
}

export function Wipe(props: WipeProps) {
        function handleWipingData() {
          //hide the report
          props.setShowReport(false);
          console.log("report hidden");
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
          props.navigate("/", { replace: true });
          // navigate('/Report');
          console.log("/Report name and reportJSON deleted");
          //TODO: check if we wiped csv data from frontend (landing/Uploader)

          //TODO: reroute to the landing page
        }
  return (
    <div className="WrappedWipe">
      <p className="WrappedWipeP">
        Finished with your report and want to wipe your data from our servers?
      </p>
      <sub className="subReroute">
        Clicking this button will also re-route you to the landing page.
      </sub>
      <Button
        aria-label={wipe_data}
        onClick={() => {
          handleWipingData();
        }}
        style={{
          padding: "0.75em 1.5em",
          fontFamily: "Metropolis-Black",
          color: "white",
          backgroundColor: "#D92929",
          marginTop: "1.5em",
          marginBottom: "8em",
        }}
        variant="contained"
        color="primary"
        component="span"
        size="large"
      >
        Clear my history
      </Button>
      <p className="cute">Made with ❤️ by Kathryn, Karen, CJ, and Brian</p>
    </div>
  );
}
