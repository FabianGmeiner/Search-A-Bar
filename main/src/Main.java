//Created by Fabian on 18.05.15.

import generificationUtil.PathFinder;
import generificationUtil.logger.Logger;
import generificationUtil.serializer.Deserializer;
import generificationUtil.serializer.Serializer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Bar;
import model.Graph;
import password.Password;
import password.PasswordStore;
import statics.Statics;

import java.io.IOException;

@SuppressWarnings("SpellCheckingInspection")
public class Main extends Application {

    public static MainController mMainController;
    public static AdminController mAdminController;
    public static DialogEditController mDialogEditController;
    public static DialogRouteController mDialogRouteController;
    public static DialogMapsController mDialogMapsController;
    public static DialogPasswordController mDialogPasswordController;
    public static Graph mGraph;
    public static String mPassword = Statics.ADMIN_PASSWORD;
    protected BorderPane rootLayout;
    private Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Logger.clearFiles();
        Logger.log(Logger.MSG, "Main:start() running\n");


        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Search-A-Bar");

        /**Save/Load added by Daniel Knuettel*/
        if (Statics.isGraphSafed()) {
            Logger.log(Logger.MSG, "Main:start(): graph is safed : loading from file " + PathFinder.getPrettyName(Statics.defaultSave) + "\n");
            Deserializer deser = new Deserializer(PathFinder.getPrettyName(Statics.defaultSave));
            deser.readObjs();
            mGraph = (Graph) deser.getObject();

            if (mGraph == null) {
                Logger.log(Logger.WARN, "Main:start(): graph is safed :but cannot loaded from file: using hard coded version \n");
                mGraph = new Graph();
                mGraph.printNodes();
                mGraph.printEdges();

            }

        } else {
            Logger.log(Logger.MSG, "Main:start(): graph is not safed : using hard coded Version.\n");
            mGraph = new Graph();
            mGraph.printNodes();
            mGraph.printEdges();
        }

        try {
            PasswordStore ps = new PasswordStore();
            ps.load();
            mPassword = ps.getPasswd("Fabian").getPasswd();
        } catch (Exception e) {
            Logger.log(Logger.MSG, "Exception in Application-Start: password could not be loaded." + e.toString() + "\n");
        }
        initialiseRootLayout();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        Logger.log(Logger.MSG, "Main:stop() called\n");
        /**added by Daniel Knuettel*/
        Serializer ser = new Serializer(PathFinder.getPrettyName(Statics.defaultSave));
        ser.addObject(mGraph);
        ser.serialize();
        Logger.log(Logger.MSG, "Main:stop(): saving Graph\n");

        PasswordStore ps = new PasswordStore();
        ps.addPasswd(new Password("Fabian", mPassword));
        ps.save();
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

    public Stage getPrimaryStage() {
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
        loader.setLocation(Main.class.getResource("view/LayoutDialogEdit.fxml"));
        AnchorPane dialog = loader.load();

        // creating the dialog stage
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        Scene scene = new Scene(dialog);
        dialogStage.setScene(scene);
        dialogStage.setTitle("Search-A-Bar");

        mDialogEditController = loader.getController();
        mDialogEditController.setDialogStage(dialogStage);
        mDialogEditController.setMainApp(this);
        mDialogEditController.setRequestCode(requestCode);
        mDialogEditController.fillDialog(bar);

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

    public void showDialogPassword() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/LayoutDialogPassword.fxml"));
        AnchorPane dialog = loader.load();

        // creating the dialog stage
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        Scene scene = new Scene(dialog);
        dialogStage.setScene(scene);
        dialogStage.setTitle("Search-A-Bar");

        mDialogPasswordController = loader.getController();
        mDialogPasswordController.setDialogStage(dialogStage);
        mDialogPasswordController.setMain(this);

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

}
