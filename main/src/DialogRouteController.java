//Created by Fabian Gmeiner on 31.05.15.

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Bar;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DialogRouteController implements Initializable {

    @FXML
    private ListView mDialogRouteList;
    @FXML
    private Button mDialogRouteButtonCancel;
    @FXML
    private Button mDialogRouteButtonOK;

    private Bar mBar;
    private Bar mDestination = null;
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
        mDialogStage.getIcons().add(new Image("file:resources/images/ic_search_black_48dp.png"));
    }
    public void setBar(Bar bar) {
        mBar = bar;
        setListItems();
    }

    // method to refresh the listView
    private void setListItems() {
        ObservableList<Bar> bars = FXCollections.observableArrayList();
        bars.addAll(Main.mGraph.getAllBars());
        bars.remove(mBar);
        mDialogRouteList.setItems(bars);
        mDialogRouteList.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue observable, Object oldValue, Object newValue) -> handleSelection(newValue)
        );
        mDialogRouteButtonOK.setDisable(true);
    }

    // method to handle selection-changes
    private void handleSelection(Object newValue) {
        mDestination = (Bar) newValue;
        mDialogRouteButtonOK.setDisable(false);
    }

    // methods to handle the buttons
    @FXML
    private void handleButtonCancel() {
        mDialogStage.close();
    }
    @FXML
    private void handleButtonOK() throws IOException {
        mMain.showDialogMaps(mBar, mDestination);
        mDialogStage.close();
    }
}
