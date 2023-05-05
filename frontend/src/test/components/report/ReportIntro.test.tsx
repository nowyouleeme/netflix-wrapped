import { render, screen } from "@testing-library/react";
import { ReportIntro } from "../../../components/report/ReportIntro";

/**
 * Test where we check that the ReportIntro component renders properly.
 */
test("render ReportIntro", () => {
  render(<ReportIntro />);
  const reportHeading = screen.getByRole("heading", {
    name: /Your Netflix Wrapped is finally here!/i,
  });
  expect(reportHeading).toBeInTheDocument();

  const reportSubtitle = screen.getByText(
    /Take a glimpse at your watch time, top shows \+ movies, favorite genres—all in one place./i
  );
  expect(reportSubtitle).toBeInTheDocument();

  const reportScroll = screen.getByText(/Scroll down to see more/i);
  expect(reportScroll).toBeInTheDocument();
});
