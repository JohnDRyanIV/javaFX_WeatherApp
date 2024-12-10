/**
 * @author John Ryan - john.ryan@drake.edu
 * CS 067 - Fall 2024
 * Dec 9th, 2024
 */
package view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * This view gives details about the program's capabilities and limitations
 */
public class AboutView extends Application {

	@Override
	public void start(Stage stage) {
		// Create content for the About View
		Label lblTitle = new Label("Weather Visualization Program");
		lblTitle.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
		// Label outputting
		Label lblDescription = new Label(
				"This program allows users to visualize weather data for a specified location and date range. "
						+ "Features include:\n\n" + "1. Querying weather data using the Visual Crossing Weather API.\n"
						+ "2. Displaying charts for various metrics such as temperature, precipitation, and wind speed.\n"
						+ "3. Scrollable views for easier data exploration.\n\n"
						+ "Note that this program can only accurate predict weather 15 days in advance, after that it will "
						+ "start to guess weather trends based on past statistical patterns.\n\n"
						+ "Author: John Ryan\nEmail:john.ryan@drake.edu\nVersion: 1.0\nYear: 2024");

		lblDescription.setWrapText(true);
		ScrollPane sp = new ScrollPane(lblDescription);
		sp.setFitToWidth(true);

		StackPane contentWrapper = new StackPane(lblTitle, lblDescription);

		// Puts padding around the scrollpane
		contentWrapper.setPadding(new Insets(10, 20, 0, 20));
		sp.setContent(contentWrapper);

		sp.setBackground(new Background(new BackgroundFill(Color.web("#f4f4f4"), CornerRadii.EMPTY, Insets.EMPTY)));

		Region viewport = (Region) sp.lookup(".viewport");
		if (viewport != null) {
			viewport.setBackground(
					new Background(new BackgroundFill(Color.web("#f4f4f4"), CornerRadii.EMPTY, Insets.EMPTY)));
		}

		StackPane root = new StackPane(lblTitle, sp);

		Scene scene = new Scene(root, 400, 200);
		stage.setScene(scene);
		stage.setTitle("About this Program");
		stage.show();
	}

	public static void main(String[] args) {
		launch();
	}
}