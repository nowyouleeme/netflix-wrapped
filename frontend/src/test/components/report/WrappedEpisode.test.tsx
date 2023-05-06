
import { render, screen } from "@testing-library/react";
import mockAll from "../../../assets/mocks/mockActor.json";
import { WrappedEpisodes, episode_display } from "../../../components/report/WrappedEpisodes";
import { carousel_label } from "../../../components/report/MediaCarousel";
import empty from "../../../assets/mocks/empty.json";

/**
 * Test where we check that the WrappedEpisodes component renders properly.
 */
test("render WrappedEpisodes", () => {
  render(
    <WrappedEpisodes
      totalEps={mockAll.shows.totalEpWatched}
      totalShows={mockAll.shows.allShows.length}
      mostWatched={mockAll.shows.topShows.mostWatched}
      leastWatched={mockAll.shows.topShows.leastWatched}
    />
  );

  const header = screen.getByText(/You watched a total of 200 episodes of 7 different shows,/i);
  expect(header).toBeInTheDocument();

  expect(
    screen.getByText(/with some you just couldn't get enough of:/i)
  ).toBeInTheDocument();

  expect(
    screen.getByText(/And others that you started but/i)
  ).toBeInTheDocument();
  expect(screen.getByText(/never quite finished:/i)).toBeInTheDocument();

  expect(
    screen.getByRole("figure", {
      name: episode_display,
    })
  ).toBeInTheDocument();

  expect(
    screen.getByRole("img", { name: "Poster for the show 'Reply 1988'"})
  ).toBeInTheDocument();

  expect(
    screen.getByRole("img", { name: "Poster for the show 'Attack on Titan'" })
  ).toBeInTheDocument();

  expect(
    screen.getByRole("img", { name: "Poster for the show 'Juvenile Justice'" })
  ).toBeInTheDocument();

  expect(
    screen.getByRole("img", { name: "Poster for the show 'Gilmore Girls'" })
  ).toBeInTheDocument();

  // link
  expect(
    screen.getByRole("link", { name: "Poster for the show 'Reply 1988'" })
  ).toBeInTheDocument();

  expect(
    screen.getByRole("link", { name: "Poster for the show 'Attack on Titan'" })
  ).toBeInTheDocument();

  expect(
    screen.getByRole("link", { name: "Poster for the show 'Juvenile Justice'" })
  ).toBeInTheDocument();

  expect(
    screen.getByRole("link", { name: "Poster for the show 'Gilmore Girls'" })
  ).toBeInTheDocument();
});

/**
 * Test where we check that the WrappedEpisodes component renders properly with empty data.
 */
test("render WrappedEpisodes empty", () => {
  render(
    <WrappedEpisodes
      totalEps={empty.shows.totalEpWatched}
      totalShows={empty.shows.allShows.length}
      mostWatched={empty.shows.topShows.mostWatched}
      leastWatched={empty.shows.topShows.leastWatched}
    />
  );

  expect(
    screen.getByText(
      /We couldn't sort through your episode viewing behavior 😕/i
    )
  ).toBeInTheDocument();


});