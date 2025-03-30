module Group.Repo {
    requires javafx.controls;
    requires javafx.fxml;

    exports Backend;
    opens StockGUI to javafx.fxml;

}