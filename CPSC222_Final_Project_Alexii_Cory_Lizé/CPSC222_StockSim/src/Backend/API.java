package Backend;

public class API
{
    private static int peopleStartMoney = 1000;
    private static int stockStartPrice = 10 ;
    private static int peopleAmount = 100 ;
    private static int cycleCount = 50 ;
    private static double cycleLength = 1.0 ;

    private static int bestPersonID ;
    private static int bestPersonProfit ;


    private static int worstPersonID ;
    private static int worstPersonProfit ;

    private static int currentStockPrice ;


    public static int getPeopleStartMoney()
    {
        return peopleStartMoney ;
    }

    public static void setPeopleStartMoney(int peopleStartMoney)
    {
        API.peopleStartMoney = peopleStartMoney ;
    }

    public static int getStockStartPrice()
    {
        return stockStartPrice ;
    }

    public static void setStockStartPrice(int stockStartPrice)
    {
        API.stockStartPrice = stockStartPrice ;
    }

    public static int getPeopleAmount()
    {
        return peopleAmount ;
    }

    public static void setPeopleAmount(int peopleAmount)
    {
        API.peopleAmount = peopleAmount ;
    }

    public static int getCycleCount()
    {
        return cycleCount ;
    }

    public static void setCycleCount(int cycleCount)
    {
        API.cycleCount = cycleCount ;
    }

    public static double getCycleLength()
    {
        return cycleLength ;
    }

    public static void setCycleLength(double cycleLength)
    {
        API.cycleLength = cycleLength;
    }


}
