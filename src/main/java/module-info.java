module com.example.hanoitower {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens view to javafx.fxml;
    opens model to javafx.base;
    exports view;
}