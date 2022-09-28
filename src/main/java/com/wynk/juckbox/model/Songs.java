package com.wynk.juckbox.model;

public class Songs {

    private int songId;
    private String songName;
    private String aitistName;
    private String gender;
    private String album;
    private String duration;

    public Songs(String songName, String aitistName, String gender, String album, String duration) {
        this.songName = songName;
        this.aitistName = aitistName;
        this.gender = gender;
        this.album = album;
        this.duration = duration;
    }

    public Songs(int songId, String songName, String aitistName, String gender, String album, String duration) {

        this.songId = songId;
        this.songName = songName;
        this.aitistName = aitistName;
        this.gender = gender;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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
        return "Songs{" +
                "songId=" + songId +
                ", songName='" + songName + '\'' +
                ", aitistName='" + aitistName + '\'' +
                ", gender='" + gender + '\'' +
                ", album='" + album + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }
}
