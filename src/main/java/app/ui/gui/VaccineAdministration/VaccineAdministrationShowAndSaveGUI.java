package app.ui.gui.VaccineAdministration;

import app.domain.model.Time;
import app.domain.model.User.UserDTO;
import app.domain.model.Vaccine.VaccineDTO;
import app.domain.model.VaccineAdministration.VaccineAdministrationDTO;
import app.domain.model.VaccineSchedule.VaccineScheduleDTO;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class VaccineAdministrationShowAndSaveGUI implements Initializable {


    @FXML
    private TextArea txtBoxVaccineAdministrations = new TextArea();
    private Stage userStage;

    public Stage getUserStage() {
        return userStage;
    }

    @FXML
    void addNewVaccineAdministration(ActionEvent event) {

        VaccineAdministrationShowAndSaveGUI vaccineAdministrationShowAndSaveGUI = new VaccineAdministrationShowAndSaveGUI();

        FXMLLoader loader;
        Parent root;
        try {
//carrega a janela secundária para memória sem a mostrar
            loader = new FXMLLoader(getClass().getResource("/VaccineAdministrationFxmlFiles/VaccineAdministrationUserSelection.fxml"));
            root = loader.load();

            Scene sceneShowUser = new Scene(root);

            userStage = new Stage();
            userStage.setTitle("Select SNS User");
            userStage.setScene(sceneShowUser);
            userStage.setResizable(false);
            userStage.initModality(Modality.APPLICATION_MODAL);


            //associa a referência desta janela à secundária
            vaccineAdministrationUserSelectionGUI = loader.getController();
            vaccineAdministrationUserSelectionGUI.sendShowGUI(vaccineAdministrationShowAndSaveGUI);


            userStage.show();

        } catch (IOException e) {
            Alert alertDate = new Alert(Alert.AlertType.ERROR);
            alertDate.setHeaderText("Error loading window");
            alertDate.showAndWait();
        }


    }

    @FXML
    void closeMenu(ActionEvent event) {
        Window window = txtBoxVaccineAdministrations.getScene().getWindow();
        window.fireEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSE_REQUEST));
    }

    public void initialize(URL url, ResourceBundle rb) {

    }

    private VaccineAdministrationUserSelectionGUI vaccineAdministrationUserSelectionGUI;

    private UserDTO user;
    private int snsNumber;
    private int age;
    private VaccineScheduleDTO vaccineSchedule;
    private Time arrivalTime;
    private VaccineDTO vaccineDTO;
    private int dose;
    private double dosage;

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public int getSnsNumber() {
        return snsNumber;
    }

    public void setSnsNumber(int snsNumber) {
        this.snsNumber = snsNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public VaccineScheduleDTO getVaccineSchedule() {
        return vaccineSchedule;
    }

    public void setVaccineSchedule(VaccineScheduleDTO vaccineSchedule) {
        this.vaccineSchedule = vaccineSchedule;
    }

    public Time getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Time arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public VaccineDTO getVaccineDTO() {
        return vaccineDTO;
    }

    public void setVaccineDTO(VaccineDTO vaccineDTO) {
        this.vaccineDTO = vaccineDTO;
    }

    public int getDose() {
        return dose;
    }

    public void setDose(int dose) {
        this.dose = dose;
    }

    public double getDosage() {
        return dosage;
    }

    public void setDosage(double dosage) {
        this.dosage = dosage;
    }


    public void setTxtStringInTextBox(VaccineAdministrationDTO vaccineAdministrationDTO) {
        setTexto(vaccineAdministrationDTO.toString());
    }

    private void setTexto(String string) {
        txtBoxVaccineAdministrations.setText(string);
    }
}
