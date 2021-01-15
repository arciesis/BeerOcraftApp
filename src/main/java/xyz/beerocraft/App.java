/*
 * @author Arciesis https://github.com/arciesis/BeerOCraft/
 *
 * The main class of the program
 */


package xyz.beerocraft;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import xyz.beerocraft.model.DBConnectionHandler;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;


public class App extends Application {

    /**
     * The method wich start the main window
     *
     * @param primaryStage the primary stage of the window
     * @throws Exception by javaFX
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("Start of the primary stage");
        //Parent root = FXMLLoader.load(getClass().getResource("resources/MainView.fxml"));
        primaryStage.setTitle("BeerOCraft");
        System.out.println(App.class.getResource("fxml/MainView.fxml"));

        Scene primaryScene = new Scene(loadFXML("/home/arciesis/dev/java/BeerOcraft/src/main/resources/fxml/MainView.fxml"), 1080, 720);
        primaryStage.setScene(primaryScene);
        primaryStage.centerOnScreen();
        primaryStage.show();

        primaryStage.setOnCloseRequest(windowEvent -> {
            System.out.println("stage is closing");

            try {
                DBConnectionHandler.myConn.close();
                System.out.println("DB is closed");
                DBConnectionHandler.onClosedRequestDB();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            primaryStage.close();

        });
    }


    public static Parent loadFXML(String absoluteFXML) throws IOException {

        Locale locale_fr_FR = new Locale("fr", "FR");
        ResourceBundle resourceBundle = ResourceBundle.getBundle("language.bundle", locale_fr_FR);
        //FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(absoluteFXML, resourceBundle);

        FXMLLoader fxmlLoader = new FXMLLoader();


        //String abslotuePathToFXML = "/home/arciesis/dev/java/BeerOcraft/src/main/resources/fxml/MainView.fxml";
        URL fxmlURL = new File(absoluteFXML).toURI().toURL();
        fxmlLoader.setLocation(fxmlURL);
        fxmlLoader.setResources(resourceBundle);


        return fxmlLoader.load();
    }

    /**
     * the main method of th program that launch all the app
     *
     * @param args the argument of the program
     */
    public static void main(String[] args) {
        launch(args);
    }
}