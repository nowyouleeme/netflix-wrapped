interface ReportWelcomeProps {
    name: string;
}
export function ReportWelcome(props: ReportWelcomeProps) {
  function fillName() {
    let name = props.name;
    name.replace(" ", "");
    if (name === "") {
      return(<span></span>)
    } else {
      return(<span>, {props.name}</span>)
    }
  }
    return (
      <div className="center WrappedWelcome">
        <p>Welcome to your Netflix Wrapped{fillName()} 🎁</p>
      </div>
    );
}