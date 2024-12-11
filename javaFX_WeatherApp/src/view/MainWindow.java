/**
 * @author John Ryan - john.ryan@drake.edu
 * CS 067 - Fall 2024
 * Dec 9th, 2024
 */
package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.ImproperDateRangeException;
import model.InvalidLocationException;
import model.QueryData;
import model.WeatherData;
import java.net.ConnectException;
import java.time.LocalDate;
import controller.APIHandler;

public class MainWindow extends Application {

	/**
	 * Launches the main window of the application. From this window, the user can
	 * enter a location, start date, and end date, and retrieve weather data from
	 * those parameters. They are alerted if the location they input cannot be
	 * found, if they left any fields empty, and if they set the end date to before
	 * the start date.
	 */
	@Override
	public void start(Stage s) {
		// set title for the stage
		s.setTitle("Weather Data Query");
		TextField locationInput = new TextField(); // Where user will input location
		locationInput.setPromptText("Enter location (e.g., London)");

		// create both date pickers
		DatePicker startDatePicker = new DatePicker();
		DatePicker endDatePicker = new DatePicker();
		// Button to submit query
		Button btnSubmitQuery = new Button("Get Weather Data");
		Button btnAbout = new Button("About this Program"); // Get information about this program
		// Label that outputs error messages
		Label statusLabel = new Label();
		statusLabel.setWrapText(true); // Ensure text doesn't continue outside of field of view

		// Action for button that submits query
		btnSubmitQuery.setOnAction(e -> {
			// Obtaining values from fields
			String location = locationInput.getText();
			LocalDate startDate = startDatePicker.getValue();
			LocalDate endDate = endDatePicker.getValue();

			// If fields are empty,
			if (location.isEmpty() || startDate == null || endDate == null) {
				statusLabel.setText("Please fill in all fields.");
				return;
			}

			// Construct QueryData
			QueryData queryData = new QueryData(startDate, endDate, location);

			// Try to retrieve results from API, several failure conditions for bad input
			// and no connection
			try {
				APIHandler apiHandler = new APIHandler();
				WeatherData weatherData = apiHandler.fetchWeatherData(queryData);

				// Pass WeatherData to ChartView
				ChartView chartView = new ChartView(weatherData);
				chartView.start(new Stage());
			} catch (ConnectException e1) { // Cannot connect to Visual Crossing API service
				statusLabel.setText(
						"Could not connect to Visual Crossing Weather API. Check your internet connection or try again later.");
				// e1.printStackTrace();
			} catch (ImproperDateRangeException e1) { // Second date chosen is before the first date
				statusLabel.setText(
						"Improper Date Selection. Dates must be selected with the second date occuring after the first.");
				// e1.printStackTrace();
			} catch (InvalidLocationException e1) { // Location entered cannot be resolved to real world location
				statusLabel.setText("Entered location is invalid. Check location again for any errors.");
				// e1.printStackTrace();
			} catch (Exception e1) { // Catch all for unpredicted exception
				statusLabel.setText(
						"An unknown exception has occured. Contact the developer and have them check the stack trace.");
				e1.printStackTrace();
			}
		});

		// About button action, opens up window detailing info about this program
		btnAbout.setOnAction(e -> {
			AboutView aboutView = new AboutView();
			try {
				aboutView.start(new Stage());
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});

		// VBox containing everything to be shown in the Main Window
		VBox layout = new VBox(10, new Label("Location:"), locationInput, new Label("Start Date:"), startDatePicker,
				new Label("End Date:"), endDatePicker, btnSubmitQuery, btnAbout, statusLabel);
		layout.setStyle("-fx-padding: 20;");

		// Set up scene and show stage
		Scene scene = new Scene(layout, 400, 350);
		s.setScene(scene);
		s.show();
	}

	public static void main(String[] args) {
		launch();
	}

}
