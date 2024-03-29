package app.ui.gui.VaccineAdministration;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TestStartVaccineAdministrationGUI extends Application implements Runnable {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setResizable(false);
        Parent root = FXMLLoader.load(getClass().getResource("/VaccineAdministrationFxmlFiles/VaccineAdministrationShowAndSave.fxml"));
        stage.setTitle("Vaccine Administrations");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void run() {
        launch();
    }
}