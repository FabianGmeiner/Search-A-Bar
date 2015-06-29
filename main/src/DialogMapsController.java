//Created by Fabian on 31.05.15.

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Bar;
import utils.GPSService;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Vector;

public class DialogMapsController implements Initializable {

    @FXML
    private Label mDialogResultLabel;
    @FXML
    private Label mDialogResultScaleLabel;

    private Bar mBar;
    private Bar mDestination = null;
    private Main mMain;
    private Stage mDialogStage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void setMain(Main main) {
        mMain = main;
    }

    public void setDialogStage(Stage dialogStage) {
        mDialogStage = dialogStage;
    }

    public void setBar(Bar bar) {
        mBar = bar;
    }

    public void setDestination(Bar bar) {
        mDestination = bar;
        if (mBar != null && mDestination != null) {
            double distance = GPSService.getDistanceFromGPS(
                    mBar.getmGpsLatitude(), mBar.getmGpsLongitude(), mDestination.getmGpsLatitude(), mDestination.getmGpsLongitude());
            if (distance > 1000.0) {
                mDialogResultLabel.setText((Math.round(distance / 1000)) + "");
                mDialogResultScaleLabel.setText("km");
            } else {
                mDialogResultLabel.setText(Math.round(distance / 10) * 10 + "");
                mDialogResultScaleLabel.setText("m");
            }
        }
    }

    @FXML
    private void handleButtonOK() {

        mMain.mGraph.initialiseGraph();
        Vector<Bar> dirtyResult = mMain.mGraph.depthSearchDestination(mBar, mDestination);
        Vector<Bar> result = new Vector<>();
        for (int i = 0; i < dirtyResult.size(); i++) {
            if (!result.contains(dirtyResult.elementAt(i))) {
                result.add(dirtyResult.elementAt(i));
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("www.google.de/maps/dir/");
        for (int i = 0; i < result.size(); i++) {
            if (i == result.size() - 1) {
                sb.append(result.elementAt(i).getmGpsLatitude() + "," + result.elementAt(i).getmGpsLongitude() + "/");
            } else if (!result.elementAt(i).equals(result.elementAt(i + 1))) {
                sb.append(result.elementAt(i).getmGpsLatitude() + "," + result.elementAt(i).getmGpsLongitude() + "/");
            }
        }
        String url = sb.toString();
        System.out.println(url);
        System.out.println(result);
        WebController controller = new WebController();
        controller.setMainApp(this.mMain);
        controller.openLink(url);
        mDialogStage.close();
    }

    @FXML
    private void handleButtonDirect() {

        String lat1 = mBar.getmGpsLatitude() + "";
        String long1 = mBar.getmGpsLongitude() + "";
        String lat2 = mDestination.getmGpsLatitude() + "";
        String long2 = mDestination.getmGpsLongitude() + "";
        String googleMapsUrl = "google.de/maps/dir/" + lat1 + "," + long1 + "/" + lat2 + "," + long2;

        WebController controller = new WebController();
        controller.setMainApp(this.mMain);
        controller.openLink(googleMapsUrl);
        mDialogStage.close();
    }

    @FXML
    private void handleButtonCancel() {
        mDialogStage.close();
    }

}
