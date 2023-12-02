package application;
	

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;

import br.ufrn.imd.model.*;
import br.ufrn.imd.service.FileManagementService;
import br.ufrn.imd.service.PlayerService;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

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
		launch(args);
	}
}
