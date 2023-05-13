/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import static javafxmlapplication.JavaFXMLApplication.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Club;
import model.ClubDAOException;
import model.Court;

/**
 * FXML Controller class
 *
 * @author yassi
 */
public class MainMenuController implements Initializable {

    private Button boton1;
    @FXML
    private ImageView userPictureBanner;
    @FXML
    private Text usernameFieldBanner;
    @FXML
    private Button signOutButton;
    @FXML
    private Button checkInfoButton;
    @FXML
    private DatePicker dateSelector;
    @FXML
    private Text courtSelectedPrompt;
    @FXML
    private Slider sessionLength;
    @FXML
    private Text courtSelectedPrompt1;
    @FXML
    private Text nameSurnameFieldBanner;
    @FXML
    private Button previousDayB;
    @FXML
    private Button dayAfterB;
    @FXML
    private Button courtsAvailable;
    @FXML
    private Button courtsReserved;
    @FXML
    private Button reservationB;
    @FXML
    private Button NorthCourt;
    @FXML
    private Button SouthCourt;
    @FXML
    private Button WestCourt;
    @FXML
    private Button EastCourt;
    @FXML
    private Button PondCourt;
    @FXML
    private Button MillCourt;
    @FXML
    private TableView<Court> TableList;

    ObservableList<Court> courts= null;
    @FXML
    private TableColumn<Court, String> firstColumn;
    @FXML
    private TableColumn<Court, String> secondColumn;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        usernameFieldBanner.setText("@"+memberLoggedIn.getNickName());
        nameSurnameFieldBanner.setText(memberLoggedIn.getName()+" "+memberLoggedIn.getSurname());
        userPictureBanner.setImage(memberLoggedIn.getImage());
        /*
        ArrayList<String> courtsdata= new ArrayList<String>();
        courtsdata.add(new String("buen"));
        courtsdata.add(new String("hol2"));
        courts=FXCollections.observableArrayList(courtsdata);
        TableList.setItems(courts);
        firstColumn.setCellValueFactory(new PropertyValueFactory<String, String>("hola"));         
        secondColumn.setCellValueFactory(new PropertyValueFactory<String, String>("User"));
        */
        
        
        
        try {
            Club club= Club.getInstance();
            ArrayList<Court> courtsdata= (ArrayList<Court>) club.getCourts();
            courts= FXCollections.observableList(courtsdata);
            TableList.setItems(courts);
            firstColumn.setCellValueFactory(new PropertyValueFactory<Court, String>("Court"));         
            secondColumn.setCellValueFactory(new PropertyValueFactory<Court, String>("User"));
        } catch (ClubDAOException ex) {
            Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);

        }
        
    }    

    @FXML
    private void onClickboton1() {
        boton1.setOnMouseClicked((MouseEvent event) -> {
            initialize(null,null);
        });
    }

    @FXML
    private void onSignOutButtonClicked() {
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
    }

    @FXML
    private void onCheckInfoButtonClicked() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(MainMenuController.this.getClass().getResource("CheckData.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            checkData = new Stage();
            checkData.setResizable(true);
            checkData.setTitle("Checking Data");
            checkData.setScene(scene);
            checkData.initModality(Modality.APPLICATION_MODAL);
            checkData.show();
        }catch (IOException e) {
        }
}

    @FXML
    private void onDateSelected(ActionEvent event) {
        
    }

    @FXML
    private void onViewBookedCourts(ActionEvent event) {
    }

    @FXML
    private void onShowAvailable(ActionEvent event) {
        
    }

    @FXML
    private void onShowReserved(ActionEvent event) {
    }

    @FXML
    private void onMakeReservation(ActionEvent event) {
    }
    
}
