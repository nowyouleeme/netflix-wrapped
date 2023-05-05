

/**
 * Function that returns an Error React Component, which is returned when a routing error occurs or components are not properly rendered.
 * @returns an Error React Component
 */
export default function Error() {

  return (
    <div className="error-page">
      <h1>Oops!</h1>
      <p>Sorry, an unexpected error has occurred :&#40;</p>
    </div>
  );
}
