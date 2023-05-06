/**
 * An interface containing all of the properties expected of the props that are passed into the MediaCarousel component.
 */
interface MediaCarouselProps {
  media: { title: string; image: string }[];
}

export const carousel_label = "Carousel of poster images";

/**
 * Function that returns a MediaCarousel component,
 * which displays posters and links to Google Searches of the poster's title in a scrollable carousel.
 * @param props all of the information needed to display the carousel contents
 * @returns a MediaCarousel component
 */
export function MediaCarousel(props: MediaCarouselProps) {
  return (
    <div role="figure" aria-label={carousel_label} className="carousel">
      {props.media.map((value, index) => (
        <div className="carouselCard" key={index}>
          <a
            rel="noreferrer"
            target="_blank"
            href={`https://www.google.com/search?q=${value.title}`}
          >
            <img src={value.image} alt={`Poster for '${value.title}'`} />
          </a>
        </div>
      ))}
    </div>
  );
}
