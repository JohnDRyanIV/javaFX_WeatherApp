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

	public WeatherDatum(double actTemp, double feelTemp, double pAmt, double pCha, double dewp, double wSpeed,
			double press, LocalDate d) {
		setActualTemp(actualTemp);
		setFeelsLikeTemp(feelTemp);
		setPrecipAmount(pAmt);
		setPrecipChance(pCha);
		setDewpoint(dewp);
		setWindSpeed(wSpeed);
		setPressure(press);
		setDate(d);
	}

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
