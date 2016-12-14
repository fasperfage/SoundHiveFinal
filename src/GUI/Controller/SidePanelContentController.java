/*
 * Erhvervsakademi Sydvest, Computer Science 2016-2017, Carlos F. Ognissanti
 * To change this header, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Controller;

import GUI.Model.PlaylistGuiModel;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableObjectValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

public class SidePanelContentController implements Initializable
 {

   @FXML
   private JFXButton exit;
   @FXML
   private JFXTreeTableView<PlaylistGuiModel> playlistList;
   @FXML
   private JFXButton newPlaylistButton;
   @FXML
   private JFXButton addSongButton;

   private Stage addPlaylistStage;
   private Stage addSongStage;
   private MainViewController mainView;

   private void playlistViewColumn()
    {//Displays the column of the playlists
      JFXTreeTableColumn<PlaylistGuiModel, String> playlistColumn = new JFXTreeTableColumn<>("Playlists");
      playlistColumn.setPrefWidth(290);
      playlistColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<PlaylistGuiModel, String>, ObservableValue<String>>()
       {
         @Override
         public ObservableObjectValue<String> call(TreeTableColumn.CellDataFeatures<PlaylistGuiModel, String> param)
          {
            return param.getValue().getValue().name;
          }
       });

      ObservableList<PlaylistGuiModel> playlistInfo = FXCollections.observableArrayList();
      /**
       * TO ADD ELEMENTS TO THE SONG LIST VIEW: users.add(new User ("song name
       * here", "artist name here", "album name here", "duration"));
       */
      // EXAMPLE:
      playlistInfo.add(new PlaylistGuiModel("\tAll Songs"));
      //END OF EXAMPLE
      final TreeItem<PlaylistGuiModel> root = new RecursiveTreeItem<PlaylistGuiModel>(playlistInfo, RecursiveTreeObject::getChildren);
      playlistList.getColumns().setAll(playlistColumn);
      playlistList.setRoot(root);
      playlistList.setShowRoot(false);
    }

   @Override
   public void initialize(URL url, ResourceBundle rb)
    {
      playlistViewColumn();
      try
      {
         addPlaylistStage = createAddPlaylistDialog();
         addSongStage = createAddSongDialog();
      }
      catch (IOException ex)
      {
         System.out.println(ex.getMessage());
      }
    }

   @FXML
   private void exit(ActionEvent event)
    {
      System.exit(0);
    }
//Open the dialog to add playlists

   @FXML
   private void displayAddPlaylistDialog(ActionEvent event) throws IOException
    {
      try
      {
         addPlaylistStage.show();
         addPlaylistStage.setX(addSongButton.getLayoutX() + 100);
         addPlaylistStage.setY(exit.getLayoutY() * 2.5);
      }
      catch (Exception ex)
      {
         System.out.println(ex.getMessage());
      }
    }
//Open the dialog to add songs

   @FXML
   private void displayAddSongDialog(ActionEvent event) throws IOException
    {
      try
      {
         addSongStage.show();
         addSongStage.setX(addSongButton.getLayoutX() + 100);
         addSongStage.setY(exit.getLayoutY() * 2);
      }
      catch (Exception ex)
      {
         System.out.println(ex.getMessage());
      }
    }
//Open the dialog to add playlists

   private Stage createAddPlaylistDialog() throws IOException
    {
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/GUI/View/AddPlaylistView.fxml"));
      Parent root2 = (Parent) fxmlLoader.load();
      Stage playlistStage = new Stage();
      playlistStage.initStyle(StageStyle.UNDECORATED);
      playlistStage.setScene(new Scene(root2));
      playlistStage.hide();
      playlistStage.setAlwaysOnTop(true);
      return playlistStage;
    }
//Open the dialog to add songs

   private Stage createAddSongDialog() throws IOException
    {
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/GUI/View/AddSongView.fxml"));
      Parent root1 = (Parent) fxmlLoader.load();
      AddSongViewController addSongView = fxmlLoader.getController();

      Stage stage = new Stage();
      stage.setScene(new Scene(root1));
      stage.initStyle(StageStyle.UNDECORATED);
      stage.hide();
      stage.setAlwaysOnTop(true);
      return stage;
    }

 }
