package application;
	

import javafx.application.Application;
import javafx.stage.Stage;

import br.ufrn.imd.modelo.*; // Only for testing

import java.util.ArrayList;

import br.ufrn.imd.dao.*; // Only for testing

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
