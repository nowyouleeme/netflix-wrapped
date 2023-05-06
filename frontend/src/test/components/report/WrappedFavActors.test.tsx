
import { render, screen } from "@testing-library/react";
import mockAll from "../../../assets/mocks/mockActor.json";
import { WrappedFavActors } from "../../../components/report/WrappedFavActors";
import { carousel_label } from "../../../components/report/MediaCarousel";
import empty from "../../../assets/mocks/empty.json";

/**
 * Test where we check that the WrappedFavActors component renders properly.
 */
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

  const header = screen.getByText(
    /You watched 3 different shows featuring/i
  );
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

  expect(
    screen.getByRole("img", { name: "Poster for 'Breaking Bad'" })
  ).toBeInTheDocument();
  expect(
    screen.getByRole("img", { name: "Poster for 'Bob's Burgers'" })
  ).toBeInTheDocument();
  expect(
    screen.getByRole("img", { name: "Poster for 'Gilmore Girls'" })
  ).toBeInTheDocument();

  // links
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
    screen.getByRole("link", { name: "Thomas Jefferson" })
  ).toBeInTheDocument();
  expect(
    screen.getByRole("link", { name: "Amy Hu" })
  ).toBeInTheDocument();
});

/**
 * Test where we check that the WrappedFavActors component renders properly with empty data.
 */
test("render WrappedFavActors empty", () => {
  render(
    <WrappedFavActors
      type="show"
      actors={empty.shows.showActors.mostWatchedActors}
      media={empty.shows.showActors.actorFeaturedShows}
      saying={
        <p>
          You've got <b>amazing</b> taste in entertainers!
        </p>
      }
      color="#EEFFE7"
      bg="#EE9021"
    />
  );

  expect(screen.getByText(/We weren't able to determine the most featured actors in the shows you’ve watched 🥹/i)).toBeInTheDocument();

});