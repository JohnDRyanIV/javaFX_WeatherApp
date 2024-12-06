package view;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ChartView extends Application {
	
	@Override
	public void start(Stage stage) {
		FlowPane charts = new FlowPane();
		ScrollPane scroll = new ScrollPane(charts);
		scroll.setMinWidth(800);
		ArrayList<Label> labels = new ArrayList<Label>();
		charts.setOrientation(Orientation.HORIZONTAL);
		charts.setHgap(20);
		for(int i = 0; i < 50; i++) {
			Label temp = null;
			labels.add(new Label());
			labels.get(i).setText(Integer.toString(i));
			charts.getChildren().add(labels.get(i));
		}
		Scene scene = new Scene(new StackPane(scroll), 640, 480);
		stage.setScene(scene);
		stage.show();
	}
	
    public static void main(String[] args) {
        launch();
    }
	
}
