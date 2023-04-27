
import { render, screen } from "@testing-library/react";
import mockAll from "../../../assets/mocks/mockActor.json";
import { WrappedFavActors } from "../../../components/report/WrappedFavActors";
import { carousel_label } from "../../../components/report/MediaCarousel";

test("render WrappedFavActors", () => {
  render(
    <WrappedFavActors
      type="show"
      actors={mockAll.shows.showActors.mostWatchedActors}
      media={mockAll.shows.showActors.actorFeaturedShows}
      saying={
        <p>
          You've got <b>amazing</b> taste in entertainers!
        </p>
      }
      color="#EEFFE7"
      bg="#EE9021"
    />
  );

  const header = screen.getByRole("heading", {
    name: "You watched 3 different shows featuring Amy Hu and Thomas Jefferson , such as:",
  });
  expect(header).toBeInTheDocument();

  expect(screen.getByRole("link", {name: "Amy Hu"})).toBeInTheDocument();
  expect(screen.getByRole("link", { name: "Thomas Jefferson" })).toBeInTheDocument();

  expect(screen.getByText(/You've got/i)).toBeInTheDocument();
  expect(screen.getByText(/amazing/i)).toBeInTheDocument();
  expect(screen.getByText(/taste in entertainers!/i)).toBeInTheDocument();

  expect(
    screen.getByRole("figure", {
      name: carousel_label,
    })
  ).toBeInTheDocument();

  // check individual cards
});
