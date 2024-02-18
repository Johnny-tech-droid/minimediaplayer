package de.fernschulen.minimediaplayer;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import javafx.stage.FileChooser;

import javafx.stage.Stage;

import org.kordamp.ikonli.Ikon;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;

import java.io.File;

import javafx.scene.media.MediaPlayer.*;


public class FXMLController {
    @FXML
    private Button openMediaBtn;
    @FXML
    private Button playPauseMediaBtn;

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
        playPauseMediaBtn.setOnAction(e-> startStopMedium());
        stopMediaBtn.setOnAction(e-> mediumStoppen());
        muteMediaBtn.setOnAction(e-> mediumStumm());
        playMediaMenuItem.setOnAction(e -> mediumAbspielen());
        pauseMediaMenuItem.setOnAction(e -> mediumPausieren());
        stopMediaMenuItem.setOnAction(e-> mediumStoppen());
        quitAppMenuItem.setOnAction(e-> Platform.exit());

        setIcon(openMediaBtn, FontAwesomeSolid.FOLDER_OPEN);
        liste.setOnMouseClicked(e-> openFromList());

    }

    /**
     * Initialisiert den FXML-Controller, richtet Event-Handler ein und setzt Icons
     */

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
        if (datei != null) {
            dateiLaden(datei);
            if (mediaplayer.getStatus().equals(Status.PAUSED))
                setIcon(playPauseMediaBtn, FontAwesomeSolid.PLAY);
            if (mediaplayer.getStatus().equals(Status.PLAYING))
                setIcon(playPauseMediaBtn, FontAwesomeSolid.PAUSE);
            setIcon(muteMediaBtn, FontAwesomeSolid.VOLUME_OFF);
            setIcon(stopMediaBtn, FontAwesomeSolid.STOP);
        }

    }

    /**
     * Öffnet einen Dateidialog zur Auswahl von Mediendateien und lädt die ausgewählte Datei.
     * und aktualisiert die Benutzeroberfläche
     */
    private void dateiLaden(File datei) {
        if (mediaplayer != null && mediaplayer.getStatus() == Status.PLAYING) {
            mediaplayer.stop();

        }
        try {
           Media medium = new Media(datei.toURI().toString());
           mediaplayer = new MediaPlayer(medium);
           mediaView.setMediaPlayer(mediaplayer);
//           mediaView.setFitWidth(619);
//           mediaView.setFitHeight(593);
           mediaView.setPreserveRatio(true);
           mediaplayer.play();
           setIcon(playPauseMediaBtn, FontAwesomeSolid.PAUSE);
           if (!liste.getItems().contains(datei.toString()))
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

    /**
     * Ruft das Dateiobjekt von dem ausgewählten Element in der Medienliste ab.
     * @return Die ausgewählte Datei
     */
    private File getValueFromList() {
        String path = liste.getSelectionModel().getSelectedItem().toString();

        return new File(path);
    }
    /**
     * Öffnet die ausgewählte Mediendatei aus der Liste und lädt sie in den Media Player.
     */
    private void openFromList() {
        if (liste.getSelectionModel().getSelectedItem() != null) {
            dateiLaden(getValueFromList());
        }
    }
    /**
     * Spielt die aktuell geladene Mediendatei ab.
     */
    private void mediumAbspielen() {
        if (mediaplayer != null) {
            mediaplayer.play();
            setIcon(playPauseMediaBtn, FontAwesomeSolid.PAUSE);
        }

    }
    /**
     * Pausiert die aktuell geladene Mediendatei.
     */
    private void mediumPausieren() {
        if (mediaplayer != null) {
            mediaplayer.pause();
            setIcon(playPauseMediaBtn, FontAwesomeSolid.PLAY);

        }
    }
    /**
     * Wechselt zwischen Abspielen und Pausieren der aktuell geladenen Mediendatei.
     */
    private void startStopMedium() {
        if (mediaplayer != null) {
            if (mediaplayer.getStatus().equals(Status.PLAYING)) {
                mediaplayer.pause();
                setIcon(playPauseMediaBtn, FontAwesomeSolid.PLAY);
            } else {
                mediaplayer.play();
                setIcon(playPauseMediaBtn, FontAwesomeSolid.PAUSE);
            }
        }
    }

    /**
     * Stoppt die gerade abgespielte Mediendatei.
     */
    private void mediumStoppen() {
        if (mediaplayer != null) {
            mediaplayer.stop();
            setIcon(playPauseMediaBtn, FontAwesomeSolid.PLAY);


        }

    }
    /**
     * Schaltet den Ton der gerade geladenen Mediendatei stumm oder wieder ein.
     */
    private void mediumStumm() {
        if (mediaplayer != null)
            mediaplayer.setMute(!mediaplayer.isMute());


    }
    /**
     * Setzt das Icon eines JavaFX-Buttons mit einem angegebenen FontAwesome-Icon.
     *
     * @param button Der Button, für den das Icon gesetzt werden soll.
     * @param name   Der Name des FontAwesome-Icons.
     */
    private void setIcon(Button button, Ikon name) {
        FontIcon tempIcon = new FontIcon();
        tempIcon.setIconCode(name);
        button.setGraphic(tempIcon);
    }




}
