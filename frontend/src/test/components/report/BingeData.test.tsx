
import { render, screen } from "@testing-library/react";
import mockAll from "../../../assets/mocks/mockActor.json";
import { BingeData, binge_grid } from "../../../components/report/BingeData";

test("renders BingeData", () => {
  render(<BingeData bingeData={mockAll.bingeData} />);

  const heading = screen.getByRole("heading", {
    name: "April 20th, 2022",
  });

  expect(heading).toBeInTheDocument();

  expect(screen.getByText(/was quite the special day./i)).toBeInTheDocument();

  expect(
    screen.getByText(/On this day, you managed to watch/i)
  ).toBeInTheDocument();

  expect(
    screen.getByRole("link", { name: "your most bingeful show day" })
  ).toBeInTheDocument();

  expect(
    screen.getByText(/Pretty productive, if we say so ourselves!/i)
  ).toBeInTheDocument();

  expect(
    screen.getByRole("figure", {
      name: binge_grid,
    })
  ).toBeInTheDocument();
  // check individual cards
});
