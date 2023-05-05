interface WrappedMinutesProps {
    totalMin: number;
}
export function WrappedMinutes(props: WrappedMinutesProps) {
    // function to calculate hours/days
    function calculateHours(minutes: number) {
        return Math.floor(minutes / 60);
    }

    function calculateDays(minutes: number) {
      return Math.floor((minutes / 60) / 24);
    }
    // ensure numbers have commas where need be for readability
    return (
      <div className="center WrappedMinutes">
        <p className="minutesNormalP">All that exploration added up to</p>
        <p className="minutesStyledP">
          {Math.floor(props.totalMin).toLocaleString()}
          <br aria-hidden="true" />
          <span aria-hidden="true">
            {Math.floor(props.totalMin).toLocaleString()}
          </span>
          <br aria-hidden="true" />
          <span aria-hidden="true">
            {Math.floor(props.totalMin).toLocaleString()}
          </span>
          <br aria-hidden="true" />
          <span aria-hidden="true">
            {Math.floor(props.totalMin).toLocaleString()}
          </span>

          <br aria-hidden="true" />
        </p>
        <p className="minutesNormalP">
          {Math.floor(props.totalMin) > 1 ? "minutes" : "minute"} on Netflix.
        </p>
        <sub>
          That's about{" "}
          <b>
            {calculateHours(props.totalMin).toLocaleString()}{" "}
            {calculateHours(props.totalMin) > 1 ? "hours" : "hour"},
          </b>{" "}
          or{" "}
          <b>
            {calculateDays(props.totalMin).toLocaleString()}{" "}
            {calculateDays(props.totalMin) > 1 ? "days" : "day"}!
          </b>
        </sub>
      </div>
    );
}