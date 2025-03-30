package Backend;


public class Person implements Runnable
{
    private final Integer ID ;
    private int buyPrice ;                                       // What price they are willing to buy at
    private int sellPrice ;                                     // What price they are willing to sell at
    private int money;                                  // How much current money a person has

    private int tolerance ;                                     // How tolerant someone is to buy

    /**
     * makes a person using ID and starting money
     * @param ID the threads ID
     * @param startingMoney the amount of money a person starts with
     */
    public Person(Integer ID, int startingMoney)
    {
        this.money = startingMoney;
        this.ID = ID;
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
