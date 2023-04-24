import { useEffect, useState } from "react";
import { key } from "../private/key";

interface MediaCarouselProps {
    media: Array<string>;
}

export function MediaCarousel(props: MediaCarouselProps) {
    return (
      <div className="carousel">  
        {props.media.map((value, index) => (
          <div className="carouselCard" key={index}> 
            <img src={value} />
          </div>
        ))}
      </div>
    );
}