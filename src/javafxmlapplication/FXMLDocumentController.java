/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmlapplication;

import com.sun.javafx.logging.PlatformLogger.Level;
import java.io.IOException;
import java.lang.System.Logger;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Club;
import model.ClubDAOException;

/**
 *
 * @author jsoler
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private TextField UsernameField;
    @FXML
    private PasswordField PasswordField;
    @FXML
    private Button LoginButton;
    @FXML
    private Button SignupButton;
    @FXML
    private Text errorUsername;
    @FXML
    private Text errorPassword;
    
    //=========================================================
    // you must initialize here all related with the object 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void OnClickedLogin() {
        LoginButton.setOnMouseClicked((MouseEvent event) -> {
        try{
            Club c = Club.getInstance();
            if(c.existsLogin(UsernameField.getText())){
                errorUsername.setText("");
                String password = "";
                try{
                    password = c.getMemberByCredentials(UsernameField.getText(), PasswordField.getText()).getPassword();
                }catch(Exception e){errorPassword.setText("Password is wrong.");}
                if(password.equals(PasswordField.getText())){
                    errorPassword.setText("");
                    JavaFXMLApplication main = new JavaFXMLApplication();
                    main.changeScene("SignupScreen.fxml");
                }
            }
            else{errorUsername.setText("This user is not registered in our system.");}
        }catch(IOException | NumberFormatException | ClubDAOException e){}
        });
    }

    @FXML
    private void OnClickedSignup() {
            SignupButton.setOnMouseClicked((MouseEvent event) -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(FXMLDocumentController.this.getClass().getResource("SignupScreen.fxml"));
                Scene signup = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                stage.setResizable(false);
                stage.setTitle("Sign Up");
                stage.setScene(signup);
                stage.show();
            }catch (IOException e) {
            }
        });
    }

    
}
