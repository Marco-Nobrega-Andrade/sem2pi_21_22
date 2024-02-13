package app.ui.gui.Coordinator;

import app.controller.ActorCenterController;
import app.domain.model.Exceptions.ListIsEmptyException;
import app.domain.model.VaccinationCenter.VaccinationCenterDTO;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Region;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CoordinatorGUI implements Initializable {

    private Stage stageStats = new Stage();
    private Stage stageImportLegacyStats = new Stage();
    private Stage stageAnalyzePerformance = new Stage();
    private ActorCenterController actorCenterController = new ActorCenterController();
    @FXML
    private ComboBox<VaccinationCenterDTO> actorCenterComboBox;

    @FXML
    private Button analyzePerformanceBtn;

    @FXML
    private Button exportVaccinationStatsBtn;

    @FXML
    private Button importLegacySystemBtn;

    @FXML
    private Button returnBtn;

    @FXML
    void analyzePerformanceClick(ActionEvent event) {

        stageAnalyzePerformance.setResizable(false);
        Parent root;
        FXMLLoader loader;
        VaccinationCenterDTO actorCenterDTO = actorCenterComboBox.getValue();

        if (actorCenterDTO != null) {
            actorCenterController.selectCenter(actorCenterDTO);
            try {
                loader = new FXMLLoader(getClass().getResource("/AnalyzePerformance.fxml"));
                root = loader.load();
                Scene scenePrint = new Scene(root);
                stageAnalyzePerformance = new Stage();
                stageAnalyzePerformance.setTitle("Performance Analysis");
                stageAnalyzePerformance.setScene(scenePrint);
                stageAnalyzePerformance.setResizable(false);
                stageAnalyzePerformance.initModality(Modality.APPLICATION_MODAL);
                stageAnalyzePerformance.show();
            } catch (IOException e) {
                Alert alertDate = new Alert(Alert.AlertType.ERROR);
                alertDate.setHeaderText("This option isn't ready it needs more information.");
                alertDate.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Vaccination center");
            alert.setContentText("You need to choose the vaccination center you're currently in.");
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.showAndWait();
        }
    }

    @FXML
    void exportVaccinationStatsClick(ActionEvent event) {
        stageStats.setResizable(false);
        Parent root;
        VaccinationCenterDTO actorCenterDTO = actorCenterComboBox.getValue();

        if (actorCenterDTO != null) {
            actorCenterController.selectCenter(actorCenterDTO);
            try {
                root = FXMLLoader.load(getClass().getResource("/ExportVaccinationStatisticsFxmlFiles/exportVaccinationsStatistics.fxml"));
                stageStats.setTitle("Export Vaccination Statistics");
                stageStats.setScene(new Scene(root, 600, 400));
                stageStats.show();
            } catch (IOException e) {
                Alert alertDate = new Alert(Alert.AlertType.ERROR);
                alertDate.setHeaderText("This option isn't ready it needs more information.");
                alertDate.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Vaccination center");
            alert.setContentText("You need to choose the vaccination center you're currently in.");
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.showAndWait();
        }
    }

    @FXML
    void importLegacySystemClick(ActionEvent event) throws IOException {
        Parent root;
        VaccinationCenterDTO actorCenterDTO = actorCenterComboBox.getValue();

        if (actorCenterDTO != null) {
            actorCenterController.selectCenter(actorCenterDTO);
            try {
                root = FXMLLoader.load(getClass().getResource("/ImportLegacySystemDataFxmlFiles/MainWindow.fxml"));
                stageImportLegacyStats.setTitle("Import and sort legacy system data");
                stageImportLegacyStats.setScene(new Scene(root, 600, 400));
                stageImportLegacyStats.initModality(Modality.APPLICATION_MODAL);
                stageImportLegacyStats.show();
            } catch (IOException e) {
                Alert alertDate = new Alert(Alert.AlertType.ERROR);
                alertDate.setHeaderText("This option isn't ready it needs more information.");
                alertDate.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Vaccination center");
            alert.setContentText("You need to choose the vaccination center you're currently in.");
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.showAndWait();
        }
    }

    @FXML
    void returnBtnClick(ActionEvent event) {
        Platform.exit();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<VaccinationCenterDTO> vaccinationCenterDTOS = new ArrayList<>();
        try {
            vaccinationCenterDTOS = actorCenterController.getActorCenter();
        } catch (ListIsEmptyException e) {
            Alert alertDate = new Alert(Alert.AlertType.ERROR);
            alertDate.setHeaderText("There isn't any Vaccination Center");
            alertDate.showAndWait();
            Platform.exit();
        }

        for (VaccinationCenterDTO vaccinationCenterDTO : vaccinationCenterDTOS) {
            actorCenterComboBox.getItems().add(vaccinationCenterDTO);
        }
    }
}
