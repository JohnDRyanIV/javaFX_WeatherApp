package model;

import java.util.ArrayList;

public class WeatherData {
	
	// No real reason to protect this info, and making it public should make
	// constructing a chart out of it way easier.
	
	public String title;
	
	public ArrayList<WeatherDatum> data = new ArrayList<WeatherDatum>();
	
	public boolean isHourly;
	public boolean isDaily;
	
	
	public void addWeatherDatum(WeatherDatum wd, boolean isHour) {
		data.add(wd);
		if(isHour) {
			isHourly = true;
			isDaily = false;
		}
		else {
			isHourly = false;
			isDaily = true;
		}
	}
	
	// TODO add exception handling if index out of bounds
	public WeatherDatum getWeatherDatum(int wdIndex) {
		return data.get(wdIndex);
	}

}
