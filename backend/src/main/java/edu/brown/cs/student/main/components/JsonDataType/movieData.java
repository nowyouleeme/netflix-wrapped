package edu.brown.cs.student.main.components.JsonDataType;

import java.util.Map;


import com.squareup.moshi.Json;



/**
class to read the netflix titles json without typecasting
*/
public class movieData {
   public record movieJson(
       @Json(name = "show_id") Map<String, String> show_id,
       @Json(name = "type") Map<String, String> type,
       @Json(name = "title") Map<String, String> title,
       @Json(name = "director") Map<String, String> director,
       @Json(name = "cast") Map<String, String> cast,
       @Json(name = "country") Map<String, String> country,
       @Json(name = "date_added") Map<String, String> date_added,
       @Json(name = "release_year") Map<String, Integer> release_year,
       @Json(name = "rating") Map<String, String> rating,
       @Json(name = "duration") Map<String, String> duration,
       @Json(name = "listed_in") Map<String, String> listed_in,
       @Json(name = "description") Map<String, String> description
   ){}
}
