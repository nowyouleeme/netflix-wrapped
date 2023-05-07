package edu.brown.cs.student.main.PosterImageGetting;
import com.squareup.moshi.Json;



public class PosterData {
    public record posterJson(
        @Json(name = "page") Integer page,
        @Json(name = "results") results[] results
   ){}
   public record results(
    @Json(name = "poster_path") String poster_path
){}
}
