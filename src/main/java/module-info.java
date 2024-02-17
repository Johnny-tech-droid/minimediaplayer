module de.fernschulen.minimediaplayer {
    requires javafx.controls;
    requires javafx.media;

    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.core;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.fontawesome5;
    // add icon pack modules

    opens de.fernschulen.minimediaplayer to javafx.fxml;
    exports de.fernschulen.minimediaplayer;
}