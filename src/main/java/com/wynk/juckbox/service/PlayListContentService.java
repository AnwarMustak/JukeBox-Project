package com.wynk.juckbox.service;

import com.wynk.juckbox.DAO.JuckboxConnection;
import com.wynk.juckbox.DAO.PlayListContentDAO;
import com.wynk.juckbox.model.Songs;

import java.util.ArrayList;
import java.util.Hashtable;

public class PlayListContentService {
    PlayListContentDAO playListContentDAO = new PlayListContentDAO();

public boolean addSongtOPlaylist(ArrayList<Songs> songsList, Hashtable<String,Integer>playlist,String songName,String playlistName)throws Exception
{
boolean result = false;
if(songName!=null||playlistName!=null||playlist.isEmpty()||songsList.isEmpty()) {
    int playlistId = playlist.get(playlistName);
    int songId = 0;
    for (Songs songs : songsList) {
        songId = songs.getSongId();
        break;
    }
    if (songId == 0) {
        throw new Exception("Songid not present");
    } else if (playlistId == 0) {
        throw new Exception("playlist not present");
    } else {
        playListContentDAO.addSongs(songId,playlistId);
        result = true;

    }
}
return result;
}
public boolean addSongByAlbumName(ArrayList<Songs> songsList, Hashtable<String,Integer>playlist,String albumName,String playlistName) throws Exception {
    boolean result = false;
    if (playlistName != null || playlist.isEmpty() || songsList.isEmpty()) {
        throw new Exception("Please provide all values");
    } else {
        int playlistId = playlist.get(playlistName);
        ArrayList<Integer> songIdList = new ArrayList<>();
        for (Songs songs : songsList) {
            if (songs.getAlbum().equals(albumName)) {
                songIdList.add(songs.getSongId());
                result = true;
            }
        }
        if (playlistId == 0) {
            throw new Exception("playlist not present");
        } else if (songIdList.isEmpty()) {
            throw new Exception("Songs not present");
        } else {
            for (int id : songIdList)
                result = playListContentDAO.addSongs(id, playlistId);
        }
    }
    return result;
}

    public  ArrayList<Songs> playlistContent(String playlistname, Hashtable<String, Integer> playlist, ArrayList<Songs> songlist) throws Exception{
    ArrayList<Integer> songIdList;
    ArrayList<Songs> songList = null;
    if(playlistname!=null||playlist.isEmpty()||songlist.isEmpty()){
        throw new Exception("Required details");
    }else {
        int playListId=playlist.get(playlistname);
        if (playListId==0)
        {
            throw new Exception("Empty");

        }
        else {
            songIdList=playListContentDAO.viewSong(playListId);
        }
        if(songIdList.isEmpty()==false){
            songList=new ArrayList<>();
            for (int id : songIdList){
                for (Songs songs : songList){
                    if (songs.getSongId()==id)
                        songList.add(songs);
                }
            }
        } else {
            throw new Exception("Playlist is Empty");
        }
    }
    return songList;
    }
}