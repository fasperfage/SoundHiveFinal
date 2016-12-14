/*
 * Erhvervsakademi Sydvest, Computer Science 2016-2017, Carlos F. Ognissanti
 * To change this header, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BE.Song;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 *
 * @author Yuki
 */
public class SongSerializer {

    /**
     * Writes a file into the musicfolder
     * when it's called from the addsongviewcontroller
     */
    public void Serialize(Song song) throws FileNotFoundException, IOException {
        try (FileOutputStream fos = new FileOutputStream("src/MusicFolder/" + song.getName())) {
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(song);
        }
    }
}
