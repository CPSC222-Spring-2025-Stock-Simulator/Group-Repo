import java.util.Random;
import java.util.Scanner;

public class CLI implements Runnable{

    Random random;
    boolean running;
    String redText = "\u001B[31m";
    String greenText = "\u001B[32m";
    String cyanText = "\u001B[36m";
    String textReset = "\u001B[0m";
    private int startingMoney = 1000 ;
    private int people = 100 ;
    private double cycleLength = 1.0 ;
    private int cycleCount = 50 ;
    // Delay is measured in seconds

    Scanner scanner = new Scanner(System.in);

    public CLI (Random r){
        random = r;
    }

    /**
     * Starts program
     */
    public void run(){
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
            case "QUIT":
                // Hard quits the program
                running = false;
                //TODO: End everything else
                break;
            case "PAUSE":
                // Temporarily pauses the program
                //TODO: Pause the program
                break;
            case "PLAY":
                // Runs the program
                //TODO: Unpause the program
                break;
            case "SETPEOPLE":
                // Sets the number of people
                if(userInput.length > 1){
                    // Requires an integer input in the second index
                    try {
                        int i =  Integer.parseInt(userInput[1]);
                        i = Math.abs(i);
                        setPeople(i);
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
                    try {
                        int i =  Integer.parseInt(userInput[1]);
                        i = Math.abs(i);
                        setStartingMoney(i);
                    } catch (Exception e){
                        System.out.println(redText + "Invalid Integer" + textReset);
                    }
                }
                else{
                    System.out.println(redText + "Invalid Input" + textReset);
                }
                break;
            case "SETDELAY":
                if(userInput.length > 1){
                    try {
                        int i =  Integer.parseInt(userInput[1]);
                        i = Math.abs(i);
                        setCycleLength(i);
                    } catch (Exception e){
                        System.out.println(redText + "Invalid Integer" + textReset);
                    }
                }
                else{
                    System.out.println(redText + "Invalid Input" + textReset);
                }
                break;
            case "SETCYCLES":
                if(userInput.length > 1){
                    try {
                        int i =  Integer.parseInt(userInput[1]);
                        i = Math.abs(i);
                        setCycleCount(i);
                    } catch (Exception e){
                        System.out.println(redText + "Invalid Integer" + textReset);
                    }
                }
                else{
                    System.out.println(redText + "Invalid Input" + textReset);
                }
                break;
            case "FORCEEVENT":
                if(userInput.length > 1){
                    try {
                        int i =  Integer.parseInt(userInput[1]);
                        if(i<-10) i = -10;
                        if(i>10) i = 10;
                        forceEvent(i);
                    } catch (Exception e){
                        System.out.println(redText + "Invalid Integer" + textReset);
                    }
                }
                else{
                    forceEvent(random.nextInt(0,21) - 10);
                }
                break;
            case "FORCEUPDATE":
                //TODO: add this.
                break;
            default:
                if(userInput.length > 1 || !userInput[0].isEmpty()){
                    System.out.println(cyanText + "Unknown Command" + textReset);
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
     * Ends program and closes
     */
    public void quit(){

    }

    /**
     * Pauses program
     */
    public void pause(){

    }

    /**
     * Unpauses program
     */
    public void play(){

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
     * Forces a random event to occur
     */
    public void forceEvent(){

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

    }
}
