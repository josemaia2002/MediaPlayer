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
	
	public void logout(ActionEvent event) 
	{
		stage = (Stage) mainPane.getScene().getWindow();
		stage.close();
	}
}
