/**
 * @author John Ryan - john.ryan@drake.edu
 * CS 067 - Fall 2024
 * Oct 24, 2024
 */
package view;

import controller.APIHandler;
import model.WeatherData;
import model.WeatherDatum;

/**
 * 
 */
public class GUIStarter {

    public static void main(final String[] args) {
    		
    		MainWindow.main(args);
    		
    		//testDataOutput();
    	}
    
    public void testDataOutput() {
    		String jsonResponse = "{\"queryCost\":15,\"latitude\":51.5064,\"longitude\":-0.12721,\"resolvedAddress\":\"London, England, United Kingdom\",\"address\":\"London\",\"timezone\":\"Europe/London\",\"tzoffset\":1.0,\"days\":[{\"datetime\":\"2024-09-14\",\"temp\":55.0,\"feelslike\":55.0,\"dew\":44.4,\"precip\":0.0,\"precipprob\":0.0,\"windspeed\":10.8,\"winddir\":232.1,\"pressure\":1030.4},{\"datetime\":\"2024-09-15\",\"temp\":58.3,\"feelslike\":58.0,\"dew\":50.1,\"precip\":0.0,\"precipprob\":0.0,\"windspeed\":11.5,\"winddir\":243.7,\"pressure\":1027.2},{\"datetime\":\"2024-09-16\",\"temp\":61.3,\"feelslike\":61.3,\"dew\":50.9,\"precip\":0.0,\"precipprob\":0.0,\"windspeed\":9.5,\"winddir\":26.9,\"pressure\":1028.4},{\"datetime\":\"2024-09-17\",\"temp\":61.1,\"feelslike\":61.1,\"dew\":50.0,\"precip\":0.0,\"precipprob\":0.0,\"windspeed\":12.1,\"winddir\":47.7,\"pressure\":1030.7},{\"datetime\":\"2024-09-18\",\"temp\":66.1,\"feelslike\":66.1,\"dew\":55.5,\"precip\":0.0,\"precipprob\":0.0,\"windspeed\":13.2,\"winddir\":49.2,\"pressure\":1027.8},{\"datetime\":\"2024-09-19\",\"temp\":67.5,\"feelslike\":67.5,\"dew\":57.4,\"precip\":0.0,\"precipprob\":0.0,\"windspeed\":16.8,\"winddir\":48.5,\"pressure\":1024.3},{\"datetime\":\"2024-09-20\",\"temp\":65.8,\"feelslike\":65.8,\"dew\":55.9,\"precip\":0.0,\"precipprob\":0.0,\"windspeed\":13.8,\"winddir\":52.8,\"pressure\":1020.8},{\"datetime\":\"2024-09-21\",\"temp\":66.6,\"feelslike\":66.6,\"dew\":58.1,\"precip\":0.0,\"precipprob\":0.0,\"windspeed\":8.7,\"winddir\":76.0,\"pressure\":1017.3},{\"datetime\":\"2024-09-22\",\"temp\":64.5,\"feelslike\":64.5,\"dew\":61.5,\"precip\":0.715,\"precipprob\":100.0,\"windspeed\":8.5,\"winddir\":99.9,\"pressure\":1011.6},{\"datetime\":\"2024-09-23\",\"temp\":62.6,\"feelslike\":62.6,\"dew\":58.0,\"precip\":1.012,\"precipprob\":100.0,\"windspeed\":15.2,\"winddir\":227.6,\"pressure\":1003.9},{\"datetime\":\"2024-09-24\",\"temp\":59.1,\"feelslike\":59.1,\"dew\":52.4,\"precip\":0.023,\"precipprob\":100.0,\"windspeed\":10.7,\"winddir\":280.8,\"pressure\":1003.1},{\"datetime\":\"2024-09-25\",\"temp\":57.2,\"feelslike\":57.2,\"dew\":51.6,\"precip\":0.228,\"precipprob\":100.0,\"windspeed\":9.6,\"winddir\":189.3,\"pressure\":998.5},{\"datetime\":\"2024-09-26\",\"temp\":58.1,\"feelslike\":58.1,\"dew\":54.5,\"precip\":0.513,\"precipprob\":100.0,\"windspeed\":17.5,\"winddir\":218.0,\"pressure\":986.7},{\"datetime\":\"2024-09-27\",\"temp\":52.9,\"feelslike\":52.4,\"dew\":45.9,\"precip\":0.417,\"precipprob\":100.0,\"windspeed\":13.6,\"winddir\":307.7,\"pressure\":1000.7},{\"datetime\":\"2024-09-28\",\"temp\":49.3,\"feelslike\":48.2,\"dew\":40.8,\"precip\":0.0,\"precipprob\":0.0,\"windspeed\":10.4,\"winddir\":287.0,\"pressure\":1023.0}]}";
	    APIHandler parser = new APIHandler();
	    WeatherData weatherData = parser.parseWeatherData(jsonResponse);
	        
	    // Print results
	    for (WeatherDatum datum : weatherData) {
	        System.out.println("Date: " + datum.date);
	        System.out.println("Actual Temp: " + datum.actualTemp);
	        System.out.println("Feels Like Temp: " + datum.feelsLikeTemp);
	        System.out.println("Precipitation: " + datum.precipAmount);
	        System.out.println("Dew Point: " + datum.dewpoint);
	        System.out.println("Wind Speed: " + datum.windSpeed);
	        System.out.println("Pressure: " + datum.pressure);
	        System.out.println("-----------");
	    }
    }

}
