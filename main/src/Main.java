//Created by Fabian on 18.05.15.

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Bar;
import model.Graph;
import model.Node;

import statics.Statics;

import java.io.IOException;

@SuppressWarnings("SpellCheckingInspection")
public class Main extends Application {

    private Stage primaryStage;
    protected BorderPane rootLayout;

    public static MainController mMainController;
    public static AdminController mAdminController;
    public static DialogController mDialogController;
    public static DialogRouteController mDialogRouteController;
    public static DialogMapsController mDialogMapsController;
    public static Graph mGraph;

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Search-A-Bar");

        Statics statics = new Statics();
        mGraph = new Graph();
        mGraph.addNode(statics.getNode1());
        mGraph.addNode(statics.getNode2());
        mGraph.addNode(statics.getNode3());
        mGraph.addNode(statics.getNode4());
        mGraph.addNode(statics.getNode5());
        mGraph.addNode(statics.getNode6());
        mGraph.addNode(statics.getNode7());
        mGraph.printNodes();
        mGraph.printEdges();

        initialiseRootLayout();
    }

    @Override
    public void stop() throws Exception {
        System.out.println("Stopped");
        super.stop();
    }


    private void initialiseRootLayout() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/LayoutRoot.fxml"));
        rootLayout = loader.load();

        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);
        primaryStage.show();
        showMainPage();
    }
    public Stage getPrimaryStage (){
        return primaryStage;
    }
    public void showMainPage() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/LayoutMain.fxml"));
        AnchorPane mainPage = loader.load();

        rootLayout.setCenter(mainPage);
        mMainController = loader.getController();
        mMainController.setMainApp(this);
    }
    public void showAdminPage() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/LayoutAdmin.fxml"));
        AnchorPane adminPage = loader.load();

        rootLayout.setCenter(adminPage);
        mAdminController = loader.getController();
        mAdminController.setMainApp(this);
    }
    public void showDialog(Bar bar, int requestCode) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/LayoutDialog.fxml"));
        AnchorPane dialog = loader.load();

        // creating the dialog stage
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        Scene scene = new Scene(dialog);
        dialogStage.setScene(scene);
        dialogStage.setTitle("Search-A-Bar");

        mDialogController = loader.getController();
        mDialogController.setDialogStage(dialogStage);
        mDialogController.setMainApp(this);
        mDialogController.setRequestCode(requestCode);
        mDialogController.fillDialog(bar);

        // Show the dialog and wait until the user closes it
        dialogStage.showAndWait();
    }
    public void showDialogRoute(Bar bar) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/LayoutDialogRoute.fxml"));
        AnchorPane dialog = loader.load();

        // creating the dialog stage
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        Scene scene = new Scene(dialog);
        dialogStage.setScene(scene);
        dialogStage.setTitle("Search-A-Bar");

        mDialogRouteController = loader.getController();
        mDialogRouteController.setDialogStage(dialogStage);
        mDialogRouteController.setMain(this);
        mDialogRouteController.setBar(bar);

        // Show the dialog and wait until the user closes it
        dialogStage.showAndWait();
    }
    public void showDialogMaps(Bar bar, Bar destination) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/LayoutDialogMaps.fxml"));
        AnchorPane dialog = loader.load();

        // creating the dialog stage
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        Scene scene = new Scene(dialog);
        dialogStage.setScene(scene);
        dialogStage.setTitle("Search-A-Bar");

        mDialogMapsController = loader.getController();
        mDialogMapsController.setDialogStage(dialogStage);
        mDialogMapsController.setMain(this);
        mDialogMapsController.setBar(bar);
        mDialogMapsController.setDestination(destination);

        // Show the dialog and wait until the user closes it
        dialogStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
