package br.ufrn.imd.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class loginMenuController extends windowController implements Initializable{
	
    @FXML
    private Button loginButton;
    
    @FXML
    private ImageView pulseImage;
    
    @FXML
    private ImageView pulseBorder;

    @FXML
    private PasswordField passwordInput;

    @FXML
    private Button signupButton;

    @FXML
    private TextField usernameInput;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		playBackgroundAnimation();
	}
    
	public void playLogoAnimation() 
	{
		Duration duration = Duration.millis(100);
		Duration delay = Duration.millis(400);
		int cycleCount = Transition.INDEFINITE;
		ScaleTransition scale = new ScaleTransition();
		scale.setNode(pulseBorder);
		scale.setDuration(duration);
		scale.setDelay(delay);
		scale.setCycleCount(cycleCount);
		scale.setFromX(1);
		scale.setFromY(1);
		scale.setFromZ(1);
		
		scale.setByX(1.2);
		scale.setByY(1.2);
		scale.setByZ(1.2);
		scale.setAutoReverse(true);
		scale.setInterpolator(Interpolator.DISCRETE);
		
		
		scale.play();
	}
	
	public void playBackgroundAnimation() 
	{
		Duration duration = Duration.millis(6000);
		Duration delay = Duration.millis(600);
		int cycleCount = Transition.INDEFINITE;
		ScaleTransition scale = new ScaleTransition();
		scale.setNode(pulseImage);
		scale.setDuration(duration);
		scale.setDelay(delay);
		scale.setCycleCount(cycleCount);
		scale.setByX(10);
		scale.setByY(10);
		scale.setByZ(10);
		scale.setAutoReverse(true);
		
		TranslateTransition translate = new TranslateTransition();
		translate.setNode(pulseImage);
		translate.setDuration(duration);
		translate.setDelay(delay);
		translate.setCycleCount(cycleCount);
		translate.setByY(250);
		translate.setAutoReverse(true);
		
		
		FadeTransition fade = new FadeTransition();
		fade.setNode(pulseImage);
		fade.setDuration(Duration.millis(6600));
		fade.setCycleCount(cycleCount);
		fade.setFromValue(0.2);
		fade.setToValue(0.0);
		fade.setInterpolator(Interpolator.EASE_BOTH);
		fade.setAutoReverse(true);
		
		RotateTransition rotate = new RotateTransition();
		rotate.setNode(pulseImage);
		rotate.setDuration(Duration.millis(6600));
		rotate.setCycleCount(cycleCount);
		rotate.setFromAngle(0);
		rotate.setToAngle(720);
		rotate.setInterpolator(Interpolator.LINEAR);
		rotate.setAutoReverse(false);
		
		rotate.play();
		translate.play();
		fade.play();
		scale.play();
		
		
		
	}
}
