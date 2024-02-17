package de.fernschulen.minimediaplayer;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;

import javafx.stage.Stage;
import org.kordamp.ikonli.Ikon;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;

import java.io.File;




public class FXMLController {
    @FXML
    private Button openMediaBtn;
    @FXML
    private Button playMediaBtn;
    @FXML
    private Button pauseMediaBtn;
    @FXML
    private Button stopMediaBtn;
    @FXML
    private Button muteMediaBtn;
    @FXML
    private MenuItem openMediaMenuItem;
    @FXML
    private MenuItem quitAppMenuItem;
    @FXML
    private MenuItem pauseMediaMenuItem;
    @FXML
    private MenuItem playMediaMenuItem;
    @FXML
    private MenuItem stopMediaMenuItem;
    @FXML
    private MediaView mediaView;
    private MediaPlayer mediaplayer;
    @FXML
    private ListView<String> liste;
    private Stage stage;
    public void setMeineStage(Stage stage) {
        this.stage = stage;
    }
    @FXML
    private void initialize() {
        openMediaBtn.setOnAction(e -> mediumOeffnen());

        openMediaMenuItem.setOnAction(e-> mediumOeffnen());
        playMediaBtn.setOnAction(e -> mediumAbspielen());
        pauseMediaBtn.setOnAction(e -> mediumPausieren());
        stopMediaBtn.setOnAction(e-> mediumStoppen());
        muteMediaBtn.setOnAction(e-> mediumStumm());
        playMediaMenuItem.setOnAction(e -> mediumAbspielen());
        pauseMediaMenuItem.setOnAction(e -> mediumPausieren());
        stopMediaMenuItem.setOnAction(e-> mediumStoppen());
        quitAppMenuItem.setOnAction(e-> Platform.exit());
        setIcon(openMediaBtn, FontAwesomeSolid.FOLDER_OPEN);
        setIcon(playMediaBtn, FontAwesomeSolid.PLAY);
        setIcon(pauseMediaBtn, FontAwesomeSolid.PAUSE);
        setIcon(muteMediaBtn, FontAwesomeSolid.VOLUME_OFF);
        setIcon(stopMediaBtn, FontAwesomeSolid.STOP);
        mediaView.setFitHeight(0);
        mediaView.setFitWidth(0);
    }

    private void mediumOeffnen() {
        FileChooser openFileDialog = new FileChooser();
        openFileDialog.setTitle("Datei öffnen");
        FileChooser.ExtensionFilter mp4Filter = new FileChooser.ExtensionFilter("Videodateien", "*.mp4");
        openFileDialog.getExtensionFilters().add(mp4Filter);
        FileChooser.ExtensionFilter mp3Filter = new FileChooser.ExtensionFilter("Audiodateien", "*.mp3");
        openFileDialog.getExtensionFilters().add(mp3Filter);
        String path = "/Users/developer/Downloads/JAVA18D_Uebungsdateien_Software";
        openFileDialog.setInitialDirectory(new File(path));
        File datei = openFileDialog.showOpenDialog(stage);
        if (datei != null)
            dateiLaden(datei);
    }

    private void dateiLaden(File datei) {
        if (mediaplayer != null && mediaplayer.getStatus() == MediaPlayer.Status.PLAYING) {
            mediaplayer.stop();
        }
        try {
           Media medium = new Media(datei.toURI().toString());
           mediaplayer = new MediaPlayer(medium);
           mediaView.setMediaPlayer(mediaplayer);

           mediaplayer.play();
           liste.getItems().add(datei.toString());
           stage.setTitle("JavaFX Multimedia-Player " + datei);
        } catch (Exception ex) {
            String tempString = "Beim Laden hat es ein Problem gegeben.\n";
            Alert alert = new Alert(Alert.AlertType.INFORMATION,  tempString + ex.getMessage());
            alert.setHeaderText("Bitte beachten");
            alert.initOwner(stage);
            alert.showAndWait();
        }
    }

    private void mediumAbspielen() {
        if (mediaplayer != null)
            mediaplayer.play();
    }
    private void mediumPausieren() {
        if (mediaplayer != null)
            mediaplayer.pause();
    }
    private void mediumStoppen() {
        if (mediaplayer != null)
            mediaplayer.stop();
    }
    private void mediumStumm() {
        if (mediaplayer != null)
            if (!mediaplayer.isMute()) {
                mediaplayer.setMute(true);
            } else {
                mediaplayer.setMute(false);
            }


    }

    private void setIcon(Button button, Ikon name) {
        FontIcon tempIcon = new FontIcon();
        tempIcon.setIconCode(name);
        button.setGraphic(tempIcon);
    }



}
