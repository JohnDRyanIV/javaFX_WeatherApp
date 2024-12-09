package controller;

import java.time.format.DateTimeFormatter;

import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.Chart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.StackPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import model.ChartWithTooltip;
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
	 * Generates a chart based on the specified metric.
	 * 
	 * @param metricIndex The index corresponding to a field in WeatherDatum (e.g.,
	 *                    1 for actualTemp, 2 for feelsLikeTemp, etc.)
	 * @return A LineChart representing the selected metric over time.
	 */
	public ChartWithTooltip generateChart(int metricIndex) {
		// Create axes
		CategoryAxis xAxis = new CategoryAxis();
		NumberAxis yAxis = new NumberAxis();
		String title = "";
		String yAxisLabel = "";
		// These two doubles will be used to determine what the min and max values of
		// the chart y axis should be
		double minValue = Double.MAX_VALUE;
		double maxValue = Double.MIN_VALUE;

		// Determine the metric to plot based on the metricIndex
		XYChart.Series<String, Number> series = new XYChart.Series<>();

		// Format date so it only shows month & day
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM-dd");

		switch (metricIndex) {
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
			throw new IllegalArgumentException("Invalid metric index: " + metricIndex);
		}

		// Populate the series with data
		for (int i = 0; i < data.size(); i++) {
			WeatherDatum wd = data.get(i);
			double value = 0;

			switch (metricIndex) {
			case 1:
				value = wd.actualTemp;
				break;
			case 2:
				value = wd.feelsLikeTemp;
				break;
			case 3:
				value = wd.precipAmount;
				break;
			case 4:
				value = wd.precipChance;
				break;
			case 5:
				value = wd.dewpoint;
				break;
			case 6:
				value = wd.windSpeed;
				break;
			case 7:
				value = wd.pressure;
				break;
			}

			// Check to see if min or max value have changed this iteration
			minValue = Math.min(minValue, value);
			maxValue = Math.max(maxValue, value);

			// Format date output to MM-dd
			String formattedDate = wd.date.format(dateFormatter);

			// Add date as category and value as data point
			series.getData().add(new XYChart.Data<>(formattedDate, value));
		}

		// If the chart is precipitation chance, manually set min and max to 0 and 100
		if (metricIndex == 4) {
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

		// Make elements accessible on mouse hover
		Text infoText = new Text();

		// Set up the mouse hover event
		Tooltip tooltip = new Tooltip();
		chart.setOnMouseMoved((MouseEvent event) -> {
			double mouseX = event.getX();
			double mouseY = event.getY();

			String xValue = xAxis.getValueForDisplay(mouseX);
			Number yValue = yAxis.getValueForDisplay(mouseY);

			if (xValue != null && yValue != null) {
				tooltip.setText("X: " + xValue + ", Y: " + yValue);
				tooltip.show(chart, event.getScreenX(), event.getScreenY());
			} else {
				tooltip.hide(); // Hide tooltip if mouse is not within bounds of graph
			}
		});

		chart.setCreateSymbols(false); // Disable circular symbols for data points

		ChartWithTooltip toolChart = new ChartWithTooltip(chart, tooltip);

		return toolChart;

	}

}
