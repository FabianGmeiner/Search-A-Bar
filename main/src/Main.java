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
import statics.Statics;

import generificationUtil.PathFinder;
import generificationUtil.serializer.*;
import generificationUtil.logger.*;

import java.io.IOException;

@SuppressWarnings("SpellCheckingInspection")
public class Main extends Application {

    public static MainController mMainController;
    public static AdminController mAdminController;
    public static DialogController mDialogController;
    public static DialogRouteController mDialogRouteController;
    public static DialogMapsController mDialogMapsController;
    public static Graph mGraph;
    protected BorderPane rootLayout;
    private Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
	Logger.clearFiles();


        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Search-A-Bar");

        Statics statics = new Statics();
	/**Save/Load added by Daniel Knuettel*/
	if(Statics.isGraphSafed())
	{
		Logger.log(Logger.MSG,"Main:start(): graph is safed : loading from file "+PathFinder.getPrettyName(Statics.defaultSave)+"\n");	
		Deserializer deser=new Deserializer(PathFinder.getPrettyName(Statics.defaultSave));
		deser.readObjs();
		mGraph=(Graph)deser.getObject();

		if(mGraph==null)
		{
			Logger.log(Logger.WARN,"Main:start(): graph is safed :but cannot loaded from file: using hard coded version \n");
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

		}
			
	}
	else
	{
		Logger.log(Logger.MSG,"Main:start(): graph is not safed : using hard coded Version.\n");
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
	}

        initialiseRootLayout();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
		/**added by Daniel Knuettel*/
		Serializer ser=new Serializer(PathFinder.getPrettyName(Statics.defaultSave));
		ser.addObject(mGraph);
		ser.serialize();
		Logger.log(Logger.MSG,"Main:stop(): saving Graph\n");
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

}
