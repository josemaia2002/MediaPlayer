package br.ufrn.imd.control;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import application.StageNavigator;
import br.ufrn.imd.model.DirectoryDTO;
import br.ufrn.imd.model.Music;
import br.ufrn.imd.model.Playlist;
import br.ufrn.imd.service.AuthService;
import br.ufrn.imd.service.FileManagementService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

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
			if(!(AuthService.getCurrentUser().getUserType().contentEquals("vipUser"))) {
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
	    	ObservableList<Playlist> playlists = FXCollections.observableArrayList(tabContentManager.loadPlaylists(AuthService.getCurrentUser()));
		    
	    	for(Playlist p : playlists) {System.out.println(p);}
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
	    
	    public void addMusic(ActionEvent event) 
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
	    
	    public void createNewPlaylist(ActionEvent event) 
	    {
	    	newPlaylistButton.setDisable(true);
	    	NewPlaylistController newPlaylist = StageNavigator.getInstance().loadNewPlaylistScreen(event);
	    	
	    	newPlaylist.getConfirmButton().setOnMousePressed(new EventHandler<MouseEvent>() {

				@Override
	            public void handle(MouseEvent event) {
					if(newPlaylist.getInput().getText() != null) 
					{
						if(newPlaylist.getInput().getText().length() > 0) 
						{
							tabContentManager.createPlaylist(newPlaylist.getInput().getText(), new ArrayList<Music>());
						}
					}
					newPlaylist.closeWindow(new ActionEvent());
					feedPlaylist();
					newPlaylistButton.setDisable(false);
	            }
	        }
	    	);
	    }
	    
	    public void AddDirectory(ActionEvent event) 
	    {
	    	DirectoryChooser directoryChooser = new DirectoryChooser();
	    	directoryChooser.setInitialDirectory(new File(getClass().getResource("/resources/data/").getPath()));
	    	directoryChooser.setTitle("Select your music directory");
			File newDir = directoryChooser.showDialog(new Stage());
			if(newDir == null) return;
			tabContentManager.addDirectory(newDir.getAbsolutePath());
	    	feedDirectories();
	    	feedMusics();
	    }

	}
