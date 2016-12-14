/*
 * Erhvervsakademi Sydvest, Computer Science 2016-2017, Carlos F. Ognissanti
 * To change this header, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Controller;

import BLL.SongManager;
import GUI.Model.UpdateListModel;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author Yuki
 */
public class AddSongViewController implements Initializable {

    private Window windowStage;
    @FXML
    public JFXTextField titleTextField;
    @FXML
    public JFXTextField albumTextField;
    @FXML
    public JFXTextField artistTextField;
    @FXML
    private JFXButton cancelButton;
    @FXML
    private JFXButton addSongButton;
    @FXML
    public JFXTextField fileTextField;
    @FXML
    private JFXButton searchFileButton;
    @FXML
    private AnchorPane addAnchor;
    File selectedFile;
    @FXML
    private Label requiredFileLabel;
    @FXML
    private Label requiredSongLabel;
    @FXML
    private Label requiredArtistLabel;
    @FXML
    private JFXTextField durationTextField;

    @FXML
    private Label requiredAlbumLabel;
    private SongManager songManager;
    private MainViewController mainView;

    /**
     * instanciates the controller and a songmanager within it
     */
    public AddSongViewController() {
        songManager = new SongManager();
    }

    //It adds a song when you click the button
    @FXML
    void addSong(ActionEvent event) throws IOException {
        getDuration();
        if (!fileTextField.getText().isEmpty() && !albumTextField.getText().isEmpty() && !artistTextField.getText().isEmpty() && !titleTextField.getText().isEmpty()) {
            getDuration();
        } else {
        }
    }

    // get the duration and adds it to the metadata about the song
    public void getDuration() {
        File filestring = new File(fileTextField.getText());
        Media file = new Media(filestring.toURI().toString());

        MediaPlayer mediaPlayer = new MediaPlayer(file);

        mediaPlayer.setOnReady(new Runnable() {

            @Override
            public void run() {
                int minutes = (int) file.getDuration().toSeconds() / 60;
                int calculation = minutes * 60;
                int seconds = (int) file.getDuration().toSeconds() - calculation;
                if (seconds < 10) {
                    durationTextField.setText(String.valueOf(minutes + ":0" + seconds));
                } else {
                    durationTextField.setText(String.valueOf(minutes + ":" + seconds));
                }
                try {
                    getTextAndAddSong();
                } catch (IOException ex) {

                }
            }
        });
    }

    //gets the data from the song, and the meta data the user gave the view
    public void getTextAndAddSong() throws IOException {
        String name = titleTextField.getText();
        String artist = artistTextField.getText();
        String album = albumTextField.getText();
        String duration = durationTextField.getText();
        String file = fileTextField.getText();
        file = file.replace(" ", "%20");
        file = file.replace("\\", "/");
        songManager.AddSong(name, artist, album, duration, file);
        durationTextField.clear();
        UpdateListModel uList = UpdateListModel.getUpdateList();
        uList.updateMainList();
        titleTextField.clear();
        artistTextField.clear();
        albumTextField.clear();
        fileTextField.clear();
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    void exit(ActionEvent event) throws IOException {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.hide();
    }

    /**
     * the filter functionality for the search option
     *
     */
    @FXML
    private void searchFile(ActionEvent event) {

        FileChooser findFile = new FileChooser();
        FileChooser.ExtensionFilter fileExtensions
                = new FileChooser.ExtensionFilter(
                        "Audio Files (*.mp3 , *.wav )", "*.mp3", "*.wav");
        findFile.getExtensionFilters().add(fileExtensions);
        findFile.setTitle("Select a Song");
        File selectedFile = findFile.showOpenDialog(windowStage);

        if (selectedFile != null) {
            fileTextField.setText(selectedFile.getAbsolutePath());
        } else {
        }
    }

}
