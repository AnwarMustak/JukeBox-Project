package org.niit.jukebox.service;

import org.niit.jukebox.dao.PlaylistDAO;
import org.niit.jukebox.exception.JukeboxException;
import org.niit.jukebox.model.Songs;

import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Set;

public class PlayListService {
    PlaylistDAO playlistDAO;
    public PlayListService(){
        playlistDAO=new PlaylistDAO();
    }

    public Hashtable<String,Integer> getAllPlayList()throws Exception{
        return playlistDAO.viewAllPlaylist();
    }

    public boolean addPlayList(String playListName,Hashtable<String,Integer> playlist)throws Exception{
        if (playListName==null&&playlist.isEmpty()){
            throw new JukeboxException("Please Provide Details Properly");
        }else {
            boolean playlistIsPresent = playlist.containsKey(playListName);
            boolean result = false;
            if (!playlistIsPresent) {
               result= playlistDAO.createPlaylist(playListName);

            }return result;
        }
    }

public Set<String> displayPlayList() throws Exception {
    Set<String>playlist=getAllPlayList().keySet();
    for (String list:playlist){
        playlist.add(list);
    }
    return playlist;
}
    public boolean deletePlayList(String playlistName,Hashtable<String,Integer> playlist) throws SQLException, JukeboxException {
        boolean result = false;
        int songId=0;
//        Set<String>play=playlist.keySet();
        if (!playlist.isEmpty() && !playlistName.isEmpty()) {
            if (playlist.containsKey(playlistName)) {
                playlistDAO.deletePlayList(playlistName);
                result = true;
            }
            else {
                throw new JukeboxException("playlist Is not Present");
            }

                }else {
            throw new JukeboxException("Please Provide Details");
            }
        return result;
    }

///Users/akhil/Documents/NIITCourse/JavaPractice/Course 6/Sprint-3/Jukebox/src/main/resources/songs/100.wav
}
