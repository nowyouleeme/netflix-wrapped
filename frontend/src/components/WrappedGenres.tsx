import { PolarArea } from "react-chartjs-2";
import React from "react";
import {
  Chart as ChartJS,
  RadialLinearScale,
  ArcElement,
  Tooltip,
  Legend,
} from "chart.js";

ChartJS.register(RadialLinearScale, ArcElement, Tooltip, Legend);

interface WrappedGenresProps {
  // just need array of data
  genres: Array<{ genre: string; count: number }>
}
// "topGenres": [
//     { "genre": "Action", "count": 12 },
//     { "genre": "Romance", "count": 10 },
//     { "genre": "Comedy", "count": 9 },
//     { "genre": "Thriller", "count": 3 },
//     { "genre": "Slice of Life", "count": 1 }
//   ],

export function WrappedGenres(props: WrappedGenresProps) {

  function buildGenreLabels(genres: Array<{ genre: string; count: number }>) {
    let genreLabel: string[] = [];
    for (let i = 0; i < genres.length; i++) {
      genreLabel.push(genres[i].genre);
    }
    return genreLabel;
  }

  function buildGenreCount(genres: Array<{ genre: string; count: number }>) {
    let genreCount: number[] = [];
    for (let i = 0; i < genres.length; i++) {
      genreCount.push(genres[i].count);
    }
    return genreCount;
  }

  let data = {
    labels: buildGenreLabels(props.genres),
    datasets: [
      {
        label: "# of shows and movies watched",
        data: buildGenreCount(props.genres),
        scaleFontColor: "#FFFFFF",
        pointLabelFontColor: "#FFFFFF",
        backgroundColor: [
          "#1CADFF",
          "#7E18A1",
          "#C72B68",
          "#95C12B",
          "#EED921",
        ],
        borderWidth: 0,
      },
    ],
  };

  let options = {
    maintainAspectRatio: false,
    plugins: {
      legend: {
        labels: {
          // This more specific font property overrides the global property
          font: {
            size: 14,
            family: "Metropolis-Medium",
          },
          color: "#4D6344",
        },
      },
    },
  };

  // ensure genre has capitalized genre name
  return (
    <div className="center WrappedGenres">
      <div className="genres">
        <div className="GenreDesc">
          <div className="DescMain">
            <p>This year, you explored</p>
            <h1>
              {props.genres.length}
              <br />
              different
              <br />
              genres,
            </h1>
            <p>
              your favorite being <b>{props.genres[0].genre}.</b>
            </p>
          </div>

          <sub>Look at you go, venturing into the unknown!</sub>
        </div>
        <div className="GenreVis">
          <PolarArea data={data} width="1vw" height="25vh" options={options} />
        </div>
      </div>
    </div>
  );
}
