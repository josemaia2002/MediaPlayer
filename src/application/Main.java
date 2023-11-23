package application;
	

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;


public class Main extends Application {
	double xOffset;
	double yOffset;
	
	@Override
	public void start(Stage primaryStage) {
		this.xOffset = 0;
		this.yOffset = 0;
		try {
			AnchorPane root = FXMLLoader.load(getClass().getResource("/br/ufrn/imd/visao/LogInScreen.fxml"));
			
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
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setResizable(false);
			primaryStage.initStyle(StageStyle.UNDECORATED);
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
