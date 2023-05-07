package edu.brown.cs.student.main.components.helpers;

import com.squareup.moshi.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * A class that provides the necessary methods to parse JSON files using Moshi.
 *
 * @param <T> The type of the object to parse the JSON file into.
 */
public class JsonReader<T> {
  private final Class<T> type;

  public JsonReader(Class<T> objType) throws IOException {
    this.type = objType;
  }

  /**
   * Parses the json into an object of type T.
   *
   * @return An object of type T representing the contents of the json file.
   * @throws IOException json can't be serialized.
   */
  public T fromJson(String filepath) throws IOException, JsonDataException {
    String json;

    try {
      json = new String(Files.readAllBytes(Paths.get(filepath)));
    } catch (IOException e) {
      throw new IOException("Could not open the GeoJson file.");
    }

    Moshi moshi = new Moshi.Builder().build();
    return moshi.adapter(this.type).fromJson(json);
  }

  /**
   * returns a deserialized object from json string
   *
   * @param json
   * @return type T object for deserializing
   * @throws IOException
   * @throws JsonDataException
   */
  public T fromJsonString(String json) throws IOException, JsonDataException {
    Moshi moshi = new Moshi.Builder().build();
    return moshi.adapter(this.type).fromJson(json);
  }

  /**
   * Serializes a type T object into a json string.
   *
   * @param input The object to serialize.
   * @return json string representing the contents of the input object.
   * @throws IOException object can't be serialized into a json string.
   */
  public String toJson(T input) throws IOException, JsonDataException {
    Moshi moshi = new Moshi.Builder().build();
    return moshi.adapter(this.type).toJson(input);
  }
}
