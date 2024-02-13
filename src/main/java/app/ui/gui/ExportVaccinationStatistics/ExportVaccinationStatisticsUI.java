package app.ui.gui.ExportVaccinationStatistics;

import app.controller.ExportVaccinationStatisticsController;
import app.domain.model.Exceptions.MissingUserException;
import app.domain.model.Exceptions.TimeOutOfBoundsException;
import app.ui.console.utils.WriteFile;
import app.ui.gui.AnalyzePerformance.AnalyzePerformancePrintGUI;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class ExportVaccinationStatisticsUI implements Initializable {

    private ExportVaccinationStatisticsController theController;

    @FXML
    private DatePicker datePickerEnd;

    @FXML
    private TextField filePathField;

    @FXML
    private DatePicker datePickerStart;

    @FXML
    private Button returnBtn;

    @FXML
    private Button submitBtn;

    @FXML
    void getEndDate(ActionEvent event) {

    }

    @FXML
    void getFilePath(ActionEvent event) {

    }

    @FXML
    void getStartDate(ActionEvent event) {

    }

    @FXML
    void returnBtnClick(ActionEvent event) {
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    void submitBtnClick(ActionEvent event) {
        List<String> exportList;
        Date dateStart;
        Date dateEnd;
        String path;
        theController = new ExportVaccinationStatisticsController();
        Stage stagePrint = new Stage();
        try{
            ZoneId defaultZoneId = ZoneId.systemDefault();
            LocalDate localDate = datePickerStart.getValue();
            dateStart = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

            LocalDate localDate1 = datePickerEnd.getValue();
            dateEnd = Date.from(localDate1.atStartOfDay(defaultZoneId).toInstant());

            if(dateStart.after(dateEnd)){
                throw new TimeOutOfBoundsException();
            }else{
                exportList = theController.getUsersFullyVaccinatedOnIntervalAndCenter(dateStart,dateEnd);
            }

            Parent root;
            FXMLLoader loader;
            try {
                loader = new FXMLLoader(getClass().getResource("/ExportVaccinationStatisticsFxmlFiles/exportVaccinationsStatistics.fxml"));
                root = loader.load();
                Scene scenePrint = new Scene(root);
                stagePrint = new Stage();
                stagePrint.setTitle("Print and export Vaccination Statistics");
                stagePrint.setScene(scenePrint);
                stagePrint.setResizable(false);
                stagePrint.initModality(Modality.APPLICATION_MODAL);

                PrintVaccinationStatisticsAndExportUI printVaccinationStatisticsAndExportUI = loader.getController();
                printVaccinationStatisticsAndExportUI.setTextprint(exportList);
                stagePrint.show();
            } catch (IOException e) {
                Alert alertDate = new Alert(Alert.AlertType.ERROR);
                alertDate.setHeaderText("Error loading window");
                alertDate.showAndWait();
            }

        } catch (TimeOutOfBoundsException e) {
            Alert alertDate = new Alert(Alert.AlertType.ERROR);
            alertDate.setHeaderText("The second date cannot be earlier than the first.");
            alertDate.showAndWait();
        }catch(MissingUserException np){
            Alert alertMissing = new Alert(Alert.AlertType.ERROR);
            alertMissing.setHeaderText(np.getMessage());
            alertMissing.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }


}
