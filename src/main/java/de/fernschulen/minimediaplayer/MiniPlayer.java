package de.fernschulen.minimediaplayer;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.File;


public class MiniPlayer extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FlowPane rootNode = new FlowPane();
        File datei = new File("src/main/resources/de/fernschulen/minimediaplayer/topologien.mp4");
        Media media = new Media(datei.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);
        rootNode.getChildren().add(mediaView);
        rootNode.setAlignment(null);
        Scene scene = new Scene(rootNode,600,400);
        stage.setTitle("Ein einfacher Multimedia-Player");
        stage.setScene(scene);
        stage.show();
        mediaPlayer.play();
    }
}
