/**
 * @author John Ryan - john.ryan@drake.edu
 * CS 067 - Fall 2024
 * Dec 9th, 2024
 */
package controller;

import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;

import javafx.geometry.Point2D;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.Chart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import model.WeatherData;
import model.WeatherDatum;

public class WeatherChartGenerator {

	public WeatherData data;

	/**
	 * Constructor
	 *
	 * @param wd Weather data object that charts will be constructed from
	 */
	public WeatherChartGenerator(WeatherData wd) {
		this.data = wd;
	}

	/**
	 * Generates a chart based on the specified chartType.
	 * 
	 * @param chartType The index corresponding to a field in WeatherDatum (e.g., 1
	 *                  for actualTemp, 2 for feelsLikeTemp, etc.)
	 * @return A LineChart representing the selected chart type over time.
	 */
	public Chart generateChart(int chartType) {
		// Create axes
		CategoryAxis xAxis = new CategoryAxis();
		NumberAxis yAxis = new NumberAxis();
		String title = "";
		String yAxisLabel = "";
		// These two doubles will be used to determine what the min and max values of
		// the chart y axis should be
		double minValue = Double.MAX_VALUE;
		double maxValue = Double.MIN_VALUE;

		// Determine the chart type to plot
		XYChart.Series<String, Number> series = new XYChart.Series<>();

		// Format date so it only shows month & day
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM-dd");

		// Determine title and axis labels for chart
		switch (chartType) {
		case 1:
			title = "Actual Temperature Over Time";
			yAxisLabel = "Temperature (°F)";
			series.setName("Temperature");
			break;
		case 2:
			title = "Feels-Like Temperature Over Time";
			yAxisLabel = "Feels-Like Temperature (°F)";
			series.setName("Feels-Like Temperature");
			break;
		case 3:
			title = "Total Precipitation Over Time";
			yAxisLabel = "Precipitation (in)";
			series.setName("Precipitation");
			break;
		case 4:
			title = "Precipitation Chance Over Time";
			yAxisLabel = "Precipitation Chance (%)";
			series.setName("Precipitation Chance");
			break;
		case 5:
			title = "Dewpoint Over Time";
			yAxisLabel = "Dewpoint (°F)";
			series.setName("Dewpoint");
			break;
		case 6:
			title = "Wind Speed Over Time";
			yAxisLabel = "Wind Speed (mph)";
			series.setName("Wind Speed");
			break;
		case 7:
			title = "Pressure Over Time";
			yAxisLabel = "Pressure (hPa)";
			series.setName("Pressure");
			break;
		default:
			throw new IllegalArgumentException("Invalid chart type: " + chartType);
		}

		// Populate the series with data depending on chartType
		for (int i = 0; i < data.size(); i++) {
			WeatherDatum wd = data.get(i);
			double value = 0;

			switch (chartType) {
			case 1:
				value = wd.getActualTemp();
				break;
			case 2:
				value = wd.getFeelsLikeTemp();
				break;
			case 3:
				value = wd.getPrecipAmount();
				break;
			case 4:
				value = wd.getPrecipChance();
				break;
			case 5:
				value = wd.getDewpoint();
				break;
			case 6:
				value = wd.getWindSpeed();
				break;
			case 7:
				value = wd.getPressure();
				break;
			}

			// Check to see if min or max value have changed this iteration
			minValue = Math.min(minValue, value);
			maxValue = Math.max(maxValue, value);

			// Format date output to MM-dd
			String formattedDate = wd.getDate().format(dateFormatter);

			// Add date as category and value as data point
			series.getData().add(new XYChart.Data<>(formattedDate, value));
		}

		// If the chart is precipitation chance, manually set min and max to 0 and 100
		if (chartType == 4) {
			yAxis.setAutoRanging(false);
			yAxis.setLowerBound(0);
			yAxis.setUpperBound(100);
		} else { // If chart isn't precip chance, use this formula to determine bounds on either
					// side

			// Adjust y axis
			// If min and max are equal, give some margin on both sides so they aren't flat
			// at bottom of chart
			if (Double.compare(minValue, maxValue) == 0) {
				yAxis.setLowerBound(minValue - 1);
				yAxis.setUpperBound(minValue + 1);
			} else {
				double range = maxValue - minValue;
				double margin = range * 0.1;
				yAxis.setAutoRanging(false);
				yAxis.setLowerBound(minValue - margin);
				yAxis.setUpperBound(maxValue + margin);
				yAxis.setTickUnit(range / 5);
			}

		}

		// Create the chart
		xAxis.setLabel("Time (Days)");
		yAxis.setLabel(yAxisLabel);

		LineChart<String, Number> chart = new LineChart<>(xAxis, yAxis);
		chart.setTitle(title);
		chart.getData().add(series);

		// Used to format the tooltips that the chart will give
		DecimalFormat df = new DecimalFormat("#.##");

		// Set up the mouse hover event
		// https://stackoverflow.com/questions/31375922/javafx-how-to-correctly-implement-getvaluefordisplay-on-y-axis-of-a-xy-line
		// above link helped immensely, couldn't get this working correctly without it
		Tooltip tooltip = new Tooltip();
		chart.setOnMouseMoved((MouseEvent event) -> {
			// Logic to ensure that the right point is being retrieved relative to the mouse
			Point2D pointInScene = new Point2D(event.getSceneX(), event.getSceneY());
			double xPosInAxis = xAxis.sceneToLocal(new Point2D(pointInScene.getX(), 0)).getX();
			double yPosInAxis = yAxis.sceneToLocal(new Point2D(0, pointInScene.getY())).getY();

			String xValue = xAxis.getValueForDisplay(xPosInAxis);
			double yValue = yAxis.getValueForDisplay(yPosInAxis).doubleValue();

			if (xValue != null) {
				tooltip.setText("X: " + xValue + "Y: " + df.format(yValue));
				// +20 in below line are pixel offsets so that the tooltip doesn't disappear the
				// second it appears
				tooltip.show(chart, event.getScreenX() + 20, event.getScreenY() + 20);
			} else {
				tooltip.hide(); // Hide tooltip if mouse is not within bounds of graph
			}
		});
		// Hide tooltip when the mouse exits the chart
		chart.setOnMouseExited(event -> tooltip.hide());
		// Disable circular symbols for data points
		chart.setCreateSymbols(false);
		// Generate chart to return
		return chart;

	}

}
