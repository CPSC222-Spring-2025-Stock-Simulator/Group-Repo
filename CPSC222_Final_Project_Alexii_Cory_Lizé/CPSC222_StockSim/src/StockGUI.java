import javax.swing.*;
import java.awt.*;

public class StockGUI {
    private JButton button1;
    private JButton button2;
    private JTextArea textArea1;
    private JTextArea textArea2;
    private final CLI mainCLI = Main.getCli();



    public void updateGUI(){}
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



}

