package controller;

import javafx.scene.chart.Chart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
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
     * @param metricIndex The index corresponding to a field in WeatherDatum
     *                    (e.g., 1 for actualTemp, 2 for feelsLikeTemp, etc.)
     * @return A LineChart representing the selected metric over time.
     */
    public Chart generateChart(int metricIndex) {
        // Create axes
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        String title = "";
        String yAxisLabel = "";

        // Determine the metric to plot based on the metricIndex
        XYChart.Series<Number, Number> series = new XYChart.Series<>();

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

            series.getData().add(new XYChart.Data<>(i, value));
        }

        // Create the chart
        xAxis.setLabel("Time (Days)");
        yAxis.setLabel(yAxisLabel);

        LineChart<Number, Number> chart = new LineChart<>(xAxis, yAxis);
        chart.setTitle(title);
        chart.getData().add(series);
        
        chart.setCreateSymbols(false); // Disable circular symbols for data points

        return chart;
    }

}
