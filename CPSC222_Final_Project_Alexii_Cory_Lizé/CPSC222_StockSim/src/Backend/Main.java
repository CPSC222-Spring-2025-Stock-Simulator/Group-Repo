package Backend;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import StockGUI.StockGUI;
import StockGUI.StartScreen;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{

    private static CLI cli;
    private static StockGUI gui;
    private static volatile boolean isRunning = false ;
    private static volatile boolean isPaused = false ;
    private static final StopWatch stopWatch = new StopWatch() ;

    public static void main(String[] args) throws InterruptedException
    {
        Random random = new Random();


        cli = new CLI(random);
        Thread t = new Thread(cli);
        t.start();
        // Start and run the Backend.CLI
        System.out.println("Pre launch");
        launch();
        // TODO: Start the GUI here
        System.out.println("Post launch");

        while (!isRunning) {
            Thread.onSpinWait();
        }
        // Wait for start to be typed in the Backend.CLI
        //TODO: If a start button has been implemented it should also pass this


        int startingMoney = cli.getStartingMoney();
        int peopleCount = cli.getPeople();
        int cycleCount = cli.getCycleCount();
        int startingStock = cli.getStartingStockPrice();
        // Grabs initial values. Will be the default values unless modified through the Backend.CLI


        Stock stock = new Stock(startingStock);

        System.out.print("PROGRAM STARTED\n$ ");
        //TODO: Remove this

        PersonManager personManager = new PersonManager();
        ArrayList<Person> people = personManager.getPeople();

        for (int i = 0; i < cycleCount; i++)                                       // this is where cycles happen
        {
            stopWatch.start() ;
            try {
                personManager.startDecisionProcess(stock, random) ;
            } catch (InterruptedException e) {
                throw new RuntimeException(e) ;
            }

            // past this point, all person threads have either bought/sold or neither
            // API has been updated with the most recent stuff
            // API may be accessed

            while (stopWatch.elapsed() < API.getCycleLength()) {
                if (isPaused)
                {
                    pause();
                }
            }
            stopWatch.stop().reset() ;

            gui.updateGUI() ;
        }




    }

    public static void pause() throws InterruptedException
    {
        stopWatch.stop() ;
        gui.pause() ;

        while (isPaused)
        {
            Thread.sleep(100) ;
        }

        gui.play();
        stopWatch.start() ;
    }


    public static void setIsRunning(boolean isRunning) {
        Main.isRunning = isRunning;
    }

    public static CLI getCli() {
        return Main.cli;
    }

    public static StockGUI getGui() {
        return Main.gui;
    }

    public static void setIsPaused(boolean isPaused) {
        Main.isPaused = isPaused;
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(StockGUI.class.getResource("StartScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        StartScreen startScreen = fxmlLoader.getController();

        stage.setOnCloseRequest( windowEvent -> {
            stage.close();
            System.exit(0);
        });

        stage.setTitle("Stock Simulator");
        stage.setScene(scene);
        stage.show();


    }
}