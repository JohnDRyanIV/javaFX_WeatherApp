package controller;

import java.io.IOException;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import model.QueryData;
import model.WeatherData;

public class APIHandler {
	
	/**
	 * https://www.baeldung.com/guide-to-okhttp
	 * final url should look like
	 * https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/[location]/[date1]/[date2]?key=YOUR_API_KEY
	 * dates should be in format of yyyy-mm-dd
	 */
	
	
	private final String BASE_URL = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/"; // base URL of visual crossing weather API
	private final String API_KEY = "9U738TQ6E5H3M3G6U8R97L3JQ"; // free API key for weather crossing weather API (1000 calls/day)
	public String requestResult = "";
		/**
		 * This class utilizes a user query stored in a QueryData object to construct
		 * an API call to the Visual Crossing weather API
		 * @param data - Query used to construct API call
		 * @return - String representing info from API call
		 * @throws IOException
		 */
	public WeatherData getRequest(QueryData qData) throws IOException {
		OkHttpClient client = new OkHttpClient();
		// Gathering data for api call
		String location = qData.getLocation();
		String startDate = qData.getStartDate().toString();
		String endDate = qData.getEndDate().toString();
		// Constructing API call
		Request request = new Request.Builder().url(BASE_URL + location + "/" + startDate + "/" + endDate + "?key=" + API_KEY).build(); // TODO might need to specify what format to receive information in
		// Sending synchronous API call
		Call call = client.newCall(request);
		Response response = call.execute();
		
		// If response code indicates that resource isn't reachable, post dialog box informing user & return to main view
		
		// Returns weatherData object created in readRequest. Should never be the case that successfully retrieved query can't be resolved into weather data object
		return readRequest(response);

		
	}
	
	public WeatherData readRequest(Response response) {
		
		
		WeatherData data = new WeatherData();
		// make a for loop going through all steps of data from api call, extracting necessary variables & adding them to weatherdata object
		// TODO
		
		return null;
		
	}
	
}
