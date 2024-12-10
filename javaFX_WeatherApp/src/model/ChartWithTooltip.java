/**
 * @author John Ryan - john.ryan@drake.edu
 * CS 067 - Fall 2024
 * Dec 9th, 2024
 */
package model;

import javafx.scene.chart.LineChart;
import javafx.scene.control.Tooltip;

/**
 * This class bundles a chart with a tooltip so one can see values for the chart in
 * areas where the mouse is hovering over
 */
public class ChartWithTooltip {
	private final LineChart<String, Number> chart;
	private final Tooltip tooltip;

	public ChartWithTooltip(LineChart<String, Number> chart, Tooltip tooltip) {
		this.chart = chart;
		this.tooltip = tooltip;
	}

	public LineChart<String, Number> getChart() {
		return chart;
	}

	public Tooltip getTooltip() {
		return tooltip;
	}

}