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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class SignupController extends WindowController implements Initializable{
	
    @FXML
    private ImageView pulseImage;
	
	@FXML
	private TextField usernameInput;
	
	@FXML
    private TextField emailInput;
    
    @FXML
    private PasswordField passwordInput;
    
    @FXML
    private PasswordField confirmPasswordInput;
    
    @FXML
    private RadioButton userTypeRadio;

    @FXML
    private Text credentialResponse;
    
    @FXML
    private Button signupButton;
    
    @FXML
    private Button loginPage;
    
    private AuthService service;
    
    @FXML
    void selectVip(ActionEvent event) {
    	if(userTypeRadio.isSelected())
    	{
    		userTypeRadio.setText("👑 Vip User");
    		return;
    	}
    	userTypeRadio.setText("Default User");
    	
    	
    }
    
    @FXML
    void trySignup(ActionEvent event) {
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
				    	navigateToLogin(event);
				    }
				});
	    		
			}
		}
    	
    	String username = usernameInput.getText();
    	String email = emailInput.getText();
    	String p1 = passwordInput.getText();
    	String p2 = confirmPasswordInput.getText();
    	String userType = userTypeRadio.isSelected() ? "vipUser" : "defaultUser";
    	String response = service.signupCredentials(username, userType, email, p1, p2);
    	credentialResponse.setText(response);
    	if(response.equals("Singed Up!")) 
    	{
    		credentialResponse.setFill(Color.GREEN);
    		credentialResponse.setVisible(true);
    		credentialResponse.setText(response);
    		Thread t = new Thread(new LoginThread());
    		t.start();
    		return;
    	}
    	
    	credentialResponse.setVisible(true);
    	//playSignupErrorAnimation();
    }

    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// playBackgroundAnimation();
		service = new AuthService();
	}
	
    //Cosmetic Methods
	private void playSignupErrorAnimation() 
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
