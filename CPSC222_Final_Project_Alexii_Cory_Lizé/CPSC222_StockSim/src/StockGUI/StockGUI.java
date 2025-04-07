package StockGUI;

import Backend.CLI;
import Backend.Main;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

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
        System.out.println("Before updateGUI, lineChart is null: " + (lineChart == null));

        // If the lineChart is null, we know the issue lies in the initialization process
        if (lineChart != null) {
            XYChart.Series<Number, Number> series = new XYChart.Series<>();
            series.getData().add(new XYChart.Data<>(1, 23));
            series.getData().add(new XYChart.Data<>(2, 14));
            series.getData().add(new XYChart.Data<>(3, 15));

            lineChart.getData().add(series);
        } else {
            System.out.println("lineChart is null inside updateGUI()");
        }
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
        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("X Axis");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Y Axis");

        // Create the LineChart
        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Stock Data");

        System.out.println("Line chart initialized: " + (lineChart != null));
    }


}
