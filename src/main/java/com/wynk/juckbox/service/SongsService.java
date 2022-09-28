package com.wynk.juckbox.service;
import com.wynk.juckbox.DAO.SongsDAO;
import com.wynk.juckbox.model.Songs;

import java.sql.SQLException;
import java.util.ArrayList;

public class SongsService {
    SongsDAO songsDAO = new SongsDAO();
    private boolean songAvilable(String song, ArrayList<Songs>songsList)throws SQLException{
        boolean result =false;
        for(Songs songs:songsList){
            if (songs.getSongName().equalsIgnoreCase(song)){
                result=true;
                break;
            }

        }
        return result;
    }
    public boolean addSongs(Songs song,ArrayList<Songs>songsList)throws SQLException{
        boolean result = false;
        if (songAvilable(song.getSongName(),songsList)==false){
            result=songsDAO.insertJuckbox(song);
        }
        return result;
    }
    public ArrayList<Songs>getSongs() throws SQLException{
        ArrayList<Songs> result =songsDAO.selectAllJuckbox();
        return result;
    }
    public void displaySongs() throws SQLException{
        ArrayList<Songs> songsList = songsDAO.selectAllJuckbox();
        System.out.format("%10s %30s %20s %15s %25s", "Song Id","Song Name","Artist Name", "Genre", "Album Name", "Duration \n");
        System.out.println();
        for(Songs songs : songsList){
            System.out.println(songs);
        }
    }
    public ArrayList<Songs> selectSongsByAlbum(String albumName,ArrayList<Songs> songsList){
        ArrayList<Songs> songsInAlbum =null;
        if(songsList.isEmpty()==false) {
            songsInAlbum = new ArrayList<>();
            for (Songs songs : songsList) {
                if (songs.getAlbum().equalsIgnoreCase(albumName)) {
                  songsInAlbum.add(songs);
                }
            }
        }
            return songsInAlbum;

        }
    public ArrayList<Songs> selectSongsByName(String songName,ArrayList<Songs> songsArrayList) {
        ArrayList<Songs> songsByName = null;
        if (songsArrayList.isEmpty() == false) {
            songsByName = new ArrayList<>();
            for (Songs songs : songsArrayList) {
                if (songs.getAlbum().contains(songName)) {
                    songsByName.add(songs);
                }
            }
        }
        return songsByName;
    }
    public ArrayList<Songs> selectSongsByArtist(String artistName,ArrayList<Songs> songsArrayList) {
        ArrayList<Songs> songsByArtist = null;
        if (songsArrayList.isEmpty() == false) {
            songsByArtist = new ArrayList<>();
            for (Songs songs : songsArrayList) {
                if (songs.getAlbum().contains(artistName)) {
                    songsByArtist.add(songs);
                }
            }
        }
        return songsByArtist;
    }
    public ArrayList<Songs> selectSongsByGenre(String Genre,ArrayList<Songs> songsArrayList) {
        ArrayList<Songs> songsByGenre = null;
        if (songsArrayList.isEmpty() == false) {
            songsByGenre = new ArrayList<>();
            for (Songs songs : songsArrayList) {
                if (songs.getAlbum().contains(Genre)) {
                    songsByGenre.add(songs);
                }
            }
        }
        return songsByGenre;
    }
}
