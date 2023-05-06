import { act, render, screen } from "@testing-library/react";
import ReportError from "../../pages/ReportError";
import userEvent from "@testing-library/user-event";

/**
 * Test where we check that the ReportError component renders properly.
 */
test("render ReportError", async () => {
  render(<ReportError/>);
  expect(screen.getByRole("heading", {name: "Oops!"})).toBeInTheDocument();
    expect(
      screen.getByText(/Sorry, a report hasn't been generated yet :\(/i)
    ).toBeInTheDocument();
});
