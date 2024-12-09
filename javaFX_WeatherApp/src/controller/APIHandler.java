package controller;

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

import model.ImproperDateSelectionException;
import model.InvalidLocationException;
import model.QueryData;
import model.WeatherData;
import model.WeatherDatum;

public class APIHandler {

	/**
	 * https://www.baeldung.com/guide-to-okhttp final url should look like
	 * https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/[location]/[date1]/[date2]?key=YOUR_API_KEY
	 * dates should be in format of yyyy-mm-dd
	 */

	// base URL of visual crossing weather API
	private final String BASE_URL = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/";
	private final String API_KEY = "9U738TQ6E5H3M3G6U8R97L3JQ"; // free API key for weather crossing weather API (1000
																// calls/day, each day retrieved counts as 1 call)
	public String buildRequest;
	public String requestResult = "";
	private final Gson gson = new Gson();
	private final OkHttpClient client = new OkHttpClient();

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
			throws ConnectException, ImproperDateSelectionException, InvalidLocationException {
		// Gathering data for api call
		String location = qData.getLocation();
		String startDate = qData.getStartDate().toString();
		String endDate = qData.getEndDate().toString();

		// If dates won't make valid query, throw exception and give user error message
		// in calling function
		if (qData.getStartDate().isAfter(qData.getEndDate())) {
			throw new ImproperDateSelectionException();
		}

		// Constructing API call
		buildRequest = BASE_URL + location + "/" + startDate + "/" + endDate + "?key=" + API_KEY;

		// Get only fields that program is interested in from API call
		buildRequest += "&elements=temp,feelslike,precip,precipprob,dew,windspeed,pressure,datetime";
		/**
		 * Current elements Temperature Feels like temperature Total precipitation
		 * Precipitation chance Dewpoint temperature Wind speed Pressure Date
		 */

		Request request = new Request.Builder().url(buildRequest).build();
		// Sending API call
		Call call = client.newCall(request);
		try { // Try to call the API, if fail then throw exception and output error message
				// alerting user
			Response response = call.execute();
			if (!response.isSuccessful()) { // If we can't connect to the resource
				throw new ConnectException("Response unsuccessful: " + response);
			}
			// Turn API result into String (response.toString doesn't work, need to get from
			// body)
			requestResult = response.body().string();

			// if location entered by user is invalid
			if (requestResult.equals("Bad API Request:Invalid location parameter value.")) {
				throw new InvalidLocationException();
			}
			// TODO - IF JSON RESPONSE IS VAILD, SAVE IT SOMEWHERE FOR LATER RETRIEVAL
			// if no errors, calls parseWeatherData function to construct weatherdata object
			return parseWeatherData(requestResult);
		} catch (Exception e) {
			// catch-all for any other exceptions, add more exceptions as they're found
			e.printStackTrace();
		}
		return null;

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

			WeatherDatum weatherDatum = new WeatherDatum(actualTemp, // Actual Temp
					feelsLikeTemp, precipAmount, precipChance, dewpoint, windSpeed, pressure, date);

			data.addWeatherDatum(weatherDatum);

		}

		return data;

	}

}
