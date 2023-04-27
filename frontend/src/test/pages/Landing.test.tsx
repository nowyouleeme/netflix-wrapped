import { act, render, screen } from "@testing-library/react";
import mockAll from "../../assets/mocks/mockActor.json";
import { WrappedOverview } from "../../components/report/WrappedOverview";
import Landing, { next_button_field } from "../../pages/Landing";
import userEvent from "@testing-library/user-event";
import { upload_csv } from "../../components/landing/Uploader";

test("render Landing", async() => {
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

  expect(screen.getByRole("button", {
    name: next_button_field,
  })).toBeInTheDocument();

  expect(
    screen.getByRole("textbox", {
      name: "What's your name?",
    })
  ).toBeInTheDocument();

  act(() => {
    userEvent.click(
      screen.getByRole("button", {
        name: next_button_field,
      })
    );
  });

  // check next, check back, check render of instructions (interactions)
  // render report
});
