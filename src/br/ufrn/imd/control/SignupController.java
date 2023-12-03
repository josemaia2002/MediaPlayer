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

/**
 * The SignupController class manages the logic and interactions for the signup scene.
 * It extends WindowController and implements Initializable for JavaFX initialization.
 * This controller handles user signup, input validation, and navigation to the login scene upon successful signup.
 * The class includes methods for handling user input, signup attempts, and cosmetic animations.
 *
 * Additionally, the class uses JavaFX components such as ImageView, TextField, PasswordField,
 * RadioButton, Text, and Buttons for user interaction.
 * 
 * @author Davi Matias
 * @author Jose Maia
 * @version 1.0
 * @since 3/12/2023
 */
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
    
    /**
     * Handles the event when the VIP radio button is selected or deselected.
     * Updates the display text based on the selection.
     *
     * @param event The ActionEvent triggered by selecting/deselecting the VIP radio button.
     */
    @FXML
    void selectVip(ActionEvent event) {
    	if(userTypeRadio.isSelected())
    	{
    		userTypeRadio.setText("👑 Vip User");
    		return;
    	}
    	userTypeRadio.setText("Default User");
    	
    	
    }
    
    /**
     * Attempts to sign up the user based on the provided credentials.
     * If successful, initiates a signup animation and navigates to the login scene.
     * If unsuccessful, plays an animation indicating signup error.
     *
     * @param event The ActionEvent triggered by the signup button.
     */
    @FXML
    void trySignup(ActionEvent event) {
    	class LoginThread implements Runnable
		{
			public void run() 
			{
				try {
					TimeUnit.MILLISECONDS.sleep(750);
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
    	playSignupErrorAnimation();
    }

    /**
     * Initializes the SignupController by setting up the AuthService and playing background animations.
     *
     * @param arg0 URL location not used in this implementation.
     * @param arg1 ResourceBundle not used in this implementation.
     */
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		playBackgroundAnimation();
		service = new AuthService();
	}
    
    //Cosmetic Methods
    
    /**
     * Plays the signup error animation, indicating an issue with the signup attempt.
     */
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
	
	/**
     * Plays the background animation for the signup scene.
     */
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
