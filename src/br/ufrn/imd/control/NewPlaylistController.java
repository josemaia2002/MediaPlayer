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

/**
 * The NewPlaylistController class manages the logic for creating a new playlist.
 * It extends WindowController, providing control over the corresponding FXML window.
 * This controller handles user input for the new playlist's name, confirmation actions,
 * and interacts with the FileManagementService for playlist creation.
 *
 * The class includes methods to access the confirm button and input field.
 * Additionally, the 'confirm' method is intended to be associated with the confirmation
 * button's action, allowing customization of the confirmation process.
 * 
 * @author Davi Matias
 * @author Jose Maia
 * @version 1.0
 * @since 3/12/2023
 */
public class NewPlaylistController extends WindowController{

    @FXML
    private TextField input;
    
    @FXML
    private Button confirmButton;
    
    /**
     * Retrieves the confirmation button associated with creating the new playlist.
     *
     * @return The JavaFX Button representing the confirmation action.
     */
	public Button getConfirmButton() {
		return confirmButton;
	}

	/**
     * Retrieves the input field where the user enters the new playlist's name.
     *
     * @return The JavaFX TextField for entering the new playlist's name.
     */
	public TextField getInput() {
		return input;
	}
	
	/**
     * Placeholder method to be associated with the confirmation button's action.
     * Customization of this method allows for specific actions when confirming
     * the creation of a new playlist.
     */
	public void confirm() {}

}
