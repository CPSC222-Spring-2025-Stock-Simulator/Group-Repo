package Backend;

import java.util.Random;
import StockGUI.StockGUI;
import StockGUI.StartScreen;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{
    private static StockGUI stockGUI;
    //private static CLI cli;
    private static Random random;

    public static void main(String[] args) throws InterruptedException
    {
        Random random = new Random();


        /*cli = new CLI(random) ;
        Thread t = new Thread(cli);
        t.start();

         */

        Thread javafxThread = new Thread(() -> Application.launch());
        javafxThread.start();
    }

    public static void pause() throws InterruptedException
    {
        Backend.getStopWatch().stop();
        StockGUI.pause();

        while (Backend.isIsPaused())
        {
            Thread.sleep(100) ;
            API.setEventType(null);
        }

        StockGUI.play();
        Backend.getStopWatch().start();
    }

    /*public static CLI getCli() {
        return cli;
    }*/

    @Override
    public void start(Stage stage) throws Exception {
        
        FXMLLoader fxmlLoader = new FXMLLoader(StockGUI.class.getResource("StartScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        StartScreen startScreen = fxmlLoader.getController();

        stage.setOnCloseRequest( windowEvent -> {
            stage.close();
            System.exit(0);
        });

        stockGUI = startScreen.getStockGUI();

        if (stockGUI != null) {
            System.out.println("StockGUI is loaded successfully.");
        } else {
            System.out.println("Error: StockGUI is not initialized.");
        }

        stage.setTitle("Stock Simulator");
        stage.setScene(scene);
        stage.show();

        updateGUIStatic();
    }

    private static void updateGUIStatic() {
        Platform.runLater(() -> {
            if (stockGUI != null) {
                stockGUI.updateGUI();
            } else {
                System.out.println("StockGUI is not initialized yet.");
            }
        });
    }

    public static void setStockGUI(StockGUI gui) {
        stockGUI = gui;
    }
    public static StockGUI getStockGUI() {
        return stockGUI;
    }
}