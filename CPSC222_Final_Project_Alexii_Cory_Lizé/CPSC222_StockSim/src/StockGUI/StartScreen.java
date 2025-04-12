package StockGUI;

/**
 * This file is part of the final concurrency project
 * 		CPSC 222 Final Project Winter 2025
 *
 * Logic for handling the start screen GUI
 *
 * @author Lize 230157950
 * @version 2024.2.3
 */

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

/**
 * This file creates the start screen GUI
 *
 * @author Lize Engelbrecht, Cory Stecyk
 * @version 2024.2.3
 */

public class StartScreen {

    @FXML public Slider graphLengthSlider;
    @FXML public TextField graphLengthText;
    @FXML public Slider eventSlider;
    @FXML public TextField eventText;
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

    /**
     * The initialize method initializes the sliders, and text boxes that allow the user input.
     */
    @FXML
    public void initialize(){
        //Setting the slider for each input to take the default value in their respective text field
        try {
            float cA = Float.parseFloat(cycleAmountText.getText());
            cycleAmountSlider.setValue(cA);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number in TextField, using default slider value.");
            cycleAmountSlider.setValue(0);
        }
        try {
            float cL = Float.parseFloat(cycleLengthText.getText());
            cycleLengthSlider.setValue(cL);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number in TextField, using default slider value.");
            cycleLengthSlider.setValue(0);
        }
        try {
            float m = Float.parseFloat(moneyText.getText());
            moneySlider.setValue(m);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number in TextField, using default slider value.");
            moneySlider.setValue(0);
        }
        try {
            float p = Float.parseFloat(peopleText.getText());
            peopleSlider.setValue(p);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number in TextField, using default slider value.");
            peopleSlider.setValue(0);
        }
        try {
            float s = Float.parseFloat(stockText.getText());
            stockSlider.setValue(s);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number in TextField, using default slider value.");
            stockSlider.setValue(0);
        }
        try {
            float s = Float.parseFloat(graphLengthText.getText());
            graphLengthSlider.setValue(s);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number in TextField, using default slider value.");
            graphLengthSlider.setValue(0);
        }
        try {
            float s = Float.parseFloat(eventText.getText());
            eventSlider.setValue(s);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number in TextField, using default slider value.");
            eventSlider.setValue(0);
        }

        //Setting the text field of each input to change if their respective slider has been adjusted
        cycleAmountSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            cycleAmountText.setText(String.format("%.0f", newValue.floatValue()));
        });
        cycleLengthSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            cycleLengthText.setText(String.format("%.1f", newValue.floatValue()));
        });
        moneySlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            moneyText.setText(String.format("%.0f", newValue.floatValue()));
        });
        peopleSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            peopleText.setText(String.format("%.0f", newValue.floatValue()));
        });
        stockSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            stockText.setText(String.format("%.0f", newValue.floatValue()));
        });
        graphLengthSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            graphLengthText.setText(String.format("%.0f", newValue.floatValue()));
        });
        eventSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            eventText.setText(String.format("%.0f", newValue.floatValue()));
        });

        //Setting the slider for each input to take the value in their respective text field if it has changed
        cycleAmountText.textProperty().addListener((obs, oldVal, newVal) -> {
            try {
                float value = Float.parseFloat(newVal);
                cycleAmountSlider.setValue(value);
            } catch (NumberFormatException e) {
                // If the input isn't a number, just ignore
            }
        });

        cycleLengthText.textProperty().addListener((obs, oldVal, newVal) -> {
            try {
                float value = Float.parseFloat(newVal);
                cycleLengthSlider.setValue(value);
            } catch (NumberFormatException e) {
                // If the input isn't a number, just ignore
            }
        });

        moneyText.textProperty().addListener((obs, oldVal, newVal) -> {
            try {
                float value = Float.parseFloat(newVal);
                moneySlider.setValue(value);
            } catch (NumberFormatException e) {
                // If the input isn't a number, just ignore
            }
        });

        peopleText.textProperty().addListener((obs, oldVal, newVal) -> {
            try {
                float value = Float.parseFloat(newVal);
                peopleSlider.setValue(value);
            } catch (NumberFormatException e) {
                // If the input isn't a number, just ignore
            }
        });

        stockText.textProperty().addListener((obs, oldVal, newVal) -> {
            try {
                float value = Float.parseFloat(newVal);
                stockSlider.setValue(value);
            } catch (NumberFormatException e) {
                // If the input isn't a number, just ignore
            }
        });
        graphLengthText.textProperty().addListener((obs, oldVal, newVal) -> {
            try {
                float value = Float.parseFloat(newVal);
                graphLengthSlider.setValue(value);
            } catch (NumberFormatException e) {
                // If the input isn't a number, just ignore
            }
        });

        eventText.textProperty().addListener((obs, oldVal, newVal) -> {
            try {
                float value = Float.parseFloat(newVal);
                eventSlider.setValue(value);
            } catch (NumberFormatException e) {
                // If the input isn't a number, just ignore
            }
        });


    }

    /**
     * This method is called when the start button on the GUI was clicked. It sets all the values that the user has inputted
     * and then starts the second GUI that displays everything.
     * @param event button clicked
     * @throws IOException  no button to click
     */
    @FXML
    private void startClicked(ActionEvent event) throws IOException{

        //Setting all the input values to the variables that are used throughout the code
        API.setCycleCount((int) cycleAmountSlider.getValue());
        API.setPeopleAmount((int) peopleSlider.getValue());
        API.setPeopleStartMoney((int) moneySlider.getValue());
        API.setCycleLength((float) cycleLengthSlider.getValue());
        API.setStockStartPrice((int) stockSlider.getValue());
        API.setEventChance((float) (eventSlider.getValue()/100));
        API.setGraphLength((int) graphLengthSlider.getValue());

        //Initializing the stockGUI
        FXMLLoader loader = new FXMLLoader(StockGUI.class.getResource("StockGUI.fxml"));
        AnchorPane parent = loader.load();
        stockGUI = loader.getController();
        Main.setStockGUI(stockGUI);

        //check if the stockGUI was set successfully, without this check the code can crash.
        if (stockGUI != null) {
            System.out.println("StockGUI controller loaded successfully!");
        } else {
            System.out.println("StockGUI controller is null.");
        }

        //Creating the scene and window for the display
        Scene main = new Scene(parent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        //This allows the stockGUI window to open at the same size as the startScreen
        double currentWidth = window.getWidth();
        double currentHeight = window.getHeight();

        window.setScene(main);
        window.setWidth(currentWidth);
        window.setHeight(currentHeight);
        window.setTitle("Stock Simulator");
        window.show();

        //Run the simulation on a separate thread
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
