
import { render, screen } from "@testing-library/react";
import mockAll from "../../../assets/mocks/mockActor.json";
import { WrappedEpisodes } from "../../../components/report/WrappedEpisodes";

test("render WrappedEpisodes", () => {
  render(
    <WrappedEpisodes
      totalEps={mockAll.shows.totalEpWatched}
      totalShows={mockAll.shows.allShows.length}
      above50={mockAll.shows.topShows.above50_3}
      below50={mockAll.shows.topShows.below50_3}
    />
  );

  const header = screen.getByRole("heading", {
    name: /You watched a total of 200 episodes of 7 different shows,/i,
  });
  expect(header).toBeInTheDocument();

  expect(
    screen.getByText(/with some you just couldn't get enough of:/i)
  ).toBeInTheDocument();

  expect(
    screen.getByText(/And others that you started but/i)
  ).toBeInTheDocument();
  expect(screen.getByText(/never finished:/i)).toBeInTheDocument();

  // render cards!!!
});
