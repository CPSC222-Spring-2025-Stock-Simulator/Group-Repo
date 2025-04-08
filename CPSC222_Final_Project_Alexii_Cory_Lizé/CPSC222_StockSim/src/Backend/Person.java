package Backend;


public class Person
{
    private final Integer ID ;
    private double buyPrice ;                                       // What price they are willing to buy at
    private double sellPrice ;                                     // What price they are willing to sell at
    private double money ;                                  // How much current money a person has
    private int shares ;

    /**
     * makes a person using ID
     * @param ID the threads ID
     */
    public Person(Integer ID)
    {
        this.ID = ID ;
        this.money = API.getPeopleStartMoney() ;
    }

    public Integer getID()
    {
        return ID ;
    }

    public double getBuyPrice()
    {
        return buyPrice ;
    }

    public double getSellPrice()
    {
        return sellPrice ;
    }

    public double getMoney()
    {
        return money ;
    }

    public int getShares()
    {
        return shares ;
    }

    public double getProfit()
    {
        return money + shares* API.getCurrentStockPrice() ;
    }

    public void decision(Stock stock)
    {
        double stockPrice = stock.getPrice() ;

        double delta = Math.random() ;    //randomness
        double strength ;
        boolean isBuy ;

        if (stockPrice < buyPrice && money > stockPrice)
        {
            isBuy = true ;

            double ratio = (stockPrice/buyPrice) ;      // ratio of price/buyPrice
            strength = (1-ratio)*delta ;         // percentage of how willing you are to buy

            double spend = money*strength ;                // they want to spend a percent of money

            int buyStockAmount = (int)Math.floor(spend/stockPrice) ;

            money -= buyStockAmount*stockPrice ;
            shares += buyStockAmount ;
        } else
            if (stockPrice > sellPrice && shares > 0)
        {
            isBuy = false ;

            double ratio = (sellPrice/stockPrice) ;      // ratio of sellPrice/price
            strength = (1-ratio)*delta ;         // percentage of how willing you are to sell

            int sellStockAmount = (int)Math.ceil(shares*strength) ;                // they want to sell a percent of shares

            money += sellStockAmount*stockPrice ;
            shares -= sellStockAmount ;
        }
            else return ;                                               // they don't want to buy or sell


        double deltaVelocity = 0 ;       // how much to change velocity
        double deltaAcceleration = 0 ;       // how much to change acceleration

        // decide whether to affect stock acceleration or velocity
        if (strength < 0.5)
        {
            // small strength means influence velocity
            deltaVelocity = isBuy? delta : -delta ;
        } else
        {
            // big strength means influence acceleration
            deltaAcceleration = isBuy? delta : -delta ;
        }

        stock.updateVelocity(deltaVelocity) ;
        stock.updateAcceleration(deltaAcceleration) ;
    }
}
