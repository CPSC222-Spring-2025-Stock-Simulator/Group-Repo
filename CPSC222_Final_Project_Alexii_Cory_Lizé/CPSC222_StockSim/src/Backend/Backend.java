package Backend ;

/**
 * This file is part of the final concurrency project
 * 		CPSC 222 Final Project Winter 2025
 *
 * Logic for starting and initializing backend
 *
 * @author Cory 230154922, Alexii 230154409, Lize 230157950
 * @version 2024.2.3
 */

import javafx.application.Platform ;

/**
 * main for all backend stuff. runs simulation and starts cycles
 */
public class Backend
{
    private static boolean isPaused = false ;
    private static boolean isRunning = true ;
    private static final StopWatch stopWatch = new StopWatch() ;
    private static boolean isSimulationFinished ;
    private static Stock stock;
    public static void runSimulation() throws InterruptedException
    {
        stock = new Stock(API.getStockStartPrice()) ;

        Thread guiUpdaterThread = new Thread(() -> {
            while (!isSimulationFinished)
            {
                try
                {
                    // wait for a moment to stop CPU from killing itself
                    Thread.sleep((long) (API.getCycleLength()*1000));  // update gui according to cycle length
                    if (Main.getStockGUI() != null)
                    {
                        Platform.runLater(() -> Main.getStockGUI().updateGUI()) ;
                    } else{
                        System.out.println("StockGUI is not initialized yet.") ;
                    }
                } catch (InterruptedException e){
                    e.printStackTrace();                // i don't know but it stopped crying
                }
            }
        }) ;

        guiUpdaterThread.start() ;  // Start the GUI update thread

        PersonManager personManager = new PersonManager() ;

        for (int cycle = 0; cycle < API.getCycleCount(); cycle++)
        {
            stopWatch.start() ;
            personManager.startDecisionProcess(stock) ;

            while (stopWatch.elapsed() < API.getCycleLength())
            {
                if (isPaused)
                {
                    Main.pause() ;  // Pause logic
                }
            }

            stopWatch.stop().reset() ;
        }

        isSimulationFinished = true ;  // End simulation and stop updating GUI
    }

    public static boolean isIsPaused()
    {
        return isPaused ;
    }

    public static boolean isIsRunning()
    {
        return isRunning ;
    }

    public static StopWatch getStopWatch()
    {
        return stopWatch ;
    }

    public static void setIsRunning(boolean b)
    {
        isRunning = b ;
    }

    public static void setIsPaused(boolean b)
    {
        isPaused = b ;
    }

    public static Stock getStock() {
        return stock;
    }
}
