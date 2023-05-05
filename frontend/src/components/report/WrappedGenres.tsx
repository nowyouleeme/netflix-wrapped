import { PolarArea } from "react-chartjs-2";
import React from "react";
import {
  Chart as ChartJS,
  RadialLinearScale,
  ArcElement,
  Tooltip,
  Legend,
} from "chart.js";

/**
 * Instantiating the polar chart with the ChartJS package. 
 */
ChartJS.register(RadialLinearScale, ArcElement, Tooltip, Legend);

/**
 * An interface containing all of the properties expected of the props that are passed into the WrappedGenres component.
 */
interface WrappedGenresProps {
  // just need array of data
  genres: Array<{ genre: string; count: number }>
}

/**
 * Function that returns a WrappedGenres component, 
 * which displays information on the top 5 genres the user has engaged with the most based on their NetflixViewingHistory.csv.
 * @param props all of the information needed to display the user's most frequently engaged genres
 * @returns a WrappedGenres component
 */
export function WrappedGenres(props: WrappedGenresProps) {
  /**
   * Function that creates an array of strings representing genre names.
   * @param genres an array containing genre names and their counts
   * @returns an array of string representing genre names
   */
  function buildGenreLabels(genres: Array<{ genre: string; count: number }>) {
    let genreLabel: string[] = [];
    for (let i = 0; i < genres.length; i++) {
      genreLabel.push(genres[i].genre);
    }
    return genreLabel;
  }

  /**
   * Function that creates an array of numbers representing genre counts.
   * @param genres an array containing genre names and their counts
   * @returns an array of numbers representing genre counts
   */
  function buildGenreCount(genres: Array<{ genre: string; count: number }>) {
    let genreCount: number[] = [];
    for (let i = 0; i < genres.length; i++) {
      genreCount.push(genres[i].count);
    }
    return genreCount;
  }

  /**
   * The data necessary to display the polar chart.
   */
  let data = {
    labels: buildGenreLabels(props.genres),
    datasets: [
      {
        label: "# of shows and movies watched",
        data: buildGenreCount(props.genres),
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

  /**
   * Styling for the polar chart.
   */
  let options = {
    scales: {
      r: {
        ticks: {
          display: false,
        },
      },
    },
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
            <p className="genresNormalP">To start off, here are your</p>

            <p className="genresStyledP">
              {props.genres.length}
              <br aria-hidden="true" />
              top
              <br aria-hidden="true" />
              {props.genres.length > 1 ? "genres." : "genre."}
            </p>
            <p className="genresNormalP">
              Your favorite seemed to be <b>{props.genres[0].genre}!</b>
            </p>
          </div>

          <sub>Look at you go, venturing into the unknown 🚀</sub>
        </div>
        <div className="GenreVis">
          <PolarArea data={data} width="1vw" height="25vh" options={options} />
        </div>
      </div>
    </div>
  );
}
