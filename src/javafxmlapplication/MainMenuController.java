/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import static javafxmlapplication.JavaFXMLApplication.*;
import model.Booking;
import model.Club;
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
    private Text nameSurnameFieldBanner;
    @FXML
    private Button previousDayB;
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
    @FXML
    private Button viewAllCourts;
    @FXML
    private Button nextDayB;
    @FXML
    private Text courtSelected;
    @FXML
    private Text DatePrompt;
    @FXML
    private Text ErrorBooking;
    
    public List<Booking> arrayListBooking = new ArrayList<>();
    public ObservableList<Booking> bookingList = FXCollections.observableArrayList();
    public ObservableList<FreeSlots> availableHours = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        usernameFieldBanner.setText("@"+memberLoggedIn.getNickName());
        nameSurnameFieldBanner.setText(memberLoggedIn.getName()+" "+memberLoggedIn.getSurname());
        userPictureBanner.setImage(memberLoggedIn.getImage());
        dateSelector.setValue(date);
        courtSelected.setText(selectedCourtText);
        DatePrompt.setText(date.toString());
        ErrorBooking.setText(bookingStatus);
        
        availableHours.clear();
        arrayListBooking.clear();

        if(NorthSelected){
            setDefaultSpecificCourt(0);
        }
        else{setDefaultAll();}
        
        try{
            Club c = Club.getInstance();
            
            if(NorthSelected){
                arrayListBooking = c.getCourtBookings("Pista 1", date);
            }
            else{
                arrayListBooking = c.getForDayBookings(date);
            }
            
            bookingList = FXCollections.observableArrayList(arrayListBooking);
            
            // DateCol.setCellValueFactory(personaFila->new SimpleStringProperty(personaFila.getValue().getBookingDate().toString().substring(0, personaFila.getValue().getBookingDate().toString().length() - 6)));
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
        NorthSelected = true;
        SouthSelected  = false;
        WestSelected  = false;
        EastSelected = false;
        PondSelected  = false; 
        MillSelected = false;
        selectedCourtText = "North Court";
        JavaFXMLApplication c = new JavaFXMLApplication();
        try{
            c.changeScene("mainMenu.fxml", mainScreen, (int) mainScreen.getX(), (int) mainScreen.getY());
        }catch(IOException e){}
    }
    
    @FXML
    private void onClickSouthCourt(ActionEvent event) {
        SouthSelected = true;
        NorthSelected  = false;
        WestSelected  = false;
        EastSelected = false;
        PondSelected  = false; 
        MillSelected = false;
        selectedCourtText = "South Court";
        JavaFXMLApplication c = new JavaFXMLApplication();
        try{
            c.changeScene("mainMenu.fxml", mainScreen, (int) mainScreen.getX(), (int) mainScreen.getY());
        }catch(IOException e){}
    }
    
    @FXML
    private void onClickMillCourt(ActionEvent event) {
        MillSelected = true;
        SouthSelected = false;
        NorthSelected  = false;
        WestSelected  = false;
        EastSelected = false;
        PondSelected  = false; 
        selectedCourtText = "Mill Court";
        JavaFXMLApplication c = new JavaFXMLApplication();
        try{
            c.changeScene("mainMenu.fxml", mainScreen, (int) mainScreen.getX(), (int) mainScreen.getY());
        }catch(IOException e){}
    }

    @FXML
    private void onClickWestCourt(ActionEvent event) {
        WestSelected = true;
        NorthSelected  = false;
        SouthSelected  = false;
        EastSelected = false;
        PondSelected  = false; 
        MillSelected = false;
        selectedCourtText = "West Court";
        JavaFXMLApplication c = new JavaFXMLApplication();
        try{
            c.changeScene("mainMenu.fxml", mainScreen, (int) mainScreen.getX(), (int) mainScreen.getY());
        }catch(IOException e){}
    }

    @FXML
    private void onClickEastCourt(ActionEvent event) {
        EastSelected = true;
        NorthSelected  = false;
        SouthSelected  = false;
        WestSelected  = false;
        PondSelected  = false; 
        MillSelected = false;
        selectedCourtText = "East Court";
        JavaFXMLApplication c = new JavaFXMLApplication();
        try{
            c.changeScene("mainMenu.fxml", mainScreen, (int) mainScreen.getX(), (int) mainScreen.getY());
        }catch(IOException e){}
    }

    @FXML
    private void onClickPondCourt(ActionEvent event) {
        PondSelected = true;
        NorthSelected  = false;
        SouthSelected  = false;
        WestSelected  = false;
        EastSelected = false;
        MillSelected = false;
        selectedCourtText = "Pond Court";
        JavaFXMLApplication c = new JavaFXMLApplication();
        try{
            c.changeScene("mainMenu.fxml", mainScreen, (int) mainScreen.getX(), (int) mainScreen.getY());
        }catch(IOException e){}
    }

    @FXML
    private void onSignOutButtonClicked() {
        icon = new Image("img/Avatar_icon_green.svg.png");
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
        
        String[] hours = {"9:00","10:00","11:00",
            "12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00",
            "20:00","21:00","22:00"};
        
        try{
            Club c = Club.getInstance();
            List<Court> listCourts = c.getCourts();
            arrayListBooking = c.getForDayBookings(date);
            
            for (int i = 0; i < 6; i++) {
                for(int j = 0; j < 14; j++){
                    LocalTime n = LocalTime.of(Integer.parseInt(hours[j].substring(0, hours[j].length()-3)),0);
                    FreeSlots f = new FreeSlots(hours[j], listCourts.get(i).getName(), n);
                    
                    availableHours.add(f);
                    
                    for(int k = 0; k < arrayListBooking.size(); k++){
                        if(arrayListBooking.get(k).getCourt().getName().equals(listCourts.get(i).getName()) &&
                        arrayListBooking.get(k).getFromTime().equals(n)){
                            availableHours.remove(f);
                        }
                    }
                }
            }
        }catch(Exception e){}
        
        HourCol1.setCellValueFactory(personaFila->new SimpleStringProperty(personaFila.getValue().getHour()));
        CourtCol1.setCellValueFactory(personaFila->new SimpleStringProperty(personaFila.getValue().getCourt()));
        
        TableList1.setItems(availableHours);
    }
    
    public void setDefaultSpecificCourt(int i){
        
        String[] hours = {"9:00","10:00","11:00",
            "12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00",
            "20:00","21:00","22:00"};
        
        try{
            Club c = Club.getInstance();
            List<Court> listCourts = c.getCourts();
            arrayListBooking = c.getForDayBookings(date);
            
            for(int j = 0; j < 14; j++){
                    LocalTime n = LocalTime.of(Integer.parseInt(hours[j].substring(0, hours[j].length()-3)),0);
                    FreeSlots f = new FreeSlots(hours[j], listCourts.get(i).getName(), n);
                    
                    availableHours.add(f);
                    
                    for(int k = 0; k < arrayListBooking.size(); k++){
                        if(arrayListBooking.get(k).getCourt().getName().equals(listCourts.get(i).getName()) &&
                        arrayListBooking.get(k).getFromTime().equals(n)){
                            availableHours.remove(f);
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
        date = dateSelector.getValue();
        JavaFXMLApplication c = new JavaFXMLApplication();
        try{
        c.changeScene("mainMenu.fxml", mainScreen, (int) mainScreen.getX(), (int) mainScreen.getY());
        }catch(IOException e){}
    }
    
    public boolean isBackToBack(Booking b){
        try{
            Club c = Club.getInstance();
            List<Booking> bookingLists = c.getUserBookings(memberLoggedIn.getNickName());
            if(bookingLists.size() < 3){
                return false;
            }
            else{
                for(int i = 0; i < bookingLists.size() - 2; i++){
                    Booking first = bookingLists.get(i);
                    Booking second =  bookingLists.get(i+1);
                    Booking third = bookingLists.get(i+2);
                    
                    if(first.getMadeForDay().equals(second.getMadeForDay()) &&
                    first.getMadeForDay().equals(third.getMadeForDay()) &&
                    first.getCourt().equals(second.getCourt()) && first.getCourt().equals(third.getCourt())
                    && first.getFromTime().plusHours(1).equals(second.getFromTime())
                    && first.getFromTime().plusHours(2).equals(third.getFromTime())){
                        bookingStatus = "Can't make more than 2 bookings back to back.";
                        return true;
                    }
                }
            }
        }
        catch(Exception e){}
        return false;
    }
    
    public boolean existsOnAnotherCourt(Booking b){
        try{
            Club c = Club.getInstance();
            List<Booking> bookingLists = c.getUserBookings(memberLoggedIn.getNickName());
            for(int i = 0; i < bookingLists.size(); i++){
                if(b.getBookingDate().equals(bookingLists.get(i).getBookingDate())
                && !b.getCourt().equals(bookingLists.get(i).getCourt())){
                    bookingStatus = "Can't book 2 courts at the same hour!";
                    return true;
                }
            }
        }
        catch(Exception e){}
        return false;
    }
    
    @FXML
    private void onMakeReservation(ActionEvent event) {
        try{
            Club c = Club.getInstance();
            Court selected = c.getCourt(TableList1.getSelectionModel().getSelectedItem().getCourt());
            LocalTime t = TableList1.getSelectionModel().getSelectedItem().getTime();
            LocalDateTime datetime = LocalDateTime.of(date, t);
            
            if(datetime != null && t != null && selected != null){
                System.out.println(datetime.compareTo(LocalDateTime.now()));
                if(datetime.compareTo(LocalDateTime.now()) > 0){
                    Booking b = c.registerBooking(datetime, date, t, false, selected , memberLoggedIn);
                    
                    if(memberLoggedIn.getSvc() == -1 && memberLoggedIn.getCreditCard().equals("")){ b.setPaid(false); }
                    else{b.setPaid(true); }
                    
                    boolean isBackToBack = isBackToBack(b);
                    boolean existsOnAnotherCourt = existsOnAnotherCourt(b);
                    System.out.println(isBackToBack && existsOnAnotherCourt(b));
                    if(isBackToBack || existsOnAnotherCourt){System.out.println("booking removed"); c.removeBooking(b);}
                    else{
                        bookingStatus = "Booking made successfully!";
                        bookingList.add(b);                    
                    }             
                }
                else{bookingStatus = "Cannot make reservation in the past!";}
            }
        }
        catch(Exception e){}
        
        JavaFXMLApplication c = new JavaFXMLApplication();
        try{
        c.changeScene("mainMenu.fxml", mainScreen, (int) mainScreen.getX(), (int) mainScreen.getY());
        }catch(IOException e){}
    }

    @FXML
    private void onViewAllCourts() {
        NorthSelected  = false;
        SouthSelected  = false;
        WestSelected  = false;
        EastSelected = false;
        PondSelected  = false; 
        MillSelected = false;
        selectedCourtText = "All Courts";
        JavaFXMLApplication c = new JavaFXMLApplication();
        try{
        c.changeScene("mainMenu.fxml", mainScreen, (int) mainScreen.getX(), (int) mainScreen.getY());
        }catch(IOException e){}
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
    private void onDayplus1() {
        date = date.plusDays(1);
        dateSelector.setValue(date);
    }

    @FXML
    private void onDayminus1() {
        date = date.minusDays(1);
        dateSelector.setValue(date);
    }
    
}
