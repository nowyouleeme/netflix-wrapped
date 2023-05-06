import { render, screen } from "@testing-library/react";
import { Wipe, wipe_data } from "../../../components/report/Wipe";

/**
 * Test where we check that the Wipe component renders properly.
 */
test("render Wipe", () => {
  render(<Wipe setShowReport={jest.fn()} navigate={jest.fn()} />);
  expect(
    screen.getByText(
      /Finished with your report and want to wipe your data from our servers\?/i
    )
  ).toBeInTheDocument();
  expect(
    screen.getByText(
      /Clicking this button will also re-route you to the landing page./i
    )
  ).toBeInTheDocument();
  expect(
    screen.getByText(/Made with ❤️ by Kathryn, Karen, CJ, and Brian/i)
  ).toBeInTheDocument();

  expect(
    screen.getByRole("button", {name: wipe_data})
  ).toBeInTheDocument();
});
