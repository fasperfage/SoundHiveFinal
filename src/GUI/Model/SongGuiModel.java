/*
 * Erhvervsakademi Sydvest, Computer Science 2016-2017, Carlos F. Ognissanti
 * To change this header, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Model;

import BE.Song;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Yuki
 */
public class SongGuiModel extends RecursiveTreeObject<SongGuiModel>
{

 public StringProperty name;
 public StringProperty artist;
 public StringProperty album;
 public StringProperty duration;
 public String path;

// Adapter pattern from stringproperty to string
 public SongGuiModel(Song song, String path)
 {
  this.name = new SimpleStringProperty(song.getName());
  this.artist = new SimpleStringProperty(song.getArtist());
  this.album = new SimpleStringProperty(song.getAlbum());
  this.duration = new SimpleStringProperty(song.getDuration());
  this.path = path;
 }

}
