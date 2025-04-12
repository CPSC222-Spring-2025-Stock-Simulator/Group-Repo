package Backend;

/**
 * This file is part of the final concurrency project
 * 		CPSC 222 Final Project Winter 2025
 *
 * Main Class to start and initialize everything
 *
 * @author Cory 230154922, Alexii 230154409, Lize 230157950
 * @version 2024.2.3
 */

import java.util.Random;
import StockGUI.StockGUI;
import StockGUI.StartScreen;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class
 */
public class Main extends Application
{
    private static StockGUI stockGUI;
    private static CLI cli;
    private static Random random;

    /**
     * main method
     * @param args args
     * @throws InterruptedException exception
     */
    public static void main(String[] args) throws InterruptedException
    {
        Random random = new Random();


        cli = new CLI(random) ;
        Thread t = new Thread(cli);
        t.start();



        Thread javafxThread = new Thread(() -> Application.launch());
        javafxThread.start();
    }

    /**
     * used to pause cycle during runtime
     * @throws InterruptedException exception
     */
    public static void pause() throws InterruptedException
    {
        Backend.getStopWatch().stop();
        StockGUI.pause();

        while (Backend.isIsPaused())
        {
            Thread.onSpinWait();
            API.setEventType(null);
        }

        StockGUI.play();
        Backend.getStopWatch().start();
    }

    /*public static CLI getCli() {
        return cli;
    }*/

    /**
     * start the gui thread
     * @param stage stage
     * @throws Exception exception
     */
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

    /**
     * update gui
     */
    private static void updateGUIStatic() {
        Platform.runLater(() -> {
            if (stockGUI != null) {
                stockGUI.updateGUI();
            } else {
                System.out.println("StockGUI is not initialized yet.");
            }
        });
    }

    /**
     * set stock gui
     * @param gui stock gui
     */
    public static void setStockGUI(StockGUI gui) {
        stockGUI = gui;
    }

    /**
     * getter for stock gui
     * @return stock gui
     */
    public static StockGUI getStockGUI() {
        return stockGUI;
    }
}