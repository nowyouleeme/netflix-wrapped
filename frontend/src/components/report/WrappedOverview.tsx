import { MediaCarousel } from "./MediaCarousel";

/**
 * An interface containing all of the properties expected of the props that are passed into the WrappedOverview component.
 */
interface WrappedOverviewProps {
  shows: { title: string; image: string }[];
  movies: { title: string; image: string }[];
}

/**
 * Function that returns a WrappedOverview component, 
 * which displays information on all of the shows and movies a user has watched in their NetflixViewingHistory.csv.
 * @param props all of the information needed to display the shows and movies a user has watched
 * @returns a WrappedOverview component
 */
export function WrappedOverview(props: WrappedOverviewProps) {
  return (
    <div className="WrappedOverview">
      <div className="OverviewDesc">
        <p className="overviewBigP">
          Out of that time you spent with us in 2022,
        </p>
        <p className="overviewDescP">
          you watched{" "}
          <b>
            {props.shows.length} {props.shows.length > 1 ? "shows" : "show"}
          </b>
          ...
        </p>
      </div>

      {/* map list of shows to make a card component */}
      <div className="carouselContainer">
        <MediaCarousel media={props.shows} />
      </div>

      <div className="OverviewDesc">
        <p className="overviewDescP">
          and{" "}
          <b>
            {props.movies.length} {props.movies.length > 1 ? "movies" : "movie"}
          </b>
          !
        </p>
      </div>

      <div className="carouselContainer">
        <MediaCarousel media={props.movies} />
      </div>
    </div>
  );
};
