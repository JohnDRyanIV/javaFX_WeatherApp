/**
 * @author John Ryan - john.ryan@drake.edu
 * CS 067 - Fall 2024
 * Dec 5, 2024
 */
package controller;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * LocalDate <---> JSON support
 * Learned how to implement in this video: https://www.youtube.com/watch?v=j2vWhohxBVA
 * Default implementation of LocalDate objects in Java 8+ doesn't allow for easy serialization, so
 * custom serialization and deserialization methods are needed for json.
 * I learned about serialization when I was making a python project a couple summers ago
 */
public class LocalDateAdapter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {

	@Override
	public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException 
	{
		return LocalDate.parse(json.getAsString(), DateTimeFormatter.ISO_LOCAL_DATE);
	}

	@Override
	public JsonElement serialize(LocalDate localDate, Type typeOfSrc, JsonSerializationContext context) 
	{
		return new JsonPrimitive (
			localDate.format(DateTimeFormatter.ISO_LOCAL_DATE)
		);
	}
	
	
	

}