/**
 * @author John Ryan - john.ryan@drake.edu
 * CS 067 - Fall 2024
 * Dec 9th, 2024
 */
package controller;

import java.io.IOException;
import java.net.ConnectException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import model.ImproperDateRangeException;
import model.InvalidLocationException;
import model.QueryData;
import model.WeatherData;
import model.WeatherDatum;

/**
 * This class is used to construct API calls and interpret the results of said API calls
 */
public class APIHandler {

	/**
	 * https://www.baeldung.com/guide-to-okhttp final url should look like
	 * https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/[location]/[date1]/[date2]?key=YOUR_API_KEY
	 * dates should be in format of yyyy-mm-dd
	 */

	// base URL of visual crossing weather API
	private final String BASE_URL = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/";
	// API key for weather crossing weather API (had to pay for professional cause I
	// reached call limit on free)
	private final String API_KEY = "9U738TQ6E5H3M3G6U8R97L3JQ";
	private String buildRequest; // builds on base url
	private String requestResult = ""; // converts json response to string for processing
	private final Gson gson = new Gson(); // Google's gson import help interpret api calls efficiently
	private final OkHttpClient client = new OkHttpClient(); // OkHttp helps a lot with construction of API calls

	/**
	 * This class utilizes a user query stored in a QueryData object to construct an
	 * API call to the Visual Crossing weather API
	 * 
	 * @param data - Query used to construct API call
	 * @return - String representing info from API call
	 * @throws ConnectException               - If Visual Crossing API servers can't
	 *                                        be connected to
	 * @throws ImproperDateSelectionException - If improper dates are contained in
	 *                                        QueryData
	 * @throws InvalidLocationException       - If location string in QueryData
	 *                                        cannot be resolved to real-world
	 *                                        location
	 */
	public WeatherData fetchWeatherData(QueryData qData)
			throws ConnectException, ImproperDateRangeException, InvalidLocationException {
		// Gathering data for api call
		String location = qData.getLocation();
		String startDate = qData.getStartDate().toString();
		String endDate = qData.getEndDate().toString();

		// If dates won't make valid query, throw exception and give user error message
		// in calling function
		if (qData.getStartDate().isAfter(qData.getEndDate())) {
			throw new ImproperDateRangeException();
		}

		// Constructing API call
		buildRequest = BASE_URL + location + "/" + startDate + "/" + endDate + "?key=" + API_KEY;

		// Get only fields that program is interested in from API call
		buildRequest += "&include=days&elements=temp,feelslike,precip,precipprob,dew,windspeed,pressure,datetime";
		/**
		 * Current elements Temperature Feels like temperature Total precipitation
		 * Precipitation chance Dewpoint temperature Wind speed Pressure Date
		 */

		Request request = new Request.Builder().url(buildRequest).build();
		// Sending API call
		Call call = client.newCall(request);
		try {
			// Execute API call
			Response response = call.execute();

			if (!response.isSuccessful()) {
				String errorBody = response.body() != null ? response.body().string() : "";

				// Check if it's an invalid location error
				if (errorBody.contains("Invalid location parameter value")) {
					throw new InvalidLocationException();
				}

				// If not an invalid location, treat as a general connection error
				throw new ConnectException(
						"Response unsuccessful. Status: " + response.code() + " Message: " + response.message());
			}

			// Turn API result into a String
			requestResult = response.body().string();
			if (requestResult == null || requestResult.isEmpty()) {
				throw new ConnectException("Empty or null response from server.");
			}

			// Parse response into WeatherData
			return parseWeatherData(requestResult);

		} catch (IOException e) {
			// Handle IOExceptions, likely connection issues
			throw new ConnectException("Failed to connect to the API: " + e.getMessage());
		}

	}

	/**
	 * This method takes in a JSON response string and parses it into a WeatherData
	 * object.
	 * 
	 * @param response - String representation of JSON response
	 * @return - WeatherData object containing data from response
	 */
	public WeatherData parseWeatherData(String response) {

		JsonObject json = gson.fromJson(response.toString(), JsonObject.class);
		WeatherData data = new WeatherData(); // WeatherData stored here
		JsonArray daysArray = json.getAsJsonArray("days"); // Splits json response into array by header "days"
		// allows reading of dates from response
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		double actualTemp, feelsLikeTemp, precipAmount, precipChance, dewpoint, windSpeed, pressure;

		// This loop puts data into WeatherData data
		for (int i = 0; i < daysArray.size(); i++) {
			// Sets current 'index' of json response for reading
			JsonObject dayObject = daysArray.get(i).getAsJsonObject();

			// Saving variables
			actualTemp = dayObject.get("temp").getAsDouble();
			feelsLikeTemp = dayObject.get("feelslike").getAsDouble();
			precipAmount = dayObject.get("precip").getAsDouble();
			precipChance = dayObject.has("precipprob") ? dayObject.get("precipprob").getAsDouble() : 0;
			dewpoint = dayObject.get("dew").getAsDouble();
			windSpeed = dayObject.get("windspeed").getAsDouble();
			pressure = dayObject.get("pressure").getAsDouble();

			String dateString = dayObject.get("datetime").getAsString();
			LocalDate date = LocalDate.parse(dateString, formatter);

			// Creating weatherDatum object to add to weatherData
			WeatherDatum weatherDatum = new WeatherDatum(actualTemp, feelsLikeTemp, precipAmount, precipChance,
					dewpoint, windSpeed, pressure, date);

			data.addWeatherDatum(weatherDatum);

		}

		return data;

	}

}
