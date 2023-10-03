package org.niit.jukebox.model;

public class Songs {
    private int song_id;
    private String song_name;
    private String artist_name;
    private String genre;
    private String album_name;
    private String duration;

    public Songs() {
    }

    public Songs(String song_name, String artist_name, String genre, String album_name, String duration) {
        this.song_name = song_name;
        this.artist_name = artist_name;
        this.genre = genre;
        this.album_name = album_name;
        this.duration = duration;
    }

    public Songs(int song_id, String song_name, String artist_name, String genre, String album_name, String duration) {
        this.song_id = song_id;
        this.song_name = song_name;
        this.artist_name = artist_name;
        this.genre = genre;
        this.album_name = album_name;
        this.duration = duration;
    }

    public int getSong_id() {
        return song_id;
    }


    public String getSong_name() {
        return song_name;
    }

    public void setSong_name(String song_name) {
        this.song_name = song_name;
    }

    public String getArtist_name() {
        return artist_name;
    }

    public void setArtist_name(String artist_name) {
        this.artist_name = artist_name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAlbum_name() {
        return album_name;
    }

    public void setAlbum_name(String album_name) {
        this.album_name = album_name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
//        return "Songs{" +
//                "song_id=" + song_id +
//                "song_name='" + song_name + '\'' +
//                ", artist_name='" + artist_name + '\'' +
//                ", genre='" + genre + '\'' +
//                ", album_name='" + album_name + '\'' +
//                ", duration='" + duration + '\'' +
//                '}';
        return
                String.format("%5s\t%20s\t%20s\t%20s\t%20s\t%20s\t",song_id,song_name,artist_name,genre,album_name,duration)+"\n";
    }

}