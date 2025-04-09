package Backend;

/**
 * This file is part of the final concurrency project
 * 		CPSC 222 Final Project Winter 2025
 *
 * Logic for people or participants in the stock market
 *
 * @author Cory Stecyk
 * Student Number: 230154922
 * @version 2024.2.3
 */

import java.util.Random;

/**
 *
 */
public class Person
{
    private final Integer ID ;
    private float buyPrice ;                                       // What price they are willing to buy at
    private float sellPrice ;                                     // What price they are willing to sell at
    private float money ;                                  // How much current money a person has
    private int shares ;
    private int FOMOCounter ;                               // fear of missing out on shares counter
    private final int FOMOLimit ;

    /**
     * makes a person using ID
     * @param ID the threads ID
     */
    public Person(Integer ID, Random rng)
    {
        this.ID = ID ;
        this.money = API.getPeopleStartMoney() ;

        this.FOMOCounter = 0 ;
        this.FOMOLimit = rng.nextInt(0, API.getCycleCount()/5) ;

        float delta = rng.nextFloat(0, 2.0f) ;
        float stockPrice = API.getStockStartPrice() ;

        this.buyPrice = rng.nextFloat(stockPrice, stockPrice*(1+delta)) ;
        this.sellPrice = rng.nextFloat(buyPrice, stockPrice * (1+delta) ) ;

        /*
            The reason for delta is to make sure the preferred /selling price is a function of the start stock price
            otherwise when choosing initial stock price, the outcome will be drastic if someone's buy price was much bigger
         */
    }

    public Integer getID()
    {
        return ID ;
    }

    public float getBuyPrice()
    {
        return buyPrice ;
    }

    public float getSellPrice()
    {
        return sellPrice ;
    }

    public float getMoney()
    {
        return money ;
    }

    public int getShares()
    {
        return shares ;
    }

    public float getProfit()
    {
        return money + shares* API.getCurrentStockPrice() - API.getPeopleStartMoney() ;
    }

    public void decision(Stock stock, Random rng)
    {
        float stockPrice = stock.getPrice() ;

        float delta = rng.nextFloat(0.1f, 1) ;    //factor of randomness
        float strength ;
        boolean isBuy ;

        if (stockPrice < buyPrice && money > stockPrice)
        {
            isBuy = true ;

            float ratio = (stockPrice/buyPrice) ;      // ratio of price/buyPrice
            strength = (1-ratio)*delta ;         // percentage of how willing you are to buy

            float spend = money*strength ;                // they want to spend a percent of money

            int buyStockAmount = (int)Math.floor(spend/stockPrice) ;

            money -= buyStockAmount*stockPrice ;

            shares += buyStockAmount ;

            buyPrice *= 2 - (stockPrice/buyPrice) ; // increase preferred buy price based on buying difference
            sellPrice *= 2 - (stockPrice/buyPrice) ;     // increase preferred sell price based on selling difference
        } else
            if (stockPrice > sellPrice && shares > 0)
        {
            isBuy = false ;

            float ratio = (sellPrice/stockPrice) ;      // ratio of sellPrice/price
            strength = (1-ratio)*delta ;         // percentage of how willing you are to sell

            int sellStockAmount = (int)Math.ceil(shares*strength) ;                // they want to sell a percent of shares

            money += sellStockAmount*stockPrice ;
            shares -= sellStockAmount ;

            //sellPrice *= 2 - (sellPrice/stockPrice) ;     // increase preferred sell price based on selling difference
        } else
            {
                if (FOMOCounter == FOMOLimit) // they've reached their limit and need to update their buy and sell price
                {
                    this.buyPrice = rng.nextFloat(stockPrice*(1-delta), stockPrice * (1+delta)) ;
                    this.sellPrice = rng.nextFloat(buyPrice, stockPrice * (1+delta) ) ;

                    FOMOCounter = 0 ;
                }

                return ;                                               // they don't want to buy or sell
            }

        FOMOCounter = 0 ;


        float deltaVelocity = 0 ;       // how much to change velocity
        float deltaAcceleration = 0 ;       // how much to change acceleration


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
