/*
 * Erhvervsakademi Sydvest, Computer Science 2016-2017, Carlos F. Ognissanti
 * To change this header, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import BE.Song;
import DAL.DeleteFile;
import DAL.SongDeserializer;
import DAL.SongSerializer;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * class that is in charge of letting the GUI and DAL communicate when;
 * serializing a song and putting it in the musicfolder deserializing a song and
 * feeding it to the observable list
 *
 * @author Yuki
 */
public class SongManager {

    SongSerializer ss = new SongSerializer();
    SongDeserializer sds = new SongDeserializer();

    /*when addSong button in "AddSongViewController" is clicked
    **songserializer is called to serialize the song
     */
    public Song AddSong(String name, String artist, String album, String duration, String file) throws IOException {
        Song song = new Song(name, artist, album, duration, new File(file));
        ss.Serialize(song);
        return song;
    }
//Getting a song with given name

    public Song GetSong(String name) throws IOException, FileNotFoundException, ClassNotFoundException {
        List<Song> songList = sds.deserialize();
        for (Song song : songList) {
            if (song.getName() == name) {
                return song;
            }
        }
        return null;
    }
// Get all the songs in the folder to send it to the GUI

    public List<Song> GetAllSongs() throws IOException, FileNotFoundException, ClassNotFoundException {
        return sds.deserialize();
    }

    public void deleteSong(String songName) {
        new DeleteFile().delete(songName);
    }
}
