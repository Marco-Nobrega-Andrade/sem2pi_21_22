package app.ui.gui.ImportDataLegacySystem;

import app.controller.ImportLegacySystemDataController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ImportLegacyFileWindowGUI implements Initializable {

    private ImportLegacySystemDataController controller;
    
    private Stage sortingStage;


    @FXML
    private TextField filePathTxtField;

    @FXML
    private Button okButton;
    @FXML
    private Button backButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        controller = new ImportLegacySystemDataController();

        Parent root;
        FXMLLoader loader;
        try {
            loader = new FXMLLoader(getClass().getResource("/ImportLegacySystemDataFxmlFiles/CriteriaWindow.fxml"));
            root = loader.load();
            Scene scenePrint = new Scene(root);
            sortingStage = new Stage();
            sortingStage.setTitle("Sort legacy data");
            sortingStage.setScene(scenePrint);
            sortingStage.setResizable(false);
            sortingStage.initModality(Modality.APPLICATION_MODAL);
            CriteriaWindowGUI criteriaWindowGUI = loader.getController();
            criteriaWindowGUI.associateParentUI(this);
        } catch (IOException e) {
            Alert alertDate = new Alert(Alert.AlertType.ERROR);
            alertDate.setHeaderText("Error loading window");
            alertDate.showAndWait();
        }
    }

    public ImportLegacySystemDataController getController() {
        return controller;
    }

    @FXML
    void onBackButtonPress(ActionEvent event) {
        filePathTxtField.clear();
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    void onOkButtonPress(ActionEvent event) {
        String path = filePathTxtField.getText();
        List<String> vaccineAdministrations = new ArrayList<>();
        try {
            Scanner in = new Scanner(new File(path));
            try {
                String header = in.nextLine();
                while (in.hasNextLine()) {
                    vaccineAdministrations.add(in.nextLine());
                }
            } finally {
                in.close();
            }
            ArrayList<Integer> [] arrayErrors = controller.importVaccineAdministrations(vaccineAdministrations);
            ArrayList<Integer> errorLines = arrayErrors[0];
            ArrayList<Integer> duplicateLines = arrayErrors[1];
            if (errorLines.size()==vaccineAdministrations.size()) {
                Alert alert = createAlert(Alert.AlertType.WARNING, "Error lines", "Import failed because the users or the vaccines might not be registered in the system, the info might not be in the correct format, or there already exist this data in the system.");
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alert.showAndWait();
                filePathTxtField.clear();
            }else if (duplicateLines.size()==vaccineAdministrations.size()){
                Alert alert = createAlert(Alert.AlertType.WARNING, "Duplicate lines", "Import failed because this data already exists in the system. Despite this, it will still appear in the sorting window.");
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alert.showAndWait();
                filePathTxtField.clear();
            }else if (errorLines.size() > 0 && duplicateLines.size() > 0){
                String errorLinesString = "";
                for (Integer i : errorLines){
                    errorLinesString = errorLinesString + i + ", ";
                }
                String duplicateLinesString = "";
                for (Integer i : duplicateLines){
                    duplicateLinesString = duplicateLinesString + i + ", ";
                }
                filePathTxtField.clear();
                sortingStage.show();
                Alert alertErrorLines = createAlert(Alert.AlertType.WARNING, "Error lines", "Some lines were not imported because the users or the vaccines might not be registered in the system, or the info my not be in the correct format.\nLine numbers:\n"+errorLinesString);
                alertErrorLines.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alertErrorLines.showAndWait();
                Alert alertDuplicateLines = createAlert(Alert.AlertType.WARNING, "Duplicate lines", "Some lines were not imported because they already exist in the system. Despite this, they will still appear in the sorting window.\nLine numbers:\n"+ duplicateLinesString);
                alertDuplicateLines.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alertDuplicateLines.showAndWait();
            }else if (errorLines.size() > 0) {
                String errorLinesString = "";
                for (Integer i : errorLines){
                    errorLinesString = errorLinesString + i + ", ";
                }
                filePathTxtField.clear();
                sortingStage.show();
                Alert alertErrorLines = createAlert(Alert.AlertType.WARNING, "Error lines", "Some lines were not imported because the users or the vaccines might not be registered in the system, or the info my not be in the correct format.\nLine numbers:\n"+errorLinesString);
                alertErrorLines.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alertErrorLines.showAndWait();
            }else if(duplicateLines.size() > 0){
                String duplicateLinesString = "";
                for (Integer i : duplicateLines){
                    duplicateLinesString = duplicateLinesString + i + ", ";
                }
                filePathTxtField.clear();
                sortingStage.show();
                Alert alertDuplicateLines = createAlert(Alert.AlertType.WARNING, "Duplicate lines", "Some lines were not imported because they already exist in the system. Despite this, they will still appear in the sorting window.\nLine numbers:\n"+ duplicateLinesString);
                alertDuplicateLines.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alertDuplicateLines.showAndWait();
            }else{
                filePathTxtField.clear();
                sortingStage.show();
            }
        } catch (FileNotFoundException e) {
            Alert alert = createAlert(Alert.AlertType.ERROR, "File input", "Import failed because the file wasn't found");
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.show();
            filePathTxtField.clear();
        }
    }

    private Alert createAlert(Alert.AlertType alertType, String header, String message) {
        Alert alert = new Alert(alertType);

        alert.setTitle("App");
        alert.setHeaderText(header);
        alert.setContentText(message);

        return alert;
    }
}
