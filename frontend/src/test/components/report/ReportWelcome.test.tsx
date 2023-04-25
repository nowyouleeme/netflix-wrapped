
import { render, screen } from "@testing-library/react";
import { ReportWelcome } from "../../../components/report/ReportWelcome";

test("render ReportWelcome", () => {
  render(<ReportWelcome name={"Karen"} />);

  const reportWelcome = screen.getByText(/Welcome to your Netflix Wrapped/i);
  expect(reportWelcome).toBeInTheDocument();
});
