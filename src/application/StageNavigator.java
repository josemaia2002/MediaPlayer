package application;

import java.io.IOException;

import br.ufrn.imd.control.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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
	
	public PlayerController loadPlayerScreen(ActionEvent event)
	{
		Stage prevStage = (Stage) ((Node)(event.getSource())).getScene().getWindow();
		Stage stage = new Stage();
        stage.setResizable(true);
        PlayerController controller =(PlayerController) loadStage(stage, "PlayerScreen");
		prevStage.close();
		return controller;
	}
	
	public NewPlaylistController loadNewPlaylistScreen(ActionEvent event) {
		Stage stage = new Stage();
        stage.setResizable(false);
		return (NewPlaylistController) loadStage(stage, "NewPlaylistScreen");
	}
	
	public LoginController loadLoginScreen(ActionEvent event)
	{
		Stage prevStage = (Stage) ((Node)(event.getSource())).getScene().getWindow();
		Stage stage = new Stage();
		stage.setX(prevStage.getX());
        stage.setY(prevStage.getY());
        stage.setResizable(false);
        LoginController controller = (LoginController) loadStage(stage, "LoginScreen");
		prevStage.close();
		
		return controller;
	}
	
	public SignupController loadSignupScreen(ActionEvent event)
	{
		Stage prevStage = (Stage) ((Node)(event.getSource())).getScene().getWindow();
		Stage stage = new Stage();
		stage.setX(prevStage.getX());
        stage.setY(prevStage.getY());
        stage.setResizable(false);
        SignupController controller = (SignupController) loadStage(stage, "SignupScreen");
		prevStage.close();
		return controller;
		
	}
	
	public WindowController loadStage(Stage primaryStage, String ScreenName)
	{
		xOffset = 0;
		yOffset = 0;
		Parent root;
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/br/ufrn/imd/view/"+ScreenName+".fxml"));
		try {
			root = fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
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
		
		primaryStage.getIcons().add(new Image(getClass().getResource("/resources/images/logo.png").toString()));
		primaryStage.initStyle(StageStyle.TRANSPARENT);
		scene.setFill(Color.TRANSPARENT);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		return (WindowController) fxmlLoader.getController();
	}

	
}
