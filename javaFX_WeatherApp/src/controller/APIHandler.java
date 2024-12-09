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

import model.ImproperDateSelectionException;
import model.InvalidLocationException;
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
		 * @throws InternetException 
		 */
	public WeatherData fetchWeatherData(QueryData qData) throws ConnectException, ImproperDateSelectionException, InvalidLocationException {
		// Gathering data for api call
		String location = qData.getLocation();
		String startDate = qData.getStartDate().toString();
		String endDate = qData.getEndDate().toString();
		
		// If dates won't make valid query, throw exception and give user error message in calling function
		if (qData.getStartDate().isAfter(qData.getEndDate())) {
			throw new ImproperDateSelectionException();
		}
		
		// Constructing API call
		buildRequest = BASE_URL + location + "/" + startDate + "/" + endDate + "?key=" + API_KEY;
		
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
		buildRequest += "&elements=temp,feelslike,precip,precipprob,dew,windspeed,winddir,pressure,datetime";
		
		Request request = new Request.Builder().url(buildRequest).build(); // TODO might need to specify what format to receive information in
		// Sending synchronous API call
		Call call = client.newCall(request);
		try {	// Try to call the API, if fail then throw exception and bring up dialog box alerting user
			Response response = call.execute();
			if(!response.isSuccessful()) { // If we can't connect to the resource
				throw new ConnectException("Response unsuccessful: " + response);
			}
			
			requestResult = response.body().string();
			
			if(requestResult.equals("Bad API Request:Invalid location parameter value.")) {
				// if location entered by user is invalid
				throw new InvalidLocationException();
			}
			
			return parseWeatherData(requestResult);
		}
		catch (Exception e) {
			// catch-all for any other exceptions, add as needed
			e.printStackTrace();
		}
		return null;
		
	}
	
	/**
	 * TODO TODO TODO
	 * @param response
	 * @return
	 */
	public WeatherData parseWeatherData(String response) {
		
		JsonObject json = gson.fromJson(response.toString(), JsonObject.class);
		WeatherData data = new WeatherData();
		JsonArray daysArray = json.getAsJsonArray("days");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
		for(int i = 0; i < daysArray.size(); i++) {
			JsonObject dayObject = daysArray.get(i).getAsJsonObject();
			
			double actualTemp = dayObject.get("temp").getAsDouble();
			double feelsLikeTemp = dayObject.get("feelslike").getAsDouble();
			double precipAmount = dayObject.get("precip").getAsDouble();
			double precipChance = dayObject.has("precipprob") ? dayObject.get("precipprob").getAsDouble() : 0;
			double dewpoint = dayObject.get("dew").getAsDouble();
			double windSpeed = dayObject.get("windspeed").getAsDouble();
			int windAngle = dayObject.get("winddir").getAsInt(); // Get wind direction as an integer
			double pressure = dayObject.get("pressure").getAsDouble();
	        
	        String dateString = dayObject.get("datetime").getAsString();
	        LocalDate date = LocalDate.parse(dateString, formatter);

	        
	        WeatherDatum weatherDatum = new WeatherDatum
	        	(
	            actualTemp, // Actual Temp
	            feelsLikeTemp,
	            precipAmount,
	            precipChance,
	            dewpoint,
	            windSpeed,
	            windAngle,  // Wind Angle
	            pressure,
	            date
	        );
	        
	        data.addWeatherDatum(weatherDatum);

		}
		
		return data;
		
	}
	
}
