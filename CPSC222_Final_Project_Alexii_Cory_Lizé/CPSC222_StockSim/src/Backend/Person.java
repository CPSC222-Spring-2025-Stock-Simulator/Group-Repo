package Backend;


public class Person implements Runnable
{
    private final Integer ID ;
    private int buyPrice ;                                       // What price they are willing to buy at
    private int sellPrice ;                                     // What price they are willing to sell at
    private int money ;                                  // How much current money a person has
    private int stockPrice ;

    /**
     * makes a person using ID and starting money
     * @param ID the threads ID
     * @param startingMoney the amount of money a person starts with
     */
    public Person(Integer ID, int startingMoney)
    {
        this.ID = ID ;
        this.money = startingMoney ;
    }



    public void makeDecision()
    {

    }


    @Override
    public void run()
    {
        while(true)
        {
            makeDecision() ;


        }
    }

}
