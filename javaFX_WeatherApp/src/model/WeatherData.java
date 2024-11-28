package model;

import java.util.ArrayList;

public class WeatherData {
	
	// No real reason to protect this info, and making it public should make
	// constructing a chart out of it way easier.
	
	public String title;
	public ArrayList<Double> actualTemp = new ArrayList<Double>();
	public ArrayList<Double> feelsLikeTemp = new ArrayList<Double>();
	public ArrayList<Double> precipAmount = new ArrayList<Double>();
	public ArrayList<Double> precipChance = new ArrayList<Double>();
	public ArrayList<Double> dewPoint = new ArrayList<Double>();
	public ArrayList<Double> windSpeed = new ArrayList<Double>();
	public ArrayList<Integer> windAngle = new ArrayList<Integer>();
	public ArrayList<String> windDirection = new ArrayList<String>();
	public ArrayList<Double> pressure = new ArrayList<Double>();
	public boolean isHourly;
	public boolean isDaily;

}
