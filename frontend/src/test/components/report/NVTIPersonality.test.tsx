
import { render, screen } from "@testing-library/react";
import mockAll from "../../../assets/mocks/mockActor.json";
import { NVTIPersonality } from "../../../components/report/NVTIPersonality";

test("renders learn react link", () => {
  render(<NVTIPersonality personality={mockAll.personality} />);

  expect(
    screen.getByRole("heading", {
      name: "Meet your NVTI personality:",
    })
  ).toBeInTheDocument();

  expect(
    screen.getByRole("heading", {
      name: /The Explorer/i,
    })
  ).toBeInTheDocument();

  expect(
    screen.getByText(/You’re an adventurous one. Not limiting yourself to either movies or shows, you delve into wide and varied range of genres to explore the unknown./i)
  ).toBeInTheDocument();

  // render graphic!! 
});
