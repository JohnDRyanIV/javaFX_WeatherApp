package view;

import javafx.application.Application; 
import javafx.scene.Scene; 
import javafx.scene.control.Button; 
import javafx.scene.layout.*; 
import javafx.event.ActionEvent; 
import javafx.event.EventHandler; 
import javafx.scene.control.*; 
import javafx.stage.Stage; 
import javafx.scene.control.Alert.AlertType; 
import java.time.LocalDate; 


public class MainWindow extends Application { 
  
    // launch the application 
    public void start(Stage s) 
    { 
        // set title for the stage 
        s.setTitle("creating date picker"); 
 
  
        // create a tile pane 
        TilePane r = new TilePane(); 
  
        // create both date pickers
        DatePicker date1 = new DatePicker();
        DatePicker date2 = new DatePicker();
  
        // add button and label 
        r.getChildren().add(date1);
        r.getChildren().add(date2);
  
        // create a scene 
        Scene sc = new Scene(r, 200, 200); 
  
        // set the scene 
        s.setScene(sc); 
  
        s.show(); 
    } 
	
	public static void main(String[] args) {
		launch();
	}

}
