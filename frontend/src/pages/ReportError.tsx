/**
 * Function that returns an ReportError React Component, which is returned when
 * a user tries to access their report before uploading a CSV/generating the report.
 * @returns an ReportError React Component
 */
export default function ReportError() {
  return (
    <div className="error-page">
      <h1>Oops!</h1>
      <p>Sorry, a report hasn't been generated yet :&#40;</p>
    </div>
  );
}
