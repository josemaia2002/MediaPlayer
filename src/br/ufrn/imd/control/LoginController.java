package br.ufrn.imd.control;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import application.StageNavigator;
import br.ufrn.imd.service.AuthService;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class LoginController extends WindowController implements Initializable{
	
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
    
    @FXML
    private Text credentialResponse;
    
    private AuthService service;
    
    // Controll Methods
    
    
    public void tryLogin(ActionEvent event) 
    {
    	class LoginThread implements Runnable
		{
			public void run() 
			{
				try {
					TimeUnit.MILLISECONDS.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Platform.runLater(new Runnable() {
				    @Override 
				    public void run() {
				    	navigateToPlayer(event);
				    }
				});
	    		
			}
		}
    	
    	String username = usernameInput.getText();
    	String password = passwordInput.getText();
    	if(service.loginCredentials(username, password)) 
    	{
    		credentialResponse.setFill(Color.GREEN);
    		credentialResponse.setVisible(true);
    		credentialResponse.setText("Loggin in!");
    		Thread t = new Thread(new LoginThread());
    		t.start();
    		return;
    	}
    	
    	credentialResponse.setVisible(true);
    	playUserNotFoundAnimation();
    }
    
    
    // Cosmetic methods
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		playBackgroundAnimation();
		playLogoAnimation();
		service = new AuthService();
	}
	
	private void playUserNotFoundAnimation() 
	{
		RotateTransition rotate = new RotateTransition();
		rotate.setNode(credentialResponse);
		rotate.setDuration(Duration.millis(30));
		rotate.setCycleCount(2);
		rotate.setFromAngle(-10);
		rotate.setToAngle(10);
		rotate.setInterpolator(Interpolator.LINEAR);
		rotate.setAutoReverse(true);
		
		RotateTransition straighten = new RotateTransition();
		straighten.setNode(credentialResponse);
		straighten.setDelay(Duration.millis(60));
		straighten.setDuration(Duration.millis(15));
		straighten.setCycleCount(1);
		straighten.setFromAngle(10);
		straighten.setToAngle(0);
		straighten.setInterpolator(Interpolator.LINEAR);
		straighten.setAutoReverse(false);
		
		rotate.play();
		straighten.play();
	}
    
	private void playLogoAnimation() 
	{
		Duration duration = Duration.millis(180);
		int cycleCount = Transition.INDEFINITE;
		ScaleTransition scale = new ScaleTransition();
		scale.setNode(pulseBorder);
		scale.setDuration(duration);
		scale.setCycleCount(cycleCount);
		scale.setFromX(0.85);
		scale.setFromY(0.85);
		scale.setFromZ(0.85);
		scale.setToX(1.0);
		scale.setToY(1.0);
		scale.setToZ(1.0);
		scale.setAutoReverse(true);
		scale.setInterpolator(Interpolator.EASE_OUT);
		

		scale.play();
	}
	
	private void playBackgroundAnimation() 
	{
		Duration duration = Duration.millis(6000);
		int cycleCount = Transition.INDEFINITE;
		ScaleTransition scale = new ScaleTransition();
		scale.setNode(pulseImage);
		scale.setDuration(duration);
		scale.setCycleCount(cycleCount);
		scale.setByX(10);
		scale.setByY(10);
		scale.setByZ(10);
		scale.setAutoReverse(true);
		
		TranslateTransition translate = new TranslateTransition();
		translate.setNode(pulseImage);
		translate.setDuration(duration);
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
