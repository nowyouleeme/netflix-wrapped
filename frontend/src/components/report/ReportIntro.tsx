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
        </div>
      </div>
    </div>
  );
}
