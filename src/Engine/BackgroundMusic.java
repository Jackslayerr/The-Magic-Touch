package Engine;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class BackgroundMusic {
    private Clip clip;

    public void loadMusic(String audioFilePath) {
        try { // Load the audio file from the resources folder
            URL audioURL = getClass().getResource(audioFilePath);

            if (audioURL == null) {
                System.out.println("No sound found fuh: " + audioFilePath);
                return;
            }

            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioURL);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            setVolume(-10.0f);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void setVolume(float decibels) {
        if (clip != null && clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(decibels);
        }
    }

    public void play() {
        if (clip != null) {
            clip.setFramePosition(0); // rewind song to start
            clip.start();
        }
    }

    public void loop() {
        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY); // The music loops forever!
        }
    }

    public void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
        clip.close();
    }

}
