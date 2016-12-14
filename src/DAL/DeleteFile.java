/*
 * Erhvervsakademi Sydvest, Computer Science 2016-2017, Carlos F. Ognissanti
 * To change this header, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import java.io.File;

/**
 * class responsible for deleting songs
 *
 * @author Yuki
 */
public class DeleteFile {

    /**
     * gets the song with given name in the musicfolder, and deletes it
     *
     */
    public void delete(String nameOfFile) {
        File file = new File("src/MusicFolder/" + nameOfFile);
        file.delete();
    }

}
