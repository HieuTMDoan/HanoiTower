module hanoitower {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.media;


    exports hanoitower;
    opens hanoitower.controllers to javafx.fxml;
    opens hanoitower.model to javafx.base;
    opens hanoitower.utilties to javafx.base;
}