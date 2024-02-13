package app.ui.gui.ExportVaccinationStatistics;

import app.domain.model.Exceptions.MissingUserException;
import app.ui.console.utils.WriteFile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PrintVaccinationStatisticsAndExportUI implements Initializable {

    List<String> exportList = new ArrayList<>();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private Button backButton;

    @FXML
    private TextArea textAreaId;

    @FXML
    private Button exportButtonId;

    @FXML
    private TextField filePath;

    @FXML
    private Label textprint;

    @FXML
    void onBackButton(ActionEvent event) {
        if (textAreaId != null) {
            textAreaId.clear();
        }
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    void onExportButtonPress(ActionEvent event) {
        String path = filePath.getText();
        if(path != null){
            try {
                if (exportList != null) {
                    WriteFile writeFile = new WriteFile(path, true);
                    for (String s : exportList) {
                        writeFile.WriteToFile(s);
                    }
                }
            }catch(IOException ioe){
                Alert alertDate = new Alert(Alert.AlertType.ERROR);
                alertDate.setHeaderText("There is no such file path.");
                alertDate.showAndWait();
            }
        }

    }

    public void setTextprint(List<String> list){
        exportList = list;
        for(String s : exportList){
            textAreaId.setText(s);
        }
    }

}
