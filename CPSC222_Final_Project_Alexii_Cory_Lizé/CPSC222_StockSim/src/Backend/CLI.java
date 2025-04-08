package Backend;

import StockGUI.StockGUI;

import java.util.Random;
import java.util.Scanner;

public class CLI implements Runnable
{
    StockGUI stockGUI;
    Random random;
    Scanner scanner = new Scanner(System.in);

    private final String redText = "\u001B[31m";
    private final String greenText = "\u001B[32m";
    private final String cyanText = "\u001B[36m";
    private final String textReset = "\u001B[0m";
    // Text Colouring

    boolean running = false;
    private int startingMoney = API.getPeopleStartMoney();
    private int people = API.getPeopleAmount() ;
    private int cycleCount = API.getCycleCount() ;
    private double cycleLength = API.getCycleLength() ;
    private int startingStockPrice = API.getStockStartPrice() ;
    // By seconds


    /**
     * The Command Line Index for the program
     * @param r The Random object for the program
     */
    public CLI (Random r){
        random = r;
    }

    /**
     * Starts program
     */
    public void run(){
        System.out.println("$ " + greenText + "Welcome to the StockSim Backend.CLI type HELP for a list of commands" + textReset);
        running = true;
        while(running){
            requestInteraction();
        }
        System.exit(0);
    }

    /**
     * Runs the interactive user input command line
     */
    private void requestInteraction(){
        System.out.print("$ ");

        String[] userInput = scanner.nextLine().split(" ");
        // Takes user input and splits it into an array

        if(userInput.length == 0) userInput = new String[]{""};
        // Handles issues with empty arrays

        switch (userInput[0].toUpperCase()){
            //Takes the first written statement by the user and processes it
            case "HELP":
                // Displays the list of functions that the user can utilize
                help();
                break;
            case "START":
                System.out.println(cyanText + "Starting Program" + textReset);
                start();
                // Starts the main program
                break;
            case "QUIT":
                System.out.println(cyanText + "Quitting Program" + textReset);
                // Hard quits the program
                quit();
                break;
            case "PAUSE":
                // Temporarily pauses the program
                //TODO: Pause the program
                System.out.println(cyanText + "Program Paused" + textReset);
                break;
            case "PLAY":
                // Runs the program
                //TODO: Unpause the program
                System.out.println(cyanText + "Program Unpaused" + textReset);
                break;
            case "SETPEOPLE":
                // Sets the number of people
                if(userInput.length > 1){
                    // Requires an integer input in the second index
                    try {
                        int i =  Integer.parseInt(userInput[1]);
                        i = Math.abs(i);
                        setPeople(i);
                        API.setPeopleAmount(i);
                        System.out.println(cyanText + "People set to " + i + textReset);
                        // Get and process an integer value
                    } catch (Exception e){
                        System.out.println(redText + "Invalid Integer" + textReset);
                        // Handle non-integer values
                    }
                }
                else{
                    System.out.println(redText + "Invalid Input" + textReset);
                    // Handles the case in which no other input is given
                }
                break;
            case "SETMONEY":
                if(userInput.length > 1){
                    // Requires an integer input in the second index
                    try {
                        int i =  Integer.parseInt(userInput[1]);
                        i = Math.abs(i);
                        setStartingMoney(i);
                        API.setPeopleStartMoney(i);
                        System.out.println(cyanText + "Money set to " + i + textReset);
                        // Get and process an integer value
                    } catch (Exception e){
                        System.out.println(redText + "Invalid Integer" + textReset);
                        // Handle non-integer values
                    }
                }
                else{
                    System.out.println(redText + "Invalid Input" + textReset);
                    // Handles the case in which no other input is given
                }
                break;
            case "SETDELAY":
                if(userInput.length > 1){
                    // Requires a double input in the second index
                    try {
                        double i =  Double.parseDouble(userInput[1]);
                        i = Math.abs(i);
                        setCycleLength(i);
                        API.setCycleLength(i);
                        System.out.println(cyanText + "Delay set to " + i + " seconds " + textReset);
                        // Get and process a double value
                    } catch (Exception e){
                        System.out.println(redText + "Invalid Value" + textReset);
                        // Handle non-numeric values
                    }
                }
                else{
                    System.out.println(redText + "Invalid Input" + textReset);
                    // Handles the case in which no other input is given
                }
                break;
            case "SETCYCLES":
                if(userInput.length > 1){
                    // Requires an integer input in the second index
                    try {
                        int i =  Integer.parseInt(userInput[1]);
                        i = Math.abs(i);
                        setCycleCount(i);
                        API.setCycleCount(i);
                        System.out.println(cyanText + "Cycles set to " + i + textReset);
                        // Get and process an integer value
                    } catch (Exception e){
                        System.out.println(redText + "Invalid Integer" + textReset);
                        // Handle non-integer values
                    }
                }
                else{
                    System.out.println(redText + "Invalid Input" + textReset);
                    // Handles the case in which no other input is given
                }
                break;
            case "FORCEEVENT":
                if(userInput.length > 1){
                    // Requires an integer input in the second index
                    try {
                        int i =  Integer.parseInt(userInput[1]);
                        if(i<-10) i = -10;
                        if(i>10) i = 10;
                        // Preventing values that are out of range
                        forceEvent(i);
                        System.out.println(cyanText + "Event " + i + " executed" + textReset);
                        // Get and process an integer value
                    } catch (Exception e){
                        System.out.println(redText + "Invalid Integer" + textReset);
                        // Handle non-integer values
                    }
                }
                else{
                    // Chooses a random event if not given a specific value
                    int i = random.nextInt(0,21) - 10;
                    forceEvent(i);
                    System.out.println(cyanText + "Event " + i + " executed"+ textReset);
                }
                break;
            case "FORCEUPDATE":
                // Forces a GUI refresh
                //TODO: add this.
                System.out.println(cyanText + "GUI Refreshed"+ textReset);

                break;
            default:
                if(userInput.length > 1 || !userInput[0].isEmpty()){
                    // If given a nonempty input, prints an error message
                    System.out.println(redText + "Unknown Command" + textReset);
                }
        }
    }

    private void help(){
        System.out.println(
                greenText +
                """
                
                ┏━━━━━━━━━━━┓
                ┃ Functions ┃
                ┗━━━━━━━━━━━┛
                
                > START
                >> Starts the main program
                
                > QUIT
                >> Ends program immediately
                
                > PAUSE
                >> Stops program from running
                
                > PLAY
                >> Resumes program
                
                > SETPEOPLE [value]
                >> Sets the number of threads to make, 
                >> only works before the stock starts
                
                > SETMONEY [value]
                >> Sets the amount of money held by each thread, 
                >> only works before the stock starts
                
                > SETCYCLES [value]
                >> Sets the number of Cycles/Rounds that the program will complete
                >> before exiting,
                >> only works before the stock starts
                
                > SETDELAY [value]
                >> Sets the delay between GUI refreshes
                
                > FORCEEVENT
                >> Forces a random event to occur
                
                > FORCEEVENT [value] 
                >> Forces a specific event to occur
                
                > FORCEUPDATE
                >> Forces a refresh of the GUI
                """
                + textReset
        );
    }

    /**
     * Allows the main program to run
     */
    public void start(){
        Backend.setIsRunning(true);
    }

    /**
     * Ends program and closes
     */
    public void quit(){
        running = false;
        //TODO: End everything else

    }

    /**
     * Pauses program
     */
    public static void pause() throws InterruptedException {
        Backend.setIsPaused(true);
        Main.pause();
    }

    /**
     * Unpauses program
     */
    public static void play() throws InterruptedException {
        Backend.setIsPaused(false);
        Main.pause();
    }

    /**
     * Sets the number of people for the program
     * @param people The number of starting people
     */
    public void setPeople(int people){
        this.people = people;
    }

    /**
     * @return The number of starting people
     */
    public int getPeople() {
        return people;
    }

    /**
     * Sets the amount of money the people start with
     * @param startingMoney The amount of money each person starts with
     */
    public void setStartingMoney(int startingMoney){
        this.startingMoney = startingMoney;
    }

    /**
     * @return The amount of starting money
     */
    public int getStartingMoney(){
        return this.startingMoney;
    }

    /**
     * Sets the delay between cycles in seconds
     * @param cycleLength Delay between cycles in seconds (can be decimal)
     */
    public void setCycleLength(double cycleLength){
        this.cycleLength = cycleLength;
    }

    /**
     * @return Delay between cycles in seconds
     */
    public double getCycleLength() {
        return cycleLength;
    }

    /**
     * sets amount of cycle to occur before ending the program
     * @param cycleCount
     */
    public void setCycleCount(int cycleCount)
    {
        this.cycleCount = cycleCount ;
    }

    /**
     * @return returns amount of cycle to happen before program ends
     */
    public int getCycleCount()
    {
        return cycleCount ;
    }

    /**
     * Forces a certain event to occur
     * @param eventNum The corresponding event value
     */
    public void forceEvent(int eventNum){

    }

    /**
     * Forces a GUI refresh
     */
    public void forceUpdate(){
        stockGUI.updateGUI();
    }

    public int getStartingStockPrice() {
        return startingStockPrice;
    }

    public void setStartingStockPrice(int startingStockPrice) {
        this.startingStockPrice = startingStockPrice;
    }


}
