package StockGUI;

/**
 * This file is part of the final concurrency project
 * 		CPSC 222 Final Project Winter 2025
 *
 * Logic for handling Person class using thread pools
 *
 * @author Lize 230157950, Alexii 230154409
 * @version 2024.2.3
 */

import Backend.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ToggleButton;
import javafx.scene.text.Text;


public class StockGUI {
    @FXML public Text peopleAmount;
    @FXML public Text moneyStart;
    @FXML public Text stockStart;
    @FXML public Text stockCurrent;
    @FXML public Text cycleAmount;
    @FXML public Text cycleLength;
    @FXML public Text bestPerson;
    @FXML public Text bestProfit;
    @FXML public Text bestBuy;
    @FXML public Text bestSell;
    @FXML public Text bestShares;
    @FXML public Text bestMoney;
    @FXML public Text worstPerson;
    @FXML public Text worstProfit;
    @FXML public Text worstBuy;
    @FXML public Text worstSell;
    @FXML public Text worstShares;
    @FXML public Text worstMoney;



    @FXML public ListView<String> eventDisplay;

    @FXML public LineChart<Number, Number> lineChart;
    @FXML private XYChart.Series<Number, Number> series;
    @FXML public NumberAxis xAxis;
    @FXML public NumberAxis yAxis;
    @FXML private ToggleButton pauseButton;

    @FXML
    private void handleToggleButtonClick(ActionEvent event) throws InterruptedException {
        if(pauseButton.isSelected()){
            pauseButton.setText("Play");
            pause();
        }
        else{
            pauseButton.setText("Pause");
            play();
        }

    }


    public void updateGUI(){
        //Index
        peopleAmount.setText(String.valueOf(API.getPeopleAmount()));
        moneyStart.setText(String.valueOf(API.getPeopleStartMoney()));
        stockStart.setText(String.valueOf(API.getStockStartPrice()));
        stockCurrent.setText(String.format("%.2f", API.getCurrentStockPrice()));
        cycleAmount.setText(String.valueOf(API.getCycleCount()));
        cycleLength.setText(String.format("%.1f sec", API.getCycleLength()));
        //best
        bestPerson.setText(String.valueOf( API.getBestPersonID()));
        bestProfit.setText(String.format("%.2f", API.getBestPersonProfit()));
        bestBuy.setText(String.format("%.2f", API.getBestPersonBuyPrice()));
        bestSell.setText(String.format("%.2f", API.getBestPersonSellPrice()));
        bestShares.setText(String.valueOf(API.getBestPersonShares()));
        bestMoney.setText(String.format("%.2f", API.getBestPersonMoney()));
        //worst
        worstPerson.setText(String.valueOf(API.getWorstPersonID()));
        worstProfit.setText(String.format("%.2f", API.getWorstPersonProfit()));
        worstBuy.setText(String.format("%.2f", API.getWorstPersonBuyPrice()));
        worstSell.setText(String.format("%.2f", API.getWorstPersonSellPrice()));
        worstShares.setText(String.valueOf( API.getWorstPersonShares()));
        worstMoney.setText(String.format("%.2f", API.getWorstPersonMoney()));

        //line chart test:
        // If the lineChart is null, we know the issue lies in the initialization process
        Float[] stocks = API.getStockPriceHistory();

        series.setName("Cycle: " + API.getCycleCounter());
        if (lineChart != null) {
            if(series != null){
                series.getData().clear();
            if (stocks != null) {
                for (int i = 1; i <= API.getGraphLength(); i++) {
                    Float stockPrice = stocks[i - 1];
                    if (stockPrice != null) {
                        // Add stock price to the chart
                        series.getData().add(new XYChart.Data<>(i, stockPrice));
                    }
                }
            } else {
                System.out.println("No Stocks");
            }
        } else {
            System.out.println("series is null inside updateGUI()");
        }
        } else {
                System.out.println("lineChart is null inside updateGUI()");
            }

        if(API.getEventType() != null) {
            eventDisplay.getItems().addFirst(API.getEventType() + " event: "+ String.format("%.2f",API.getEventStrength()));

        }
    }

    public static void pause() throws InterruptedException {
        Backend.setIsPaused(true);
    }

    public static void play() throws InterruptedException {
        Backend.setIsPaused(false);
    }

    public void initialize() {
        System.out.println("initialize() method called");
        if (lineChart == null) {
            System.out.println("LineChart is null, initializing...");
            lineChart = new LineChart<>(xAxis, yAxis);
        }
        if (series == null) {
            System.out.println("Series is null, initializing...");
            series = new XYChart.Series<>();
            lineChart.getData().add(series);
        }

        // Continue with your other setup code
        xAxis.setLabel("");
        yAxis.setLabel("Stock Price");
        lineChart.setTitle("Stock Data");

        peopleAmount.setText("");
        moneyStart.setText("");
        stockStart.setText("");
        stockCurrent.setText("");
        cycleAmount.setText("");
        cycleLength.setText("");
        //best
        bestPerson.setText("");
        bestProfit.setText("");
        bestBuy.setText("");
        bestSell.setText("");
        //worst
        worstPerson.setText("");
        worstProfit.setText("");
        worstBuy.setText("");
        worstSell.setText("");

        System.out.println("LineChart has been initialized.");

        eventDisplay.setCellFactory(lv -> new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(item);
                    if (item.startsWith("Good")) {
                        setStyle("-fx-background-color: lightgreen; -fx-text-fill: black;");
                    } else {
                        setStyle("-fx-background-color: lightcoral; -fx-text-fill: black;");
                    }
                }
            }
        });

        Platform.runLater(this::updateGUI);

    }
}
