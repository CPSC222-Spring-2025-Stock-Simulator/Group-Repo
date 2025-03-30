module Group.Repo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;

    exports Backend;
    opens StockGUI to javafx.fxml;

}