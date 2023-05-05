interface WrappedMinutesProps {
    totalMin: number;
}
export function WrappedMinutes(props: WrappedMinutesProps) {
    // function to calculate hours/days
    function calculateHours(minutes: number) {
      let hours = minutes / 60 ;
        return hours.toFixed(1);
    }

    function calculateDays(minutes: number) {
      let days = (minutes / 60) / 24
      return days.toFixed(1);
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
            {Number(calculateHours(props.totalMin)) > 1 ? "hours" : "hour"},
          </b>{" "}
          or{" "}
          <b>
            {calculateDays(props.totalMin).toLocaleString()}{" "}
            {Number(calculateDays(props.totalMin)) > 1 ? "days" : "day"}!
          </b>
        </sub>
      </div>
    );
}