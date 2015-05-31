//Created by Fabian on 31.05.15.

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Bar;
import utils.GPSService;

import java.net.URL;
import java.util.ResourceBundle;

public class DialogMapsController implements Initializable{

    @FXML private Label mDialogResultLabel;
    @FXML private Button mDialogResultButtonCancel;
    @FXML private Button mDialogResultButtonOK;

    private Bar mBar;
    private Bar mDestination = null;
    private Main mMain;
    private Stage mDialogStage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    public void setMain(Main main) {
        mMain = main;
    }
    public void setDialogStage(Stage dialogStage) {
        mDialogStage = dialogStage;
    }
    public void setBar(Bar bar){
        mBar = bar;
    }
    public void setDestination(Bar bar){
        mDestination = bar;
        if(mBar != null && mDestination != null){
            mDialogResultLabel.setText(GPSService.getDistanceFromGPS(
                    mBar.getmGpsLatitude(),mBar.getmGpsLongitude(), mDestination.getmGpsLatitude(),mDestination.getmGpsLongitude())+"");
        }
    }

    @FXML
    private void handleButtonOK(){
        String lat1 = mBar.getmGpsLatitude()+"";
        String long1 = mBar.getmGpsLongitude()+"";
        String lat2 = mDestination.getmGpsLatitude()+"";
        String long2 = mDestination.getmGpsLongitude()+"";
        String googleMapsUrl = "google.de/maps/dir/"+lat1+","+long1+"/"+lat2+","+long2;

        WebController controller = new WebController();
        controller.setMainApp(this.mMain);
        controller.openLink(googleMapsUrl);
        mDialogStage.close();

    }
    @FXML
    private void handleButtonCancel(){
        mDialogStage.close();
    }

}
