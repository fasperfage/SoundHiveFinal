/*
 * Erhvervsakademi Sydvest, Computer Science 2016-2017, Carlos F. Ognissanti
 * To change this header, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author Yuki
 */
public class MyTunes extends Application
 {

   public static Boolean isSplashLoaded = false;

   @Override
   public void start(Stage stage) throws Exception
    {
      Parent root = FXMLLoader.load(getClass().getResource("/GUI/View/MainView.fxml"));

      Scene scene = new Scene(root);
      String title = "Sound Hive";
      stage.setTitle(title);
      stage.setScene(scene);
      stage.show();
      stage.setResizable(true);
      stage.setMaximized(false);
//      Screen screen = Screen.getPrimary();
//      Rectangle2D bounds = screen.getVisualBounds();
//      stage.setX(bounds.getMinX());
//      stage.setY(bounds.getMinY());
//      stage.setWidth(bounds.getWidth());
//      stage.setHeight(bounds.getHeight());
      stage.setMinWidth(1024);
      stage.setMinHeight(768);
      stage.getIcons().add(new Image("file:src/GUI/Images/hiveicon.png"));
    }

   /**
    * @param args the command line arguments
    */
   public static void main(String[] args)
    {
      launch(args);
    }

 }
