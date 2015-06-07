//Created by Fabian on 20.05.15.

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Bar;
import model.Node;
import statics.Statics;
import utils.ValidCheck;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


@SuppressWarnings("unchecked")
public class DialogController implements Initializable {

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
    private Bar bar = null;
    private int requestCode;
    private ObservableList list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        list.addAll(Statics.CATEGORYS);
        mDialogChoiceBoxCategory.setItems(list);
        mDialogChoiceBoxCategory.getSelectionModel().selectFirst();
        mDialogChoiceBoxCategory.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue observable, Object oldValue, Object newValue) -> changeCategory(newValue));
    }

    private void changeCategory(Object newValue) {
        int index = list.indexOf(newValue);
        bar.setCategory(index);
    }

    public void setMainApp(Main main) {
        this.mMain = main;
    }

    public void fillDialog(Bar bar) {
        if (bar != null) {
            this.bar = bar;
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

    public void setDialogStage(Stage dialogStage) {
        this.mDialogStage = dialogStage;
    }

    public void setRequestCode(int requestCode) {
        this.requestCode = requestCode;
    }

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
        dialog.setOnCloseRequest(new EventHandler<DialogEvent>() {
            @Override
            public void handle(DialogEvent event) {
                dialog.close();
            }
        });
        if (requestCode == Statics.DIALOG_CODE_EDIT) {
            if (!ValidCheck.isValidInput(mDialogTextFieldAvg.getText(), Statics.VALID_CODE_INT)) {
                mDialogTextFieldAvg.clear();
                mDialogTextFieldAvg.setPromptText("Gültigen Wert eingeben !");
                dialog.show();
            } else if (!ValidCheck.isValidInput(mDialogTextFieldRestriction.getText(), Statics.VALID_CODE_INT)) {
                mDialogTextFieldRestriction.clear();
                mDialogTextFieldRestriction.setPromptText("Gültigen Wert eingeben !");
                dialog.show();
            } else if (!ValidCheck.isValidInput(mDialogTextFieldPrice.getText(), Statics.VALID_CODE_DOUBLE)) {
                mDialogTextFieldPrice.clear();
                mDialogTextFieldPrice.setPromptText("Gültigen Wert eingeben !");
                dialog.show();
            } else if (!ValidCheck.isValidInput(mDialogTextFieldLat.getText(), Statics.VALID_CODE_DOUBLE)) {
                mDialogTextFieldLat.clear();
                mDialogTextFieldLat.setPromptText("Gültigen Wert eingeben !");
                dialog.showAndWait();
            } else if (!ValidCheck.isValidInput(mDialogTextFieldLong.getText(), Statics.VALID_CODE_DOUBLE)) {
                mDialogTextFieldLong.clear();
                mDialogTextFieldLong.setPromptText("Gültigen Wert eingeben !");
                dialog.show();
            } else {
                bar.setName(mDialogTextFieldName.getText());
                bar.setDescription(mDialogTextFieldDescription.getText());
                bar.setUrl(mDialogTextFieldUrl.getText());
                bar.setAdress(mDialogTextFieldAdress.getText());
                bar.setPrice(Double.parseDouble(mDialogTextFieldPrice.getText()));
                bar.setAgeRestriction(Integer.parseInt(mDialogTextFieldRestriction.getText()));
                bar.setAverageAge(Integer.parseInt(mDialogTextFieldAvg.getText()));
                bar.setGpsLatitude(Double.parseDouble(mDialogTextFieldLat.getText()));
                bar.setGpsLongitude(Double.parseDouble(mDialogTextFieldLong.getText()));

                printGraphContent();

                mMain.showAdminPage();
                mDialogStage.close();
            }

        } else if (requestCode == Statics.DIALOG_CODE_NEW) {
            if (!ValidCheck.isValidInput(mDialogTextFieldAvg.getText(), Statics.VALID_CODE_INT)) {
                mDialogTextFieldAvg.clear();
                mDialogTextFieldAvg.setPromptText("Gültigen Wert eingeben !");
                dialog.show();
            } else if (!ValidCheck.isValidInput(mDialogTextFieldRestriction.getText(), Statics.VALID_CODE_INT)) {
                mDialogTextFieldRestriction.clear();
                mDialogTextFieldRestriction.setPromptText("Gültigen Wert eingeben !");
                dialog.show();
            } else if (!ValidCheck.isValidInput(mDialogTextFieldPrice.getText(), Statics.VALID_CODE_DOUBLE)) {
                mDialogTextFieldPrice.clear();
                mDialogTextFieldPrice.setPromptText("Gültigen Wert eingeben !");
                dialog.show();
            } else if (!ValidCheck.isValidInput(mDialogTextFieldLat.getText(), Statics.VALID_CODE_DOUBLE)) {
                mDialogTextFieldLat.clear();
                mDialogTextFieldLat.setPromptText("Gültigen Wert eingeben !");
                dialog.show();
            } else if (!ValidCheck.isValidInput(mDialogTextFieldLong.getText(), Statics.VALID_CODE_DOUBLE)) {
                mDialogTextFieldLong.clear();
                mDialogTextFieldLong.setPromptText("Gültigen Wert eingeben !");
                dialog.show();
            } else {
                bar = new Bar(
                        mDialogTextFieldName.getText(),
                        mDialogTextFieldDescription.getText(),
                        mDialogTextFieldUrl.getText(),
                        mDialogTextFieldAdress.getText(),
                        0,
                        Double.parseDouble(mDialogTextFieldLat.getText()),
                        Double.parseDouble(mDialogTextFieldLong.getText()),
                        Double.parseDouble(mDialogTextFieldPrice.getText()),
                        Integer.parseInt(mDialogTextFieldRestriction.getText()),
                        Integer.parseInt(mDialogTextFieldAvg.getText()));

                Main.mGraph.addNode(new Node(bar));

                printGraphContent();

            }

        }
    }

    private void printGraphContent() {
        System.out.println(Main.mGraph.mNodes);
        System.out.println(Main.mGraph.mEdges);
    }

}
