//Created by Fabian on 18.05.15.

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.Bar;
import statics.Statics;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Vector;


@SuppressWarnings({"SpellCheckingInspection", "unchecked", "SuspiciousMethodCalls"})
public class MainController implements Initializable {

    ObservableList<String> mFilterCategory = FXCollections.observableArrayList();
    ObservableList<String> mFilterAgeRestriction = FXCollections.observableArrayList();
    ObservableList<String> mFilterAvgAge = FXCollections.observableArrayList();
    ObservableList<String> mFilterPrice = FXCollections.observableArrayList();
    // the FXML-Annotation allows JavaFX to inject the views based on their fx:id
    @FXML
    private TextField mTextFieldSearch;
    @FXML
    private PasswordField mTextFieldPassword;
    @FXML
    private Button mButtonSearch;
    @FXML
    private ListView mScrollPane;
    @FXML
    private ChoiceBox mFilter1;
    @FXML
    private ChoiceBox mFilter2;
    @FXML
    private ChoiceBox mFilter3;
    @FXML
    private ChoiceBox mFilter4;
    @FXML
    private Label mDescription;
    @FXML
    private Hyperlink mUrl;
    @FXML
    private Label mAdress;
    @FXML
    private Button mButtonCalculateRoute;
    @FXML
    private Button mButtonPlanTour;
    @FXML
    private ToggleButton mToggleButtonAlphabet;
    @FXML
    private ToggleButton mToggleButtonPopular;
    @FXML
    private ToggleButton mToggleButtonPrice;

    private Main mMain;
    private int[] mFilters = new int[4];
    private int mSortMode = Statics.SORT_CODE_ALPHABETICAL;
    private Vector<Bar> mCurrentList = null;
    private Bar mSelectedBar = null;
    private boolean mPasswordMode = false;

    public MainController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setListItems(Main.mGraph.sortListBy(Main.mGraph.getAllBars()));
        mFilterCategory.addAll(Statics.CATEGORYS);
        mFilter1.setItems(mFilterCategory);
        mFilter1.getSelectionModel().selectFirst();
        mFilter1.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue observable, Object oldValue, Object newValue) -> filterBarsCategory(newValue));
        mFilterAgeRestriction.addAll(Statics.AGE_RESTRICTIONS);
        mFilter2.setItems(mFilterAgeRestriction);
        mFilter2.getSelectionModel().selectFirst();
        mFilter2.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue observable, Object oldValue, Object newValue) -> filterBarsRestriction(newValue));
        mFilterAvgAge.addAll(Statics.AVG_AGE);
        mFilter3.setItems(mFilterAvgAge);
        mFilter3.getSelectionModel().selectFirst();
        mFilter3.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue observable, Object oldValue, Object newValue) -> filterBarsAverage(newValue));
        mFilterPrice.addAll(Statics.PRICE_RANGE);
        mFilter4.setItems(mFilterPrice);
        mFilter4.getSelectionModel().selectFirst();
        mFilter4.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue observable, Object oldValue, Object newValue) -> filterBarsPrice(newValue));
        mToggleButtonAlphabet.setSelected(true);
        mToggleButtonPopular.setSelected(false);
        mToggleButtonPrice.setSelected(false);
    }

    private void filterBarsPrice(Object newValue) {
        int index = mFilterPrice.indexOf(newValue);
        mFilters[3] = index;
        setListItems(Main.mGraph.getBarsFiltered(mFilters));
    }

    private void filterBarsAverage(Object newValue) {
        int index = mFilterAvgAge.indexOf(newValue);
        mFilters[2] = index;
        setListItems(Main.mGraph.getBarsFiltered(mFilters));
    }

    private void filterBarsRestriction(Object newValue) {
        int index = mFilterAgeRestriction.indexOf(newValue);
        mFilters[1] = index;
        setListItems(Main.mGraph.getBarsFiltered(mFilters));
    }

    private void filterBarsCategory(Object newValue) {
        int index = mFilterCategory.indexOf(newValue);
        mFilters[0] = index;
        setListItems(Main.mGraph.getBarsFiltered(mFilters));
    }

    private void showBarDetails(Object newValue) {
        if (newValue != null) {
            if (mMain.mGraph.mNodes.size() > 1) {
                mButtonCalculateRoute.setDisable(false);
            }
            mSelectedBar = (Bar) newValue;
            Bar selectedBar = (Bar) newValue;
            String description = selectedBar.getmDescription();
            String url = selectedBar.getmUrl();
            String adress = selectedBar.getmAdress();
            mDescription.setText(description);
            mUrl.setText(url);
            mAdress.setText(adress);
        } else {
            mSelectedBar = null;
            mButtonCalculateRoute.setDisable(true);
            mDescription.setText("");
            mUrl.setText("");
            mAdress.setText("");
        }
    }


    public void setMainApp(Main main) {
        this.mMain = main;
        mTextFieldSearch.requestFocus();
    }

    @FXML
    private void handleButtonSearch() throws IOException {
        if (!mPasswordMode) {
            if (mTextFieldSearch.getText().equals(Statics.ADMIN_CODE)) {
                mButtonSearch.setText("Anmelden");
                mTextFieldSearch.clear();
                mTextFieldSearch.setManaged(false);
                mTextFieldSearch.setVisible(false);
                mTextFieldPassword.setManaged(true);
                mTextFieldPassword.setVisible(true);

                mPasswordMode = true;
            }
            if (mTextFieldSearch.getText().isEmpty() ||
                    mTextFieldSearch.getText().equals("") ||
                    mTextFieldSearch.getText().contains("alle") ||
                    mTextFieldSearch.getText().contains("Alle") ||
                    mTextFieldSearch.getText().equalsIgnoreCase("alle")) {
                setListItems(Main.mGraph.getBarsFiltered(mFilters));
            } else {
                String search = mTextFieldSearch.getText();
                String numerical;
                String text;

                if (!search.isEmpty() && !search.equals("")) {
                    StringBuilder sbN = new StringBuilder();
                    StringBuilder sbT = new StringBuilder();
                    Vector<Bar> num = null;
                    Vector<Bar> alph = null;

                    for (char c : search.toCharArray()) {
                        if (Character.isDigit(c)) {
                            sbT.append(c);
                            sbN.append(c);
                        } else {
                            sbT.append(c);
                        }
                    }

                    numerical = sbN.toString();
                    text = sbT.toString();

                    if (!numerical.equals("") && !numerical.isEmpty()) {
                        num = Main.mGraph.getBarsFilteredByNumericalSearch(Integer.parseInt(numerical));
                    }
                    if (!text.equals("") && !text.isEmpty()) {
                        alph = Main.mGraph.getBarsFilteredByStringSearch(text);
                    }
                    Vector<Bar> result = new Vector<>();
                    if (num != null) {
                        for (int i = 0; i < num.size(); i++) {
                            result.add(num.elementAt(i));
                        }
                    }
                    if (alph != null) {
                        for (int i = 0; i < alph.size(); i++) {
                            result.add(alph.elementAt(i));
                        }
                    }
                    setListItems(result);
                }
            }
        } else {
            if (mTextFieldPassword.getText().equals(mMain.mPassword)) {
                mPasswordMode = false;
                mTextFieldSearch.setManaged(true);
                mTextFieldSearch.setVisible(true);
                mTextFieldPassword.setManaged(false);
                mTextFieldPassword.setVisible(false);
                mTextFieldPassword.clear();
                mButtonSearch.setText("Suche");
                mMain.showAdminPage();
            } else if (!mTextFieldPassword.getText().equals(mMain.mPassword)) {
                mPasswordMode = false;
                mTextFieldSearch.setManaged(true);
                mTextFieldSearch.setVisible(true);
                mTextFieldPassword.setManaged(false);
                mTextFieldPassword.setVisible(false);
                mButtonSearch.setText("Suche");
                mTextFieldPassword.clear();
            }
        }
    }

    @FXML
    private void handleLinkClicked() {
        WebController controller = new WebController();
        controller.setMainApp(mMain);
        controller.openLink(mUrl.getText());
    }

    @FXML
    private void handleButtonRoute() throws IOException {
        mMain.showDialogRoute(mSelectedBar);
    }

    @FXML
    private void handleButtonTour() {

    }

    private void setListItems(Vector<Bar> items) {
        mCurrentList = items;
        showBarDetails(null);
        updatePopularity(items);
        ObservableList<Bar> bars = FXCollections.observableArrayList();
        if (items == null) {
            mScrollPane.setItems(bars);
            mScrollPane.getSelectionModel().selectedItemProperty().addListener(
                    (ObservableValue observable, Object oldValue, Object newValue) -> showBarDetails(newValue));
        } else {
            for (int i = 0; i < items.size(); i++) {
                bars.add(items.elementAt(i));
            }
            mScrollPane.setItems(bars);
            mScrollPane.getSelectionModel().selectedItemProperty().addListener(
                    (ObservableValue observable, Object oldValue, Object newValue) -> showBarDetails(newValue));
        }
    }

    public void updatePopularity(Vector<Bar> bars) {
        for (int i = 0; i < bars.size(); i++) {
            bars.elementAt(i).increasePopularity();
        }
    }

    public void handleToggleButtonAlphabetic() throws IOException {
        mSortMode = Statics.SORT_CODE_ALPHABETICAL;
        Main.mGraph.setSortMode(mSortMode);
        mToggleButtonAlphabet.setSelected(true);
        mToggleButtonPopular.setSelected(false);
        mToggleButtonPrice.setSelected(false);
        setListItems(Main.mGraph.sortListBy(mCurrentList));
    }

    public void handleToggleButtonPopular() throws IOException {
        mSortMode = Statics.SORT_CODE_POPULAR;
        Main.mGraph.setSortMode(mSortMode);
        mToggleButtonAlphabet.setSelected(false);
        mToggleButtonPopular.setSelected(true);
        mToggleButtonPrice.setSelected(false);
        setListItems(Main.mGraph.sortListBy(mCurrentList));
    }

    public void handleToggleButtonPrice() throws IOException {
        mSortMode = Statics.SORT_CODE_PRICE;
        Main.mGraph.setSortMode(mSortMode);
        mToggleButtonAlphabet.setSelected(false);
        mToggleButtonPopular.setSelected(false);
        mToggleButtonPrice.setSelected(true);
        setListItems(Main.mGraph.sortListBy(mCurrentList));
    }
}
