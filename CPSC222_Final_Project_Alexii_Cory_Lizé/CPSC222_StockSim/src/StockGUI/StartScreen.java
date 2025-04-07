package StockGUI;

import Backend.API;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class StartScreen {
    @FXML
    private Slider cycleAmountSlider;

    @FXML
    private TextField cycleAmountText;

    @FXML
    private Slider cycleLengthSlider;

    @FXML
    private TextField cycleLengthText;

    @FXML
    private Slider moneySlider;

    @FXML
    private TextField moneyText;

    @FXML
    private Slider peopleSlider;

    @FXML
    private TextField peopleText;

    @FXML
    private Slider stockSlider;

    @FXML
    private TextField stockText;

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
    private void startClicked(ActionEvent event) throws IOException{

        API.setCycleCount((int) cycleAmountSlider.getValue());
        API.setPeopleAmount((int) peopleSlider.getValue());
        API.setPeopleStartMoney((int) moneySlider.getValue());
        API.setCycleLength(cycleLengthSlider.getValue());
        API.setStockStartPrice((int) stockSlider.getValue());

        Parent parent = FXMLLoader.load(Objects.requireNonNull(StockGUI.class.getResource("StockGUI.fxml")));
        Scene main = new Scene(parent);
        StockGUI stockGUI = new StockGUI();

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Stock Simulator");
        stockGUI.initialize();
        window.setScene(main);
        window.show();
    }
}
