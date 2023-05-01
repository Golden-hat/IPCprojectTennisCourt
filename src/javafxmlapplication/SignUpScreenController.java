/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author yassi
 */
public class SignUpScreenController implements Initializable {

    @FXML
    private Button doneButton;
    @FXML
    private TextField nameField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField userField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField cardField;
    @FXML
    private Text errorUsername;
    @FXML
    private Text errorPhone;
    @FXML
    private Text errorPassword;
    @FXML
    private Text errorCardNumber;
    @FXML
    private Text signUpSuccessful;
    @FXML
    private TextField familyNameField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    public static boolean checkNumber(String s){
      if (s == null) return false;
      int len = s.length();
      for (int i = 0; i < len; i++){
         // checks whether the character is not a letter
         // if it is not a letter ,it will return false
         String t = Character.toString(s.charAt(i));
         if (!"1234567890".contains(t)){
            return false;
         }
      }
      return true;
    }
    
    @FXML
    private void onClickDoneLogin() {
       doneButton.setOnMouseClicked((MouseEvent event) -> {
           if(!checkNumber(phoneField.getText())||phoneField.getText().equals("")){errorPhone.setText("Non-numeric character introduced.");}
           else{errorPhone.setText("");}
           if(passwordField.getText().length() < 6){errorPassword.setText("Must be at least 6 char. long.");}
           else{errorPassword.setText("");}
           if(!checkNumber(cardField.getText()) || cardField.getText().length() != 16){errorCardNumber.setText("Incorrect input.");}
           else{errorCardNumber.setText("");}
           //if(existsLogin(userField.getText()))
           if(errorCardNumber.getText().equals("")&& errorPhone.getText().equals("")&& errorPassword.getText().equals("")
           && !nameField.getText().equals("")&& !familyNameField.getText().equals(""))
           {signUpSuccessful.setText("You signed up successfully!");}
           else{signUpSuccessful.setText("Solve errors before Signing Up.");}
        });
    }
    
}
