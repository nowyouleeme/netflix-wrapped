import { render, screen } from "@testing-library/react";
import mockAll from "../../../assets/mocks/mockActor.json";
import { WrappedOverview } from "../../../components/report/WrappedOverview";
import { carousel_label } from "../../../components/report/MediaCarousel";
import empty from "../../../assets/mocks/empty.json";

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

  const header = screen.getByText(/Out of the time you've spent with us,/i);
  expect(header).toBeInTheDocument();

  expect(screen.getByText(/you watched/i)).toBeInTheDocument();
  expect(screen.getByText(/7 shows/i)).toBeInTheDocument();
  expect(screen.getByText(/5 movies/i)).toBeInTheDocument();

  expect(
    screen.getAllByRole("figure", {
      name: carousel_label,
    }).length
  ).toBe(2);

  expect(
    screen.getByRole("img", { name: "Poster for 'BEEF'" })
  ).toBeInTheDocument();

  expect(
    screen.getByRole("img", { name: "Poster for 'Breaking Bad'" })
  ).toBeInTheDocument();

  expect(
    screen.getByRole("img", { name: "Poster for 'Bob's Burgers'" })
  ).toBeInTheDocument();

  expect(
    screen.getByRole("img", { name: "Poster for 'Gilmore Girls'" })
  ).toBeInTheDocument();

  expect(
    screen.getByRole("img", { name: "Poster for 'Reply 1988'" })
  ).toBeInTheDocument();

  expect(
    screen.getByRole("img", { name: "Poster for 'Attack on Titan'" })
  ).toBeInTheDocument();

  expect(
    screen.getByRole("img", { name: "Poster for 'Juvenile Justice'" })
  ).toBeInTheDocument();

  expect(
    screen.getByRole("img", { name: "Poster for 'Inception'" })
  ).toBeInTheDocument();

  expect(
    screen.getByRole("img", {
      name: "Poster for 'Minions: The Rise of Gru'",
    })
  ).toBeInTheDocument();

  expect(
    screen.getByRole("img", { name: "Poster for 'La La Land'" })
  ).toBeInTheDocument();
  expect(
    screen.getByRole("img", { name: "Poster for 'The Call'" })
  ).toBeInTheDocument();
  expect(
    screen.getByRole("img", { name: "Poster for '21 Jump Street'" })
  ).toBeInTheDocument();

  // links

  expect(
    screen.getByRole("link", { name: "Poster for '21 Jump Street'" })
  ).toBeInTheDocument();
  expect(
    screen.getByRole("link", { name: "Poster for 'BEEF'" })
  ).toBeInTheDocument();

  expect(
    screen.getByRole("link", { name: "Poster for 'Breaking Bad'" })
  ).toBeInTheDocument();

  expect(
    screen.getByRole("link", { name: "Poster for 'Bob's Burgers'" })
  ).toBeInTheDocument();

  expect(
    screen.getByRole("link", { name: "Poster for 'Gilmore Girls'" })
  ).toBeInTheDocument();

  expect(
    screen.getByRole("link", { name: "Poster for 'Reply 1988'" })
  ).toBeInTheDocument();

  expect(
    screen.getByRole("link", { name: "Poster for 'Attack on Titan'" })
  ).toBeInTheDocument();

  expect(
    screen.getByRole("link", { name: "Poster for 'Juvenile Justice'" })
  ).toBeInTheDocument();

  expect(
    screen.getByRole("link", { name: "Poster for 'Inception'" })
  ).toBeInTheDocument();

  expect(
    screen.getByRole("link", {
      name: "Poster for 'Minions: The Rise of Gru'",
    })
  ).toBeInTheDocument();

  expect(
    screen.getByRole("link", { name: "Poster for 'La La Land'" })
  ).toBeInTheDocument();
  expect(
    screen.getByRole("link", { name: "Poster for 'The Call'" })
  ).toBeInTheDocument();

});

/**
 * Test where we check that the WrappedOverview component renders properly with empty data.
 */
test("render WrappedOverview empty", () => {
  render(
    <WrappedOverview
      shows={empty.shows.allShows}
      movies={empty.movie.allMovies}
    />
  );

  expect(
    screen.getByText(/We couldn’t summarize your show viewing history 😣/i)
  ).toBeInTheDocument();
});
