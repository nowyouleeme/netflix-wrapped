import { act, render, screen } from "@testing-library/react";
import Landing, { next_button_field } from "../../pages/Landing";
import userEvent from "@testing-library/user-event";
import { upload_csv } from "../../components/landing/Uploader";

/**
 * Test where we check that the Landing component renders properly.
 */
test("render Landing", () => {
  render(
    <Landing/>
  );

  const header = screen.getByRole("heading", {
    name: /NETFLIX WRAPPED/i,
  });
  expect(header).toBeInTheDocument();

  expect(
    screen.getByText(/A lookback on your time with Netflix/i)
  ).toBeInTheDocument();

  expect(
    screen.getByText(/Made with ❤️ by Kathryn, Karen, CJ, and Brian/i)
  ).toBeInTheDocument();

  expect(screen.getByRole("button", {
    name: next_button_field,
  })).toBeInTheDocument();

  expect(
    screen.getByRole("textbox", {
      name: "What's your name?",
    })
  ).toBeInTheDocument();
});


/**
 * Test where we check that the Landing component renders properly with interaction.
 */
test("render Landing with interaction", () => {
  render(
    <Landing/>
  );

  userEvent.type(screen.getByRole("textbox"), "Hello,{enter}World!");
  screen.getByRole("button", {name: next_button_field})
});
