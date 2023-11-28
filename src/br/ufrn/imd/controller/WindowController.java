package br.ufrn.imd.controller;


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
	
	protected void loadStage()
	{
		if(stage == null) stage = (Stage) mainPane.getScene().getWindow();
	}
	
	public void closeWindow(ActionEvent event) 
	{
		loadStage();
		stage.close();
	}
}
