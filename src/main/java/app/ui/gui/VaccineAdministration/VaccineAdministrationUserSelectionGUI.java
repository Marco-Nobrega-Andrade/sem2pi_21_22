package app.ui.gui.VaccineAdministration;

import app.controller.VaccineAdministrationController;
import app.domain.model.Exceptions.*;
import app.domain.model.Time;
import app.domain.model.User.UserDTO;
import app.domain.model.Vaccine.VaccineDTO;
import app.domain.model.VaccineSchedule.VaccineScheduleDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class VaccineAdministrationUserSelectionGUI implements Initializable {

    @FXML
    private ComboBox<Integer> selecterUser;

    private Stage vaccineStage;
    private Stage lotNumberStage;
    private VaccineAdministrationShowAndSaveGUI vaccineAdministrationShowAndSaveGUI;
    private VaccineAdministrationVaccineSelectionGUI vaccineAdministrationVaccineSelectionGUI;
    private VaccineAdministrationController vaccineAdministrationController;
    private VaccineDTO vaccine;
    private int dose;
    private double dosage;
    private VaccineAdministrationLotNumberSelectionGUI vaccineAdministrationLotNumberSelectionGUI;

    public Stage getVaccineStage() {
        return vaccineStage;
    }

    public Stage getLotNumberStage() {
        return lotNumberStage;
    }

    @FXML
    void submitBtnClick(ActionEvent event) {

        try {

            VaccineAdministrationController vaccineAdministrationController = new VaccineAdministrationController();
            ArrayList<UserDTO> userList = vaccineAdministrationController.getUserFromWaitingUserList();

            if (selecterUser.getValue() != null) {

                int select = selecterUser.getValue();

                UserDTO user = vaccineAdministrationController.getUserByNumber(userList,select);
                vaccineAdministrationShowAndSaveGUI.setUser(user);

                int snsNumber = user.getSnsNumber();
                vaccineAdministrationShowAndSaveGUI.setSnsNumber(snsNumber);

                int age = vaccineAdministrationController.getAge(user);
                vaccineAdministrationShowAndSaveGUI.setAge(age);


                VaccineScheduleDTO vaccineSchedule = vaccineAdministrationController.getVaccineSchedule(snsNumber);
                vaccineAdministrationShowAndSaveGUI.setVaccineSchedule(vaccineSchedule);

                Time arrivalTime = vaccineAdministrationController.getArrivalTime(snsNumber);
                vaccineAdministrationShowAndSaveGUI.setArrivalTime(arrivalTime);

                if (!vaccineAdministrationController.verifyIfExistVaccinationRecord(vaccineSchedule.getVaccineType(), user)) {

                    FXMLLoader loader;
                    Parent root;

                    try {

                        //carrega a janela secundária para memória sem a mostrar
                         loader = new FXMLLoader(getClass().getResource("/VaccineAdministrationFxmlFiles/VaccineAdministrationVaccineSelection.fxml"));
                         root = loader.load();

                        Scene scene = new Scene(root);

                        vaccineStage = new Stage();
                        vaccineStage.initModality(Modality.APPLICATION_MODAL);
                        vaccineStage.setTitle("Select SNS User");
                        vaccineStage.setResizable(false);
                        vaccineStage.setScene(scene);

                        //associa a referência desta janela à secundária

                        vaccineAdministrationVaccineSelectionGUI = loader.getController();
                        vaccineAdministrationVaccineSelectionGUI.sendUserSelectionGUI(this);

                        ((Node) event.getSource()).getScene().getWindow().hide();
                        vaccineStage.show();

                        //The vaccine stage is not completed yet

                    } catch (IOException e) {
                        Alert alertDate = new Alert(Alert.AlertType.ERROR);
                        alertDate.setHeaderText("Error loading window");
                        e.printStackTrace();
                        alertDate.showAndWait();
                    }


                }
                else{
                    


                    vaccine = vaccineAdministrationController.getVaccine(vaccineSchedule.getVaccineType(), user);
                    vaccineAdministrationShowAndSaveGUI.setVaccineDTO(vaccine);

                    dose = vaccineAdministrationController.getNextDose(vaccineSchedule.getVaccineType(), user);
                    vaccineAdministrationShowAndSaveGUI.setDose(dose);

                    int position;
                    position = vaccineAdministrationController.verifyAgeInAgeGroup(age, vaccine);
                    dosage = vaccineAdministrationController.getNextDosage(position, dose, user, vaccineSchedule.getVaccineType(),vaccine);
                    vaccineAdministrationShowAndSaveGUI.setDosage(dosage);

                    //now i wil create another window to select the lot number
                    try {

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/VaccineAdministrationFxmlFiles/VaccineAdministrationLotNumberSelection.fxml"));
                        Parent root = loader.load();

                        Scene scene = new Scene(root);

                        lotNumberStage = new Stage();
                        lotNumberStage.initModality(Modality.APPLICATION_MODAL);
                        lotNumberStage.setTitle("Select SNS User");
                        lotNumberStage.setResizable(false);
                        lotNumberStage.setScene(scene);

                        //associa a referência desta janela à secundária
                        vaccineAdministrationLotNumberSelectionGUI = loader.getController();
                        vaccineAdministrationLotNumberSelectionGUI.sendUserSelectionGUI(this);

                        ((Node) event.getSource()).getScene().getWindow().hide();
                        lotNumberStage.show();

                    } catch (IOException e) {
                        Alert alertDate = new Alert(Alert.AlertType.ERROR);
                        alertDate.setHeaderText("Error loading window");
                        e.printStackTrace();
                        alertDate.showAndWait();
                    }
                }
            }
            else {
                Alert alertDate = new Alert(Alert.AlertType.ERROR);
                alertDate.setHeaderText("You need to select a user");
                alertDate.showAndWait();
            }
        }catch (NoArrivalTimeForSnsNumberException | ListIsEmptyException | NotVaccineForThatVaccineTypeException | NotPreviousDoseForThatVaccineException | AgeIsNotMatchingWithAgeGroupException | NotDosageWasFoundedForThatVaccineException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {


            vaccineAdministrationController = new VaccineAdministrationController();


            ArrayList<UserDTO> userList = vaccineAdministrationController.getUserFromWaitingUserList();
            ArrayList<Integer> snsList = new ArrayList<>();

            for (UserDTO userDTO : userList) {
                snsList.add(userDTO.getSnsNumber());
            }

            selecterUser.getItems().addAll(snsList);

        } catch (ListIsEmptyException e) {
            Alert alertDate = new Alert(Alert.AlertType.ERROR);
            alertDate.setHeaderText("There is no user registered in the waiting list");
            alertDate.showAndWait();
            e.printStackTrace();
        }

    }


    public void sendShowGUI(VaccineAdministrationShowAndSaveGUI vaccineAdministrationShowAndSaveGUI) {
        this.vaccineAdministrationShowAndSaveGUI = vaccineAdministrationShowAndSaveGUI;
    }

    public VaccineAdministrationShowAndSaveGUI getVaccineAdministrationShowAndSaveGUI() {
        return vaccineAdministrationShowAndSaveGUI;
    }

    public VaccineAdministrationController getVaccineAdministrationController() {
        return vaccineAdministrationController;
    }
}