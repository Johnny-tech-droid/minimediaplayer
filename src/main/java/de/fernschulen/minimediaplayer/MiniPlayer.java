package de.fernschulen.minimediaplayer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;


import java.io.IOException;


public class MiniPlayer extends Application {
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(MiniPlayer.class.getResource("sb_mediaplayer.fxml"));
            Parent root = loader.load();
            FXMLController controller = loader.getController();
            controller.setMeineStage(stage);
            Scene scene = new Scene(root);
            stage.setMaximized(true);
            stage.setTitle("Ein einfacher Multimedia-Player");
            stage.setScene(scene);

            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}
