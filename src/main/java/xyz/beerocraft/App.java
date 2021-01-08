/**
 * @author Arciesis https://github.com/arciesis/BeerOCraft/
 * <p>
 * The main class of the program
 */


package xyz.beerocraft;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import xyz.beerocraft.model.DBConnectionHandler;

import java.io.IOException;
import java.sql.SQLException;


public class App extends Application {

    private static Scene primaryScene;

    /**
     * The method wich start the main window
     *
     * @param primaryStage the primary stage of the window
     * @throws Exception by javaFX
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        System.out.println("Start of the primary stage");
        //Parent root = FXMLLoader.load(getClass().getResource("resources/MainView.fxml"));
        primaryStage.setTitle("BeerOCraft");
        primaryScene = new Scene(loadFXML("/MainView"),1280,720);
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

   /* static void setRoot(String fxml) throws IOException {
        primaryScene.setRoot(loadFXML(fxml));
    }*/

    private static Parent loadFXML(String fxml) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
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