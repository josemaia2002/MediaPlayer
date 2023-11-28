package application;
	

import javafx.event.EventHandler;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;


public class Main extends Application {
	double xOffset;
	double yOffset;
	
	@Override
	public void start(Stage primaryStage) {
		xOffset = 0;
		yOffset = 0;
		
		try {
			AnchorPane root = FXMLLoader.load(getClass().getResource("/br/ufrn/imd/visao/SignupScreen.fxml"));

			root.setOnMousePressed(new EventHandler<MouseEvent>() {

				@Override
	            public void handle(MouseEvent event) {
	                xOffset = primaryStage.getX() - event.getScreenX();
	                yOffset = primaryStage.getY() - event.getScreenY();
	            }
	        });
			root.setOnMouseDragged(new EventHandler<MouseEvent>() {
				@Override
	            public void handle(MouseEvent event) {
	                primaryStage.setX(event.getScreenX() + xOffset);
	                primaryStage.setY(event.getScreenY() + yOffset);
	            }
	        });
			
			
			Scene scene = new Scene(root,550,460);
			scene.setFill(Color.TRANSPARENT);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setResizable(false);
			primaryStage.initStyle(StageStyle.TRANSPARENT);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
