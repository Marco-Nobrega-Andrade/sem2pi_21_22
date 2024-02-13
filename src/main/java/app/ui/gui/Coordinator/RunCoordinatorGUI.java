package app.ui.gui.Coordinator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RunCoordinatorGUI extends Application implements Runnable{

    public void start(Stage coordinatorStage) throws Exception {
        coordinatorStage.setResizable(false);
        Parent root = FXMLLoader.load(getClass().getResource("/CoordinatorGUI.fxml"));
        coordinatorStage.setTitle("CoordinatorGUI");
        coordinatorStage.setScene(new Scene(root, 402, 400));
        coordinatorStage.show();
    }
    @Override
    public void run() {
        launch();
    }
    public static void main(String[] args) {
        launch();
    }

}
