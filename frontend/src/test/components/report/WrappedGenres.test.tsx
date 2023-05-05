
import { render, screen } from "@testing-library/react";
import { WrappedGenres } from "../../../components/report/WrappedGenres";
import mockAll from "../../../assets/mocks/mockActor.json";

/**
 * Test where we check that the WrappedGenres component renders properly.
 */
test("render WrappedGenres", () => {
  render(<WrappedGenres genres={mockAll.top5Genres} />);

  const thisYear = screen.getByText(/This year, you explored/i);
  expect(thisYear).toBeInTheDocument();

  expect(screen.getByText(/different/i)).toBeInTheDocument();
  expect(screen.getByText(/genres/i)).toBeInTheDocument();

  const favorite = screen.getByText(/your favorite being/i);
  expect(favorite).toBeInTheDocument();

  const explore = screen.getByText(
    /Look at you go, venturing into the unknown!/i
  );
  expect(explore).toBeInTheDocument();

  expect(screen.getByText(/Action./i)).toBeInTheDocument();

  // check polar chart!!!
});
