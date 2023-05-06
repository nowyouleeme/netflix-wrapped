
import { render, screen } from "@testing-library/react";
import mockAll from "../../../assets/mocks/mockActor.json";
import { WrappedMinutes } from "../../../components/report/WrappedMinutes";
import empty from "../../../assets/mocks/empty.json";

/**
 * Test where we check that the WrappedMinutes component renders properly.
 */
test("renders WrappedMinutes", () => {
  render(<WrappedMinutes totalMin={mockAll.totalMin} />);

  expect(
    screen.getByText(/All that exploration added up to/i)
  ).toBeInTheDocument();


  expect(screen.getByText(/minutes on Netflix./i)).toBeInTheDocument();

  expect(screen.getByText(/That's about/i)).toBeInTheDocument();
  expect(screen.getByText(/1666.7 hours,/i)).toBeInTheDocument();
  expect(screen.getByText(/69.4 days!/i)).toBeInTheDocument();
});

/**
 * Test where we check that the WrappedMinutes component renders properly with empty data.
 */
test("renders WrappedMinutes empty", () => {
  render(<WrappedMinutes totalMin={empty.totalMin} />);

  expect(
    screen.getByText(/All that exploration added up to/i)
  ).toBeInTheDocument();



  expect(screen.getByText(/minutes on Netflix./i)).toBeInTheDocument();

  expect(screen.getByText(/That's about/i)).toBeInTheDocument();
  expect(screen.getByText(/0.0 hours,/i)).toBeInTheDocument();
  expect(screen.getByText(/0.0 days!/i)).toBeInTheDocument();
});
