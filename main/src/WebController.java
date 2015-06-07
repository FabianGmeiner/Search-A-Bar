//Created by Fabian on 20.05.15.

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WebController implements Initializable{

    private WebView mWebView;
    private Main mMain;

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    public void setMainApp(Main main){
        this.mMain = main;
    }

    public void openLink(String url){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/LayoutWeb.fxml"));
        try {
            mWebView = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage webStage = new Stage();
        webStage.initModality(Modality.WINDOW_MODAL);
        webStage.initOwner(mMain.getPrimaryStage());
        Scene scene = new Scene(mWebView);
        webStage.setScene(scene);
        webStage.setTitle("Search-A-Bar");
        WebEngine webEngine = mWebView.getEngine();
        webEngine.load("http://" + url);
        webStage.setMaximized(true);

        webStage.showAndWait();
    }
}
