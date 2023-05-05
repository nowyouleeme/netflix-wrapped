/**
 * An interface containing all of the properties expected of the props that are passed into the ReportWelcome component.
 */
interface ReportWelcomeProps {
  name: string;
}

/**
 * Function that returns a ReportWelcome component, 
 * which welcomes the user by their name if input.
 * @param props all of the information needed to welcome the user
 * @returns a ReportWelcome component
 */
export function ReportWelcome(props: ReportWelcomeProps) {
  /**
   * Function that fills in the ReportWelcome component with the user name if provided.
   * @returns a JSX.Element containing a name.
   */
  function fillName() {
    let name = props.name;
    name.replace(" ", "");
    if (name === "") {
      return <span></span>;
    } else {
      return <span>, {props.name}</span>;
    }
  }
  return (
    <div className="center WrappedWelcome">
      <p>Welcome to your Netflix Wrapped{fillName()} 🎁</p>
    </div>
  );
}
