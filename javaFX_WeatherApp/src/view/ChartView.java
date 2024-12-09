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


	@Override
	public void start(Stage stage) {
		FlowPane charts = new FlowPane();
		ScrollPane scroll = new ScrollPane(charts);
		
		Chart chartTemp, chartFeelsLike, chartPrecipAmt, chartPrecipChance, chartDewpoint, chartWindSpeed, chartPressure = null;
		
		WeatherChartGenerator chartMaker = new WeatherChartGenerator(wd); // Will create the charts with data from wd
		
		chartTemp = chartMaker.generateChart(1);
		chartFeelsLike = chartMaker.generateChart(2);
		chartPrecipAmt = chartMaker.generateChart(3);
		chartPrecipChance = chartMaker.generateChart(4);
		chartDewpoint = chartMaker.generateChart(5);
		chartWindSpeed = chartMaker.generateChart(6);
		chartPressure = chartMaker.generateChart(7);
		
		charts.getChildren().addAll(chartTemp, chartFeelsLike, chartPrecipAmt, chartPrecipChance, chartDewpoint, chartWindSpeed, chartPressure);
		
		scroll.setMinWidth(800);

		Scene scene = new Scene(new StackPane(scroll), 640, 480);
		stage.setScene(scene);
		stage.show();
	}
	
    public static void main(String[] args) {
        launch();
    }
	
}
