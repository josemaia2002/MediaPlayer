package br.ufrn.imd.control;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import application.StageNavigator;
import br.ufrn.imd.model.DirectoryDTO;
import br.ufrn.imd.model.Music;
import br.ufrn.imd.model.Playlist;
import br.ufrn.imd.service.AuthService;
import br.ufrn.imd.service.FileManagementService;
import br.ufrn.imd.service.PlayerService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.Separator;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class PlayerController extends WindowController implements Initializable  {
		
		@FXML
	    private Menu AddPlaylistContextMenu;
		
		@FXML
		private Menu AddPlaylistContextMenu1;
		
	    @FXML
	    private Button leftButton;

	    @FXML
	    private Button rightButton;

	    @FXML
	    private ToggleButton playButton;

	    @FXML
	    private ImageView playButtonImage;
	    
	    @FXML
	    private Text muteBtn;
	    
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
	    private TableView<Music> queueTable;
	    
	    @FXML
	    public TableColumn<Music, String> queueColumn;
	    
	    @FXML
	    private ScrollBar progressBar;
	    
	    @FXML
	    private ScrollBar volumeSlider;

	    @FXML
	    private Pane videoPane;
	    
	    private FileManagementService tabContentManager;
	    
	    private PlayerService mediaPlayerManager;
	    
	    @Override
		public void initialize(URL arg0, ResourceBundle arg1) {
	    	tabContentManager = new FileManagementService();
			if(!(AuthService.getCurrentUser().getUserType().contentEquals("vipUser"))) {
				playlistTab.setDisable(true);
				newPlaylistButton.setDisable(true);
				AddPlaylistContextMenu.setDisable(true);
				AddPlaylistContextMenu1.setDisable(true);
			}
			else {
				playlistTab.setDisable(false);
				newPlaylistButton.setDisable(false);
				AddPlaylistContextMenu.setDisable(false);
				AddPlaylistContextMenu1.setDisable(false);
				feedPlaylist();
			}
			feedMusics();
			feedDirectories();
			
			musicTable.setOnDragOver(new EventHandler<DragEvent>() {

		            @Override
		            public void handle(DragEvent event) {
		                if (event.getGestureSource() != mainPane
		                        && event.getDragboard().hasFiles()) {
		                    /* allow for both copying and moving, whatever user chooses */
		                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
		                }
		                event.consume();
		            }
		        });
			
			musicTable.setOnDragDropped(new EventHandler<DragEvent>() {

	            @Override
	            public void handle(DragEvent event) {
	                if (event.getDragboard().hasFiles()) {
	                	for(File f : event.getDragboard().getFiles()) 
	        			{
	        				tabContentManager.addSong(f.getAbsolutePath());
	        			}
	                	feedMusics();
	                }
	                event.consume();
	            }
	        });
			
			directoryTable.setOnDragOver(new EventHandler<DragEvent>() {

	            @Override
	            public void handle(DragEvent event) {
	                if (event.getGestureSource() != mainPane
	                        && event.getDragboard().hasFiles()) {
	                    /* allow for both copying and moving, whatever user chooses */
	                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
	                }
	                event.consume();
	            }
	        });
		
			directoryTable.setOnDragDropped(new EventHandler<DragEvent>() {

            @Override
            public void handle(DragEvent event) {
                if (event.getDragboard().hasFiles()) {
                	for(File f : event.getDragboard().getFiles()) 
        			{
        				tabContentManager.addDirectory(f.getAbsolutePath());
        			}
                	feedDirectories();
                	feedMusics();
                }
                event.consume();
            }
        });
			
			
		}
	    
	    
	    public void feedPlaylist()
	    {
	    	ObservableList<Playlist> playlists = FXCollections.observableArrayList(tabContentManager.loadCurrentUserPlaylists());
	    	
	    	playlistsColumn.setCellValueFactory(new PropertyValueFactory<Playlist, String>("name"));
	    	
	    	AddPlaylistContextMenu.setOnShowing(new EventHandler<Event>() {

				@Override
	            public void handle(Event event) {
					ObservableList<MenuItem> menu = AddPlaylistContextMenu.getItems();
					MenuItem newBTN = menu.get(0);
					MenuItem sep =  menu.get(1);
					menu.clear();
					menu.addAll(newBTN, sep);
					
					for(Playlist p : playlists) 
					{
						MenuItem PlaylistBtn = new MenuItem(p.getName());
						PlaylistBtn.setOnAction(new EventHandler<ActionEvent>() 
						{
							Playlist playlist = p;
							
							@Override
							public void handle(ActionEvent event) 
							{
								addMusicsToPlayList(playlist);
							}
						});
						menu.add(PlaylistBtn);
					}
	            }
	        });
	    	
	    	AddPlaylistContextMenu1.setOnShowing(new EventHandler<Event>() {

				@Override
	            public void handle(Event event) {
					ObservableList<MenuItem> menu = AddPlaylistContextMenu.getItems();
					MenuItem newBTN = menu.get(0);
					MenuItem sep =  menu.get(1);
					menu.clear();
					menu.addAll(newBTN, sep);
					
					for(Playlist p : playlists) 
					{
						MenuItem PlaylistBtn = new MenuItem(p.getName());
						PlaylistBtn.setOnAction(new EventHandler<ActionEvent>() 
						{
							Playlist playlist = p;
							
							@Override
							public void handle(ActionEvent event) 
							{
								addMusicsToPlayList(playlist);
							}
						});
						menu.add(PlaylistBtn);
					}
	            }
	        });
	    	
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
	    
	    public void feedQueue()
	    {
	    	
	    	if(mediaPlayerManager == null)  return;
	    
    		ObservableList<Music> queue = FXCollections.observableArrayList(mediaPlayerManager.getSongQueue());
		    
	    	queueColumn.setCellValueFactory(new PropertyValueFactory<Music, String>("title"));
	    	
	    	queueTable.setItems(queue);

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
				tabContentManager.addSong(f.getAbsolutePath());
			}
	    	feedMusics();
	    }
	    
	    public void addMusicsToPlayList(Playlist p) 
	    {
	    	ArrayList<Music> selection = new ArrayList<Music>();
	    	selection.addAll(musicTable.getSelectionModel().getSelectedItems());
	    	tabContentManager.addMusicsToPlaylist(p, selection);
	    }
	    
	    
	    
	    public void createNewPlaylist(ActionEvent event) 
	    {
	    	
	    	newPlaylistButton.setDisable(true);
	    	AddPlaylistContextMenu.setDisable(true);
	    	AddPlaylistContextMenu1.setDisable(true);
	    	
	    	ArrayList<Music> selection = new ArrayList<Music>();
	    	selection.addAll(musicTable.getSelectionModel().getSelectedItems());
	    	
	    	NewPlaylistController newPlaylist = StageNavigator.getInstance().loadNewPlaylistScreen(event);
	    	newPlaylist.getConfirmButton().setOnMousePressed(new EventHandler<MouseEvent>() {

				@Override
	            public void handle(MouseEvent event) {
					if(newPlaylist.getInput().getText() != null) 
					{
						if(newPlaylist.getInput().getText().length() > 0) 
						{
							tabContentManager.createPlaylist(newPlaylist.getInput().getText(), selection);
						}
					}
					
					newPlaylist.closeWindow(new ActionEvent());
					feedPlaylist();
					
	            }
	        });
	    	
	    	newPlaylistButton.setDisable(false);
	    	AddPlaylistContextMenu.setDisable(false);
	    	AddPlaylistContextMenu1.setDisable(false);
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
	    
	    private ArrayList<Music> getSelectedMusics() 
	    {
	    	ArrayList<Music> selection = new ArrayList<Music>();
	    	selection.addAll(musicTable.getSelectionModel().getSelectedItems());
	    	if(selection.size()> 0 && mediaPlayerManager == null) mediaPlayerManager = new PlayerService(selection.get(0));
	    	return selection;
	    }
	    
	    
	    @FXML
	    void addCurrentSong(ActionEvent event) {
	    	ArrayList<Music> selection = getSelectedMusics();
	    	if(selection.size() == 0) return;
	  
	    	mediaPlayerManager.addCurrentSong(selection.get(0));
	    	selection.remove(0);
	    	for(Music m : selection) mediaPlayerManager.addNextSong(m);
	    	
	    	feedQueue();
	    }

	    @FXML
	    void addLastSong(ActionEvent event) {
	    	ArrayList<Music> selection = getSelectedMusics();
	    	if(selection.size() == 0) return;
	    	
	    	for(Music m : selection) mediaPlayerManager.addLastSong(m);
	    	
	    	feedQueue();
	    }

	    @FXML
	    void addNextSong(ActionEvent event) {
	    	ArrayList<Music> selection = getSelectedMusics();
	    	if(selection.size() == 0) return;
	    	
	    	for(Music m : selection) mediaPlayerManager.addNextSong(m);
	    	
	    	feedQueue();
	    }

	    @FXML
	    void addPlaylistToQueue(ActionEvent event) {
	    	Playlist p = playlistTable.getSelectionModel().getSelectedItem();
	    	if(p == null) return;
	    	mediaPlayerManager.setPlaylist(p);
	    	feedQueue();
	    }
	    
	    @FXML
	    public void removeSongFromQueue() 
	    {
	    	ArrayList<Music> selection = new ArrayList<Music>();
	    	selection.addAll(queueTable.getSelectionModel().getSelectedItems());
	    	if(selection.size() == 0) return;
	    	if(mediaPlayerManager == null) mediaPlayerManager = new PlayerService(selection.get(0));
	    	for(Music m : selection) mediaPlayerManager.removeSong(m);
	    }
	    
	    @FXML
	    void deleteDirectory(ActionEvent event) {
	    	//TODO
	    }

	    @FXML
	    void deletePlaylist(ActionEvent event) {
	    	//TODO
	    }

	    @FXML
	    void playToggle(ActionEvent event) {
	    	//TODO
	    }
	    
	    @FXML
	    void nextMusic(ActionEvent event) {
	    	if(mediaPlayerManager == null) return;
	    	mediaPlayerManager.nextSong();
	    	feedQueue();
	    }
	    
	    @FXML
	    void prevMusic(ActionEvent event) {
	    	if(mediaPlayerManager == null) return;
	    	mediaPlayerManager.prevSong();
	    	feedQueue();
	    }
	    
	    @FXML
	    void setVolume(ScrollEvent event) {
	    	if(mediaPlayerManager == null) return;
	    	double total = volumeSlider.getMax() - volumeSlider.getMin();
	    	mediaPlayerManager.setVolume(volumeSlider.getValue()/total);
	    	volumeSlider.getMin();
	    }
	    
	    @FXML
	    void toggleMute(MouseEvent event) {
	    	if(mediaPlayerManager == null) return;
	    	if(mediaPlayerManager.isMuted()) { mediaPlayerManager.setMute(false); muteBtn.setText("🔊"); }
	    	else {mediaPlayerManager.setMute(true); muteBtn.setText("🔇");}
	    }
	}
