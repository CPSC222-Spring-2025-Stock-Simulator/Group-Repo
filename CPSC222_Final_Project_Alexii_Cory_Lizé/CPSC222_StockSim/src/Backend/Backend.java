package Backend;

import javafx.application.Platform;

import java.util.Random;

public class Backend {
    private static boolean isPaused = false;
    private static boolean isRunning = true;
    private static StopWatch stopWatch = new StopWatch();
    private static long lastUpdateTime = 0;
    private static boolean isSimulationFinished;


    public static void runSimulation() throws InterruptedException {
        System.out.println("Simulation started");
        Stock stock = new Stock(API.getStockStartPrice());

        Thread guiUpdaterThread = new Thread(() -> {
            while (!isSimulationFinished) {
                try {
                    // Wait for a moment to avoid high CPU usage
                    Thread.sleep(1000);  // Update the GUI once every second
                    if (Main.getStockGUI() != null) {
                        Platform.runLater(() -> Main.getStockGUI().updateGUI());
                    } else {
                        System.out.println("StockGUI is not initialized yet.");
                    }                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        guiUpdaterThread.start();  // Start the GUI update thread

        PersonManager personManager = new PersonManager();
        Random rnd = new Random();

        for (int cycle = 0; cycle < API.getCycleCount(); cycle++) {
            stopWatch.start();
            personManager.startDecisionProcess(stock, rnd);

            while (stopWatch.elapsed() < API.getCycleLength()) {
                if (isPaused) {
                    Main.pause();  // Pause logic
                }
            }

            stopWatch.stop().reset();
        }

        isSimulationFinished = true;  // End simulation and stop updating GUI
    }

    public static boolean isIsPaused() {
        return isPaused;
    }

    public static boolean isIsRunning() {
        return isRunning;
    }

    public static StopWatch getStopWatch() {
        return stopWatch;
    }

    public static void setIsRunning(boolean b) {
    }

    public static void setIsPaused(boolean b) {
    }
}
