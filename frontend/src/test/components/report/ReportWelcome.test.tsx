
import { render, screen } from "@testing-library/react";
import { ReportWelcome } from "../../../components/report/ReportWelcome";

/**
 * Test where we check that the ReportWelcome component renders properly.
 */
test("render ReportWelcome", () => {
  render(<ReportWelcome name={"Karen"} />);

  const reportWelcome = screen.getByText(/Welcome to your Netflix Wrapped/i);
  expect(reportWelcome).toBeInTheDocument();

  expect(
    screen.getByText(/\*Any data that we couldn't generate is because our database is limited to Netflix content from 2021 or before/i)
  ).toBeInTheDocument();

  expect(
    screen.getByText(
      /Karen/i
    )
  ).toBeInTheDocument();

});

/**
 * Test where we check that the ReportWelcome component renders properly with no name.
 */
test("render ReportWelcome empty", () => {
  render(<ReportWelcome name={""} />);

  const reportWelcome = screen.getByText(/Welcome to your Netflix Wrapped/i);
  expect(reportWelcome).toBeInTheDocument();

  expect(
    screen.getByText(/\*Any data that we couldn't generate is because our database is limited to Netflix content from 2021 or before/i)
  ).toBeInTheDocument();

});

