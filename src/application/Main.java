package application;
	

import javafx.application.Application;
import javafx.stage.Stage;

import br.ufrn.imd.modelo.*; 
import br.ufrn.imd.dao.*;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {

		
		try {
			StageNavigator.getInstance().loadStage(primaryStage, "LoginScreen");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		MusicaDao md = new MusicaDao();
		md.loadSongs();
		md.printSongs();
		
		PlaylistDao pd = new PlaylistDao();
		pd.loadPlaylists();
		pd.printPlaylists();
		// launch(args);
	}
}
