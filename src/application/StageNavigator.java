package application;

import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class StageNavigator {
	
	private static StageNavigator instance;
	
	double xOffset;
	double yOffset;
	
	public static StageNavigator getInstance() 
	{
		if(instance == null) 
		{
			instance = new StageNavigator();
		}
		return instance;
	}
	
	public void loadLoginScreen(ActionEvent event)
	{
		Stage prevStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		Stage stage = new Stage();
		stage.setX(prevStage.getX());
        stage.setY(prevStage.getY());
		loadStage(stage, "LoginScreen");
		prevStage.close();
	}
	
	public void loadSignupScreen(ActionEvent event)
	{
		Stage prevStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		Stage stage = new Stage();
		stage.setX(prevStage.getX());
        stage.setY(prevStage.getY());
		loadStage(stage, "SignupScreen");
		prevStage.close();
	}
	
	public void loadStage(Stage primaryStage, String ScreenName)
	{
		xOffset = 0;
		yOffset = 0;
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/br/ufrn/imd/visao/"+ScreenName+".fxml"));
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

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
		
		
		Scene scene = new Scene(root);
		
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setResizable(false);
		primaryStage.initStyle(StageStyle.TRANSPARENT);
		scene.setFill(Color.TRANSPARENT);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
