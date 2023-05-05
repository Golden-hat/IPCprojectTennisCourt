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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import static javafxmlapplication.JavaFXMLApplication.*;

/**
 * FXML Controller class
 *
 * @author yassi
 */
public class MainMenuController implements Initializable {

    @FXML
    private Button boton1;
    @FXML
    private ImageView userPictureBanner;
    @FXML
    private Text usernameFieldBanner;
    @FXML
    private Text nameSurnameFieldBanner1;
    @FXML
    private Button signOutButton;
    @FXML
    private Button checkInfoButton;
    @FXML
    private ListView<?> reservationList;
    @FXML
    private DatePicker dateSelector;
    @FXML
    private Text courtSelectedPrompt;
    @FXML
    private Slider sessionLength;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onClickboton1(ActionEvent event) {
    }

    @FXML
    private void onSignOutButtonClicked() {
        signOutButton.setOnMouseClicked((MouseEvent event) -> {
            try {
                mainScreen.close();
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(MainMenuController.this.getClass().getResource("FXMLDocument.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                login = new Stage();
                login.setResizable(true);
                login.setTitle("Login");
                login.setScene(scene);
                login.show();
            }catch (IOException e) {
            }
        });
    }

    @FXML
    private void onCheckInfoButtonClicked() {
        checkInfoButton.setOnMouseClicked((MouseEvent event) -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(MainMenuController.this.getClass().getResource("SignUpScreen.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                signup = new Stage();
                signup.setResizable(true);
                signup.setTitle("Login");
                signup.setScene(scene);
                signup.show();
            }catch (IOException e) {
            }
        });
    }

    @FXML
    private void onDateSelected(ActionEvent event) {
    }
    
}
