/*
 * Erhvervsakademi Sydvest, Computer Science 2016-2017, Carlos F. Ognissanti
 * To change this header, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Controller;

import BE.Song;
import BLL.MediaPlayerHandler;
import BLL.SongManager;
import GUI.Main.MyTunes;
import GUI.Model.SongGuiModel;
import GUI.Model.UpdateListModel;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.beans.value.ObservableObjectValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.util.Callback;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Yuki
 */
public class MainViewController implements Initializable {

    @FXML
    private JFXTextField searchText;
    @FXML
    private JFXButton playButton;
    @FXML
    private ImageView muteButton;
    @FXML
    private JFXSlider volumeSlider;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private AnchorPane anchorPane;
    public static AnchorPane rootP;
    @FXML
    private JFXButton stopButton;
    @FXML
    private JFXTreeTableView<SongGuiModel> songList;
    private SongManager songManager = new SongManager();

    private MediaPlayerHandler mpHandler = new MediaPlayerHandler();
    @FXML
    private Pane mediaViewer;

    List<SongGuiModel> allSongsList;
    ObservableList<SongGuiModel> songInfo;
    @FXML
    private Label songTextLabel;
    @FXML
    private JFXButton deleteSong;
    @FXML
    private Label changelogLabel;
    @FXML
    private Label changelogLabelFunctions;
    @FXML
    private ImageView mutedButton;

    /* It's the button to open the side panel and it's animation*/
    private void hamburgerTransition() {
        rootP = anchorPane;
        try {
            VBox box = FXMLLoader.load(getClass().getResource("/GUI/View/SidePanelContent.fxml"));
            drawer.setSidePane(box);
        } catch (IOException ex) {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
        transition.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e)
                -> {
            transition.setRate(transition.getRate() * -1);
            transition.play();
            if (drawer.isShown()) {
                drawer.close();
            } else {
                drawer.open();
            }
        });
    }
// Creates the columns for the list view

    private void songListViewColumns() {

        JFXTreeTableColumn<SongGuiModel, String> songNameColumn = new JFXTreeTableColumn<>("Song");
        songNameColumn.setPrefWidth(281);
        songNameColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<SongGuiModel, String>, ObservableValue<String>>() {
            @Override
            public ObservableObjectValue<String> call(TreeTableColumn.CellDataFeatures<SongGuiModel, String> param) {
                return param.getValue().getValue().name;
            }
        });
        JFXTreeTableColumn<SongGuiModel, String> artistColumn = new JFXTreeTableColumn<>("Artist");
        artistColumn.setPrefWidth(280);
        artistColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<SongGuiModel, String>, ObservableValue<String>>() {
            @Override
            public ObservableObjectValue<String> call(TreeTableColumn.CellDataFeatures<SongGuiModel, String> param) {
                return param.getValue().getValue().artist;
            }
        });
        JFXTreeTableColumn<SongGuiModel, String> albumColumn = new JFXTreeTableColumn<>("Album");
        albumColumn.setPrefWidth(278);
        albumColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<SongGuiModel, String>, ObservableValue<String>>() {
            @Override
            public ObservableObjectValue<String> call(TreeTableColumn.CellDataFeatures<SongGuiModel, String> param) {
                return param.getValue().getValue().album;
            }
        });
        JFXTreeTableColumn<SongGuiModel, String> durationColumn = new JFXTreeTableColumn<>("Duration");
        durationColumn.setPrefWidth(84);
        durationColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<SongGuiModel, String>, ObservableValue<String>>() {
            @Override
            public ObservableObjectValue<String> call(TreeTableColumn.CellDataFeatures<SongGuiModel, String> param) {
                return param.getValue().getValue().duration;
            }
        });

        final TreeItem<SongGuiModel> root = new RecursiveTreeItem<>(songInfo, RecursiveTreeObject::getChildren);
        songList.getColumns().setAll(songNameColumn, artistColumn, albumColumn, durationColumn);
        songList.setRoot(root);
        songList.setShowRoot(false);
    }

    /*It plays the song when you double click it*/
    public void doubleClickPlaySong() {
        songList.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                    playMedia(null); //It calls "playMedia()"
                }
            }
        });
    }

    /**
     * sets the media to the given path
     *
     */
    void setMedia(String path) {
        mpHandler.setMedia(new Media(path));
    }

    /**
     * It plays the song chosen in the list
     */
    @FXML
    void playMedia(ActionEvent event) {
        try {
            SongGuiModel user = songList.getSelectionModel().getSelectedItem().getValue();
            mpHandler.setMedia(new Media(user.path));
            mpHandler.setVolumeAlgorithm((float) volumeSlider.getValue());
            mpHandler.play();
            //Sets label of the song
            String source = user.name.getValue() + ",  " + user.artist.getValue();
            source = source.substring(source.lastIndexOf("/") + 1).replaceAll("%20", " ");

            songTextLabel.setText(
                    "Now Playing: " + source);
        } catch (Exception e) {
            System.out.println("No Song Selected");
        }

    }

    /**
     *
     * it resumes the song
     */
    void resumeMedia(ActionEvent event
    ) {
        mpHandler.play();
    }

    /**
     * It stops the song
     */
    @FXML
    void stopMedia(ActionEvent event
    ) {
        mpHandler.stop();
        //set text
        songTextLabel.setText("Player stopped");
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (!MyTunes.isSplashLoaded) {
            loadSplashScreen();
        }

        doubleClickPlaySong();
        //Updates the columns.
        updateList();
        hamburgerTransition(); //The button for the side panel
        UpdateListModel uList = UpdateListModel.getUpdateList();
        uList.setMainView(this);
    }

    /**
     * It filters the songs by what has been put into the search field
     */
    @FXML
    void searchOnInput(KeyEvent event
    ) {
        try {
            String query = searchText.getText().trim();
            if (query.isEmpty()) {
                songInfo.clear();
                songInfo.addAll(allSongsList);
            } else {
                List<SongGuiModel> searchResults = new ArrayList<>();
                for (SongGuiModel song : allSongsList) {
                    String songValue = song.name.getValue().toLowerCase();
                    String artistValue = song.artist.getValue().toLowerCase();
                    if (songValue.contains(query.toLowerCase()) || artistValue.contains(query.toLowerCase())) {
                        searchResults.add(song);
                    }
                }
                //    List<SongGuiModel> searchResults = songFinder.mathchSongsContains(query)
                songInfo.clear();
                songInfo.addAll(searchResults);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    // Creates the AllSongList and fills the observable list
    public void updateList() {
        allSongsList = new ArrayList<>();
        try {
            List<Song> listOfAllSongs = songManager.GetAllSongs();
            for (Song song : listOfAllSongs) {
                String path = song.getFile().getPath();
                path = path.replace("\\", "/");
                path = "file:///" + path;
                allSongsList.add(new SongGuiModel(song, path));
            }
        } catch (IOException ex) {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

        songInfo = FXCollections.observableArrayList(allSongsList);
        songListViewColumns();
    }

    /**
     * the controls for the volume
     */
    @FXML
    public void handleVolume() {
        mpHandler.setVolumeAlgorithm((float) volumeSlider.getValue());
    }

    /**
     * deletes selected song
     *
     */
    private void deleteSong(ActionEvent event) {
        String name = songList.getSelectionModel().getSelectedItem().getValue().name.getValue();
        name = name.replaceAll("%20", " ");
        songManager.deleteSong(name);
        updateList();
    }

    /**
     * mutes the currently playing song
     *
     */
    @FXML
    private void muteMedia(MouseEvent event) {
        if (volumeSlider.getValue() > 0) {
            volumeSlider.adjustValue(0);
            mpHandler.setVolumeAlgorithm(0);
            muteButton.setVisible(false);
            mutedButton.setVisible(true);
        } else {
            volumeSlider.adjustValue(60);
            mpHandler.setVolumeAlgorithm(60);
            muteButton.setVisible(true);
            mutedButton.setVisible(false);
        }
    }

    /**
     * asks if you are sure you want to delete the currently selected song
     *
     */
    @FXML
    private void confirmationDialog(ActionEvent event) {
        SongGuiModel user = songList.getSelectionModel().getSelectedItem().getValue();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Confirmation");
        String source = user.name.getValue() + ", by " + user.artist.getValue();
        source = source.substring(source.lastIndexOf("/") + 1).replaceAll("%20", " ");
        alert.setHeaderText("You are about to delete: " + source + " from your library.");
        alert.setContentText("Are you sure you want to delete this song?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            deleteSong(event);
        } else {
            System.out.println("Song deletion canceled by user");
        }
    }

    /**
     * makes the splash screen come up when you open the program
     */
    private void loadSplashScreen() {
        try {
            MyTunes.isSplashLoaded = true;

            StackPane pane = FXMLLoader.load(getClass().getResource(("/GUI/View/SplashFXML.fxml")));
            anchorPane.getChildren().setAll(pane);

            FadeTransition fadeIn = new FadeTransition(Duration.seconds(3), pane);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.setCycleCount(1);

            FadeTransition fadeOut = new FadeTransition(Duration.seconds(3), pane);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);
            fadeOut.setCycleCount(1);

            fadeIn.play();

            fadeIn.setOnFinished((e)
                    -> {
                fadeOut.play();
            });

            fadeOut.setOnFinished((e)
                    -> {
                try {
                    AnchorPane parentContent = FXMLLoader.load(getClass().getResource(("/GUI/View/MainView.fxml")));
                    anchorPane.getChildren().setAll(parentContent);
                } catch (IOException ex) {
                    Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

        } catch (IOException ex) {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
