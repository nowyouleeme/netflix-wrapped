
interface MediaCarouselProps {
  media: { title: string; image: string }[];
}

export function MediaCarousel(props: MediaCarouselProps) {
    return (
      <div className="carousel">
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