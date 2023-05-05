import { render, screen } from "@testing-library/react";
import mockAll from "../../../assets/mocks/mockActor.json";
import { WrappedOverview } from "../../../components/report/WrappedOverview";
import { carousel_label } from "../../../components/report/MediaCarousel";

/**
 * Test where we check that the WrappedOverview component renders properly.
 */
test("render WrappedOverview", () => {
  render(
    <WrappedOverview
      shows={mockAll.shows.allShows}
      movies={mockAll.movie.allMovies}
    />
  );

  const header = screen.getByText(
    /Out of that time you spent with us in 2022/i);
  expect(header).toBeInTheDocument();

  expect(screen.getByText(/you watched/i)).toBeInTheDocument();
  expect(screen.getByText(/7 shows/i)).toBeInTheDocument();
  expect(screen.getByText(/5 movies/i)).toBeInTheDocument();

  expect(
    screen.getAllByRole("figure", {
      name: carousel_label,
    }).length
  ).toBe(2);

  // check individual cards
});
