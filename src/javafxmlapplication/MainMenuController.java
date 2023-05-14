/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication;

import java.io.IOException;
import java.lang.reflect.Member;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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
import model.Booking;
import model.Club;
import model.ClubDAOException;
import model.Court;

/**
 * FXML Controller class
 *
 * @author yassi
 */
public class MainMenuController implements Initializable {

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
    private Text courtSelectedPrompt1;
    @FXML
    private Text nameSurnameFieldBanner;
    @FXML
    private Button previousDayB;
    @FXML
    private Button dayAfterB;
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
    private TableView<Booking> TableList;
    @FXML
    private TableColumn<Booking, String> DateCol;
    @FXML
    private TableColumn<Booking, String> HourCol;
    @FXML
    private TableColumn<Booking, String> PaidCol;
    @FXML
    private TableColumn<Booking, String> CourtCol;
    @FXML
    private TableColumn<Booking, String> NameCol;
    @FXML
    private TableView<FreeSlots> TableList1;
    @FXML
    private TableColumn<FreeSlots, String> HourCol1;
    @FXML
    private TableColumn<FreeSlots, String> CourtCol1;
    
    public ArrayList<Booking> arrayListBooking = new ArrayList<>();
    public ObservableList<Booking> bookingList = FXCollections.observableArrayList();
    public ObservableList<FreeSlots> availableHours = FXCollections.observableArrayList();
    
    /**
     * Initializes the controller class.
     */
   
    
    LocalDate date;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        usernameFieldBanner.setText("@"+memberLoggedIn.getNickName());
        nameSurnameFieldBanner.setText(memberLoggedIn.getName()+" "+memberLoggedIn.getSurname());
        userPictureBanner.setImage(memberLoggedIn.getImage());
        dateSelector.setValue(LocalDate.now());
        date=LocalDate.now();
        /*
        ArrayList<String> courtsdata= new ArrayList<String>();
        courtsdata.add(new String("buen"));
        courtsdata.add(new String("hol2"));
        courts=FXCollections.observableArrayList(courtsdata);
        TableList.setItems(courts);
        firstColumn.setCellValueFactory(new PropertyValueFactory<String, String>("hola"));         
        secondColumn.setCellValueFactory(new PropertyValueFactory<String, String>("User"));
        */
        
        setDefaultAll();
        
        try{
            Club c = Club.getInstance();
            List<Court> listCourts = c.getCourts();
            arrayListBooking = c.getBookings();
            bookingList = FXCollections.observableArrayList(arrayListBooking);
            for(int i = 0; i < 6; i++){
                System.out.println(listCourts.get(i).getName());
            }
            DateCol.setCellValueFactory(personaFila->new SimpleStringProperty(personaFila.getValue().getBookingDate().toString().substring(0, personaFila.getValue().getBookingDate().toString().length() - 6)));
            HourCol.setCellValueFactory(personaFila->new SimpleStringProperty(personaFila.getValue().getFromTime().toString()));
            PaidCol.setCellValueFactory(personaFila->new SimpleStringProperty(personaFila.getValue().getPaid().toString()));
            CourtCol.setCellValueFactory(personaFila->new SimpleStringProperty(personaFila.getValue().getCourt().getName()));
            NameCol.setCellValueFactory(personaFila->new SimpleStringProperty(personaFila.getValue().getMember().getNickName()));
            
            TableList.setItems(bookingList);
        }
        catch(Exception e){}
    }
    
    @FXML
    private void onClickNorthCourt() {
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
    public void setDefaultAll(){
        
        String[] hours = {"6:00","7:00","8:00","9:00","10:00","11:00",
            "12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00",
            "20:00","21:00","22:00"};
        
        try{
            Club c = Club.getInstance();
            List<Court> listCourts = c.getCourts();
            arrayListBooking = c.getBookings();
            int slotBookinglist = 0;
            
            for (int i = 0; i < 6; i++) {
                for(int j = 0; j < 17; j++){
                    LocalTime n = LocalTime.of(Integer.parseInt(hours[j].substring(0, hours[j].length()-3)),0);
                    FreeSlots f = new FreeSlots(hours[j], listCourts.get(i).getName(), n);
                    
                    if(arrayListBooking.get(slotBookinglist).getCourt().getName().equals(listCourts.get(i).getName()) &&
                    arrayListBooking.get(slotBookinglist).getFromTime().equals(n)){
                        if(arrayListBooking.size() > slotBookinglist + 1) slotBookinglist++;
                    }
                    else{
                        availableHours.add(f);
                    }
                }
            }
        }catch(Exception e){}
        
        HourCol1.setCellValueFactory(personaFila->new SimpleStringProperty(personaFila.getValue().getHour()));
        CourtCol1.setCellValueFactory(personaFila->new SimpleStringProperty(personaFila.getValue().getCourt()));
        
        TableList1.setItems(availableHours);
    }
    
    @FXML
    private void onDateSelected(ActionEvent event) {   
    }

    @FXML
    private void onViewBookedCourts(ActionEvent event) {
    }


    @FXML
    private void onMakeReservation(ActionEvent event) {
        try{
            Club c = Club.getInstance();
            Court selected = c.getCourt("Pista 4");
            LocalTime t = LocalTime.of(13,0);
            Booking b = c.registerBooking(LocalDateTime.MAX, LocalDate.MIN, t, true, selected , memberLoggedIn);
            bookingList.add(b);
        }
        catch(Exception e){}

        initialize(null, null);
    }

    public class FreeSlots {

        public String getHour() {
            return hour;
        }

        public String getCourt() {
            return court;
        }

        public LocalTime getTime() {
            return time;
        }

       private final String hour;
       private final String court;
       private LocalTime time;

       public FreeSlots(String hour, String court, LocalTime time) {
           this.hour = hour;
           this.court = court;
           this.time = time;
        }
    }


    @FXML
    private void onDayplus1(MouseEvent event) {
        dateSelector.setValue(date.plusDays(1));
    }

    @FXML
    private void onDayminus1(MouseEvent event) {
        dateSelector.setValue(date.minusDays(1));
    }
    
}
