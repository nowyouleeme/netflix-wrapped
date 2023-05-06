
import { render, screen } from "@testing-library/react";
import mockAll from "../../../assets/mocks/mockActor.json";
import { NVTIPersonality } from "../../../components/report/NVTIPersonality";
import empty from "../../../assets/mocks/empty.json";

/**
 * Test where we check that the NVTIPersonality component renders properly.
 */
test("render NVTIPersonality", () => {
  render(<NVTIPersonality personality={mockAll.personality} />);

  expect(
    screen.getByText(/Meet your NVTI personality/i)
  ).toBeInTheDocument();

  expect(
    screen.getByText(/The Otaku/i)).toBeInTheDocument();

  expect(
    screen.getByText(
      /You have an unmatched love for anime and other Japanese pop culture! Whether it be because of the compelling characters, creative visuals, interesting world building, or exposure to Japanese history and language, you show clear appreciation for quality media./i
    )
  ).toBeInTheDocument();

  // render graphic!! 
});

/**
 * Test where we check that the NVTIPersonality component renders properly with empty data.
 */
test("render NVTIPersonality empty", () => {
  render(<NVTIPersonality personality={empty.personality} />);

  expect(
    screen.getByText(/We couldn't generate a viewing personality for you 😭/i)
  ).toBeInTheDocument();
});
