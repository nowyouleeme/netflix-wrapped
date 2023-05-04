import { carousel_label } from "./MediaCarousel";

interface WrappedFavActorsProps {
    type: string;
  actors: string[];
  media: { title: string; image: string }[];
  saying: JSX.Element;
  bg: string;
  color: string;
}

export function WrappedFavActors(props: WrappedFavActorsProps) {
  function getActors(actors: string[]) {
    let actorsMentioned: JSX.Element[] = [];
    for (let i = 0; i < actors.length; i++) {
      let stringElement: JSX.Element;
      stringElement = (
        <>
          <a
            style={{ color: props.color}}
            rel="noreferrer"
            target="_blank"
            href={"https://www.google.com/search?q=" + actors[i]}
          >
            {actors[i]}
          </a>
        </>
      );
      actorsMentioned.push(stringElement);
    }

    if (actorsMentioned.length === 2) {
      actorsMentioned[0] = <>{actorsMentioned[0]} and&nbsp;</>;
      return actorsMentioned;
    } else if (actorsMentioned.length === 1) {
      return actorsMentioned;
    } else {
      for (let i = 0; i < actorsMentioned.length; i++) {
        if (i === actorsMentioned.length - 1) {
          actorsMentioned[i] = <>and&nbsp;{actorsMentioned[i]}</>;
        } else {
          actorsMentioned[i] = <>{actorsMentioned[i]},&nbsp;</>;
        }
      }
      return actorsMentioned;
    }
  }

  function numberMedia(media: { title: string; image: string }[]) {
    if (media.length === 1){
        return <>great {props.type}</>
    } else {
        return <>different {props.type}s</>
    }
  }
  return (
    <div
       
      className="center WrappedActors"
      style={{ color: props.color, backgroundColor: props.bg }}
    >
      <p className="actorsBigP">
        You watched {props.media.length} {numberMedia(props.media)} featuring {getActors(props.actors)}, such as:
      </p>
      <div className="actorContainer">
        <div role="figure" aria-label={carousel_label} className="carousel">
          {props.media.map((value, index) => (
            <div
              className="actorCarouselCard"
              key={"Poster for " + value.title + index + props.media}
            >
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
      </div>
      {props.saying}
    </div>
  );
}
