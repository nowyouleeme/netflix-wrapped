
import { render, screen } from "@testing-library/react";
import mockAll from "../../../assets/mocks/mockActor.json";
import { WrappedMoviesOverview } from "../../../components/report/WrappedMoviesOverview";

test("renders WrappedMoviesOverview", () => {
  render(
    <WrappedMoviesOverview year={2022} movies={mockAll.movie.top5Movies} />
  );

  const header1 = screen.getByRole("heading", {
    name: /When it came to movies,/i,
  });
  expect(header1).toBeInTheDocument();

  expect(
    screen.getByText(/you were a number one fan for/i)
  ).toBeInTheDocument();
  expect(screen.getByText(/which you watched/i)).toBeInTheDocument();

  const header2 = screen.getByRole("heading", {
    name: /Some of your other favorites included:/i,
  });
  expect(header2).toBeInTheDocument();

  // render cards!!
});
