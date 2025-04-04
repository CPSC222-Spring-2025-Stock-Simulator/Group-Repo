package Backend;

public class API
{
    //------- DEFAULT VALUES ---------------------------------
    private static int peopleStartMoney = 1000;
    private static int stockStartPrice = 10 ;
    private static int peopleAmount = 100 ;
    private static int cycleCount = 50 ;
    private static double cycleLength = 1.0 ;
    private static int graphLength = 10 ;
    //-------------------------------------------------------


    //------- DYNAMIC VALUES ---------------------------------
    private static Integer[] stockPriceHistory ;  //TODO: make a setter in CLI

    private static int bestPersonID ;
    private static int bestPersonProfit ;

    private static int worstPersonID ;
    private static int worstPersonProfit ;

    private static int currentStockPrice ;
    //--------------------------------------------------------


    //---------- DEFAULT VALUES SETTERS/GETTERS ------------------------------

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

    //------------------------------------------------------------



    //------------- DYNAMIC VALUES SETTERS/GETTERS----------------

    public static Integer[] getStockPriceHistory()
    {
        return stockPriceHistory ;
    }

    public static void addNextStockPrice(int stockPrice)
    {
        int i=0 ;

        if (stockPriceHistory[graphLength-1] == null)

            while (stockPriceHistory[i] != null)                  // gets to first null value to replace
                i++ ;

        else
            for ( ; i<graphLength-1 ; i++)
                stockPriceHistory[i] = stockPriceHistory[i+1] ;   // if no null, shift everything and replace rightmost

        stockPriceHistory[i] = stockPrice ;
    }



    //------------------------------------------------------------


}
