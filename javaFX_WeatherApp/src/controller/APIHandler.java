package controller;

import java.io.IOException;

import com.squareup.okhttp.Request;

import model.QueryData;
import model.WeatherData;

public class APIHandler {
	
	private final String BASE_URL = "TODO"; // TODO base URL of visual crossing weather API
	private final String API_KEY = "TODO"; // free API key for weather crossing weather API (1000 calls/day)
	public String requestResult = "";
		/**
		 * This class utilizes a user query stored in a QueryData object to construct
		 * an API call to the Visual Crossing weather API
		 * @param data - Query used to construct API call
		 * @return - String representing info from API call
		 * @throws IOException
		 */
	public String getRequest(QueryData data) throws IOException {
		Request request = new Request.Builder().url(BASE_URL + "/date").build();
		
		
		
		
		
		
		
		return null;
		
	}
	
	public WeatherData readRequest() {
		
		
		
		return null;
		
	}
	
}
