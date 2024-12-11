/**
 * @author John Ryan - john.ryan@drake.edu
 * CS 067 - Fall 2024
 * Dec 9th, 2024
 */
package view;

import controller.WeatherChartGenerator;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.WeatherData;

public class ChartView extends Application {

	WeatherData wd = new WeatherData();	// WeatherData object used to get info for charts
	
	/**
	 * Constructs a new chart view and assigns value to its weatherData objct
	 * @param weatherData
	 */
	public ChartView(WeatherData weatherData) {
		wd = weatherData;
	}

	/**
	 * The ChartView is used to display a variety of different charts based
	 * on the WeatherData object that it is constructed with. Each of these
	 * charts can be interacted with by the user with a mouse-hover to get
	 * more precise data on a specific point.
	 */
	@Override
	public void start(Stage stage) {
		FlowPane charts = new FlowPane();	// Create a FlowPane for chart layout


		// Add charts to the FlowPane
		WeatherChartGenerator chartMaker = new WeatherChartGenerator(wd); // Generate charts from WeatherData
		charts.getChildren().addAll(chartMaker.generateChart(1).getChart(), // Temperature
				chartMaker.generateChart(2).getChart(), // Feels-Like Temperature
				chartMaker.generateChart(3).getChart(), // Precipitation Amount
				chartMaker.generateChart(4).getChart(), // Precipitation Chance
				chartMaker.generateChart(5).getChart(), // Dewpoint
				chartMaker.generateChart(6).getChart(), // Wind Speed
				chartMaker.generateChart(7).getChart() // Pressure
		);

		// Make the FlowPane scrollable
		ScrollPane scrollPane = new ScrollPane(charts);
		scrollPane.setFitToWidth(true); // Allow the charts to fit to the width of the ScrollPane
		StackPane contentWrapper = new StackPane(charts);	// Wrap scrollpane in a content wrapper
		scrollPane.setContent(contentWrapper);
		// Create the scene
		Scene scene = new Scene(new StackPane(scrollPane), 1100, 600);
		stage.setScene(scene);
		stage.setTitle("Weather Data Charts");
		stage.show();
	}
	
	// Program launch
	public static void main(String[] args) {
		launch();
	}

}
