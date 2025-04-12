package StockGUI;

/**
 * This file is part of the final concurrency project
 * 		CPSC 222 Final Project Winter 2025
 *
 * Logic for Displaying GUI
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

/**
 * This file creates the stock display GUI
 *
 * @author Lize Engelbrecht, Alexii Saliken, Cory Stecyk
 * @version 2024.2.3
 */

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

    /**
     * this method determines what happens when the toggle button is clicked, when it changes states it will pause or play
     * the simulation
     * @param event button was clicked
     */
    @FXML
    private void handleToggleButtonClick(ActionEvent event){
        if(pauseButton.isSelected()){
            pauseButton.setText("Play");
            pause();
        }
        else{
            pauseButton.setText("Pause");
            play();
        }

    }

    /**
     * this method is called everytime that the threads for people and stock have finished running through calculation to
     * display what changes occurred.
     */
    public void updateGUI(){
        //Index text changes
        peopleAmount.setText(String.valueOf(API.getPeopleAmount()));
        moneyStart.setText(String.valueOf(API.getPeopleStartMoney()));
        stockStart.setText(String.valueOf(API.getStockStartPrice()));
        stockCurrent.setText(String.format("%.2f", API.getCurrentStockPrice()));
        cycleAmount.setText(String.valueOf(API.getCycleCount()));
        cycleLength.setText(String.format("%.1f sec", API.getCycleLength()));
        //Best trader text changes
        bestPerson.setText(String.valueOf( API.getBestPersonID()));
        bestProfit.setText(String.format("%.2f", API.getBestPersonProfit()));
        bestBuy.setText(String.format("%.2f", API.getBestPersonBuyPrice()));
        bestSell.setText(String.format("%.2f", API.getBestPersonSellPrice()));
        bestShares.setText(String.valueOf(API.getBestPersonShares()));
        bestMoney.setText(String.format("%.2f", API.getBestPersonMoney()));
        //Worst trader text changes
        worstPerson.setText(String.valueOf(API.getWorstPersonID()));
        worstProfit.setText(String.format("%.2f", API.getWorstPersonProfit()));
        worstBuy.setText(String.format("%.2f", API.getWorstPersonBuyPrice()));
        worstSell.setText(String.format("%.2f", API.getWorstPersonSellPrice()));
        worstShares.setText(String.valueOf( API.getWorstPersonShares()));
        worstMoney.setText(String.format("%.2f", API.getWorstPersonMoney()));

        Float[] stocks = API.getStockPriceHistory();
        // Line Chart updates to add more points
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

        //Adds the event that occurred to the top of the event list view, if an event has occurred.
        if(API.getEventType() != null) {
            eventDisplay.getItems().addFirst(API.getEventType() + " event: "+ String.format("%.2f",API.getEventStrength()));

        }
    }

    /**
     * Sets a value in the backend class to true that sends the threads to sleep
     */
    public static void pause(){
        Backend.setIsPaused(true);
    }

    /**
     * Sets a value in the backend class to false that lets the threads run again
     */
    public static void play(){
        Backend.setIsPaused(false);
    }

    /**
     * Initializes the line chart, series, text boxes, and list view.
     */
    public void initialize() {
        //initialization of the line chart and series if they have not been initialized by the controller
        if (lineChart == null) {
            System.out.println("LineChart is null, initializing...");
            lineChart = new LineChart<>(xAxis, yAxis);
        }
        if (series == null) {
            System.out.println("Series is null, initializing...");
            series = new XYChart.Series<>();
            lineChart.getData().add(series);
        }

        xAxis.setLabel("");
        yAxis.setLabel("Stock Price");
        lineChart.setTitle("Stock Data");

        // Sets default text to nothing if no information has been added to be displayed
        peopleAmount.setText("");
        moneyStart.setText("");
        stockStart.setText("");
        stockCurrent.setText("");
        cycleAmount.setText("");
        cycleLength.setText("");
        bestPerson.setText("");
        bestProfit.setText("");
        bestBuy.setText("");
        bestSell.setText("");
        worstPerson.setText("");
        worstProfit.setText("");
        worstBuy.setText("");
        worstSell.setText("");

        System.out.println("LineChart has been initialized.");

        //Changes the cell colour for each event.
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

        //Runs the updateGUI method later to ensure that everything has had time to initialize.
        Platform.runLater(this::updateGUI);

    }
}
