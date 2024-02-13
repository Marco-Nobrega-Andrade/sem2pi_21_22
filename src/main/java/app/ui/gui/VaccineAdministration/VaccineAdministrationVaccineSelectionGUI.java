package app.ui.gui.VaccineAdministration;

import app.controller.VaccineAdministrationController;
import app.domain.model.Exceptions.AgeIsNotMatchingWithAgeGroupException;
import app.domain.model.Exceptions.NameNotFoundException;
import app.domain.model.Exceptions.NotDosageWasFoundedForThatVaccineException;
import app.domain.model.Vaccine.VaccineDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class VaccineAdministrationVaccineSelectionGUI implements Initializable {

    public Button returnButton;
    public Button submitButton;
    @FXML
    private ComboBox<String> selecterVaccine;

    private Stage lotNumberStage;
    private VaccineAdministrationLotNumberSelectionGUI vaccineAdministrationLotNumberSelectionGUI;
    private VaccineAdministrationController vaccineAdministrationController = new VaccineAdministrationController();
    private VaccineAdministrationUserSelectionGUI vaccineAdministrationUserSelectionGUI;


    @FXML
    void getVaccines(ActionEvent event) {

        vaccineAdministrationController=vaccineAdministrationUserSelectionGUI.getVaccineAdministrationController();
        ArrayList<VaccineDTO> vaccineList = vaccineAdministrationController.getVaccineByTypeAndAge(vaccineAdministrationUserSelectionGUI.getVaccineAdministrationShowAndSaveGUI().getVaccineSchedule().getVaccineType(), vaccineAdministrationUserSelectionGUI.getVaccineAdministrationShowAndSaveGUI().getAge());
        ArrayList<String> nameList = new ArrayList<>();

        for (VaccineDTO vaccineDTO : vaccineList) {
            nameList.add(vaccineDTO.getBrand());
        }

        selecterVaccine.getItems().addAll(nameList);
    }

    @FXML
    void returnOption(ActionEvent event) {

        vaccineAdministrationUserSelectionGUI.getVaccineStage().hide();
        vaccineAdministrationUserSelectionGUI.getVaccineAdministrationShowAndSaveGUI().getUserStage().show();

    }

    @FXML
    void submitedButton(ActionEvent event) {

        try {

            VaccineAdministrationController vaccineAdministrationController = new VaccineAdministrationController();
            ArrayList<VaccineDTO> vaccineList = vaccineAdministrationController.getVaccineByTypeAndAge(vaccineAdministrationUserSelectionGUI.getVaccineAdministrationShowAndSaveGUI().getVaccineSchedule().getVaccineType(), vaccineAdministrationUserSelectionGUI.getVaccineAdministrationShowAndSaveGUI().getAge());

            if (selecterVaccine.getValue() != null) {

                String select = selecterVaccine.getValue();

                VaccineDTO vaccine = vaccineAdministrationController.getVaccineByName(select,vaccineList);
                vaccineAdministrationUserSelectionGUI.getVaccineAdministrationShowAndSaveGUI().setVaccineDTO(vaccine);

                int dose = 1;
                vaccineAdministrationUserSelectionGUI.getVaccineAdministrationShowAndSaveGUI().setDose(dose);

                int position = vaccineAdministrationController.verifyAgeInAgeGroup(vaccineAdministrationUserSelectionGUI.getVaccineAdministrationShowAndSaveGUI().getAge(), vaccine);
                double dosage = vaccineAdministrationController.getNextDosage(position, dose, vaccineAdministrationUserSelectionGUI.getVaccineAdministrationShowAndSaveGUI().getUser(), vaccineAdministrationUserSelectionGUI.getVaccineAdministrationShowAndSaveGUI().getVaccineSchedule().getVaccineType(),vaccine);
                vaccineAdministrationUserSelectionGUI.getVaccineAdministrationShowAndSaveGUI().setDosage(dosage);

                try {

                    //carrega a janela secundária para memória sem a mostrar
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/VaccineAdministrationFxmlFiles/VaccineAdministrationLotNumberSelection.fxml"));
                    Parent root = loader.load();

                    Scene scene = new Scene(root);

                    lotNumberStage = new Stage();
                    lotNumberStage.initModality(Modality.APPLICATION_MODAL);
                    lotNumberStage.setTitle("Select a Lot Number");
                    lotNumberStage.setResizable(false);
                    lotNumberStage.setScene(scene);

                    //associa a referência desta janela à secundária
                    vaccineAdministrationLotNumberSelectionGUI = loader.getController();
                    vaccineAdministrationLotNumberSelectionGUI.associarParentUI(this);

                    ((Node) event.getSource()).getScene().getWindow().hide();
                    lotNumberStage.show();

                } catch (IOException e) {
                    Alert alertDate = new Alert(Alert.AlertType.ERROR);
                    alertDate.setHeaderText("Error loading window");
                    alertDate.showAndWait();
                }



            }
            else {
                Alert alertDate = new Alert(Alert.AlertType.ERROR);
                alertDate.setHeaderText("You need to select a vaccine");
                alertDate.showAndWait();
            }

        } catch (NameNotFoundException | NotDosageWasFoundedForThatVaccineException | AgeIsNotMatchingWithAgeGroupException e) {
            Alert alertDate = new Alert(Alert.AlertType.ERROR);
            alertDate.setHeaderText("There was an issue processing the information");
            alertDate.showAndWait();
            e.printStackTrace();
        }

    }

    public VaccineAdministrationUserSelectionGUI getVaccineAdministrationUserSelectionGUI() {
        return vaccineAdministrationUserSelectionGUI;
    }




    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void sendUserSelectionGUI(VaccineAdministrationUserSelectionGUI vaccineAdministrationUserSelectionGUI) {
        this.vaccineAdministrationUserSelectionGUI= vaccineAdministrationUserSelectionGUI;
    }
}
