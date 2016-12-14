/*
 * Erhvervsakademi Sydvest, Computer Science 2016-2017, Carlos F. Ognissanti
 * To change this header, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Yuki
 */
public class AddPlaylistViewController implements Initializable
 {

   @FXML
   private JFXTextField newPlaylistTextField;
   @FXML
   private JFXButton cancelButton;
   @FXML
   private JFXButton saveButton;

   /**
    * Initializes the controller class.
    */
   @Override
   public void initialize(URL url, ResourceBundle rb)
    {
    }

//exit the stage
   @FXML
   void exitPlaylistStage(ActionEvent event)
    {
      Stage stage = (Stage) cancelButton.getScene().getWindow();
      stage.hide();
    }

 }
