
import { render, screen } from "@testing-library/react";
import mockAll from "../../../assets/mocks/mockActor.json";
import { BingeData, binge_grid } from "../../../components/report/BingeData";
import empty from "../../../assets/mocks/empty.json";

/**
 * Test where we check that the BingeData component renders properly.
 */
test("renders BingeData", () => {
  render(<BingeData bingeData={mockAll.bingeData} />);

  expect(screen.getByText(/April 20th, 2022/i)).toBeInTheDocument();
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

/**
 * Test where we check that the BingeData component renders properly with empty data.
 */
test("renders BingeData empty", () => {
  render(<BingeData bingeData={empty.bingeData} />);
  expect(
    screen.getByText(
      /We weren't able to find information on when and what you binged the most on Netflix 😪/i
    )
  ).toBeInTheDocument();

});
