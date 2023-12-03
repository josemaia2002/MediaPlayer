package br.ufrn.imd.control;

import java.util.ArrayList;

import br.ufrn.imd.model.Music;
import br.ufrn.imd.service.FileManagementService;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class NewPlaylistController extends WindowController{

    @FXML
    private TextField input;
    
    @FXML
    private Button confirmButton;

	public Button getConfirmButton() {
		return confirmButton;
	}

	public TextField getInput() {
		return input;
	}
	
	public void confirm() {}

}
