package StockGUI;

import Backend.API;
import Backend.CLI;
import Backend.Main;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.text.Text;

import java.util.Random;

public class StockGUI {
    public Text peopleAmount;
    public Text moneyStart;
    public Text stockStart;
    public Text stockCurrent;
    public Text cycleAmount;
    public Text cycleLength;
    public Text bestPerson;
    public Text bestProfit;
    public Text bestBuy;
    public Text bestSell;
    public Text worstPerson;
    public Text worstProfit;
    public Text worstBuy;
    public Text worstSell;

    CLI mainCLI = Main.getCli();
    private Random rnd;

    @FXML
    public LineChart<Number, Number> lineChart;

    @FXML
    public NumberAxis xAxis;

    @FXML
    public NumberAxis yAxis;

    public void updateGUI(){
        //line chart test:
        // If the lineChart is null, we know the issue lies in the initialization process
        Double[] stocks = API.getStockPriceHistory();

        if (lineChart != null) {
            if(stocks != null) {
                XYChart.Series<Number, Number> series = new XYChart.Series<>();
                for (int i = 1; i <= API.getGraphLength(); i++) {
                    series.getData().add(new XYChart.Data<>(i, stocks[i - 1]));
                }

                lineChart.getData().add(series);
            } else System.out.println("No Stocks");
        } else {
            System.out.println("lineChart is null inside updateGUI()");
        }

        //Index
        peopleAmount.setText(String.valueOf(API.getPeopleAmount()));
        moneyStart.setText(String.valueOf(API.getPeopleStartMoney()));
        stockStart.setText(String.valueOf(API.getStockStartPrice()));
        stockCurrent.setText(String.valueOf(API.getCurrentStockPrice()));
        cycleAmount.setText(String.valueOf(API.getCycleCount()));
        cycleLength.setText(String.valueOf(API.getCycleLength()));
        //best
        bestPerson.setText(String.valueOf(API.getBestPersonID()));
        bestProfit.setText(String.valueOf(API.getBestPersonProfit()));
        bestBuy.setText(String.valueOf(API.getBestPersonBuyPrice()));
        bestSell.setText(String.valueOf(API.getBestPersonSellPrice()));
        //worst
        worstPerson.setText(String.valueOf(API.getWorstPersonID()));
        worstProfit.setText(String.valueOf(API.getWorstPersonProfit()));
        worstBuy.setText(String.valueOf(API.getWorstPersonBuyPrice()));
        worstSell.setText(String.valueOf(API.getWorstPersonSellPrice()));
    }
    //TODO: Refreshes the GUI


    //TODO: Use these when triggering their respective buttons
    public void pause(){
        mainCLI.pause();
    }
    public void play(){
        mainCLI.play();
    }

    //TODO: Use these if you add the ability to enter
    // values before the main program starts or just
    // delete if we don't get around to that
    private void setPeople(int numPeople){
        mainCLI.setPeople(numPeople);
    }
    private void setMoney(int amount){
        mainCLI.setStartingMoney(amount);
    }
    private void setCycleAmount(int amount){
        mainCLI.setCycleCount(amount);
    }
    private void setCycleLength(double seconds){
        mainCLI.setCycleLength(seconds);
    }

    @FXML
    public void initialize() {
        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("X Axis");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Y Axis");

        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Stock Data");

        rnd = new Random();

        peopleAmount = new Text();
        moneyStart = new Text();
        stockStart = new Text();
        stockCurrent = new Text();
        cycleAmount = new Text();
        cycleLength = new Text();
        bestPerson = new Text();
        bestProfit = new Text();
        bestBuy = new Text();
        bestSell = new Text();
        worstPerson = new Text();
        worstProfit = new Text();
        worstBuy = new Text();
        worstSell = new Text();

        updateGUI();
    }


}
