
import { render, screen } from "@testing-library/react";
import mockAll from "../../../assets/mocks/mockActor.json";
import { WrappedMinutes } from "../../../components/report/WrappedMinutes";

/**
 * Test where we check that the WrappedMinutes component renders properly.
 */
test("renders WrappedMinutes", () => {
  render(<WrappedMinutes totalMin={mockAll.totalMin} />);

  expect(
    screen.getByText(/All that exploration added up to/i)
  ).toBeInTheDocument();


  expect(screen.getByText(/minutes on Netflix./i)).toBeInTheDocument();

  expect(screen.getByText(/That's about/i)).toBeInTheDocument();
  expect(screen.getByText(/1,666 hours,/i)).toBeInTheDocument();
  expect(screen.getByText(/69 days!/i)).toBeInTheDocument();
});
