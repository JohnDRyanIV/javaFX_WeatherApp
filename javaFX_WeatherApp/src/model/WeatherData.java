package model;

import java.util.ArrayList;

public class WeatherData extends ArrayList<WeatherDatum> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2446544465857819183L;
	public String title;

	/**
	 * Adds a WeatherDatum and sets the type (hourly or daily).
	 * 
	 * @param wd     The WeatherDatum to add.
	 * @param isHour True if the data is hourly; false if daily.
	 */
	public void addWeatherDatum(WeatherDatum wd) {
		this.add(wd); // Use ArrayList's add method
	}

	/**
	 * Get the WeatherDatum at a specific index.
	 * 
	 * @param wdIndex The index to retrieve.
	 * @return The WeatherDatum at the specified index.
	 */
	public WeatherDatum getWeatherDatum(int wdIndex) {
		return this.get(wdIndex); // Use ArrayList's get method
	}
}
