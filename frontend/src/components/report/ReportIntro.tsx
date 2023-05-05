/**
 * Function that returns a ReportIntro component,
 * which gives context to what the report contains.
 * @returns a ReportIntro component
 */
export function ReportIntro() {
  return (
    <div className="center WrappedIntroduction">
      <div className="Here">
        <h1>Your Netflix Wrapped is finally here!</h1>
        <div className="HereSub">
          <sub>
            Take a glimpse at your watch time, top shows + movies, and favorite
            genres—all in one place.
          </sub>
        </div>
      </div>
      <div className="center ScrollDown">
        <div className="arrow">
          <label>Scroll down to see more ⬇</label>
          {/* <svg
              width="30"
              height="18"
              viewBox="0 0 30 18"
              fill="none"
              xmlns="http://www.w3.org/2000/svg"
            >
              <path
                d="M3.42048 0L14.5553 11.1348L25.69 0L29.1105 3.44474L14.5553 18L0 3.44474L3.42048 0Z"
                fill="#F5EC72"
              />
            </svg> */}
        </div>
      </div>
    </div>
  );
}
