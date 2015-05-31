//Created by Fabian on 31.05.15.

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import model.Bar;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@SuppressWarnings("unchecked")
public class DialogRouteController implements Initializable{

    @FXML private ListView mDialogRouteList;
    @FXML private Button mDialogRouteButtonCancel;
    @FXML private Button mDialogRouteButtonOK;

    private Bar mBar;
    private Bar mDestination = null;
    private Main mMain;
    private Stage mDialogStage;

    public void setMain(Main main) {
        mMain = main;
    }
    public void setDialogStage(Stage dialogStage) {
        mDialogStage = dialogStage;
    }
    public void setBar(Bar bar){
        mBar = bar;
        setListItems();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    private void setListItems(){
        ObservableList<Bar> bars = FXCollections.observableArrayList();
        bars.addAll(Main.mGraph.getAllBars());
        bars.remove(mBar);
        mDialogRouteList.setItems(bars);
        mDialogRouteList.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue observable, Object oldValue, Object newValue) -> handleSelection(newValue)
        );
        mDialogRouteButtonOK.setDisable(true);
    }

    private void handleSelection(Object newValue) {
        mDestination = (Bar) newValue;
        mDialogRouteButtonOK.setDisable(false);
    }

    @FXML
    private void handleButtonCancel(){
        mDialogStage.close();
    }
    @FXML
    private void handleButtonOK() throws IOException {
        mMain.showDialogMaps(mBar, mDestination);
        mDialogStage.close();
    }
}
