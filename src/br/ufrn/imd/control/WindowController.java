package br.ufrn.imd.control;


import java.io.IOException;

import application.StageNavigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class WindowController {
	
	@FXML
    protected Button exitButton;
	
	@FXML
    protected AnchorPane mainPane;

	protected Stage stage;
	
	protected StageNavigator stageNavi;
	
	protected void loadStage()
	{
		if(stage == null) stage = (Stage) mainPane.getScene().getWindow();
	}
	
	public void closeWindow(ActionEvent event) 
	{
		loadStage();
		stage.close();
	}
	
	public void navigateToLogin(ActionEvent event)
	{
		StageNavigator.getInstance().loadLoginScreen(event);
	}
	
	public void navigateToSignup(ActionEvent event)
	{
		StageNavigator.getInstance().loadSignupScreen(event);
	}
	
	public void navigateToPlayer(ActionEvent event)
	{
		StageNavigator.getInstance().loadPlayerScreen(event);
	}
	
	
}
