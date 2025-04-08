package Backend;

import java.util.Random;

public class Stock
{
    private double price;
    private double velocity;
    private double acceleration;
    private Random rng = new Random() ;

    public Stock(double startPrice)
    {
        price = startPrice ;
        this.velocity = 0 ;
        this.acceleration = 0 ;
    }

    public double getPrice()
    {
        return price ;
    }

    public synchronized void updateVelocity(double deltaVelocity)
    {
        this.velocity += deltaVelocity ;
    }

    public synchronized void updateAcceleration(double deltaAcc)
    {
        this.acceleration += deltaAcc ;
    }

    public synchronized void update()
    {
        velocity += acceleration ;
        price += velocity ;

        if (rng.nextDouble(0, 1.0) < API.getEventChance())
        {
            double event = rng.nextDouble(0,2) ;

            if (event > 1)
            {
                API.setEventType("Good");
                API.setEventStrength(event);
            } else {
                API.setEventType("Bad");
                API.setEventStrength(event+1);
            }
            velocity *= event ;                             // event affect velocity (short term)
        } else API.setEventType(null);

        if (price < 1)          // prevent price below 1
            price = 1 ;

        //acceleration = 0;       // reset after each update              /TODO: see if we need this or not

        API.setCurrentStockPrice(price) ;
        API.addNextStockPrice(price);
    }
}
