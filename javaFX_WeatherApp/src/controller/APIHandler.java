package controller;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import model.QueryData;
import model.WeatherData;
import model.WeatherDatum;

public class APIHandler {
	
	/**
	 * https://www.baeldung.com/guide-to-okhttp
	 * final url should look like
	 * https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/[location]/[date1]/[date2]?key=YOUR_API_KEY
	 * dates should be in format of yyyy-mm-dd
	 */
	
	
	private final String BASE_URL = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/"; // base URL of visual crossing weather API
	private final String API_KEY = "9U738TQ6E5H3M3G6U8R97L3JQ"; // free API key for weather crossing weather API (1000 calls/day)
	public String buildRequest;
	public String requestResult = "";
	private final Gson gson = new Gson();
	private final OkHttpClient client = new OkHttpClient();
		/**
		 * This class utilizes a user query stored in a QueryData object to construct
		 * an API call to the Visual Crossing weather API
		 * @param data - Query used to construct API call
		 * @return - String representing info from API call
		 * @throws IOException
		 */
	public WeatherData fetchWeatherData(QueryData qData, boolean isDays) throws IOException {
		// Gathering data for api call
		String location = qData.getLocation();
		String startDate = qData.getStartDate().toString();
		String endDate = qData.getEndDate().toString();
		
		// Constructing API call
		buildRequest = BASE_URL + location + "/" + startDate + "/" + endDate + "?key=" + API_KEY;
		
		if(isDays) {
			buildRequest += "&include=days";
		}
		else {
			buildRequest += "&include=hours";
		}
		// Get only fields that I'm interested in from api call
		/** Current elements
		 * Temperature
		 * Feels like temperature
		 * Total precipitation
		 * Precipitation chance
		 * Dewpoint temperature
		 * Wind speed
		 * Wind direction
		 * Pressure
		 */
		buildRequest += "&elements=temp,feelslike,precip,precipprob,dew,windspeed,winddir,pressure";
		
		Request request = new Request.Builder().url(buildRequest).build(); // TODO might need to specify what format to receive information in
		// Sending synchronous API call
		Call call = client.newCall(request);
		try {	// Try to call the API, if fail then throw exception and bring up dialog box alerting user
			Response response = call.execute();
			if(!response.isSuccessful()) {
				throw new IOException("Response unsuccessful: " + response);
			}
			
			requestResult = response.body().string();
			
			return parseWeatherData(response, isDays);
		}
		catch(Exception e) {
			// TODO pull up window alerting user
		}
		
		return null;

		
	}
	
	public WeatherData parseWeatherData(Response response, boolean isDays) {
		
		JsonObject json = gson.fromJson(response.toString(), JsonObject.class);
		WeatherData data = new WeatherData();
		if(isDays) {
			data.isDaily = true;
		}
		else {
			data.isHourly = true;
		}
		
		// Extract fields from the response
		
		
		// Create weather object based on these fields
		WeatherDatum currDatum = null; // TODO initialize a weatherDatum object
		//data.addWeatherDatum(currDatum);
		
		// Add weatherDatum object to WeatherData object
		// make a for loop going through all steps of data from api call, extracting necessary variables & adding them to weatherdata object
		// TODO
		
		return null;
		
	}
	
}
