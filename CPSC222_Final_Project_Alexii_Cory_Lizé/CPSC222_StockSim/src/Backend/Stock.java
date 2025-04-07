package Backend;

public class Stock
{
    private double price;
    private double velocity;
    private double acceleration;

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
        velocity += acceleration;
        price += velocity;

        if (price < 1)          // prevent price below 1
            price = 1 ;

        acceleration = 0;       // reset after each update              /TODO: see if we need this or not

        API.setCurrentStockPrice(price) ;
        API.addNextStockPrice(price);
    }
}
