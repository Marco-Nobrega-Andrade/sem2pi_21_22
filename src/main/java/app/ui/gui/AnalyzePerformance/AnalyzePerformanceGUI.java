package app.ui.gui.AnalyzePerformance;

import app.controller.AnalyzePerformanceController;
import app.domain.model.Exceptions.ListIsEmptyException;
import app.domain.model.Exceptions.NullDateException;
import app.domain.model.Exceptions.TimeOutOfBoundsException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.text.html.ImageView;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

public class AnalyzePerformanceGUI implements Initializable {
    @FXML
    private DatePicker datePicker;

    @FXML
    private Button returnBtn;

    @FXML
    private Button submitBtn;

    @FXML
    private ImageView imageViewReturn;

    @FXML
    private ComboBox<Integer> timeIntervalsComboBox;
    private Stage stagePrint;

    @FXML
    private ComboBox<String> algorithmComboBox;

    @FXML
    void onDateSelection(ActionEvent event) {
    }

    @FXML
    void onTimeIntervalsSelection(ActionEvent event) {

    }


    @FXML
    void returnBtnClick(ActionEvent event) throws IOException {
        datePicker.getEditor().clear();
        timeIntervalsComboBox.getSelectionModel().clearSelection();
        algorithmComboBox.getSelectionModel().clearSelection();
        ((Node) event.getSource()).getScene().getWindow().hide();
    }
    private AnalyzePerformanceController controller = new AnalyzePerformanceController();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        timeIntervalsComboBox.getItems().addAll(1, 2, 3, 4, 5, 6, 8, 9, 10, 12, 15, 16, 18, 20, 24, 30, 36, 40, 45, 48, 60, 72, 80, 90, 120, 144, 180, 240, 360, 720);
        algorithmComboBox.getItems().addAll("Brute Force Algorithm", "Benchmark Algorithm");

    }

    @FXML
    void submitBtnClick(ActionEvent event) {
        Date date;
        int timeIntervals;
        String algorithmUsed;

        try {
            ZoneId defaultZoneId = ZoneId.systemDefault();
            LocalDate localDate = datePicker.getValue();
            if(localDate == null)
                throw new NullDateException();

            date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());


            Calendar today = Calendar.getInstance();
            Calendar calendarDate = Calendar.getInstance();
            calendarDate.setTime(date);
            today.set(Calendar.HOUR_OF_DAY, 0);

            if (calendarDate.after(today)) {
                throw new TimeOutOfBoundsException();
            } else {

                if (timeIntervalsComboBox.getValue() != null) {
                    if(algorithmComboBox.getValue() != null) {
                        timeIntervals = timeIntervalsComboBox.getValue();
                        algorithmUsed = algorithmComboBox.getValue();



                        try {
                            controller.analyzePerformance(date, timeIntervals, algorithmUsed);
                            Parent root;
                            FXMLLoader loader;
                            try {
                                loader = new FXMLLoader(getClass().getResource("/AnalyzePerformancePrint.fxml"));
                                root = loader.load();
                                Scene scenePrint = new Scene(root);
                                stagePrint = new Stage();
                                stagePrint.setTitle("Performance Analysis Data");
                                stagePrint.setScene(scenePrint);
                                stagePrint.setResizable(false);
                                stagePrint.initModality(Modality.APPLICATION_MODAL);

                                AnalyzePerformancePrintGUI analyzePerformancePrintUI = loader.getController();
                                analyzePerformancePrintUI.setPrintTxtField(controller.getPerformanceInfo());
                                stagePrint.show();
                            } catch (IOException e) {
                                Alert alertDate = new Alert(Alert.AlertType.ERROR);
                                alertDate.setHeaderText("Error loading window");
                                alertDate.showAndWait();
                            }
                        } catch (ListIsEmptyException e) {
                            Alert alertDate = new Alert(Alert.AlertType.ERROR);
                            if(e.getMessage().equals("UsersList")) {
                                alertDate.setHeaderText("There is no user registered in the system.");
                            }else{
                                alertDate.setHeaderText("There is no one on the center on the selected day.");
                            }
                            alertDate.showAndWait();
                        }
                    }else {
                        Alert alertDate = new Alert(Alert.AlertType.ERROR);
                        alertDate.setHeaderText("You need to select the type of algorithm used to proceed.");
                        alertDate.showAndWait();
                    }
                } else {
                    Alert alertDate = new Alert(Alert.AlertType.ERROR);
                    alertDate.setHeaderText("You need to select a time interval to proceed.");
                    alertDate.showAndWait();
                }
                }
        } catch (RuntimeException e) {
            Alert alertDate = new Alert(Alert.AlertType.ERROR);
            alertDate.setHeaderText("Some error occurred.");
            alertDate.showAndWait();
        } catch (TimeOutOfBoundsException e) {
            Alert alertDate = new Alert(Alert.AlertType.ERROR);
            alertDate.setHeaderText("You need to select a date before the current one to proceed.");
            alertDate.showAndWait();
        } catch (NullDateException e) {
            Alert alertDate = new Alert(Alert.AlertType.ERROR);
            alertDate.setHeaderText("You need to select a date to proceed.");
            alertDate.showAndWait();
        }

    }

    public AnalyzePerformanceController getController() {
        return controller;
    }
}