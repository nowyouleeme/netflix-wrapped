interface ReportJSON {
    top5Genres: [
        {
            genre: string;
            count: number;
        }
    ];
    totalMin: number;
    shows: {
        totalEpWatched: number;
        allShows: [
            {
                title: string;
                image: string;
            }
        ];
        topShows: {
            above50_3: [
                {
                    title: string;
                    numEpWatched: number;
                    percentageFinished: number;
                    image: string;
                }
            ];
            below50_3: [
                {
                    title: string;
                    numEpWatched: number;
                    percentageFinished: number;
                    image: string;
                }
            ];
        };
        showActors: {
            mostWatchedActors: [string];
            actorFeaturedShows: [
                {
                    title: string;
                    image: string;
                }
            ]
        }
    };
    movie: {
        allMovies: [
            {
                title: string;
                image: string; //TODO: optional numtimeswatched
            }
        ];
        top5Movies: [
            {
                title: string;
                numTimesWatched: number;
                image: string;
            }
        ];
        movieActors: {
            mostWatchedActors: [string];
            actorFeaturedMovies: [
                {
                    title: string;
                    image: string;
                }
            ]
        }
    };
    bingeData: {
        date: string;
        shows: [
            {
                title: string;
                numEpWatched: number;
                image: string;
            }
        ];
        movies: [
            {
                title: string;
                image: string;
            }
        ]
    };
    personality: {
        title: string;
        description: string;
    };

}