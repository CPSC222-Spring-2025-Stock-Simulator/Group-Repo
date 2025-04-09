package Backend;

import java.util.Random;

public class Stock
{
    private float price ;
    private float velocity ;
    private float acceleration ;
    private Random rng = new Random() ;
    private boolean isEventForced ;

    public Stock(float startPrice)
    {
        price = startPrice ;
        this.velocity = 0 ;
        this.acceleration = 0 ;
        this.isEventForced = false ;
    }

    public float getPrice()
    {
        return price;
    }

    public synchronized void updateVelocity(float deltaVelocity)
    {
        this.velocity += deltaVelocity ;
    }

    public synchronized void updateAcceleration(float deltaAcc)
    {
        this.acceleration += deltaAcc ;
    }

    public synchronized void update() {
        velocity += acceleration ;
        price += velocity ;

        eventUpdate() ;

        // price*= .9f ;
        velocity -= API.getStockStartPrice() * .1f ;

        if (price < 1)          // prevent price below 1
            price = 1 ;

        acceleration = 0 ;       // reset after each update              /TODO: see if we need this or not

        API.setCurrentStockPrice(price) ;
        API.addNextStockPrice(price) ;
    }

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

    public void forceEvent(float event) throws InterruptedException
    {
        isEventForced = true ;
        Thread.sleep((long) (API.getCycleLength()*1000)) ;
        privateForceEvent(event) ;
    }

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