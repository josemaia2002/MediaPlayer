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
import br.ufrn.imd.service.PlayerService;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
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
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 * The PlayerController class manages the main functionality of the music player application.
 * It extends WindowController and implements Initializable for JavaFX initialization.
 * This controller handles various actions related to playlists, music, and directories,
 * as well as interactions with the FileManagementService for data management.
 * The class includes methods for feeding data to tables, adding new music, creating playlists,
 * adding directories, and handling player controls.
 *
 * Additionally, the class uses JavaFX components such as TableView, Menu, and Button for user interaction.
 * 
 * @author Davi Matias
 * @author Jose Maia
 * @version 1.0
 * @since 3/12/2023
 */
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
	    private Text textView;
	    
	    @FXML
	    private Text artistView;
	    
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
	    
	    /**
	     * Initializes the PlayerController by setting up the FileManagementService and JavaFX components.
	     * It also customizes the user interface based on the user's type (e.g., vipUser).
	     *
	     * @param arg0 URL location not used in this implementation.
	     * @param arg1 ResourceBundle not used in this implementation.
	     */
	    @Override
		public void initialize(URL arg0, ResourceBundle arg1) {
	    	progressBar.valueProperty().addListener(new InvalidationListener() {

				@Override
				public void invalidated(Observable arg0) {
					if(mediaPlayerManager == null) return;
					if(progressBar.getValue() > 99.9) nextMusic(new ActionEvent());
				}
	    		
	    	});
	    	
	    	volumeSlider.setValue(100);
	    	volumeSlider.valueProperty().addListener(new InvalidationListener()
	    	{

				@Override
				public void invalidated(javafx.beans.Observable arg0) {
					if(mediaPlayerManager == null) return;
					mediaPlayerManager.setVolume(volumeSlider.getValue()/100);
					
				}
	    		
	    	});
	    	
	    	progressBar.setValue(0);
	    	
	    	
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
			
			progressBar.setOnDragDetected(new EventHandler<MouseEvent>()
	    	{
				@Override
				public void handle(MouseEvent arg0) {
					if(mediaPlayerManager == null)  return;
					mediaPlayerManager.pause();
				}
	    	});
			
			progressBar.setOnMouseReleased(new EventHandler<MouseEvent>()
	    	{
				@Override
				public void handle(MouseEvent arg0) {
					if(mediaPlayerManager == null)  return;
					mediaPlayerManager.seek(progressBar.getValue()/100);
					mediaPlayerManager.play();
				}
	    	});
			
		}
	    
	    /**
	     * Feeds the playlist data into the playlist table and updates context menus, allowing users to interact with playlists.
	     */
	    public void feedPlaylist()
	    {
	    	ObservableList<Playlist> playlists = FXCollections.observableArrayList(tabContentManager.loadCurrentUserPlaylists());
	    	
	    	playlistsColumn.setCellValueFactory(new PropertyValueFactory<Playlist, String>("name"));
	    	
	    	playlistTable.setItems(playlists);
	    	
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
					ObservableList<MenuItem> menu = AddPlaylistContextMenu1.getItems();
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
	    	
	    	
		}
	    	
	    /**
	     * Feeds the music data into the music table, displaying available songs to the user.
	     */
	    public void feedMusics()
	    {
	    		
	    	ObservableList<Music> musicnames = FXCollections.observableArrayList(tabContentManager.loadMusics());
	    
	    	musicColumn.setCellValueFactory(new PropertyValueFactory<Music, String>("title"));
	    	
	    	musicTable.setItems(musicnames);
	    
	    }
	    
	    /**
	     * Feeds the directory data into the directory table, showing directories containing music files.
	     */
	    public void feedDirectories()
	    {
	    	ObservableList<DirectoryDTO> directories = FXCollections.observableArrayList(tabContentManager.listDirectoriesDTO());
		    
	    	directoriesColumn.setCellValueFactory(new PropertyValueFactory<DirectoryDTO, String>("path"));
	    	
	    	directoryTable.setItems(directories);
	 
	    }
	    
	    /**
	     * Feeds the queue data into the queue table, displaying queued songs to the user.
	     */
	    public void feedQueue()
	    {
	    	
	    	if(mediaPlayerManager == null)  return;
	    
    		ObservableList<Music> queue = FXCollections.observableArrayList(mediaPlayerManager.getSongQueue());

	    	queueColumn.setCellValueFactory(new PropertyValueFactory<Music, String>("title"));
	    	
	    	queueTable.setItems(queue);
	    	
	    	updateSong();

	    }
	    
	    /**
	     * Adds a new music file to the application based on user selection.
	     *
	     * @param event The ActionEvent triggered by the add music button.
	     */
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
	    
	    /**
	     * Adds selected music to a specified playlist.
	     *
	     * @param p The playlist to which the selected music should be added.
	     */
	    public void addMusicsToPlayList(Playlist p) 
	    {
	    	ArrayList<Music> selection = new ArrayList<Music>();
	    	selection.addAll(musicTable.getSelectionModel().getSelectedItems());
	    	tabContentManager.addMusicsToPlaylist(p, selection);
	    }
	    

	    /**
	     * Initiates the process of creating a new playlist, allowing the user to name and customize it.
	     *
	     * @param event The ActionEvent triggered by the new playlist button.
	     */
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
	    
	    /**
	     * Adds a directory containing music files to the application.
	     *
	     * @param event The ActionEvent triggered by the add directory button.
	     */
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
	    
	    /**
	     * Gets the musics selected by the user at the music table.
	     *
	     * @return ArrayList<Music> The selected musics.
	     */
	    private ArrayList<Music> getSelectedMusics() 
	    {
	    	ArrayList<Music> selection = new ArrayList<Music>();
	    	selection.addAll(musicTable.getSelectionModel().getSelectedItems());
	    	if(selection.size()> 0 && mediaPlayerManager == null) mediaPlayerManager = new PlayerService(selection.get(0), progressBar);
	    	mediaPlayerManager.getStatusProperty().addListener(new InvalidationListener()
	    	{
				@Override
				public void invalidated(Observable observable) {		
					updatePlayButton(mediaPlayerManager.getStatusProperty().getValue());
				}
				
	    	});
	    	return selection;
	    }

	    /**
	     * Sets the volume of the music player.
	     *
	     * @param event The ActionEvent triggered by the set volume button.
	     */
	    @FXML
	    void addCurrentSong(ActionEvent event) {
	    	ArrayList<Music> selection = getSelectedMusics();
	    	if(selection.size() == 0) return;
	  
	    	mediaPlayerManager.addCurrentSong(selection.get(0));
	    	selection.remove(0);
	    	for(Music m : selection) mediaPlayerManager.addNextSong(m);
	    	mediaPlayerManager.play();
	    	feedQueue();
	    	
	    	updateSong();
	    	
	    }

	    /**
	     * Initiates the playback of selected music.
	     *
	     * @param event The ActionEvent triggered by the play music button.
	     */
	    @FXML
	    void addLastSong(ActionEvent event) {
	    	ArrayList<Music> selection = getSelectedMusics();
	    	if(selection.size() == 0) return;
	    	
	    	for(Music m : selection) mediaPlayerManager.addLastSong(m);
	    	
	    	feedQueue();
	    }
	    
	    /**
	     * Add the next song of the queue.
	     *
	     * @param event The ActionEvent triggered by the play music button.
	     */
	    @FXML
	    void addNextSong(ActionEvent event) {
	    	ArrayList<Music> selection = getSelectedMusics();
	    	if(selection.size() == 0) return;
	    	for(Music m : selection) mediaPlayerManager.addNextSong(m);
	    	feedQueue();
	    	
	    }

	    /**
	     * Add a playlist to the queue.
	     *
	     * @param event The ActionEvent triggered by the play music button.
	     */
	    @FXML
	    public void addPlaylistToQueue(ActionEvent event) {
	    	Playlist p = playlistTable.getSelectionModel().getSelectedItem();
	    	if(p == null) return;
	    	mediaPlayerManager.addPlaylistToQueue(p);
	    	feedQueue();
	    	
	    	updateSong();
	    }
	    
	    /**
	     * Method to remove a songs from the queue.
	     */
	    @FXML
	    public void removeSongFromQueue() 
	    {
	    	ArrayList<Music> selection = new ArrayList<Music>();
	    	selection.addAll(queueTable.getSelectionModel().getSelectedItems());
	    	if(selection.size() == 0) return;
	    	if(mediaPlayerManager == null) mediaPlayerManager = new PlayerService(selection.get(0), progressBar);
	    	for(Music m : selection) mediaPlayerManager.removeSong(m);
	    	
	    	feedQueue();
	    	
	    }
	    
	    /**
	     * Deletes selected directory from the application.
	     *
	     * @param event The ActionEvent triggered by the delete directory button.
	     */
	    @FXML
	    public void deleteDirectory(ActionEvent event) {
	    	ArrayList<DirectoryDTO> selection = new ArrayList<DirectoryDTO>();
	    	selection.addAll(directoryTable.getSelectionModel().getSelectedItems());
	    	
	    	for(DirectoryDTO d : selection) {
	    		tabContentManager.removeDirectory(d.getPath());
	    	}
	    	
	    	feedDirectories();
	    	feedMusics();
	    }
	    
		/**
	     * Deletes selected music from the application.
	     *
	     * @param event The ActionEvent triggered by the delete music button.
	     */
	    @FXML
	    public void deleteMusic(ActionEvent event) {
	    	ArrayList<Music> selection = new ArrayList<Music>();
	    	selection.addAll(musicTable.getSelectionModel().getSelectedItems());
	    	tabContentManager.removeAllSongs(selection);
	    	feedMusics();
	    }
	    
	    /**
	     * Deletes selected playlist from the application.
	     *
	     * @param event The ActionEvent triggered by the delete playlist button.
	     */
	    @FXML
	    public void deletePlaylist(ActionEvent event) {
	    	ArrayList<Playlist> selection = new ArrayList<Playlist>();
	    	selection.addAll(playlistTable.getSelectionModel().getSelectedItems());
	    	tabContentManager.removeAllPlaylists(selection);
	    	feedPlaylist();
	    }

	    /**
	     * Toggle the state of the mediaPlayerManager at the press of the play/pause button.
	     *
	     * @param event The ActionEvent triggered by the button.
	     */
	    @FXML
	    public void playToggle(ActionEvent event) {
	    	if(mediaPlayerManager == null) return;
	    	
	    	if(mediaPlayerManager.isPlaying()) 
	    	{ 
	    		mediaPlayerManager.pause(); 
	    	}
	    	else 
	    	{
	    		mediaPlayerManager.play();
	    		
	    	}
	    	updateSong();
	    	
	    }
	    
	    
	    /**
	     * Toggles the image of the play/pause button based on the media player status.
	     */
	    private void updatePlayButton(Status s) {
	    	boolean isPlaying = mediaPlayerManager.getStatusProperty().getValue().equals(Status.PLAYING);
	    	playButtonImage.setImage(new Image(getClass().getResource("/resources/images/" + (isPlaying?"pause":"play") + ".png").toString()));
	    }
	    
	    /**
	     * Method to skip to the next song.
	     *
	     * @param event The ActionEvent triggered by the next music button.
	     */
	    @FXML
	    private void nextMusic(ActionEvent event) {
	    	if(mediaPlayerManager == null) return;
	    	mediaPlayerManager.nextSong();
	    	feedQueue();
	    	updateSong();
	    }
	    
	    /**
	     * Method to go back to the previous song.
	     *
	     * @param event The ActionEvent triggered by the previous music button.
	     */
	    @FXML
	    private void prevMusic(ActionEvent event) {
	    	if(mediaPlayerManager == null) return;
	    	mediaPlayerManager.prevSong();
	    	feedQueue();
	    	updateSong();
	    }
	    
	    /**
	     * Method to mute the media player or unmute it if it's
	     * already muted.
	     *
	     * @param event The ActionEvent triggered by the toggle mute button.
	     */
	    @FXML
	    private void toggleMute(MouseEvent event) {
	    	if(mediaPlayerManager == null) return;
	    	if(mediaPlayerManager.isMuted()) { mediaPlayerManager.setMute(false); muteBtn.setText("🔊"); }
	    	else {mediaPlayerManager.setMute(true); muteBtn.setText("🔇");}
	    }
	    
	    /**
	     * Method to update the texts fields that show the current song data.
	     */
	    private void updateSong() 
	    {
	    	Music m = mediaPlayerManager.getCurrentSong();
	    	if(m == null) {textView.setText(""); artistView.setText(""); return;}
	    	textView.setText("Now Playing: \n"  + m.getTitle());
	    	artistView.setText(m.getAuthor());
	    }
	}
