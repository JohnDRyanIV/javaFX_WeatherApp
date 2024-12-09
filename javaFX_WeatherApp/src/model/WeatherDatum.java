/**
 * @author John Ryan - john.ryan@drake.edu
 * CS 067 - Fall 2024
 * Dec 5, 2024
 */
package model;

import java.time.LocalDate;

/**
 * This class contains collected weather data for a specific point in time
 */
public class WeatherDatum {
	
	public double actualTemp;
	public double feelsLikeTemp;
	public double precipAmount;
	public double precipChance;
	public double dewpoint;
	public double windSpeed;
	public double pressure;
	public LocalDate date;
	
	public WeatherDatum(double actTemp, double feelTemp, double pAmt, double pCha, double dewp,
			double wSpeed, int wAngle, double press, LocalDate date) {
		actualTemp = actTemp;
		feelsLikeTemp = feelTemp;
		precipAmount = pAmt;
		precipChance = pCha;
		dewpoint = dewp;
		windSpeed = wSpeed;
		pressure = press;
		this.date = date;
		
	}

}
