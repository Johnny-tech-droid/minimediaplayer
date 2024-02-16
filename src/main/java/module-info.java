module de.fernschulen.minimediaplayer {
    requires javafx.controls;
    requires javafx.media;

    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens de.fernschulen.minimediaplayer to javafx.fxml;
    exports de.fernschulen.minimediaplayer;
}