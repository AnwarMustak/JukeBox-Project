package com.wynk.juckbox.service;

import com.wynk.juckbox.DAO.PlaylistDAO;

import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Set;

public class PlaylistService {
    public boolean addPlaylist(String playlistname, Hashtable<String, Integer> playlist,int playlistId) throws Exception {
        boolean result = false;
        if (playlist.containsKey(playlistname) == false) {
            result = PlaylistDAO.createPlaylist(playlistname,playlistId);
        }
        return result;
    }

    public Hashtable<String, Integer> getAllPlaylist() throws Exception {
        Hashtable<String, Integer> result = PlaylistDAO.viewAllPlaylist();
        return result;

    }

}

