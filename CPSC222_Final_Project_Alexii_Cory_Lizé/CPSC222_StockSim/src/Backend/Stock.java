package Backend;

/**
 * This file is part of the final concurrency project
 * 		CPSC 222 Final Project Winter 2025
 *
 * Logic for stocks and stock behavior
 *
 * @author Cory Stecyk
 * Student Number: 230154922
 * @version 2024.2.3
 */

import java.util.Random;

/**
 * stock class used to represent a stock in the market
 */
public class Stock
{
    private float price ;
    private float velocity ;
    private float acceleration ;
    private final Random rng = new Random() ;
    private boolean isEventForced ;

    /**
     * stock constructor
     * @param startPrice stock starting price
     */
    public Stock(float startPrice)
    {
        price = startPrice ;
        this.velocity = 0 ;
        this.acceleration = 0 ;
        this.isEventForced = false ;
    }

    /**
     * getter for stock price
     * @return stock price
     */
    public float getPrice()
    {
        return price;
    }

    /**
     * updates the velocity of the price
     * @param deltaVelocity change in velocity
     */
    public synchronized void updateVelocity(float deltaVelocity)
    {
        this.velocity += deltaVelocity ;
    }

    /**
     * updates the acceleration of the price
     * @param deltaAcc change in acceleration
     */
    public synchronized void updateAcceleration(float deltaAcc)
    {
        this.acceleration += deltaAcc ;
    }

    /**
     * updates the stock with behaviors
     */
    public synchronized void update()
    {
        velocity += acceleration ;
        price += velocity ;

        //eventUpdate() ;
        if (rng.nextFloat(0f, 1.0f) < API.getEventChance())
        {
            float event = rng.nextFloat(0, 2) ;

            if (event > 1)
            {
                API.setEventType("Good") ;
                API.setEventStrength(event) ;
            } else {
                API.setEventType("Bad") ;
                API.setEventStrength(2 - event) ;
            }

            price *= event ;                             // event affect price (short term)
        } else API.setEventType(null) ;

        //price*= .9f ;
        //velocity *= .90f ;

        if (price < 1)          // prevent price below 1
            price = 1 ;

        acceleration = 0 ;       // reset after each update              /TODO: see if we need this or not

        API.setCurrentStockPrice(price) ;
        API.addNextStockPrice(price) ;
    }

    /**
     * original event update used in prior version to handle CLI usage
     */
    public void eventUpdate()
    {
        if (isEventForced)
            return ;

        if (rng.nextFloat(0f, 1.0f) < API.getEventChance())
        {
            float event = rng.nextFloat(0, 2) ;

            if (event > 1)
            {
                API.setEventType("Good") ;
                API.setEventStrength(event) ;
            } else {
                API.setEventType("Bad") ;
                API.setEventStrength(2 - event) ;
            }

            price += API.getStockStartPrice() * event ;                             // event affect price (short term)
        } else API.setEventType(null) ;
    }

    /**
     * used by CLI to force an event to occur
     * @param event 0.1 to 1 for bad event, 1 to 2.0 for good event
     * @throws InterruptedException exception
     */
    public void forceEvent(float event) throws InterruptedException
    {
        isEventForced = true ;
        privateForceEvent(event) ;
    }

    /**
     * forces event
     * @param event 0.1 to 1 for bad event, 1 to 2.0 for good event
     */
    private void privateForceEvent(float event)
    {
        // bad event is (0.0,1.0], good event is (1.0,2.0]
        if (event > 1)
        {
            API.setEventType("Good") ;
            API.setEventStrength(event) ;
        } else {
            API.setEventType("Bad" ) ;
            API.setEventStrength(2 - event) ;
        }

        price += API.getStockStartPrice() * event ;                             // event affect price (short term)else API.setEventType(null);

        isEventForced = false ;
    }
}