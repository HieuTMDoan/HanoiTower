module com.example.hanoitower {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens view to javafx.fxml;
    exports view;
}