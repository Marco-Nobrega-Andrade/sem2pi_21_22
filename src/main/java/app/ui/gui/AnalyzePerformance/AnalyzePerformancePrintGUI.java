package app.ui.gui.AnalyzePerformance;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;



public class AnalyzePerformancePrintGUI implements Initializable {


        @FXML
        private TextArea printTxtArea;

        @FXML
        private Button returnBtn;

        @FXML
        void returnBtnClick(ActionEvent event) {
            if (printTxtArea != null) {
                printTxtArea.clear();
            }
            ((Node) event.getSource()).getScene().getWindow().hide();
        }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setPrintTxtField(String info){
        printTxtArea.setText(info);
    }


}
