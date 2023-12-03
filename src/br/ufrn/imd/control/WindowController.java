package br.ufrn.imd.control;


import java.io.IOException;

import application.StageNavigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The WindowController class serves as the base controller for handling common window-related actions
 * across different scenes in the application. It includes methods for closing the window and
 * navigating to specific scenes such as login, signup, and player.
 *
 * This class is meant to be extended by other controllers to reuse common functionality.
 * It contains references to JavaFX components like Button and AnchorPane and provides methods
 * for managing the associated stage and navigation.
 *
 * Additionally, the class includes methods for loading the stage, closing the window, and navigating
 * to different scenes using the StageNavigator utility.
 * 
 * @author Davi Matias
 * @author Jose Maia
 * @version 1.0
 * @since 3/12/2023
 */
public class WindowController {
	
	@FXML
    protected Button exitButton;
	
	@FXML
    protected AnchorPane mainPane;

	protected Stage stage;
	
	protected StageNavigator stageNavi;
	
	/**
     * Loads the current stage, ensuring that the stage reference is initialized.
     */
	protected void loadStage()
	{
		if(stage == null) stage = (Stage) mainPane.getScene().getWindow();
	}
	
	/**
     * Closes the associated window or stage.
     *
     * @param event The ActionEvent triggered by the exit/close button.
     */
	public void closeWindow(ActionEvent event) 
	{
		loadStage();
		stage.close();
	}
	
	/**
     * Toggles the maximization of the associated window or stage.
     *
     * @param event The ActionEvent triggered by the maximize button.
     */
	public void maximizeWindow(ActionEvent event) 
	{
		loadStage();
		if(stage.isMaximized()) 
		{
			stage.setMaximized(false);
			return;
		}
		stage.setMaximized(true);
		
	}
	
	/**
     * Navigates to the login scene using the StageNavigator utility.
     *
     * @param event The ActionEvent triggered by a navigation button or action.
     */
	public void navigateToLogin(ActionEvent event)
	{
		StageNavigator.getInstance().loadLoginScreen(event);
	}
	
	/**
     * Navigates to the signup scene using the StageNavigator utility.
     *
     * @param event The ActionEvent triggered by a navigation button or action.
     */
	public void navigateToSignup(ActionEvent event)
	{
		StageNavigator.getInstance().loadSignupScreen(event);
	}
	
	/**
     * Navigates to the player scene using the StageNavigator utility.
     *
     * @param event The ActionEvent triggered by a navigation button or action.
     */
	public void navigateToPlayer(ActionEvent event)
	{
		StageNavigator.getInstance().loadPlayerScreen(event);
	}
	
	
}
