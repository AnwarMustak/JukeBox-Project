package org.niit.jukebox.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.InvalidPathException;
import java.util.Scanner;
// file using Clip Object

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
// Java program to play an Audio

public class PlayerService
{

    // to store current position
    Long currentFrame;
    Clip clip;

    // current status of clip
    String status;

    // AudioInputStream audioInputStream;
    String filePath;

    // constructor to initialize streams and clip
    public void playSong(int songId)  throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        // create AudioInputStream object
        // audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());

        // create clip reference

            clip = AudioSystem.getClip();

            // open audioInputStream to the clip
            clip.open(audioInputStream(songId));

            clip.loop(Clip.LOOP_CONTINUOUSLY);

    }

    public AudioInputStream audioInputStream(int songId) throws UnsupportedAudioFileException ,IOException {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("/Users/akhil/Documents/NIITCourse/JavaPractice/Course 6/Sprint-3/Jukebox/src/main/resources/songs/"+songId+".wav").getAbsoluteFile());
        return audioInputStream;
    }

    // Method to play the audio
    public void play() {
            //start the clip
            clip.start();
            status = "play";
    }

    // Method to pause the audio
    public void pause() {
        if (status.equals("paused")) {
            System.out.println("audio is already paused");
            return;
        }
        this.currentFrame = this.clip.getMicrosecondPosition();
        clip.stop();
        status = "paused";
    }

    // Method to resume the audio
    public void resumeAudio(int songId) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        if (status.equals("play")) {
            System.out.println("Audio is already " + "being played");
            return;
        }
        clip.close();
        resetAudioStream(songId);
        clip.setMicrosecondPosition(currentFrame);
        this.play();
    }

    // Method to restart the audio
    public void restart(int songId) throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        clip.stop();
        clip.close();
        resetAudioStream(songId);
        currentFrame = 0L;
        clip.setMicrosecondPosition(0);
        this.play();
    }

    // Method to stop the audio
    public void stop() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        currentFrame = 0L;
        clip.stop();
        clip.close();
    }
    public void forWord10sec(){
        System.out.println("Song Is forwarded For 10 Sec");
        clip.setMicrosecondPosition(clip.getMicrosecondPosition()+2000000);
    }
    public void backWord10sec(){
        System.out.println("Song Is forwarded For 10 Sec");
        clip.setMicrosecondPosition(clip.getMicrosecondPosition()-3000000);
    }

    // Method to reset audio stream
    public void resetAudioStream(int songId) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        // audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
        clip.open(audioInputStream(songId));
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
}