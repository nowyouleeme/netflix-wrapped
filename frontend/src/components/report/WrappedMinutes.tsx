/**
 * An interface containing all of the properties expected of the props that are passed into the WrappedMinutes component.
 */
interface WrappedMinutesProps {
  totalMin: number;
}

/**
 * Function that returns a WrappedMinutes component, 
 * which displays information on the total minutes the user has spent on Netflix based on their NetflixViewingHistory.csv
 * show and movie history.
 * @param props all of the information needed to display the time a user has spent on Netflix
 * @returns a WrappedMinutes component
 */
export function WrappedMinutes(props: WrappedMinutesProps) {
  /**
   * Function that converts minutes to hours as an integer.
   * @param minutes
   * @returns
   */
  function calculateHours(minutes: number) {
    let hours = minutes / 60;
    return hours.toFixed(1);
  }

  /**
   * Function that converts minutes to days as an integer.
   * @param minutes
   * @returns
   */
  function calculateDays(minutes: number) {
    let days = minutes / 60 / 24;
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
