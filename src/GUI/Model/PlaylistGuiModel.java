/*
 * Erhvervsakademi Sydvest, Computer Science 2016-2017, Carlos F. Ognissanti
 * To change this header, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Model;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Yuki
 */
public class PlaylistGuiModel extends RecursiveTreeObject<PlaylistGuiModel> {

    public StringProperty name;

    public PlaylistGuiModel(String playlist)
      {
        this.name = new SimpleStringProperty(playlist);
      }
}
