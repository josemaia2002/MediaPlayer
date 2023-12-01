package br.ufrn.imd.control;

import java.net.URL;
import java.util.ResourceBundle;

import br.ufrn.imd.modelo.UserVip;
import br.ufrn.imd.service.AuthService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class PlayerController extends WindowController implements Initializable {

    @FXML
    private Button leftButton;

    @FXML
    private TableColumn<?, String> musicColumn;

    @FXML
    private ToggleButton playButton;

    @FXML
    private ImageView playButtonImage;

    @FXML
    private TableColumn<?, String> playListColumn;

    @FXML
    private Tab playlistTab;

    @FXML
    private ScrollBar progressBar;

    @FXML
    private Button rightButton;

    @FXML
    private Pane videoPane;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if(AuthService.getCurrentUser() instanceof UserVip) playlistTab.setDisable(false);
		else playlistTab.setDisable(true);	
	}

}
