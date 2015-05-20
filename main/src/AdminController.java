import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class AdminController implements Initializable{

    // the FXML-Annotation allows JavaFX to inject the views based on their fx:id
    @FXML private Button mAdminButtonLogout;
    @FXML private Button mAdminButtonNew;
    @FXML private Button mAdminButtonEdit;
    @FXML private Button mAdminButtonDelete;

    private Main mMain;

    @Override
    public void initialize(URL location, ResourceBundle resources) {}
    public void setMainApp(Main main) {
        this.mMain = main;
    }

    @FXML
    private void handleButtonLogout() throws IOException {
        mMain.showMainPage();
    }
    @FXML
    private void handleButtonNew() throws IOException {
        mMain.showDialog();
    }
    @FXML
    private void handleButtonEdit() throws IOException {
        mMain.showDialog();
    }
    @FXML
    private void handleButtonDelete() throws IOException {
        mMain.showMainPage();
    }
}
