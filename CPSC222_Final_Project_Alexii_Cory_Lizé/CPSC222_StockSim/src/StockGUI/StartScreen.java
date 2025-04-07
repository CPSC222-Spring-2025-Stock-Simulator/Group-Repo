package StockGUI;

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
    private Slider cycleLenghtSlider;

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
        cycleAmountSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            cycleAmountText.setText(String.format("%.0f", newValue.doubleValue()));
        });
        cycleLenghtSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
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
    }

    @FXML
    private void startClicked(ActionEvent event) throws IOException{
        Parent parent = FXMLLoader.load(Objects.requireNonNull(StockGUI.class.getResource("StockGUI.fxml")));
        Scene main = new Scene(parent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Stock Simulator");
        window.setScene(main);
        window.show();
    }
}
