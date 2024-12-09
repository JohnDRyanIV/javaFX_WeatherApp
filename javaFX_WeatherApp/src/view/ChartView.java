package view;

import controller.WeatherChartGenerator;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.Chart;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.WeatherData;

public class ChartView extends Application {

	WeatherData wd = new WeatherData();

	public ChartView(WeatherData weatherData) {
		wd = weatherData;
	}

	public void start(Stage stage) {
		// Create a FlowPane for chart layout
		FlowPane charts = new FlowPane();
		charts.setHgap(20); // Set horizontal gap between charts
		charts.setVgap(20); // Set vertical gap between charts
		charts.setPrefWrapLength(640); // Wrap after 640 pixels
		charts.setStyle("-fx-padding: 10;");

		// Add charts to the FlowPane
		WeatherChartGenerator chartMaker = new WeatherChartGenerator(wd); // Generate charts from WeatherData
		charts.getChildren().addAll(chartMaker.generateChart(1), // Temperature
				chartMaker.generateChart(2), // Feels-Like Temperature
				chartMaker.generateChart(3), // Precipitation Amount
				chartMaker.generateChart(4), // Precipitation Chance
				chartMaker.generateChart(5), // Dewpoint
				chartMaker.generateChart(6), // Wind Speed
				chartMaker.generateChart(7) // Pressure
		);

		// Make the FlowPane scrollable
		ScrollPane scrollPane = new ScrollPane(charts);
		scrollPane.setFitToWidth(true); // Allow the charts to fit to the width of the ScrollPane
		scrollPane.setFitToHeight(true);

		// Create the scene
		Scene scene = new Scene(new StackPane(scrollPane), 800, 600);
		stage.setScene(scene);
		stage.setTitle("Weather Data Charts");
		stage.show();
	}

	public static void main(String[] args) {
		launch();
	}

}
