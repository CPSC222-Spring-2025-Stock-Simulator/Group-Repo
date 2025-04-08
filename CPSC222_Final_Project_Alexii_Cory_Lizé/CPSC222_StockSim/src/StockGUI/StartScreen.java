package StockGUI;

import Backend.Backend ;
import Backend.API;
import Backend.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class StartScreen {

    @FXML private Slider cycleAmountSlider;
    @FXML private TextField cycleAmountText;
    @FXML private Slider cycleLengthSlider;
    @FXML private TextField cycleLengthText;
    @FXML private Slider moneySlider;
    @FXML private TextField moneyText;
    @FXML private Slider peopleSlider;
    @FXML private TextField peopleText;
    @FXML private Slider stockSlider;
    @FXML private TextField stockText;

    private StockGUI stockGUI;

    @FXML
    public void initialize(){
        try {
            double cA = Double.parseDouble(cycleAmountText.getText());
            cycleAmountSlider.setValue(cA);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number in TextField, using default slider value.");
            cycleAmountSlider.setValue(0);
        }
        try {
            double cL = Double.parseDouble(cycleLengthText.getText());
            cycleLengthSlider.setValue(cL);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number in TextField, using default slider value.");
            cycleLengthSlider.setValue(0);
        }
        try {
            double m = Double.parseDouble(moneyText.getText());
            moneySlider.setValue(m);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number in TextField, using default slider value.");
            moneySlider.setValue(0);
        }
        try {
            double p = Double.parseDouble(peopleText.getText());
            peopleSlider.setValue(p);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number in TextField, using default slider value.");
            peopleSlider.setValue(0);
        }
        try {
            double s = Double.parseDouble(stockText.getText());
            stockSlider.setValue(s);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number in TextField, using default slider value.");
            stockSlider.setValue(0);
        }

        cycleAmountSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            cycleAmountText.setText(String.format("%.0f", newValue.doubleValue()));
        });
        cycleLengthSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            cycleLengthText.setText(String.format("%.1f", newValue.doubleValue()));
        });
        moneySlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            moneyText.setText(String.format("%.0f", newValue.doubleValue()));
        });
        peopleSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            peopleText.setText(String.format("%.0f", newValue.doubleValue()));
        });
        stockSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            stockText.setText(String.format("%.0f", newValue.doubleValue()));
        });

        cycleAmountText.textProperty().addListener((obs, oldVal, newVal) -> {
            try {
                double value = Double.parseDouble(newVal);
                cycleAmountSlider.setValue(value);
            } catch (NumberFormatException e) {
                // If the input isn't a number (e.g., user types letters), just ignore
            }
        });

        cycleLengthText.textProperty().addListener((obs, oldVal, newVal) -> {
            try {
                double value = Double.parseDouble(newVal);
                cycleLengthSlider.setValue(value);
            } catch (NumberFormatException e) {
                // If the input isn't a number (e.g., user types letters), just ignore
            }
        });

        moneyText.textProperty().addListener((obs, oldVal, newVal) -> {
            try {
                double value = Double.parseDouble(newVal);
                moneySlider.setValue(value);
            } catch (NumberFormatException e) {
                // If the input isn't a number (e.g., user types letters), just ignore
            }
        });

        peopleText.textProperty().addListener((obs, oldVal, newVal) -> {
            try {
                double value = Double.parseDouble(newVal);
                peopleSlider.setValue(value);
            } catch (NumberFormatException e) {
                // If the input isn't a number (e.g., user types letters), just ignore
            }
        });

        stockText.textProperty().addListener((obs, oldVal, newVal) -> {
            try {
                double value = Double.parseDouble(newVal);
                stockSlider.setValue(value);
            } catch (NumberFormatException e) {
                // If the input isn't a number (e.g., user types letters), just ignore
            }
        });


    }

    @FXML
    private void startClicked(ActionEvent event) throws IOException, InterruptedException {

        API.setCycleCount((int) cycleAmountSlider.getValue());
        API.setPeopleAmount((int) peopleSlider.getValue());
        API.setPeopleStartMoney((int) moneySlider.getValue());
        API.setCycleLength(cycleLengthSlider.getValue());
        API.setStockStartPrice((int) stockSlider.getValue());

        FXMLLoader loader = new FXMLLoader(StockGUI.class.getResource("StockGUI.fxml"));
        AnchorPane parent = loader.load();
        stockGUI = loader.getController();
        Main.setStockGUI(stockGUI);

        if (stockGUI != null) {
            System.out.println("StockGUI controller loaded successfully!");
        } else {
            System.out.println("StockGUI controller is null.");
        }

        Scene main = new Scene(parent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Stock Simulator");
        window.setScene(main);
        window.show();

        new Thread(() -> {
            try {
                // Ensure stockGUI is initialized before running the simulation
                while (stockGUI == null) {
                    Thread.sleep(100); // Sleep briefly and retry
                }

                System.out.println("Starting the simulation...");
                Backend.runSimulation();  // Start the simulation
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    public StockGUI getStockGUI() {
        return stockGUI;
    }
}
