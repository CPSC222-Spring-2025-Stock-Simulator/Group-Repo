package Backend;

public class API
{
    //------- DEFAULT VALUES ---------------------------------
    private static int peopleStartMoney = 1000;
    private static int stockStartPrice = 10 ;
    private static int peopleAmount = 100 ;
    private static int cycleCount = 50 ;
    private static double cycleLength = 1.0 ;
    private static int graphLength = 50 ;
    private static double eventChance = .5 ;
    //-------------------------------------------------------


    //------- DYNAMIC VALUES ---------------------------------
    private static final Double[] stockPriceHistory = new Double[graphLength];
    private static int cycleCounter = 0 ;

    private static int bestPersonID ;
    private static Double bestPersonMoney ;
    private static int bestPersonShares ;
    private static Double bestPersonProfit ;
    private static Double bestPersonBuyPrice ;
    private static Double bestPersonSellPrice ;

    private static int worstPersonID ;
    private static Double worstPersonMoney ;
    private static int worstPersonShares ;
    private static Double worstPersonProfit ;
    private static Double worstPersonBuyPrice ;
    private static Double worstPersonSellPrice ;

    private static double currentStockPrice ;
    private static String eventType ;
    private static double eventStrength ;
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

    public static Double getCycleLength()
    {
        return cycleLength ;
    }

    public static void setCycleLength(double cycleLength)
    {
        API.cycleLength = cycleLength;
    }

    public static int getGraphLength()
    {
        return graphLength ;
    }

    public static void setGraphLength(int graphLength)
    {
        API.graphLength = graphLength ;
    }

    public static double getEventChance() {
        return eventChance ;
    }

    public static void setEventChance(double eventChance) {
        API.eventChance = eventChance;
    }

    //------------------------------------------------------------



    //------------- DYNAMIC VALUES SETTERS/GETTERS----------------

    public static Double[] getStockPriceHistory()
    {
        return stockPriceHistory ;
    }

    public static void addNextStockPrice(double stockPrice)
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

    public static int getCycleCounter() {
        return cycleCounter;
    }

    public static void incrementCycleCounter() {
        API.cycleCounter++ ;
    }

    public static double getCurrentStockPrice()
    {
        return currentStockPrice ;
    }

    public static void setCurrentStockPrice(double currentStockPrice)
    {
        API.currentStockPrice = currentStockPrice ;
    }

    public static int getBestPersonID() {
        return bestPersonID;
    }

    public static void setBestPersonID(int bestPersonID) {
        API.bestPersonID = bestPersonID;
    }

    public static Double getBestPersonMoney() {
        return bestPersonMoney;
    }

    public static void setBestPersonMoney(Double bestPersonMoney) {
        API.bestPersonMoney = bestPersonMoney;
    }

    public static int getBestPersonShares() {
        return bestPersonShares;
    }

    public static void setBestPersonShares(int bestPersonShares) {
        API.bestPersonShares = bestPersonShares;
    }

    public static Double getBestPersonProfit() {
        return bestPersonProfit;
    }

    public static void setBestPersonProfit(Double bestPersonProfit) {
        API.bestPersonProfit = bestPersonProfit;
    }

    public static Double getBestPersonBuyPrice() {
        return bestPersonBuyPrice;
    }

    public static void setBestPersonBuyPrice(Double bestPersonBuyPrice) {
        API.bestPersonBuyPrice = bestPersonBuyPrice;
    }

    public static Double getBestPersonSellPrice() {
        return bestPersonSellPrice;
    }

    public static void setBestPersonSellPrice(Double bestPersonSellPrice) {
        API.bestPersonSellPrice = bestPersonSellPrice;
    }

    public static int getWorstPersonID() {
        return worstPersonID;
    }

    public static void setWorstPersonID(int worstPersonID) {
        API.worstPersonID = worstPersonID;
    }

    public static Double getWorstPersonMoney() {
        return worstPersonMoney;
    }

    public static void setWorstPersonMoney(Double worstPersonMoney) {
        API.worstPersonMoney = worstPersonMoney;
    }

    public static int getWorstPersonShares() {
        return worstPersonShares;
    }

    public static void setWorstPersonShares(int worstPersonShares) {
        API.worstPersonShares = worstPersonShares;
    }

    public static Double getWorstPersonProfit() {
        return worstPersonProfit;
    }

    public static void setWorstPersonProfit(Double worstPersonProfit) {
        API.worstPersonProfit = worstPersonProfit;
    }

    public static Double getWorstPersonBuyPrice() {
        return worstPersonBuyPrice;
    }

    public static void setWorstPersonBuyPrice(Double worstPersonBuyPrice) {
        API.worstPersonBuyPrice = worstPersonBuyPrice;
    }

    public static Double getWorstPersonSellPrice() {
        return worstPersonSellPrice;
    }

    public static void setWorstPersonSellPrice(Double worstPersonSellPrice) {
        API.worstPersonSellPrice = worstPersonSellPrice;
    }

    public static String getEventType() {
        return eventType;
    }

    public static void setEventType(String eventType) {
        API.eventType = eventType;
    }

    public static double getEventStrength() {
        return eventStrength;
    }

    public static void setEventStrength(double eventStrength) {
        API.eventStrength = eventStrength;
    }

    //------------------------------------------------------------


}
