package model;

import javafx.scene.chart.LineChart;
import javafx.scene.control.Tooltip;

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