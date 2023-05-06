import { carousel_label } from "./MediaCarousel";

/**
 * An interface containing all of the properties expected of the props that are passed into the WrappedFavActors component.
 */
interface WrappedFavActorsProps {
    type: string;
  actors: string[];
  media: { title: string; image: string }[];
  saying: JSX.Element;
  bg: string;
  color: string;
}

interface Media {
  title: string;
  image: string; 
}

/**
 * Function that returns a WrappedFavActors component, 
 * which displays information on the most frequently featured actors based on their NetflixViewingHistory.csv shows and movies.
 * @param props all of the information needed to display the user's most frequently featured actors
 * @returns a WrappedFavActors component
 */
export function WrappedFavActors(props: WrappedFavActorsProps) {
  /**
   * Function that creates an array of actor names and links to Google Searches of those actors.
   * @param actors an array of strings of actor names
   * @returns an array of JSX.Elements representing actor names and links to Google Searches of those actors
   */
  function getActors(actors: string[]) {
    let actorsMentioned: JSX.Element[] = [];
    for (let i = 0; i < actors.length; i++) {
      let stringElement: JSX.Element;
      stringElement = (
        <>
          <a
            style={{ color: props.color }}
            rel="noreferrer"
            target="_blank"
            href={`https://www.google.com/search?q=${actors[i]}`}
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

  /**
   * Function that filters out poster images and titles repeated multiple times.
   * @returns an Array of Media objects.
   */
  function filterMedia() {
    let media = new Map<string, string>();

     for (let i = 0; i < props.media.length; i++) {
      if (!media.has(props.media[i].title)) {
        media.set(props.media[i].title, props.media[i].image);
      }
     }

     let mediaArray: Media[] = [];
     media.forEach((value: string, key: string) => {
      let temp: Media = {title: key, image: value}
      mediaArray.push(temp);
     });
     return mediaArray
  }

  /**
   * Function that counts how many media types featured the most actors the user watched.
   * @param media an array of titles mapped to poster images of the shows/movies featured the actors most watched
   * @returns a JSX.Element showing how many media types were watched
   */
  function numberMedia(media: { title: string; image: string }[]) {
    if (media.length === 1) {
      return <>great {props.type}</>;
    } else {
      return <>different {props.type}s</>;
    }
  }
  return (
    <div
      className="center WrappedActors"
      style={{ color: props.color, backgroundColor: props.bg }}
    >
      {props.actors.length > 0 ? (
        <>
          <p className="actorsBigP">
            You watched {props.media.length} {numberMedia(props.media)}{" "}
            featuring {getActors(props.actors)}, such as:
          </p>
          <div className="actorContainer">
            <div role="figure" aria-label={carousel_label} className="carousel">
              
              {filterMedia().map((value, index) => (
                <div
                  className="actorCarouselCard"
                  key={`Poster for ${value.title}${index}${props.media}`}
                >
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
          </div>
          {props.saying}
        </>
      ) : (
        <div className="emptyJSON">
          <p>
            We weren't able to determine the most featured actors in the{" "}
            {props.type}s you’ve watched 🥹
          </p>
        </div>
      )}
    </div>
  );
}
