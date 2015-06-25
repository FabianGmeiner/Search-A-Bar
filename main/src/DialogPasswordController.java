//Created by Fabian Gmeiner on 31.05.15.

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class DialogPasswordController implements Initializable {

    @FXML
    private PasswordField mDialogPasswordOld;
    @FXML
    private PasswordField mDialogPasswordNew;
    @FXML
    private PasswordField mDialogPasswordConfirm;
    @FXML
    private Button mDialogPasswordOK;
    @FXML
    private Button mDialogPasswordCancel;


    private Main mMain;
    private Stage mDialogStage;

    @Override // method called after the constructor
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void setMain(Main main) {
        mMain = main;
    }
    public void setDialogStage(Stage dialogStage) {
        mDialogStage = dialogStage;
    }

    // methods to handle the buttons
    @FXML
    private void handleButtonOK() {

        Dialog warning = new Alert(Alert.AlertType.WARNING);
        warning.setHeaderText("Falsche Eingabe !");
        warning.setContentText("Bitte Ueberpruefen Sie ihre Eingabe !");
        warning.setOnCloseRequest(event -> warning.close());

        if (mDialogPasswordOld.getText().equals(Main.mPassword)
                && mDialogPasswordNew.getText().equals(mDialogPasswordConfirm.getText())) {

            Main.mPassword = mDialogPasswordNew.getText();
            mDialogStage.close();

        } else {
            warning.showAndWait();
        }
    }
    @FXML
    private void handleButtonCancel() {
        mDialogStage.close();
    }

}

