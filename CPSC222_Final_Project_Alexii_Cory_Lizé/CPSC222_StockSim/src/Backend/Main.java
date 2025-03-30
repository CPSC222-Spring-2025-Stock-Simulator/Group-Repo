package Backend;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import StockGUI.StockGUI;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{
    private static CLI cli;
    private static StockGUI gui;
    private static volatile boolean isRunning = false ;
    private static volatile boolean isPaused = false ;
    public static void main(String[] args)
    {

        Random random = new Random();


        cli = new CLI(random) ;
        Thread t = new Thread(cli);
        t.start();
        // Start and run the Backend.CLI
        launch();
        // TODO: Start the GUI here

        while (!isRunning) {
            Thread.onSpinWait();
        }
        // Wait for start to be typed in the Backend.CLI
        //TODO: If a start button has been implemented it should also pass this


        int startingMoney = cli.getStartingMoney() ;
        int peopleCount = cli.getPeople() ;
        int cycleCount = cli.getCycleCount() ;
        int startingStock = cli.getStartingStockPrice() ;
        // Grabs initial values. Will be the default values unless modified through the Backend.CLI

        Stock stock = new Stock(startingStock) ;

        System.out.print("PROGRAM STARTED\n$ ");
        //TODO: Remove this

        while (isRunning)
        {
            PersonManager personManager = new PersonManager(peopleCount, startingMoney) ;
            ArrayList<Person> people = personManager.getPeople() ;
            for (int i=0 ; i<cycleCount ; i++)                                       // this is where cycles happen
            {

                // person buys, sells, or does nothing
                    // each person updates teh acceleration
                    //




                for (Person person : people)
                {
                    person.setStockPrice(stock.getPrice());

                }








            }
        }
    }

    public static void setIsRunning(boolean isRunning) {
        Main.isRunning = isRunning;
    }

    public static CLI getCli() {
        return cli;
    }

    public static StockGUI getGui() {
        return gui;
    }

    public static void setIsPaused(boolean isPaused) {
        Main.isPaused = isPaused;
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StockGUI.class.getResource("StockGUI.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Backend.Stock Simulator");
        stage.setScene(scene);
        stage.show();
    }
}