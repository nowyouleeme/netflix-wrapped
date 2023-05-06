
import { render, screen } from "@testing-library/react";
import { WrappedGenres } from "../../../components/report/WrappedGenres";
import mockAll from "../../../assets/mocks/mockActor.json";
import empty from "../../../assets/mocks/empty.json";

/**
 * Test where we check that the WrappedGenres component renders properly.
 */
test("render WrappedGenres", () => {
  render(<WrappedGenres genres={mockAll.top5Genres} />);

  const thisYear = screen.getByText(/To start off, here are your/i);
  expect(thisYear).toBeInTheDocument();

  expect(screen.getByText(/top/i)).toBeInTheDocument();
  expect(screen.getByText(/genres./i)).toBeInTheDocument();

  const favorite = screen.getByText(/Your favorite seemed to be/i);
  expect(favorite).toBeInTheDocument();

  const explore = screen.getByText(
    /Look at you go, venturing into the unknown/i
  );
  expect(explore).toBeInTheDocument();

  expect(screen.getByText(/Action./i)).toBeInTheDocument();

  // check polar chart!!!
});

/**
 * Test where we check that the WrappedGenres component renders properly with no genre data.
 */
test("render WrappedGenres empty", () => {
  render(<WrappedGenres genres={empty.top5Genres} />);

  const explore = screen.getByText(
    /We weren’t able to determine your favorite genres 😞/i
  );
});
