/*
 * Erhvervsakademi Sydvest, Computer Science 2016-2017, Carlos F. Ognissanti
 * To change this header, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.Port;

/**
 * Class responsible of playing the songs and all other functions contained in
 * the "mediaplayer" class already existing in the java files
 *
 * @author Yuki
 */
public class MediaPlayerHandler {

    MediaPlayer mp;

    /**
     * checks if a mediaplayer is already instanciated then checks if it is
     * playing, if so, it stops it
     *
     */
    public void setMedia(Media media) {
        if (mp != null) {
            if (mp.getStatus().equals(Status.PLAYING)) {
                mp.stop();
            }
        }
        mp = new MediaPlayer(media);
    }

    /**
     * makes the mediaplayer, play
     */
    public void play() {
        mp.play();
    }

    /**
     * makes the mediaplayer, stop
     */
    public void stop() {
        mp.stop();
    }

    /**
     * makes the mediaplayer, mute
     */
    public void mute() {
        mp.setMute(true);
    }

    /**
     * functionality for setting the volume of the currently played song in the
     * mediaplayer. and showing the current % volume in GUI
     *
     */
    public void setVolumeAlgorithm(float vol) {
        try {
            Mixer.Info[] infos = AudioSystem.getMixerInfo();
            for (Mixer.Info info : infos) {
                Mixer mixer = AudioSystem.getMixer(info);
                if (mixer.isLineSupported(Port.Info.SPEAKER)) {
                    Port port = (Port) mixer.getLine(Port.Info.SPEAKER);
                    port.open();
                    if (port.isControlSupported(FloatControl.Type.VOLUME)) {
                        FloatControl volume = (FloatControl) port.getControl(FloatControl.Type.VOLUME);
                        volume.setValue(vol / 100);
                    }
                    port.close();
                }
            }
        } catch (LineUnavailableException ex) {
            Logger.getLogger(MediaPlayerHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
