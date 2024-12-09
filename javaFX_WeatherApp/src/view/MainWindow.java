package view;

import javafx.application.Application; 
import javafx.scene.Scene;
import javafx.scene.control.Button; 
import javafx.scene.layout.*; 
import javafx.scene.control.*; 
import javafx.stage.Stage;
import model.ImproperDateSelectionException;
import model.InvalidLocationException;
import model.QueryData;
import model.WeatherData;
import java.net.ConnectException;
import java.time.LocalDate;
import controller.APIHandler;


public class MainWindow extends Application { 
  
    // launch the application 
    public void start(Stage s) 
    { 
        // set title for the stage 
        s.setTitle("Weather Data Query"); 
        
        TextField locationInput = new TextField();
        locationInput.setPromptText("Enter location (e.g., London)");
  
        // create both date pickers
        DatePicker startDatePicker = new DatePicker();
        DatePicker endDatePicker = new DatePicker();
        
        Button btnSubmitQuery = new Button("Get Weather Data");
        
        Label statusLabel = new Label();
        
        btnSubmitQuery.setOnAction(e -> {
            String location = locationInput.getText();
            LocalDate startDate = startDatePicker.getValue();
            LocalDate endDate = endDatePicker.getValue();

            if (location.isEmpty() || startDate == null || endDate == null) {
                statusLabel.setText("Please fill in all fields.");
                return;
            }

            // Construct QueryData
            QueryData queryData = new QueryData(startDate, endDate, location);

            try {
                APIHandler apiHandler = new APIHandler();
                WeatherData weatherData = apiHandler.fetchWeatherData(queryData);

                // Pass WeatherData to ChartView
                ChartView chartView = new ChartView(weatherData);
                chartView.start(new Stage());
            } catch (ConnectException e1) {
                statusLabel.setText("Could not connect to Visual Crossing Weather API. Check your internet connection or try again later.");
                e1.printStackTrace();
            } catch (ImproperDateSelectionException e1) {
            		statusLabel.setText("Improper Date Selection. Dates must be selected\n with the second date occuring after the first\n"
            				+ ", cannot measure a time period greater than 30 days.");
            		e1.printStackTrace(); 
            } catch (InvalidLocationException e1) {
            		statusLabel.setText("Entered location is invalid. Check location again for any errors.");
				e1.printStackTrace();
			}
        });
        
        VBox layout = new VBox(10, new Label("Location:"), locationInput,
                new Label("Start Date:"), startDatePicker,
                new Label("End Date:"), endDatePicker,
                btnSubmitQuery, statusLabel);
        layout.setStyle("-fx-padding: 20;");

  
        // Set up scene and show stage
        Scene scene = new Scene(layout, 400, 300);
        s.setScene(scene);
        s.show();
    } 
	
	public static void main(String[] args) {
		launch();
	}

}
