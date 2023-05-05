import { render, screen } from "@testing-library/react";
import mockAll from "../../../assets/mocks/mockActor.json";
import Uploader, { generate_wrapped, load_csv, upload_csv } from "../../../components/landing/Uploader";

/**
 * Test where we check that the Uploader component renders properly.
 */
test("render Uploader", () => {
  render(<Uploader name={"Tim"}/>)

  expect(screen.getAllByRole("button").length).toBe(2);

  expect(screen.getByRole("button", { name: upload_csv })).toBeInTheDocument();

  expect(
    screen.getByRole("button", {
      name: load_csv,
    })
  ).toBeInTheDocument();

  expect(
    screen.getByRole("button", {
      name: upload_csv,
    })
  ).toBeInTheDocument();
});
