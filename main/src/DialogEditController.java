//Created by Fabian Gmeiner on 20.05.15.

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Bar;
import model.Node;
import statics.Statics;
import utils.ValidInputCheck;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DialogEditController implements Initializable {

    // the FXML-Annotation allows JavaFX to inject the views based on their fx:id
    @FXML
    private TextField mDialogTextFieldName;
    @FXML
    private TextArea mDialogTextFieldDescription;
    @FXML
    private TextField mDialogTextFieldUrl;
    @FXML
    private TextArea mDialogTextFieldAdress;
    @FXML
    private TextField mDialogTextFieldPrice;
    @FXML
    private TextField mDialogTextFieldRestriction;
    @FXML
    private TextField mDialogTextFieldAvg;
    @FXML
    private TextField mDialogTextFieldLat;
    @FXML
    private TextField mDialogTextFieldLong;
    @FXML
    private ChoiceBox mDialogChoiceBoxCategory;

    private Main mMain;
    private Stage mDialogStage;
    private Bar mBar = null;
    private int mRequestCode;
    private ObservableList mList = FXCollections.observableArrayList();

    @Override // method called after the constructor, views are injected
    public void initialize(URL location, ResourceBundle resources) {
        mList.addAll(Statics.CATEGORYS);
        mDialogChoiceBoxCategory.setItems(mList);
        mDialogChoiceBoxCategory.getSelectionModel().selectFirst();
    }

    public void setMainApp(Main main) {
        this.mMain = main;
    }

    public void setDialogStage(Stage dialogStage) {
        this.mDialogStage = dialogStage;
        mDialogStage.getIcons().add(new Image("file:resources/images/ic_search_black_48dp.png"));
    }

    public void setRequestCode(int requestCode) {
        this.mRequestCode = requestCode;
    }

    // fills the textFields with informations from the bar (can be empty)
    public void fillDialog(Bar bar) {
        if (bar != null) {
            this.mBar = bar;
            mDialogTextFieldName.setText(bar.getmName());
            mDialogTextFieldDescription.setText(bar.getmDescription());
            mDialogTextFieldUrl.setText(bar.getmUrl());
            mDialogTextFieldAdress.setText(bar.getmAdress());
            mDialogTextFieldPrice.setText(bar.getmPrice() + "");
            mDialogTextFieldRestriction.setText(bar.getmAgeRestriction() + "");
            mDialogTextFieldAvg.setText(bar.getmAverageAge() + "");
            mDialogTextFieldLat.setText(bar.getmGpsLatitude() + "");
            mDialogTextFieldLong.setText(bar.getmGpsLongitude() + "");
            mDialogChoiceBoxCategory.getSelectionModel().select(bar.getmCategory());
        }
    }

    // methods to handle the buttons
    @FXML
    private void handleButtonCancel() {
        mDialogStage.close();
    }
    @FXML
    private void handleButtonSave() throws IOException {
        Dialog dialog = new Alert(Alert.AlertType.WARNING);
        dialog.setOnHiding(event -> dialog.close());
        dialog.setHeaderText("Achtung !");
        dialog.setContentText("Bitte geben sie gültige Werte ein.");
        dialog.setOnCloseRequest(event -> dialog.close());
        // bar already exists
        if (mRequestCode == Statics.DIALOG_CODE_EDIT) {
            if (!ValidInputCheck.isValidInput(mDialogTextFieldAvg.getText(), Statics.VALID_CODE_INT)) {
                mDialogTextFieldAvg.clear();
                mDialogTextFieldAvg.setPromptText("Gültigen Wert eingeben !");
                dialog.show();
            } else if (!ValidInputCheck.isValidInput(mDialogTextFieldRestriction.getText(), Statics.VALID_CODE_INT)) {
                mDialogTextFieldRestriction.clear();
                mDialogTextFieldRestriction.setPromptText("Gültigen Wert eingeben !");
                dialog.show();
            } else if (!ValidInputCheck.isValidInput(mDialogTextFieldPrice.getText(), Statics.VALID_CODE_DOUBLE)) {
                mDialogTextFieldPrice.clear();
                mDialogTextFieldPrice.setPromptText("Gültigen Wert eingeben !");
                dialog.show();
            } else if (!ValidInputCheck.isValidInput(mDialogTextFieldLat.getText(), Statics.VALID_CODE_DOUBLE)) {
                mDialogTextFieldLat.clear();
                mDialogTextFieldLat.setPromptText("Gültigen Wert eingeben !");
                dialog.showAndWait();
            } else if (!ValidInputCheck.isValidInput(mDialogTextFieldLong.getText(), Statics.VALID_CODE_DOUBLE)) {
                mDialogTextFieldLong.clear();
                mDialogTextFieldLong.setPromptText("Gültigen Wert eingeben !");
                dialog.show();
            } else {
                mBar.setName(mDialogTextFieldName.getText());
                mBar.setDescription(mDialogTextFieldDescription.getText());
                mBar.setUrl(mDialogTextFieldUrl.getText());
                mBar.setAdress(mDialogTextFieldAdress.getText());
                mBar.setPrice(Double.parseDouble(mDialogTextFieldPrice.getText()));
                mBar.setAgeRestriction(Integer.parseInt(mDialogTextFieldRestriction.getText()));
                mBar.setAverageAge(Integer.parseInt(mDialogTextFieldAvg.getText()));
                mBar.setGpsLatitude(Double.parseDouble(mDialogTextFieldLat.getText()));
                mBar.setGpsLongitude(Double.parseDouble(mDialogTextFieldLong.getText()));
                mBar.setCategory(mDialogChoiceBoxCategory.getSelectionModel().getSelectedIndex());

                mMain.showAdminPage();
                mDialogStage.close();
            }

        }
        // the bar has to be created first
        else if (mRequestCode == Statics.DIALOG_CODE_NEW) {
            if (!ValidInputCheck.isValidInput(mDialogTextFieldAvg.getText(), Statics.VALID_CODE_INT)) {
                mDialogTextFieldAvg.clear();
                mDialogTextFieldAvg.setPromptText("Gültigen Wert eingeben !");
                dialog.show();
            } else if (!ValidInputCheck.isValidInput(mDialogTextFieldRestriction.getText(), Statics.VALID_CODE_INT)) {
                mDialogTextFieldRestriction.clear();
                mDialogTextFieldRestriction.setPromptText("Gültigen Wert eingeben !");
                dialog.show();
            } else if (!ValidInputCheck.isValidInput(mDialogTextFieldPrice.getText(), Statics.VALID_CODE_DOUBLE)) {
                mDialogTextFieldPrice.clear();
                mDialogTextFieldPrice.setPromptText("Gültigen Wert eingeben !");
                dialog.show();
            } else if (!ValidInputCheck.isValidInput(mDialogTextFieldLat.getText(), Statics.VALID_CODE_DOUBLE)) {
                mDialogTextFieldLat.clear();
                mDialogTextFieldLat.setPromptText("Gültigen Wert eingeben !");
                dialog.show();
            } else if (!ValidInputCheck.isValidInput(mDialogTextFieldLong.getText(), Statics.VALID_CODE_DOUBLE)) {
                mDialogTextFieldLong.clear();
                mDialogTextFieldLong.setPromptText("Gültigen Wert eingeben !");
                dialog.show();
            } else {
                mBar = new Bar(
                        mDialogTextFieldName.getText(),
                        mDialogTextFieldDescription.getText(),
                        mDialogTextFieldUrl.getText(),
                        mDialogTextFieldAdress.getText(),
                        mDialogChoiceBoxCategory.getSelectionModel().getSelectedIndex(),
                        Double.parseDouble(mDialogTextFieldLat.getText()),
                        Double.parseDouble(mDialogTextFieldLong.getText()),
                        Double.parseDouble(mDialogTextFieldPrice.getText()),
                        Integer.parseInt(mDialogTextFieldRestriction.getText()),
                        Integer.parseInt(mDialogTextFieldAvg.getText()));

                Main.mGraph.addNode(new Node(mBar));

                mMain.showAdminPage();
                mDialogStage.close();
            }
        }
        Dialog dialogInfo = new Alert(Alert.AlertType.INFORMATION);
        dialogInfo.setHeaderText("Eintrag");
        dialogInfo.setContentText("Ihre Änderungen wurden gespeichert !");
        dialogInfo.setOnCloseRequest(event -> dialogInfo.close());
        dialogInfo.show();
    }
}
