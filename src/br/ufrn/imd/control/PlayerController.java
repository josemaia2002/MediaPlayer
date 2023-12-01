package br.ufrn.imd.control;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import br.ufrn.imd.model.Music;
import br.ufrn.imd.model.Playlist;
import br.ufrn.imd.model.UserVip;
import br.ufrn.imd.service.AuthService;
import br.ufrn.imd.service.FileManagementService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class PlayerController extends WindowController implements Initializable {

	    @FXML
	    private Button leftButton;

	    @FXML
	    private Button rightButton;

	    @FXML
	    private ToggleButton playButton;

	    @FXML
	    private ImageView playButtonImage;
	    
	    @FXML
	    private Tab playlistTab;
	    
	    @FXML
	    private TableView<Playlist> playlistTable;
	    
	    @FXML
	    private TableColumn<Playlist, String> playlistsColumn;
	       
	    @FXML
	    private TableView<Music> musicTable;
	    
	    @FXML
	    private TableColumn<Music, String> musicsColumn;
	    
	    @FXML
	    private TableView<String> directoryTable;
	    
	    @FXML
	    private TableColumn<String, String> directoriesColumn;

	    @FXML
	    private ScrollBar progressBar;

	    @FXML
	    private Pane videoPane;
	    
	    private FileManagementService tabContentManager;
	    
	    @Override
		public void initialize(URL arg0, ResourceBundle arg1) {
	    	tabContentManager = new FileManagementService();
			if(!(AuthService.getCurrentUser() instanceof UserVip)) {
				playlistTab.setDisable(true); 
			}
			else {
				playlistTab.setDisable(false);
				feedPlaylist();
			}
			feedMusics();
			feedDirectories();
		}
	    
	    
	    public void feedPlaylist()
	    {
	    	ArrayList<Playlist> playlists = tabContentManager.loadPlaylists((UserVip) AuthService.getCurrentUser());
	    }
	    
	    public void feedMusics()
	    {
	    	
	    	ObservableList<Music> musicnames = FXCollections.observableArrayList(tabContentManager.loadMusics());
	    
	    	musicsColumn.setCellValueFactory(new PropertyValueFactory<Music, String>("title"));
	    	
	    	if(musicTable == null) System.out.println("NULOAAAAAAAAAAAAAAAAAAA");
	    	musicTable.setItems(musicnames);
	    }
	    
	    public void feedDirectories()
	    {
	    	ArrayList<String> directories = tabContentManager.loadDirectories();
	    }

	}
