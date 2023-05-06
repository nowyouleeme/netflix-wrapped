
import { render, screen } from "@testing-library/react";
import mockAll from "../../../assets/mocks/json/mockActor.json";
import { WrappedMoviesOverview, favorite_movie, remaining_movies } from "../../../components/report/WrappedMoviesOverview";
import empty from "../../../assets/mocks/json/empty.json";

/**
 * Test where we check that the WrappedMoviesOverview component renders properly.
 */
test("renders WrappedMoviesOverview", () => {
  render(
    <WrappedMoviesOverview year={2022} movies={mockAll.movie.top5Movies} />
  );

  const header1 = screen.getByText(/When it came to movies,/i);
  expect(header1).toBeInTheDocument();

  expect(
    screen.getByText(/you were a number one fan for/i)
  ).toBeInTheDocument();
  expect(screen.getByText(/which you watched/i)).toBeInTheDocument();

  const header2 = screen.getByText(/Some of your other favorites included:/i);
  expect(header2).toBeInTheDocument();

  expect(
    screen.getByRole("figure", {
      name: favorite_movie,
    })
  ).toBeInTheDocument();
  expect(
    screen.getByRole("figure", {
      name: remaining_movies,
    })
  ).toBeInTheDocument();
  
  expect(
    screen.getByRole("img", { name: "Poster for the movie 'Inception'" })
  ).toBeInTheDocument();
  expect(
    screen.getByRole("img", {
      name: "Poster for the movie 'Minions: The Rise of Gru'",
    })
  ).toBeInTheDocument();
  expect(
    screen.getByRole("img", { name: "Poster for the movie 'La La Land'" })
  ).toBeInTheDocument();
  expect(
    screen.getByRole("img", { name: "Poster for the movie 'The Call'" })
  ).toBeInTheDocument();
  expect(
    screen.getByRole("img", { name: "Poster for the movie '21 Jump Street'" })
  ).toBeInTheDocument();

  // links
  expect(
    screen.getByRole("link", { name: "Poster for the movie 'Inception'" })
  ).toBeInTheDocument();
  expect(
    screen.getByRole("link", {
      name: "Poster for the movie 'Minions: The Rise of Gru'",
    })
  ).toBeInTheDocument();
  expect(
    screen.getByRole("link", { name: "Poster for the movie 'La La Land'" })
  ).toBeInTheDocument();
  expect(
    screen.getByRole("link", { name: "Poster for the movie 'The Call'" })
  ).toBeInTheDocument();
  expect(
    screen.getByRole("link", { name: "Poster for the movie '21 Jump Street'" })
  ).toBeInTheDocument();
});

/**
 * Test where we check that the WrappedMoviesOverview component renders properly with empty data.
 */
test("renders WrappedMoviesOverview empty", () => {
  render(
    <WrappedMoviesOverview year={2022} movies={empty.movie.top5Movies} />
  );

  expect(
    screen.getByText(/We couldn't sort through your movie viewing behavior 😕/i)
  ).toBeInTheDocument();

});
