import { Fade } from "react-awesome-reveal";
interface ReportIntroProps {
  year: number;
}
export function ReportIntro(props: ReportIntroProps) {
  return (
    <div className="center WrappedIntroduction">
      <Fade triggerOnce delay={1000} duration={2000}>
        <div className="Here">
          <h2>Your Netflix {props.year} Wrapped is finally here!</h2>
          <div className="HereSub">
            <sub>
              Take a glimpse at your watch time, top shows + movies, favorite
              genres—all in one place.
            </sub>
          </div>
        </div>
      </Fade>
      <Fade className="center" triggerOnce delay={3000} duration={2000}>
        <div className="center ScrollDown">
          <label>Scroll down to see more</label>
          <div className="arrow">
            <svg
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
            </svg>
          </div>
        </div>
      </Fade>
    </div>
  );
}
