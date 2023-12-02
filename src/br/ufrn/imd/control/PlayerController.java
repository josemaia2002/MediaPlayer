package br.ufrn.imd.control;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import br.ufrn.imd.model.DirectoryDTO;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Callback;

public class PlayerController extends WindowController implements Initializable  {

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
	    private Button addDirectoryButton;

	    @FXML
	    private Button addMusicButton;
	    
	    @FXML
	    private Button newPlaylistButton;
	    
	    @FXML
	    private TableView<Playlist> playlistTable;
	    
	    @FXML
	    private TableColumn<Playlist, String> playlistsColumn;
	       
	    @FXML
	    private TableView<Music> musicTable;
	    
	    @FXML
	    public TableColumn<Music, String> musicColumn;
	    
	    @FXML
	    private TableView<DirectoryDTO> directoryTable;
	    
	    @FXML
	    private TableColumn<DirectoryDTO, String> directoriesColumn;

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
				newPlaylistButton.setDisable(true);
			}
			else {
				playlistTab.setDisable(false);
				newPlaylistButton.setDisable(false);
				feedPlaylist();
			}
			feedMusics();
			feedDirectories();
		}
	    
	    
	    public void feedPlaylist()
	    {
	    	ObservableList<Playlist> playlists = FXCollections.observableArrayList(tabContentManager.loadPlaylists((UserVip) AuthService.getCurrentUser()));
		    
	    	playlistsColumn.setCellValueFactory(new PropertyValueFactory<Playlist, String>("name"));
	    	
	    	playlistTable.setItems(playlists);
	    }
	    
	    public void feedMusics()
	    {
	    	
	    	ObservableList<Music> musicnames = FXCollections.observableArrayList(tabContentManager.loadMusics());
	    
	    	musicColumn.setCellValueFactory(new PropertyValueFactory<Music, String>("title"));
	    	
	    	musicTable.setItems(musicnames);
	    
	    }
	    
	    public void feedDirectories()
	    {
	    	ObservableList<DirectoryDTO> directories = FXCollections.observableArrayList(tabContentManager.listDirectoriesDTO());
		    
	    	directoriesColumn.setCellValueFactory(new PropertyValueFactory<DirectoryDTO, String>("path"));
	    	
	    	directoryTable.setItems(directories);
	 
	    }
	    
	    public void addMusic(ActionEvent e) 
	    {
	    	FileChooser musicChooser = new FileChooser();
			musicChooser.setInitialDirectory(new File(getClass().getResource("/resources/data/").getPath()));
			musicChooser.setTitle("Select your musics");
			musicChooser.getExtensionFilters().add(new ExtensionFilter("Select mp3 Files", "*.mp3"));
			List<File> newSongs = musicChooser.showOpenMultipleDialog(new Stage());
			if(newSongs == null) return;
			for(File f : newSongs) 
			{
				tabContentManager.addMusic(f.getAbsolutePath());
			}
	    	feedMusics();
	    }
	    
	    public void createNewPlaylist(ActionEvent e) 
	    {
	    	
	    }
	    
	    public void AddDirectory(ActionEvent e) 
	    {
	    	DirectoryChooser directoryChooser = new DirectoryChooser();
	    	directoryChooser.setInitialDirectory(new File(getClass().getResource("/resources/data/").getPath()));
	    	directoryChooser.setTitle("Select your music directory");
			File newDir = directoryChooser.showDialog(new Stage());
			tabContentManager.addDirectory(newDir.getAbsolutePath());
	    	feedDirectories();
	    }

	}
