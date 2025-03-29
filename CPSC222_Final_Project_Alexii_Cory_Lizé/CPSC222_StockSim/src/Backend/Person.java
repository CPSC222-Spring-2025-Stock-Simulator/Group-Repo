package Backend;


public class Person implements Runnable
{
    private final Integer ID;

    // What price they are willing to buy at
    private int buyPrice;

    // What price they are willing to sell at
    private int sellPrice;

    // How much money person starts with
    private final int startingMoney;             // This can be changed

    // How much current money a person has
    private int currentMoney;

    // How tolerant someone is to buy
    private int tolerance;

    /**
     * makes a person using ID and starting money
     * @param ID the threads ID
     * @param startingMoney the amount of money a person starts with
     */
    public Person(Integer ID, int startingMoney)
    {
        this.startingMoney = startingMoney;
        this.ID = ID;
        this.currentMoney = this.startingMoney;
    }



    public void makeDecision(int stockPrice)
    {
        
    }


    @Override
    public void run()
    {
        while(true)
        {






        }
    }





}
