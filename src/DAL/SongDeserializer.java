/*
 * Erhvervsakademi Sydvest, Computer Science 2016-2017, Carlos F. Ognissanti
 * To change this header, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BE.Song;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Yuki
 */
public class SongDeserializer {

    /**
     * This gets the file from the musicfolder, deserializes it and gives it to
     * the observablelist in the mainviewcontroller
     */
    public List<Song> deserialize() throws FileNotFoundException, IOException, ClassNotFoundException {
        List<Song> songList = new ArrayList<>(); //Container
        File folder = new File("src/MusicFolder");
        if (folder.listFiles() == null) {
            return songList;
        }
        for (File f : folder.listFiles()) // All the file in the folder
        {
            try (FileInputStream fis = new FileInputStream(f))//Read data
            {
                ObjectInputStream ois = new ObjectInputStream(fis); // Converting code
                Song song = (Song) ois.readObject(); //Read the converted object
                songList.add(song); // Add to the list
            }
        }
        return songList;
    }
}
