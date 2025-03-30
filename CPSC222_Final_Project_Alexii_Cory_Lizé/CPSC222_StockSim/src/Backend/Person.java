package Backend;


public class Person implements Runnable
{
    private final Integer ID ;
    private int buyPrice ;                                       // What price they are willing to buy at
    private int sellPrice ;                                     // What price they are willing to sell at
    private int money ;                                  // How much current money a person has
    private int stockPrice ;

    /**
     * makes a person using ID
     * @param ID the threads ID
     */
    public Person(Integer ID)
    {
        this.ID = ID ;
        this.money = API.getPeopleStartMoney() ;
    }


    @Override
    public void run()
    {
        while(true)
        {
            //makeDecision() ;


        }
    }

    public void decision()
    {

    }
}
