interface WrappedMinutesProps {
    totalMin: number;
}
export function WrappedMinutes(props: WrappedMinutesProps) {
    // function to calculate hours/days
    // ensure numbers have commas where need be for readability
    return (
      <div className="center WrappedMinutes">
        <p>All that exploration added up to</p>
        <h1>
          {props.totalMin}
          <br />
          {props.totalMin}
          <br />
          {props.totalMin}
          <br />
          {props.totalMin}
          <br />
        </h1>
        <p>minutes on Netflix.</p>
        <sub>
          That's about <b>1667 hours,</b> or <b>69 days!</b>
        </sub>
      </div>
    );
}