package com.wynk.juckbox.model;

public class Songs {

    private int songId;
    private String songName;
    private String aitistName;
    private String gener;
    private String album;
    private String duration;
    public Songs(int songId, String songName, String aitistName, String gener, String album, String duration) {

        this.songId = songId;
        this.songName = songName;
        this.aitistName = aitistName;
        this.gener = gener;
        this.album = album;
        this.duration = duration;
    }

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getAitistName() {
        return aitistName;
    }

    public void setAitistName(String aitistName) {
        this.aitistName = aitistName;
    }

    public String getGener() {
        return gener;
    }

    public void setGener(String gender) {
        this.gener = gender;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return String.format("%7s\t%30s\t%30s\t%15s\t%20s\t\t%20s",songId,songName,aitistName,gener,album,duration)+"\n";
    }
}