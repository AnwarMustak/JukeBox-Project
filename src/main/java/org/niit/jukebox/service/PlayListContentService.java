package org.niit.jukebox.service;

import org.niit.jukebox.dao.PlayListContentDAO;
import org.niit.jukebox.exception.JukeboxException;
import org.niit.jukebox.model.Songs;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;

import java.util.Set;

public class PlayListContentService {
    PlayListContentDAO playListContentDAO;

    public PlayListContentService() {
        playListContentDAO = new PlayListContentDAO();
    }

    public boolean addSongToPlayList(ArrayList<Songs> songsList, Hashtable<String, Integer> playlist, String playlistName, String songName) throws Exception
    {
        boolean result = false;
        if(songName!=null&& playlistName!=null&&!songsList.isEmpty()&&!playlist.isEmpty()){
            int playlistId;
            int songId=0;
            for( Songs song:songsList){
                if(song.getSong_name().equalsIgnoreCase(songName)){
                    songId=song.getSong_id();
                    result=true;
                    break;
                }}
            if(songId==0){
                throw new Exception("Song is not present in JukeBox");
            } else if (!playlist.containsKey(playlistName)) {
                throw new Exception("Playlist Name is not present");
            }else{
                    playlistId = playlist.get(playlistName);
//                    if (!playListContentDAO.viewSongsInPlaylist(playlistId).contains(songId)) {
                        result = playListContentDAO.addSongsToPlaylist(songId, playlistId);
//                    }
//                    else throw new JukeboxException("Song Is Already Present");
            }
        }else {
            throw new Exception("please provide all details");
        }
        return result;
    }
    public boolean addAlbumToPlaylist(ArrayList<Songs> songsArrayList, Hashtable<String, Integer> playList, String albumName, String playlistName) throws Exception {
        boolean result = false;
        ArrayList<Integer> songIdList =null;
        if (albumName != null && playlistName != null &&!songsArrayList.isEmpty()&&!playList.isEmpty()) {
            int playlistId;
            int songId=0;
            songIdList = new ArrayList<>();
            for (Songs song : songsArrayList) {
                if (song.getAlbum_name().equalsIgnoreCase(albumName)) {
                    songId = song.getSong_id();
                    songIdList.add(songId);
                }
            }
            if (songIdList.isEmpty()) {
                throw new JukeboxException("Song Id is not present");
            } else if (!playList.containsKey(playlistName)) {
                throw new JukeboxException("PlayList Id is not Present");
            } else {
                playlistId = playList.get(playlistName);
                for (int id:songIdList) {
                    if (!playListContentDAO.viewSongsInPlaylist(playlistId).contains(id)) {
                        playListContentDAO.addSongsToPlaylist(id, playlistId);
                        result= true;
                    }
                }
            }
        }else {
            throw new Exception("please provide All details");
        }

        return result;
    }
    public ArrayList<Songs> playlistContent(String playlistName, Hashtable<String, Integer> playlist, ArrayList<Songs> songsArrayList) throws Exception {

        ArrayList<Integer> songsIdList = null;
        ArrayList<Songs> songsInPlaylistContent = null;
        int playlistId = 0;
        if (playlistName == null || playlist.isEmpty() || songsArrayList.isEmpty()) {
            throw new JukeboxException("Please Provide Valid Details");
        } else {
            if (!playlist.containsKey(playlistName.trim())) {
                throw new JukeboxException("Playlist Is not Present");
            } else {
                playlistId = playlist.get(playlistName.trim());
                songsIdList = playListContentDAO.viewSongsInPlaylist(playlistId);
                if (songsIdList != null) {
                    songsInPlaylistContent = new ArrayList<>();
                    for (int id : songsIdList) {
                        for (Songs song : songsArrayList) {
                            if (song.getSong_id() == id) {
                                songsInPlaylistContent.add(song);
                            }
                        }
                    }
                }
            }
        }
        return songsInPlaylistContent;
    }
    public boolean deletePlayListContent() throws SQLException {
        boolean result=playListContentDAO.deleteAllPlaylistContent();
       return result;
    }
    public Hashtable<String, Integer> getAllPlayListContent() throws Exception{
        return playListContentDAO.viewAllPlaylistContent();
    }

    public void displayListOfContent()throws Exception{
        Set<String>listOfContent=getAllPlayListContent().keySet();
        for (String data:listOfContent){
            System.out.println(data);
        }
    }
}
