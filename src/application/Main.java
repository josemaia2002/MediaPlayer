package application;
	

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;

import br.ufrn.imd.dao.*; // Only for testing
import br.ufrn.imd.model.*;

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
