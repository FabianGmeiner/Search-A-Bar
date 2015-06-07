import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import model.Bar;
import statics.Statics;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Vector;


@SuppressWarnings("unchecked")
public class AdminController implements Initializable {

    @FXML
    public ListView mAdminListView;
    // the FXML-Annotation allows JavaFX to inject the views based on their fx:id
    @FXML
    private Button mAdminButtonEdit;
    @FXML
    private Button mAdminButtonDelete;
    private Main mMain;
    private Bar mSelectedBar = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setListItems();
    }

    public void setMainApp(Main main) {
        this.mMain = main;
    }

    @FXML
    private void handleButtonLogout() throws IOException {
        mMain.showMainPage();
    }

    @FXML
    private void handleButtonNew() throws IOException {
        mMain.showDialog(null, Statics.DIALOG_CODE_NEW);
    }

    @FXML
    private void handleButtonEdit() throws IOException {
        mMain.showDialog(mSelectedBar, Statics.DIALOG_CODE_EDIT);
    }

    @FXML
    private void handleButtonDelete() throws IOException {
        Main.mGraph.removeNode(mSelectedBar);
        setListItems();
        printGraphContent();
    }

    public void setListItems() {
        Vector<Bar> items = Main.mGraph.getAllBars();
        ObservableList<Bar> bars = FXCollections.observableArrayList();
        if (items == null) {
            mAdminListView.setItems(bars);
            mAdminListView.getSelectionModel().selectedItemProperty().addListener(
                    (ObservableValue observable, Object oldValue, Object newValue) -> handleItemSelected(newValue));
        } else {
            for (int i = 0; i < items.size(); i++) {
                bars.add(items.elementAt(i));
            }
            mAdminListView.setItems(bars);
            mAdminListView.getSelectionModel().selectedItemProperty().addListener(
                    (ObservableValue observable, Object oldValue, Object newValue) -> handleItemSelected(newValue));
        }
    }

    private void handleItemSelected(Object newValue) {
        mAdminButtonDelete.setDisable(false);
        mAdminButtonEdit.setDisable(false);
        mSelectedBar = (Bar) newValue;
    }

    private void printGraphContent() {
        System.out.println(Main.mGraph.mNodes);
        System.out.println(Main.mGraph.mEdges);
    }
}
