import { useEffect, useState } from "react";
import { key } from "../private/key";
import { MediaCarousel } from "./MediaCarousel";

interface WrappedOverviewProps {
  shows: Array<string>;
  movies: Array<string>;
}

export function WrappedOverview(props: WrappedOverviewProps) {
  
  return (
    <div className="WrappedOverview">
      <div className="OverviewDesc">
        <h3>Out of that time you spent with us in 2022,</h3>
        <p>
          you watched <b>{props.shows.length} shows</b>...
        </p> 
      </div>
 
      {/* map list of shows to make a card component */}
      <div className="carouselContainer">
        <MediaCarousel media={props.shows} />
      </div>  

      <div className="OverviewDesc">
        <p>
          and <b>{props.movies.length} movies</b>!
        </p>
      </div>

      <div className="carouselContainer">
        <MediaCarousel media={props.movies} /> 
      </div>
    </div>
  );
};
