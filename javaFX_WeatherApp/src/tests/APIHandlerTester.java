package tests;

import static org.junit.Assert.*;
import org.junit.Test;

import controller.APIHandler;

import java.time.LocalDate;

import model.QueryData;
import model.WeatherData;
import model.WeatherDatum;
import model.ImproperDateRangeException;
import model.InvalidLocationException;

public class APIHandlerTester {

	public APIHandler aHand = new APIHandler();

	@Test(expected = ImproperDateRangeException.class)
	public void testFetchWeatherDataWithInvalidDates() throws Exception {
		// Mock QueryData with invalid dates
		QueryData queryData = new QueryData(LocalDate.of(2023, 9, 10), LocalDate.of(2023, 9, 1), "London");

		// Expect ImproperDateSelectionException
		aHand.fetchWeatherData(queryData);
	}

	@Test(expected = InvalidLocationException.class)
	public void testFetchWeatherDataWithInvalidLocation() throws Exception {
		// Mock QueryData with an invalid location
		QueryData queryData = new QueryData(LocalDate.of(2023, 9, 1), LocalDate.of(2023, 9, 5), "fdasfsdafasdf");

		// Expect InvalidLocationException
		aHand.fetchWeatherData(queryData);
	}

	@Test
	public void testParseWeatherDataWithValidResponse() {
		// Mock valid JSON response
		String validJsonResponse = "{" + "\"days\": ["
				+ "  { \"datetime\": \"2023-09-01\", \"temp\": 25.0, \"feelslike\": 24.5, \"precip\": 0.0, \"precipprob\": 10, \"dew\": 12.0, \"windspeed\": 10.5, \"pressure\": 1015.0 },"
				+ "  { \"datetime\": \"2023-09-02\", \"temp\": 26.0, \"feelslike\": 25.5, \"precip\": 0.2, \"precipprob\": 20, \"dew\": 13.0, \"windspeed\": 12.0, \"pressure\": 1013.0 }"
				+ "]" + "}";

		// Call parseWeatherData with mock response
		WeatherData weatherData = aHand.parseWeatherData(validJsonResponse);

		// Assertions for WeatherData
		assertNotNull(weatherData);
		assertEquals(2, weatherData.size());

		// Check data for the first day
		WeatherDatum day1 = weatherData.get(0);
		assertEquals(LocalDate.of(2023, 9, 1), day1.getDate());
		assertEquals(25.0, day1.getActualTemp(), 0.01);
		assertEquals(24.5, day1.getFeelsLikeTemp(), 0.01);
		assertEquals(0.0, day1.getPrecipAmount(), 0.01);
	}
}