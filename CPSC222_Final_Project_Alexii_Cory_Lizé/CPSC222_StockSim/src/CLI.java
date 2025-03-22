public class CLI{

    int startingMoney = 1000;
    int startingPeople = 100;
    double delay = 1.0;
    // Delay is measured in seconds

    public CLI (){

    }

    /**
     * Starts program
     */
    public void run(){

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
     * Lists all people and their current amount of money
     */
    public void listAll(){

    }

    /**
     * Sets the number of people for the program
     * @param startingPeople The number of starting people
     */
    public void setStartingPeople(int startingPeople){
        this.startingPeople = startingPeople;
    }

    /**
     * @return The number of starting people
     */
    public int getStartingPeople() {
        return startingPeople;
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
     * @param delay Delay between cycles in seconds (can be decimal)
     */
    public void setDelay(double delay){
        this.delay = delay;
    }

    /**
     * @return Delay between cycles in seconds
     */
    public double getDelay() {
        return delay;
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
