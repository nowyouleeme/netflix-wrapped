import { render, screen } from "@testing-library/react";
import mockAll from "../../../assets/mocks/json/mockActor.json";
import Uploader, { generate_wrapped, load_csv, upload_csv } from "../../../components/landing/Uploader";

/**
 * Test where we check that the Uploader component renders properly.
 */
test("render Uploader", () => {
  render(<Uploader name={"Tim"}/>)

  expect(screen.getByRole("button", { name: load_csv })).toBeInTheDocument();
});

/**
 * Test where we check that the Uploader component renders properly with interaction (selecting a file)
 */
test("render Uploader with interaction", () => {
  render(<Uploader name={"Tim"}/>)

  expect(screen.getByRole("button", { name: load_csv })).toBeInTheDocument();
});
