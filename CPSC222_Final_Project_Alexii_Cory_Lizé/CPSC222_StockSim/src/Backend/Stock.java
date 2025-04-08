package Backend;

import java.util.Random;

public class Stock
{
    private float price;
    private float velocity;
    private float acceleration;
    private Random rng = new Random() ;

    public Stock(float startPrice)
    {
        price = startPrice ;
        this.velocity = 0 ;
        this.acceleration = 0 ;
    }

    public float getPrice()
    {
        return price ;
    }

    public synchronized void updateVelocity(float deltaVelocity)
    {
        this.velocity += deltaVelocity ;
    }

    public synchronized void updateAcceleration(float deltaAcc)
    {
        this.acceleration += deltaAcc ;
    }

    public synchronized void update()
    {
        velocity += acceleration ;
        price += velocity ;

        if (rng.nextFloat(0f, 1.0f) < API.getEventChance())
        {
            float event = rng.nextFloat(0,2) ;

            if (event > 1)
            {
                API.setEventType("Good");
                API.setEventStrength(event);
            } else {
                API.setEventType("Bad");
                API.setEventStrength(event+1);
            }

            price *= event ;                             // event affect price (short term)
        } else API.setEventType(null);

        if (price < 1)          // prevent price below 1
            price = 1 ;

        acceleration = 0;       // reset after each update              /TODO: see if we need this or not

        API.setCurrentStockPrice(price) ;
        API.addNextStockPrice(price);
    }
}
