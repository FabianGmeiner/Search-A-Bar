//Created by Fabian on 20.05.15.

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Bar;
import model.Edge;
import model.Node;
import statics.Statics;
import utils.GPSService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class DialogController implements Initializable {

    // the FXML-Annotation allows JavaFX to inject the views based on their fx:id
    @FXML private TextField mDialogTextFieldName;
    @FXML private TextArea mDialogTextFieldDescription;
    @FXML private TextField mDialogTextFieldUrl;
    @FXML private TextArea mDialogTextFieldAdress;
    @FXML private TextField mDialogTextFieldPrice;
    @FXML private TextField mDialogTextFieldRestriction;
    @FXML private TextField mDialogTextFieldAvg;
    @FXML private TextField mDialogTextFieldLat;
    @FXML private TextField mDialogTextFieldLong;
    @FXML private ChoiceBox mDialogChoiceBoxCategory;

    private Main mMain;
    private AdminController mAdmin = Main.mAdminController;
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

    //TODO: null-pointer !
    private void changeCategory(Object newValue) {
        int index = list.indexOf(newValue);
        bar.setCategory(index);
    }

    public void setMainApp(Main main) {
        this.mMain = main;
    }
    public void fillDialog(Bar bar){
        if(bar != null) {
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
    private void handleButtonCancel(){
        mDialogStage.close();
    }
    @FXML //TODO: add savety !
    private void handleButtonSave() throws IOException {
        if(requestCode == Statics.DIALOG_CODE_EDIT) {
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
        else if (requestCode == Statics.DIALOG_CODE_NEW){
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
                    Integer.parseInt(mDialogTextFieldAvg.getText())
            );
            Main.mGraph.addNode(new Node(bar));
            int index = Main.mGraph.indexOf(new Node(bar));
            double distance = Integer.MAX_VALUE;
            int indexNearest = -1;
            for(int i = 0; i < Main.mGraph.mNodes.size() ; i++){
                if(i != Main.mGraph.indexOf(new Node(bar))) {
                    double distanceBars = GPSService.getDistanceFromGPS(
                            bar.getmGpsLatitude(),
                            bar.getmGpsLongitude(),
                            ((Bar)Main.mGraph.mNodes.elementAt(i).getContent()).getmGpsLatitude(),
                            ((Bar)Main.mGraph.mNodes.elementAt(i).getContent()).getmGpsLongitude());
                    if(distanceBars < distance){
                        indexNearest = i;
                    }
                }
            }
            Main.mGraph.addEdge(new Edge(
                    Main.mGraph.mNodes.elementAt(index),
                    Main.mGraph.mNodes.elementAt(indexNearest),
                    distance
            ));

            printGraphContent();

            mMain.showAdminPage();
            mDialogStage.close();
        }
    }
    private void printGraphContent(){
        System.out.println(Main.mGraph.mNodes);
        System.out.println(Main.mGraph.mEdges);
    }

}
