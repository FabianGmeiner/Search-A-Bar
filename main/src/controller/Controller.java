package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Fabian on 18.05.15.
 */

public class Controller implements Initializable{

    // the FXML-Annotation allows JavaFX to inject the views based on their fx:id
    @FXML private TextField mTextFieldSearch;
    @FXML private ScrollPane mScrollPane;
    @FXML private ChoiceBox mFilter1;
    @FXML private ChoiceBox mFilter2;
    @FXML private ChoiceBox mFilter3;
    @FXML private ChoiceBox mFilter4;
    @FXML private ListView mListView;
    @FXML private Label mDescription;
    @FXML private Label mUrl;
    @FXML private Label mAdress;
    @FXML private ImageView mImageView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mDescription.setText("Dies ist nur ein Test.\n" +
                    "Wir haben zwar noch nichts \neingefuegt, " +
                    "aber es \nfunktioniert.");
        mAdress.setText("Musterbar\n" +
                    "Musterstraße 1\n" +
                    "93049 Regensburg");
        }
}
