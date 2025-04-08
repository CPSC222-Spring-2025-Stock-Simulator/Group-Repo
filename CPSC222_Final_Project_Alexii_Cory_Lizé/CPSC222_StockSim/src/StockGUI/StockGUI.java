package StockGUI;

import Backend.API;
import Backend.CLI;
import Backend.Main;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ToggleButton;
import javafx.scene.text.Text;

import java.util.Random;

public class StockGUI {
    @FXML
    public Text peopleAmount;
    @FXML
    public Text moneyStart;
    @FXML
    public Text stockStart;
    @FXML
    public Text stockCurrent;
    @FXML
    public Text cycleAmount;
    @FXML
    public Text cycleLength;
    @FXML
    public Text bestPerson;
    @FXML
    public Text bestProfit;
    @FXML
    public Text bestBuy;
    @FXML
    public Text bestSell;
    @FXML
    public Text worstPerson;
    @FXML
    public Text worstProfit;
    @FXML
    public Text worstBuy;
    @FXML
    public Text worstSell;


    @FXML
    private final StringProperty pauseButtonText = new SimpleStringProperty("Pause");
    CLI mainCLI = Main.getCli();
    private Random rnd;

    @FXML
    public LineChart<Number, Number> lineChart;

    @FXML
    public NumberAxis xAxis;

    @FXML
    public NumberAxis yAxis;

    @FXML
    public ToggleButton pauseButton;

    @FXML
    private void handleToggleButtonClick(ActionEvent event){
        if(pauseButton.isSelected()){
            pauseButtonText.setValue("Play");
        }
        else{
            pauseButtonText.setValue("Pause");
        }

    }

    public void updateGUI(){
        //Index
        System.out.println(API.getPeopleAmount());
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
    public String getPauseButtonText() {
        return pauseButtonText.get();
    }

    public StringProperty pauseButtonTextProperty() {
        return pauseButtonText;
    }

    public void initialize() {
        xAxis = (NumberAxis) lineChart.getXAxis();
        xAxis.setLabel("X Axis");

        yAxis = (NumberAxis) lineChart.getYAxis();
        yAxis.setLabel("Y Axis");

        lineChart.setTitle("Stock Data");

        pauseButton = new ToggleButton("Pause");
        pauseButton.setText("Pause");


        rnd = new Random();

        updateGUI();
    }


}
