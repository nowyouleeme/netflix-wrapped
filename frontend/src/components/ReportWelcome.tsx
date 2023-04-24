import { useState } from "react";

interface ReportWelcomeProps {
    name: string;
}
export function ReportWelcome(props: ReportWelcomeProps) {
    return (
      <div className="center WrappedWelcome">
        <h2>Welcome to your Netflix Wrapped, <span>{props.name}</span></h2>
      </div>
    );
}