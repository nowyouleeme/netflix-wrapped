import { render, screen } from "@testing-library/react";
import mockAll from "../../../assets/mocks/mockActor.json";
import Uploader from "../../../components/landing/Uploader";

test("render Uploader", () => {
  render(<Uploader/>)

  expect(screen.getAllByRole("button").length).toBe(2);

  expect(screen.getByRole("button", {name: /Upload NetflixViewingHistory.CSV File/i})).toBeInTheDocument();
  expect(
    screen.getByRole("button", {
      name: /Get your wrapped/i,
    })
  ).toBeInTheDocument();
});
