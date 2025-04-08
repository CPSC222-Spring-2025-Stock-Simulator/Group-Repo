package Backend;

import java.util.ArrayList;
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
    private static CLI cli;
    //private static volatile boolean isRunning = false ;
    //private static volatile boolean isPaused = false ;
    //private static final StopWatch stopWatch = new StopWatch() ;
    private static Random random;

    public static void main(String[] args) throws InterruptedException
    {

        System.out.println("some thing random");
        Random random = new Random();


        cli = new CLI(random) ;
        Thread t = new Thread(cli);
        t.start();
        // Start and run the Backend.CLI
        Thread javafxThread = new Thread(() -> Application.launch());
        javafxThread.start();

        // TODO: Start the GUI here

        while (!cli.running) {
            Thread.onSpinWait();
        }
        // Wait for start to be typed in the Backend.CLI
        //TODO: If a start button has been implemented it should also pass this


      /*  int startingMoney = cli.getStartingMoney() ;
        int peopleCount = cli.getPeople() ;
        int cycleCount = cli.getCycleCount() ;
        int startingStock = cli.getStartingStockPrice() ;
        // Grabs initial values. Will be the default values unless modified through the Backend.CLI




        Stock stock = new Stock(startingStock) ;

        System.out.print("PROGRAM STARTED\n$ ");
        //TODO: Remove this

        PersonManager personManager = new PersonManager() ;
        ArrayList<Person> people = personManager.getPeople() ;

        for (int i=0 ; i<cycleCount ; i++)                                       // this is where cycles happen
        {
            stopWatch.start() ;
            personManager.startDecisionProcess(stock, random) ;
            // past this point, all person threads have either bought/sold or neither
            // API has been updated with the most recent stuff
            // API may be accessed

            while (stopWatch.elapsed() < API.getCycleLength())
            {
                if (isPaused)
                    pause() ;                                       //TODO: make method to pause main thread
            }
            stopWatch.stop().reset() ;

            Platform.runLater(() -> stockGUI.updateGUI() );
        }*/
    }

    public static void pause() throws InterruptedException
    {
        Backend.getStopWatch().stop() ;
        StockGUI.pause() ;

        while (Backend.isIsPaused())
        {
            Thread.sleep(100) ;
        }

        StockGUI.play();
        Backend.getStopWatch().start() ;
    }

   /* public static void setIsRunning(boolean isRunning) {
        Backend.isIsRunning() = isRunning;
    }*/

    public static CLI getCli() {
        return cli;
    }

   /* public static void setIsPaused(boolean isPaused) {
        Main.isPaused = isPaused;
    }*/

   /* public static boolean getIsPaused() {
        return isPaused;
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