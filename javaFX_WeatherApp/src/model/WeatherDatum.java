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

	public double actualTemp;
	public double feelsLikeTemp;
	public double precipAmount;
	public double precipChance;
	public double dewpoint;
	public double windSpeed;
	public double pressure;
	public LocalDate date;

	public WeatherDatum(double actTemp, double feelTemp, double pAmt, double pCha, double dewp, double wSpeed,
			double press, LocalDate date) {
		actualTemp = actTemp;
		feelsLikeTemp = feelTemp;
		precipAmount = pAmt;
		precipChance = pCha;
		dewpoint = dewp;
		windSpeed = wSpeed;
		pressure = press;
		this.date = date;

	}

	public double getActualTemp() {
		return actualTemp;
	}

	public void setActualTemp(double actualTemp) {
		this.actualTemp = actualTemp;
	}

	public double getFeelsLikeTemp() {
		return feelsLikeTemp;
	}

	public void setFeelsLikeTemp(double feelsLikeTemp) {
		this.feelsLikeTemp = feelsLikeTemp;
	}

	public double getPrecipAmount() {
		return precipAmount;
	}

	public void setPrecipAmount(double precipAmount) {
		this.precipAmount = precipAmount;
	}

	public double getPrecipChance() {
		return precipChance;
	}

	public void setPrecipChance(double precipChance) {
		this.precipChance = precipChance;
	}

	public double getDewpoint() {
		return dewpoint;
	}

	public void setDewpoint(double dewpoint) {
		this.dewpoint = dewpoint;
	}

	public double getWindSpeed() {
		return windSpeed;
	}

	public void setWindSpeed(double windSpeed) {
		this.windSpeed = windSpeed;
	}

	public double getPressure() {
		return pressure;
	}

	public void setPressure(double pressure) {
		this.pressure = pressure;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

}
