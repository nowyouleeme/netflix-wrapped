import { act, render, screen, waitFor } from "@testing-library/react";
import Landing, { back_button_field, netflixAccount, netflixSettings, next_button_field } from "../../pages/Landing";
import userEvent from "@testing-library/user-event";
import { load_csv, upload_csv } from "../../components/landing/Uploader";

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
test("render Landing with interaction", async () => {
  render(
    <Landing/>
  );

  userEvent.type(screen.getAllByRole("textbox")[0], "Kathryn!");
  userEvent.click(screen.getByRole("button", { name: next_button_field }));
  
  expect(
    await screen.findByRole("button", { name: load_csv })
  ).toBeInTheDocument();
  expect(
    await screen.findByRole("button", { name: back_button_field })
  ).toBeInTheDocument();
  expect(
    await screen.findByText(/How do I get my NetflixViewingHistory.csv/i)
  ).toBeInTheDocument();

  expect(await screen.findByText(/How to get your CSV:/i)).toBeInTheDocument();
  expect(
    await screen.findByText(/1. Find your Netflix Viewing History/i)
  ).toBeInTheDocument();
  expect(
    await screen.findByText(/2. Download your Netflix Viewing History/i)
  ).toBeInTheDocument();

  expect(
    await screen.findByRole("img", { name: netflixAccount })
  ).toBeInTheDocument();
  expect(
    await screen.findByRole("img", { name: netflixSettings })
  ).toBeInTheDocument();
  

  act(() => {
    const file = new File(
      ["example"],
      "binge_1.csv",
      {
        type: "csv",
      }
    );

    const input = screen.getByLabelText(
      /Select Your NetflixViewingHistory.CSV File/i
    );
    userEvent.upload(input, file);
    
    
  });

  const fileSelected = await screen.findByText(/File selected: 'binge_1.csv'/i);
  const upload = await screen.findByRole("button", { name: upload_csv });
  expect(fileSelected).toBeInTheDocument();
  expect(upload).toBeInTheDocument();

  userEvent.click(screen.getByRole("button", {name: upload_csv}))
  expect(
    await screen.findByText(
      /'binge_1.csv' upload failed, please try again later./i
    )
  ).toBeInTheDocument();
});
