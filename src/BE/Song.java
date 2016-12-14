/*
 * Erhvervsakademi Sydvest, Computer Science 2016-2017, Carlos F. Ognissanti
 * To change this header, choose Tools | Templates
 * and open the template in the editor.
 */
package BE;

import java.io.File;
import java.io.Serializable;

/**
 * This class holds the metadata of the file and implements Serializable so that
 * it can be stored
 *
 * @author Fage
 */
public class Song implements Serializable {

    private String name;
    private String artist;
    private String album;
    private String duration;
    private File file;

    /**
     * holds all of the information about the song so it can easily be pulled
     * from one place to another
     *
     */
    public Song(String name, String artist, String album, String duration, File file) {
        this.name = name;
        this.artist = artist;
        this.album = album;
        this.duration = duration;
        this.file = file;
    }

    /**
     * getters and setters for all of the properties of the song
     *
     */
    public void setName(String name) {
        this.name = name;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getName() {
        return this.name;
    }

    public String getArtist() {
        return this.artist;
    }

    public String getAlbum() {
        return this.album;
    }

    public String getDuration() {
        return this.duration;
    }

    public File getFile() {
        return file;
    }

    @Override
    public String toString() {
        return new StringBuffer(" Name : ").append(this.name).append(" Artist : ")
                .append(this.artist).append(" Album : ").append(this.album)
                .append(" Duration : ").append(this.duration).toString();
    }
}
