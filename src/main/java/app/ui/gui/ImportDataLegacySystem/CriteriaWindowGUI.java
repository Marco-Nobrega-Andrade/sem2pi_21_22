package app.ui.gui.ImportDataLegacySystem;

import app.domain.model.VaccineAdministration.VaccineAdministrationDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CriteriaWindowGUI implements Initializable {
    private ImportLegacyFileWindowGUI importLegacyFileWindowGUI;

    private ArrayList<VaccineAdministrationDTO> newList = new ArrayList<>();
    @FXML
    private Button backButton;

    @FXML
    private ComboBox<String> criteriaComboBox;

    @FXML
    private ComboBox<String> orderComboBox;

    @FXML
    private Button sortButton;

    @FXML
    private TextArea sortedListTxtArea;

    @FXML
    private Label titleLbl;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        criteriaComboBox.getItems().addAll("Arrival time","Leaving time");
        orderComboBox.getItems().addAll("Ascending", "Descending");
    }

    public void associateParentUI(ImportLegacyFileWindowGUI importLegacyFileWindowGUI) {
        this.importLegacyFileWindowGUI = importLegacyFileWindowGUI;
    }

    @FXML
    void onSortButtonPress(ActionEvent event) {
        String criteria = criteriaComboBox.getValue();
        String order = orderComboBox.getValue();
        if (criteria == null || order == null){
            createAlert(Alert.AlertType.ERROR,"Options","You need to choose both criteria and order options").showAndWait();
        }else{
            try{
                newList =  importLegacyFileWindowGUI.getController().sortVaccineAdministration(criteria,order);
                updateSortedlist(newList,criteria);
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e ) {
                createAlert(Alert.AlertType.ERROR,"Sorting","The algorithm chosen in configuration files isn't implemented in the system").showAndWait();
            }
        }

    }

    @FXML
    void onBackButtonPress(ActionEvent event) {
        sortedListTxtArea.clear();
        importLegacyFileWindowGUI.getController().clearList();
        ((Node) event.getSource()).getScene().getWindow().hide();

    }

    private Alert createAlert(Alert.AlertType alertType, String header, String message) {


        Alert alert = new Alert(alertType);
        alert.setTitle("App");
        alert.setHeaderText(header);
        alert.setContentText(message);

        return alert;
    }

    private void updateSortedlist(ArrayList<VaccineAdministrationDTO> newList,String criteria){
        sortedListTxtArea.clear();
        StringBuilder s = new StringBuilder();
        for (VaccineAdministrationDTO vaccineAdministrationDTO : newList) {
            s.append(vaccineAdministrationDTO.toStringByCriteria(criteria));
            s.append("\n");
        }
        sortedListTxtArea.setText(s.toString().trim());
    }
}
