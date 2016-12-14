/*
 * Erhvervsakademi Sydvest, Computer Science 2016-2017, Carlos F. Ognissanti
 * To change this header, choose Tools | Templates
 * and open the template in the editor.
 */
package BE;

import java.util.ArrayList;
import java.util.List;

/**
 * the playlist class holds the properties and methods needed but was not worked
 * on enough to be implemented
 *
 * @author Yuki
 */
public class Playlist {

    private List<Song> songList;
    private String playlistName;

    public Playlist() {
        songList = new ArrayList<Song>();
    }

    public void setPlaylistName() {
        this.playlistName = playlistName;
    }

    public List<Song> getSongList() {
        return songList;
    }

    public void setSongList(List<Song> songList) {
        this.songList = songList;
    }

    public String getPlaylistName() {
        return playlistName;
    }

}
