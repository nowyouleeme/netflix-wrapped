import { MediaCarousel } from "./MediaCarousel";

interface WrappedOverviewProps {
  shows: { title: string; image: string }[];
  movies: { title: string; image: string }[];
}

export function WrappedOverview(props: WrappedOverviewProps) {
  return (
    <div className="WrappedOverview">
      <div className="OverviewDesc">
        <p className="overviewBigP">
          Out of that time you spent with us in 2022,
        </p>
        <p className="overviewDescP">
          you watched <b>{props.shows.length} shows</b>...
        </p>
      </div>

      {/* map list of shows to make a card component */}
      <div className="carouselContainer">
        <MediaCarousel media={props.shows} />
      </div>

      <div className="OverviewDesc">
        <p className="overviewDescP">
          and <b>{props.movies.length} movies</b>!
        </p>
      </div>

      <div className="carouselContainer">
        <MediaCarousel media={props.movies} />
      </div>
    </div>
  );
};
