interface ReportWelcomeProps {
    name: string;
}
export function ReportWelcome(props: ReportWelcomeProps) {
    return (
      <div className="center WrappedWelcome">
        <h2>Welcome to your Netflix Wrapped, {props.name}</h2>
      </div>
    );
}