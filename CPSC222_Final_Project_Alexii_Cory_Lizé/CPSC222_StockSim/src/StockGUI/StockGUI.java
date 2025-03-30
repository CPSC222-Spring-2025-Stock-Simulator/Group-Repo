package StockGUI;

import Backend.CLI;
import Backend.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.util.ResourceBundle;

public class StockGUI {
    CLI mainCLI = Main.getCli();

    @FXML
    public LineChart<Number, Number> lineChart;

    @FXML
    public NumberAxis xAxis;

    @FXML
    public NumberAxis yAxis;

    public void updateGUI(){
        //line chart test:
        XYChart.Series series = new XYChart.Series();

        series.getData().add(new XYChart.Data(1, 23));
        series.getData().add(new XYChart.Data(2, 14));
        series.getData().add(new XYChart.Data(3, 15));
        series.getData().add(new XYChart.Data(4, 24));
        series.getData().add(new XYChart.Data(5, 34));
        series.getData().add(new XYChart.Data(6, 36));
        series.getData().add(new XYChart.Data(7, 22));
        series.getData().add(new XYChart.Data(8, 45));
        series.getData().add(new XYChart.Data(9, 43));
        series.getData().add(new XYChart.Data(10, 17));
        series.getData().add(new XYChart.Data(11, 29));
        series.getData().add(new XYChart.Data(12, 25));

        lineChart.getData().addAll(series);
    }
    //TODO: Refreshes the GUI


    //TODO: Use these when triggering their respective buttons
    private void pause(){
        mainCLI.pause();
    }
    private void play(){
        mainCLI.play();
    }
    private void quit(){
        mainCLI.quit();
        //TODO: Close the GUI thread using its safe close thing (I don't remember how, sorry)
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
        if (lineChart == null) {
            System.out.println("lineChart is null!");
        } else {
            System.out.println("lineChart is properly initialized.");
        }
    }


}
