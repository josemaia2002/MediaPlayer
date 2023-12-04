package application;

import java.io.IOException;

import br.ufrn.imd.control.LoginController;
import br.ufrn.imd.control.NewPlaylistController;
import br.ufrn.imd.control.PlayerController;
import br.ufrn.imd.control.SignupController;
import br.ufrn.imd.control.WindowController;
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

/**
 * Singleton Class responsible for the stage changing operations
 * 
 * @author Davi Matias
 * @author Jose Maia
 * @version 1.0
 * @since 3/12/2023
 */

public class StageNavigator {
	
	private static StageNavigator instance;
	
	double xOffset;
	double yOffset;
	
	/**
	 * Method to get the singleton's static instance.
	 * @return The single instance of StageNavigator
	 */
	public static StageNavigator getInstance() 
	{
		if(instance == null) 
		{
			instance = new StageNavigator();
		}
		return instance;
	}
	
	/**
	 * Method loader for the music player main screen
	 * @param event The event that triggered the screen loading
	 * @return a controller to the new scene
	 */
	public PlayerController loadPlayerScreen(ActionEvent event)
	{
		Stage prevStage = (Stage) ((Node)(event.getSource())).getScene().getWindow();
		Stage stage = new Stage();
        stage.setResizable(true);
        PlayerController controller =(PlayerController) loadStage(stage, "PlayerScreen");
		prevStage.close();
		return controller;
	}
	
	/**
	 * Method loader for the playlist creation screen.
	 * @param event The event that triggered the screen loading
	 * @return a controller to the new scene
	 */
	public NewPlaylistController loadNewPlaylistScreen(ActionEvent event) {
		Stage stage = new Stage();
        stage.setResizable(false);
		return (NewPlaylistController) loadStage(stage, "NewPlaylistScreen");
	}
	
	/**
	 * Method loader for the login screen.
	 * @param event The event that triggered the screen loading
	 * @return a controller to the new scene
	 */
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
	
	/**
	 * Method loader for the sign up screen.
	 * @param event The event that triggered the screen loading
	 * @return a controller to the new scene
	 */
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
	
	/**
	 * Method responsible for loading a given scene screen.
	 * @param primaryStage the stage that the screen will be loaded to.
	 * @param screenName the name of the screen to be loaded.
	 * @return a controller to the new screen
	 */
	public WindowController loadStage(Stage primaryStage, String screenName)
	{
		xOffset = 0;
		yOffset = 0;
		Parent root;
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/br/ufrn/imd/view/"+screenName+".fxml"));
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
		
		primaryStage.getIcons().add(new Image(getClass().getResource("/resources/images/icon.png").toString()));
		primaryStage.initStyle(StageStyle.TRANSPARENT);
		scene.setFill(Color.TRANSPARENT);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		return (WindowController) fxmlLoader.getController();
	}

	
}
