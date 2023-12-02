package application;
	

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;

import br.ufrn.imd.dao.*; // Only for testing
import br.ufrn.imd.model.*;
import br.ufrn.imd.service.FileManagementService;

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
		FileManagementService fms = new FileManagementService();
		ArrayList<Playlist> playlists = fms.loadPlaylists(2);
		
		for(Playlist p : playlists) {
			System.out.println(p.getId());
			System.out.println(p.getName());
			
			for(Music m : p.getSongs()) {
				System.out.println(m.getPath());
			}
		}
		
		// launch(args);
	}
}
