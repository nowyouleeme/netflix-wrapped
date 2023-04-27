
interface MediaCarouselProps {
  media: { title: string; image: string }[];
}

export const carousel_label = "Carousel of poster images"

export function MediaCarousel(props: MediaCarouselProps) {
    return (
      <div role="figure" aria-label={carousel_label} className="carousel">
        {props.media.map((value, index) => (
          <div className="carouselCard" key={index}>
            <a
              rel="noreferrer"
              target="_blank"
              href={"https://www.google.com/search?q=" + value.title}
            >
              <img src={value.image} alt={"Poster for " + value.title} />
            </a>
          </div>
        ))}
      </div>
    );
}