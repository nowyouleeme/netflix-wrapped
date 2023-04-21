interface WrappedGenresProps {
    numGenres: number;
    favGenre: string;
}

export function WrappedGenres(props: WrappedGenresProps) {
    // ensure genre has capitalized genre name
    return (
      <div className="center WrappedGenres">
        <div className="genres">
          <div className="GenreDesc">
            <div className="DescMain">
              <p>This year, you explored</p>
              <h1>
                {props.numGenres}
                <br />
                different
                <br />
                genres,
              </h1>
              <p>
                your favorite being <b>{props.favGenre}.</b>
              </p>
            </div>

            <sub>Look at you go, venturing into the unknown!</sub>
          </div>
          <div className="GenreVis"></div>
        </div>
      </div>
    );
}