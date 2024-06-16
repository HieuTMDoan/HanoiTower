module hanoitower {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    exports hanoitower;
    opens hanoitower.control to javafx.fxml;
    opens hanoitower.model to javafx.base;
}