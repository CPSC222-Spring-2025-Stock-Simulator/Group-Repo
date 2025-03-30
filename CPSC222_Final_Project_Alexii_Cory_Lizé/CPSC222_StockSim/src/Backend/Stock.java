package Backend;

public class Stock
{
    private int price;
    private int velocity;
    private int acceleration;

    public Stock(int startPrice)
    {
        price = startPrice ;
        this.velocity = 0 ;
        this.acceleration = 0 ;
    }

    public int getPrice()
    {
        return price ;
    }

}
