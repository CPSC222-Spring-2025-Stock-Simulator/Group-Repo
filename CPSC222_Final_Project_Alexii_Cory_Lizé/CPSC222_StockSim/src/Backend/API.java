package Backend;

public class API
{
    //------- DEFAULT VALUES ---------------------------------
    private static int peopleStartMoney = 1000;
    private static int stockStartPrice = 10 ;
    private static int peopleAmount = 100 ;
    private static int cycleCount = 50 ;
    private static float cycleLength = 1.0f ;
    private static int graphLength = 50 ;
    private static float eventChance = .5f ;
    //-------------------------------------------------------


    //------- DYNAMIC VALUES ---------------------------------
    private static Float[] stockPriceHistory ;
    private static int cycleCounter = 0 ;

    private static int bestPersonID ;
    private static Float bestPersonMoney ;
    private static int bestPersonShares ;
    private static Float bestPersonProfit ;
    private static Float bestPersonBuyPrice ;
    private static Float bestPersonSellPrice ;

    private static int worstPersonID ;
    private static Float worstPersonMoney ;
    private static int worstPersonShares ;
    private static Float worstPersonProfit ;
    private static Float worstPersonBuyPrice ;
    private static Float worstPersonSellPrice ;

    private static float currentStockPrice ;
    private static String eventType ;
    private static float eventStrength ;
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

    public static Float getCycleLength()
    {
        return cycleLength ;
    }

    public static void setCycleLength(float cycleLength)
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
        stockPriceHistory = new Float[graphLength] ;
    }

    public static float getEventChance() {
        return eventChance ;
    }

    public static void setEventChance(float eventChance) {
        API.eventChance = eventChance;
    }

    //------------------------------------------------------------



    //------------- DYNAMIC VALUES SETTERS/GETTERS----------------

    public static Float[] getStockPriceHistory()
    {
        return stockPriceHistory ;
    }

    public static void addNextStockPrice(float stockPrice)
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

    public static float getCurrentStockPrice()
    {
        return currentStockPrice ;
    }

    public static void setCurrentStockPrice(float currentStockPrice)
    {
        API.currentStockPrice = currentStockPrice ;
    }

    public static int getBestPersonID() {
        return bestPersonID;
    }

    public static void setBestPersonID(int bestPersonID) {
        API.bestPersonID = bestPersonID;
    }

    public static Float getBestPersonMoney() {
        return bestPersonMoney;
    }

    public static void setBestPersonMoney(Float bestPersonMoney) {
        API.bestPersonMoney = bestPersonMoney;
    }

    public static int getBestPersonShares() {
        return bestPersonShares;
    }

    public static void setBestPersonShares(int bestPersonShares) {
        API.bestPersonShares = bestPersonShares;
    }

    public static Float getBestPersonProfit() {
        return bestPersonProfit;
    }

    public static void setBestPersonProfit(Float bestPersonProfit) {
        API.bestPersonProfit = bestPersonProfit;
    }

    public static Float getBestPersonBuyPrice() {
        return bestPersonBuyPrice;
    }

    public static void setBestPersonBuyPrice(Float bestPersonBuyPrice) {
        API.bestPersonBuyPrice = bestPersonBuyPrice;
    }

    public static Float getBestPersonSellPrice() {
        return bestPersonSellPrice;
    }

    public static void setBestPersonSellPrice(Float bestPersonSellPrice) {
        API.bestPersonSellPrice = bestPersonSellPrice;
    }

    public static int getWorstPersonID() {
        return worstPersonID;
    }

    public static void setWorstPersonID(int worstPersonID) {
        API.worstPersonID = worstPersonID;
    }

    public static Float getWorstPersonMoney() {
        return worstPersonMoney;
    }

    public static void setWorstPersonMoney(Float worstPersonMoney) {
        API.worstPersonMoney = worstPersonMoney;
    }

    public static int getWorstPersonShares() {
        return worstPersonShares;
    }

    public static void setWorstPersonShares(int worstPersonShares) {
        API.worstPersonShares = worstPersonShares;
    }

    public static Float getWorstPersonProfit() {
        return worstPersonProfit;
    }

    public static void setWorstPersonProfit(Float worstPersonProfit) {
        API.worstPersonProfit = worstPersonProfit;
    }

    public static Float getWorstPersonBuyPrice() {
        return worstPersonBuyPrice;
    }

    public static void setWorstPersonBuyPrice(Float worstPersonBuyPrice) {
        API.worstPersonBuyPrice = worstPersonBuyPrice;
    }

    public static Float getWorstPersonSellPrice() {
        return worstPersonSellPrice;
    }

    public static void setWorstPersonSellPrice(Float worstPersonSellPrice) {
        API.worstPersonSellPrice = worstPersonSellPrice;
    }

    public static String getEventType() {
        return eventType;
    }

    public static void setEventType(String eventType) {
        API.eventType = eventType;
    }

    public static float getEventStrength() {
        return eventStrength;
    }

    public static void setEventStrength(float eventStrength) {
        API.eventStrength = eventStrength;
    }

    //------------------------------------------------------------


}
