package org.niit.jukebox.service;// Java program to play an Audio
// file using Clip Object

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SimpleAudioPlayer {

    // to store current position
    Long currentFrame;
    Clip clip;

    // current status of clip
    String status;

   // AudioInputStream audioInputStream;
     String filePath;

    // constructor to initialize streams and clip
    public SimpleAudioPlayer(int songId) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        // create AudioInputStream object
       // audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());

        // create clip reference
        clip = AudioSystem.getClip();

        // open audioInputStream to the clip
        clip.open(audioInputStream(songId));

        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public AudioInputStream audioInputStream(int songId) throws UnsupportedAudioFileException, IOException {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("/Users/akhil/Documents/NIITCourse/JavaPractice/Course 6/Sprint-3/Jukebox/src/main/resources/songs/108.wav").getAbsoluteFile());
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
    // Method to jump over a specific part
    public void jump(long c,int songId) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        if (c > 0 && c < clip.getMicrosecondLength()) {
            clip.stop();
            clip.close();
            resetAudioStream(songId);
            currentFrame = c;
            clip.setMicrosecondPosition(c);
            this.play();
        }
    }
    // Method to reset audio stream
    public void resetAudioStream(int songId) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
       // audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
        clip.open(audioInputStream(songId));
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    // Work as the user enters his choice

    private void gotoChoice(int c,int songId) throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        switch (c) {
            case 1:
                pause();
                break;
            case 2:
                resumeAudio(songId);
                break;
            case 3:
                restart(songId);
                break;
            case 4:
                stop();
                break;
            case 5:
                System.out.println("Enter time (" + 0 + ", " + clip.getMicrosecondLength() + ")");
                Scanner sc = new Scanner(System.in);
                long c1 = sc.nextLong();
                jump(c1,songId);
                break;
        }

    }
    public static void main(String[] args) {
        try {
            int songId=108;
            //filePath = "/Users/akhil/Documents/NIITCourse/JavaPractice/Course 6/Sprint-3/jukebox-practice/src/main/resources/songs/100.wav";
            SimpleAudioPlayer audioPlayer = new SimpleAudioPlayer(songId);
///Users/akhil/Documents/NIITCourse/JavaPractice/Course 6/Sprint-3/Jukebox/src/main/resources/songs/100.wav
            audioPlayer.play();
            Scanner sc = new Scanner(System.in);

            while (true) {
                System.out.println("1. pause");
                System.out.println("2. resume");
                System.out.println("3. restart");
                System.out.println("4. stop");
                System.out.println("5. Jump to specific time");
                int c = sc.nextInt();
                audioPlayer.gotoChoice(c,songId);
                if (c == 4)
                    break;
            }
            sc.close();
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();

        }
    }
}
