package model;

import java.util.ArrayList;

public class WeatherData extends ArrayList<WeatherDatum> {
    /**
	 * 
	 */
	private static final long serialVersionUID = -2446544465857819183L;
	public String title;
    public boolean isHourly;
    public boolean isDaily;

    /**
     * Adds a WeatherDatum and sets the type (hourly or daily).
     * 
     * @param wd     The WeatherDatum to add.
     * @param isHour True if the data is hourly; false if daily.
     */
    public void addWeatherDatum(WeatherDatum wd, boolean isHour) {
        this.add(wd); // Use ArrayList's add method
        if (isHour) {
            isHourly = true;
            isDaily = false;
        } else {
            isHourly = false;
            isDaily = true;
        }
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
