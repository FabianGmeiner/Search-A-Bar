import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Fabian on 20.05.15.
 */
public class DialogController implements Initializable {

    // the FXML-Annotation allows JavaFX to inject the views based on their fx:id
    @FXML private Button mDialogButtonCancel;
    @FXML private Button mDialogButtonSave;
    @FXML private TextField mDialogTextFieldName;
    @FXML private TextField mDialogTextFieldDescription;
    @FXML private TextField mDialogTextFieldPrice;
    @FXML private TextField mDialogTextFieldRestriction;
    @FXML private TextField mDialogTextFieldAvg;
    @FXML private TextField mDialogTextFieldLat;
    @FXML private TextField mDialogTextFieldLong;
    @FXML private ChoiceBox mDialogChoiceBoxCategory;

    private Main mMain;
    private Stage mDialogStage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {}
    public void setMainApp(Main main) {
        this.mMain = main;
    }
    public void setDialogStage(Stage dialogStage) {
        this.mDialogStage = dialogStage;
    }

    @FXML
    private void handleButtonCancel(){
        mDialogStage.close();
    }
    @FXML
    private void handleButtonSave(){
        mDialogStage.close();
    }
}
