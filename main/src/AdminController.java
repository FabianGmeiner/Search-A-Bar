//Created by Fabian Gmeiner

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListView;
import model.Bar;
import statics.Statics;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Vector;


public class AdminController implements Initializable {

    @FXML
    public ListView mAdminListView;
    @FXML
    private Button mAdminButtonNew;
    @FXML
    private Button mAdminButtonEdit;
    @FXML
    private Button mAdminButtonDelete;

    private Main mMain;
    private Bar mSelectedBar = null;

    @Override // method called after the constructor, views have been injected
    public void initialize(URL location, ResourceBundle resources) {
        mAdminButtonNew.requestFocus();
        setListItems();
    }
    public void setMainApp(Main main) {
        this.mMain = main;
    }

    // methods to handle the buttons
    @FXML
    private void handleButtonLogout() throws IOException {
        mMain.showMainPage();
    }
    @FXML
    private void handleButtonChangePassword() throws IOException {
        mMain.showDialogPassword();
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
        Dialog dialog = new Alert(Alert.AlertType.INFORMATION);
        dialog.setHeaderText("Löschen");
        dialog.setContentText("Der gewählte Eintrag wurde gelöscht !");
        dialog.setOnCloseRequest(event -> dialog.close());
        dialog.show();
        setListItems();
    }

    // method to refresh the listView
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

    // method to react to selection-changes
    private void handleItemSelected(Object newValue) {
        mAdminButtonDelete.setDisable(false);
        mAdminButtonEdit.setDisable(false);
        mSelectedBar = (Bar) newValue;
    }
}
