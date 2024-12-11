/**
 * @author John Ryan - john.ryan@drake.edu
 * CS 067 - Fall 2024
 * Dec 9th, 2024
 */
package model;

import java.time.LocalDate;

/**
 * This class contains collected weather data for a specific point in time
 */
public class WeatherDatum {

	private double actualTemp;
	private double feelsLikeTemp;
	private double precipAmount;
	private double precipChance;
	private double dewpoint;
	private double windSpeed;
	private double pressure;
	private LocalDate date;

	/**
	 * Constructs a WeatherDatum object based on the parameters
	 * 
	 * @param actTemp  // Current actual temp of the weather
	 * @param feelTemp // Current feels-like temp of the weather
	 * @param pAmt     // Current precipitation amount
	 * @param pCha     // Current precipitation chance
	 * @param dewp     // Current dewpoint
	 * @param wSpeed   // Current wind speed
	 * @param press    // Current pressure
	 * @param d        // Current date
	 */
	public WeatherDatum(double actTemp, double feelTemp, double pAmt, double pCha, double dewp, double wSpeed,
			double press, LocalDate d) {
		setActualTemp(actTemp);
		setFeelsLikeTemp(feelTemp);
		setPrecipAmount(pAmt);
		setPrecipChance(pCha);
		setDewpoint(dewp);
		setWindSpeed(wSpeed);
		setPressure(press);
		setDate(d);
	}

	// Getters - Setters

	public double getActualTemp() {
		return actualTemp;
	}

	private void setActualTemp(double actualTemp) {
		this.actualTemp = actualTemp;
	}

	public double getFeelsLikeTemp() {
		return feelsLikeTemp;
	}

	private void setFeelsLikeTemp(double feelsLikeTemp) {
		this.feelsLikeTemp = feelsLikeTemp;
	}

	public double getPrecipAmount() {
		return precipAmount;
	}

	private void setPrecipAmount(double precipAmount) {
		this.precipAmount = precipAmount;
	}

	public double getPrecipChance() {
		return precipChance;
	}

	private void setPrecipChance(double precipChance) {
		this.precipChance = precipChance;
	}

	public double getDewpoint() {
		return dewpoint;
	}

	private void setDewpoint(double dewpoint) {
		this.dewpoint = dewpoint;
	}

	public double getWindSpeed() {
		return windSpeed;
	}

	private void setWindSpeed(double windSpeed) {
		this.windSpeed = windSpeed;
	}

	public double getPressure() {
		return pressure;
	}

	private void setPressure(double pressure) {
		this.pressure = pressure;
	}

	public LocalDate getDate() {
		return date;
	}

	private void setDate(LocalDate date) {
		this.date = date;
	}

}
