import java.util.Scanner;

public class CLI implements Runnable{


    boolean running;
    String redText = "\u001B[31m";
    String greenText = "\u001B[32m";
    String textReset = "\u001B[0m";
    private int startingMoney = 1000 ;
    private int people = 100 ;
    private double cycleLength = 1.0 ;
    private int cycleCount = 50 ;
    // Delay is measured in seconds

    Scanner scanner = new Scanner(System.in);

    public CLI (){

    }

    /**
     * Starts program
     */
    public void run(){
        running = true;
        while(running){
            requestInteraction();
        }
    }

    private void requestInteraction(){
        System.out.print("$ ");
        String[] userInput = scanner.nextLine().split(" ");


        switch (userInput[0].toUpperCase()){
            case "HELP":
                help();
                break;
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
                
                > STARTINGPEOPLE [value]
                >> Sets the number of threads to make, 
                >> only works before the stock starts
                
                > STARTINGMONEY [value]
                >> Sets the amount of money held by each thread, 
                >> only works before the stock starts
                
                > DELAY [value]
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
