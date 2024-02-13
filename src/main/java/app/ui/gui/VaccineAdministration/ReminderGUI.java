package app.ui.gui.VaccineAdministration;

import app.domain.model.VaccineAdministration.Reminder;
import app.domain.shared.Constants;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

import static java.lang.System.getProperties;


public class ReminderGUI implements Initializable {

    @FXML
    private Button cancelButton;

    @FXML
    private Button returnButton;

    @FXML
    private Button yesButton;
    private Stage stage;
    private VaccineAdministrationLotNumberSelectionGUI vaccineAdministrationLotNumberSelectionGUI;

    @FXML
    void cancelAction(ActionEvent event) {
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    void proceedAction(ActionEvent event) {

        Properties props = getProperties();
        String recoveryPeriodString = props.getProperty(Constants.PARAMS_RECOVERY_PERIOD);
        int recoveryPeriod = Integer.parseInt(recoveryPeriodString);

        new Reminder(recoveryPeriod);
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    void returnAction(ActionEvent event) {
        if (vaccineAdministrationLotNumberSelectionGUI.getVaccineAdministrationUserSelectionGUI()==null){
            vaccineAdministrationLotNumberSelectionGUI.getReminderStage().hide();
            vaccineAdministrationLotNumberSelectionGUI.getVaccineAdministrationVaccineSelectionGUI().getVaccineAdministrationUserSelectionGUI().getVaccineStage().show();
        }
        else{
            vaccineAdministrationLotNumberSelectionGUI.getReminderStage().hide();
            vaccineAdministrationLotNumberSelectionGUI.getVaccineAdministrationUserSelectionGUI().getVaccineAdministrationShowAndSaveGUI().getUserStage().show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void sendLotNumberGUI(VaccineAdministrationLotNumberSelectionGUI vaccineAdministrationLotNumberSelectionGUI) {
    this.vaccineAdministrationLotNumberSelectionGUI = vaccineAdministrationLotNumberSelectionGUI;
    }
}
