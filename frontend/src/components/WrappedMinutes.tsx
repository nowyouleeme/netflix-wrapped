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
        <p>All that exploration added up to</p>
        <h1>
          {Math.floor(props.totalMin).toLocaleString()}
          <br />
          {Math.floor(props.totalMin).toLocaleString()}
          <br />
          {Math.floor(props.totalMin).toLocaleString()}
          <br />
          {Math.floor(props.totalMin).toLocaleString()}
          <br />
        </h1>
        <p>minutes on Netflix.</p>
        <sub>
          That's about{" "}
          <b>{calculateHours(props.totalMin).toLocaleString()} hours,</b> or{" "}
          <b>{calculateDays(props.totalMin).toLocaleString()} days!</b>
        </sub>
      </div>
    );
}