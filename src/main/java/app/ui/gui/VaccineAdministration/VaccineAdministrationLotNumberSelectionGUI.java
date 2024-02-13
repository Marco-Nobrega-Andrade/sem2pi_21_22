package app.ui.gui.VaccineAdministration;

import app.controller.VaccineAdministrationController;
import app.domain.model.Exceptions.MissingCorrectFormatToLotNumber;
import app.domain.model.Time;
import app.domain.model.VaccineAdministration.VaccineAdministrationDTO;
import app.domain.shared.Constants;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;

import static java.lang.System.getProperties;

public class VaccineAdministrationLotNumberSelectionGUI implements Initializable {
    private VaccineAdministrationVaccineSelectionGUI vaccineAdministrationVaccineSelectionGUI;
    private VaccineAdministrationUserSelectionGUI vaccineAdministrationUserSelectionGUI;
    private ReminderGUI reminderGUI;

    public VaccineAdministrationVaccineSelectionGUI getVaccineAdministrationVaccineSelectionGUI() {
        return vaccineAdministrationVaccineSelectionGUI;
    }

    public VaccineAdministrationUserSelectionGUI getVaccineAdministrationUserSelectionGUI() {
        return vaccineAdministrationUserSelectionGUI;
    }

    @FXML
    private Button returnButton;

    @FXML
    private Button submitButton;

    @FXML
    private TextField textField;
    private Stage reminderStage;

    public Stage getReminderStage() {
        return reminderStage;
    }

    @FXML
    void returnOption(ActionEvent event) {
        if (vaccineAdministrationUserSelectionGUI==null){
            vaccineAdministrationVaccineSelectionGUI.getVaccineAdministrationUserSelectionGUI().getLotNumberStage().hide();
            vaccineAdministrationVaccineSelectionGUI.getVaccineAdministrationUserSelectionGUI().getVaccineStage().show();
        }
        else{
            vaccineAdministrationUserSelectionGUI.getLotNumberStage().hide();
            vaccineAdministrationUserSelectionGUI.getVaccineAdministrationShowAndSaveGUI().getUserStage().show();
        }
    }

    @FXML
    void submitAction(ActionEvent event) {



            /*
                Obtenção do texto na forma de String da textField
             */

        VaccineAdministrationController vaccineAdministrationController = new VaccineAdministrationController();
        String lotNumber = textField.getText().trim();

            /*
                Verificação do lot number caso exista uma dose prévia
             */

        if (vaccineAdministrationUserSelectionGUI == null) {

            try {

                if (vaccineAdministrationController.verifyLotNumber(vaccineAdministrationVaccineSelectionGUI.getVaccineAdministrationUserSelectionGUI().getVaccineAdministrationShowAndSaveGUI().getUser(), lotNumber)) {
                    Time administrationTime = Time.currentTime();
                    Date administrationDate = vaccineAdministrationVaccineSelectionGUI.getVaccineAdministrationUserSelectionGUI().getVaccineAdministrationShowAndSaveGUI().getVaccineSchedule().getDate();
                    int recoveryPeriod = vaccineAdministrationController.getRecoveryPeriod();
                    Time leavingTime = administrationTime.addMinutes(recoveryPeriod);
                    Date leavingDate = vaccineAdministrationVaccineSelectionGUI.getVaccineAdministrationUserSelectionGUI().getVaccineAdministrationShowAndSaveGUI().getVaccineSchedule().getDate();
                    VaccineAdministrationDTO vaccineAdministrationDTO = vaccineAdministrationController.getDTO(vaccineAdministrationVaccineSelectionGUI.getVaccineAdministrationUserSelectionGUI().getVaccineAdministrationShowAndSaveGUI().getVaccineSchedule(), vaccineAdministrationVaccineSelectionGUI.getVaccineAdministrationUserSelectionGUI().getVaccineAdministrationShowAndSaveGUI().getArrivalTime(), vaccineAdministrationVaccineSelectionGUI.getVaccineAdministrationUserSelectionGUI().getVaccineAdministrationShowAndSaveGUI().getUser(), vaccineAdministrationVaccineSelectionGUI.getVaccineAdministrationUserSelectionGUI().getVaccineAdministrationShowAndSaveGUI().getVaccineDTO(), vaccineAdministrationVaccineSelectionGUI.getVaccineAdministrationUserSelectionGUI().getVaccineAdministrationShowAndSaveGUI().getDose(), administrationDate, administrationTime, leavingDate, leavingTime, lotNumber);
                    vaccineAdministrationController.saveNewVaccineAdministration(vaccineAdministrationDTO, vaccineAdministrationVaccineSelectionGUI.getVaccineAdministrationUserSelectionGUI().getVaccineAdministrationShowAndSaveGUI().getUser());

                    vaccineAdministrationVaccineSelectionGUI.getVaccineAdministrationUserSelectionGUI().getVaccineAdministrationShowAndSaveGUI().setTxtStringInTextBox(vaccineAdministrationDTO);

                    try {
                        //carrega a janela secundária para memória sem a mostrar
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/VaccineAdministrationFxmlFiles/VaccineAdministrationReminder.fxml"));
                        Parent root = loader.load();

                        Scene scene = new Scene(root);

                        reminderStage = new Stage();
                        reminderStage.initModality(Modality.APPLICATION_MODAL);
                        reminderStage.setTitle("Reminder Option");
                        reminderStage.setResizable(false);
                        reminderStage.setScene(scene);

                        //associa a referência desta janela à secundária
                        reminderGUI = loader.getController();
                        reminderGUI.sendLotNumberGUI(this);

                        ((Node) event.getSource()).getScene().getWindow().hide();
                        reminderStage.show();

                    } catch (IOException e) {
                        Alert alertDate = new Alert(Alert.AlertType.ERROR);
                        alertDate.setHeaderText("Error loading window");
                        alertDate.showAndWait();
                    }

                } else {
                    Alert alertDate = new Alert(Alert.AlertType.ERROR);
                    alertDate.setHeaderText("Incorrect lot number format");
                    alertDate.showAndWait();
                }
            } catch (MissingCorrectFormatToLotNumber | IOException missingCorrectFormatToLotNumber) {
                missingCorrectFormatToLotNumber.printStackTrace();
            }


        }


             /*
                Verificação do lot number caso não exista uma dose prévia
             */

        else {

            try {
            if (vaccineAdministrationController.verifyLotNumber(vaccineAdministrationUserSelectionGUI.getVaccineAdministrationShowAndSaveGUI().getUser(), lotNumber)) {
                Time administrationTime = Time.currentTime();
                Date administrationDate = vaccineAdministrationUserSelectionGUI.getVaccineAdministrationShowAndSaveGUI().getVaccineSchedule().getDate();
                Properties props = getProperties();
                String recoveryPeriodString = props.getProperty(Constants.PARAMS_RECOVERY_PERIOD);
                int recoveryPeriod = Integer.parseInt(recoveryPeriodString);
                Time leavingTime = administrationTime.addMinutes(recoveryPeriod);
                Date leavingDate = vaccineAdministrationUserSelectionGUI.getVaccineAdministrationShowAndSaveGUI().getVaccineSchedule().getDate();
                VaccineAdministrationDTO vaccineAdministrationDTO = vaccineAdministrationController.getDTO(vaccineAdministrationUserSelectionGUI.getVaccineAdministrationShowAndSaveGUI().getVaccineSchedule(), vaccineAdministrationUserSelectionGUI.getVaccineAdministrationShowAndSaveGUI().getArrivalTime(), vaccineAdministrationUserSelectionGUI.getVaccineAdministrationShowAndSaveGUI().getUser(), vaccineAdministrationUserSelectionGUI.getVaccineAdministrationShowAndSaveGUI().getVaccineDTO(), vaccineAdministrationUserSelectionGUI.getVaccineAdministrationShowAndSaveGUI().getDose(), administrationDate, administrationTime, leavingDate, leavingTime, lotNumber);
                vaccineAdministrationController.saveNewVaccineAdministration(vaccineAdministrationDTO, vaccineAdministrationUserSelectionGUI.getVaccineAdministrationShowAndSaveGUI().getUser());

                vaccineAdministrationUserSelectionGUI.getVaccineAdministrationShowAndSaveGUI().setTxtStringInTextBox(vaccineAdministrationDTO);

                ((Node) event.getSource()).getScene().getWindow().hide();

                vaccineAdministrationUserSelectionGUI.getLotNumberStage().hide();

                try {

                    //carrega a janela secundária para memória sem a mostrar
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/VaccineAdministrationFxmlFiles/VaccineAdministrationLotNumberSelection.fxml"));
                    Parent root = loader.load();

                    Scene scene = new Scene(root);

                    reminderStage = new Stage();
                    reminderStage.initModality(Modality.APPLICATION_MODAL);
                    reminderStage.setTitle("Reminder Option");
                    reminderStage.setResizable(false);
                    reminderStage.setScene(scene);

                    //associa a referência desta janela à secundária
                    ReminderGUI reminderGUI = loader.getController();


                    reminderStage.show();

                } catch (IOException e) {
                    Alert alertDate = new Alert(Alert.AlertType.ERROR);
                    alertDate.setHeaderText("Error loading window");
                    alertDate.showAndWait();
                }
            }
            } catch (MissingCorrectFormatToLotNumber missingCorrectFormatToLotNumber) {
                Alert alertDate = new Alert(Alert.AlertType.ERROR);
                alertDate.setHeaderText("Incorrect lot number format");
                alertDate.showAndWait();
            }
        }
    }

        public void associarParentUI(VaccineAdministrationVaccineSelectionGUI vaccineAdministrationVaccineSelectionGUI) {
        this.vaccineAdministrationVaccineSelectionGUI = vaccineAdministrationVaccineSelectionGUI;
        this.vaccineAdministrationUserSelectionGUI = null;
    }

    public void initialize(URL url, ResourceBundle rb) {

        /*
            Voltar à janela inicial
         */

    }

    public void sendUserSelectionGUI(VaccineAdministrationUserSelectionGUI vaccineAdministrationUserSelectionGUI) {
        this.vaccineAdministrationUserSelectionGUI = vaccineAdministrationUserSelectionGUI;
    }
}
