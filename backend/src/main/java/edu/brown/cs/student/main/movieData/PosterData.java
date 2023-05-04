package edu.brown.cs.student.main.movieData;
import java.util.Map;
import com.squareup.moshi.Json;
import java.util.ArrayList;



public class PosterData {
    public record posterJson(
        @Json(name = "page") Integer page,
        @Json(name = "results") results[] results
   ){}
   public record results(
    @Json(name = "poster_path") String poster_path
){}
}
