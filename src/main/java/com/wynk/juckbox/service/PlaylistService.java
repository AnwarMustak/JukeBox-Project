package com.wynk.juckbox.service;

import com.wynk.juckbox.DAO.PlaylistDAO;

import java.sql.SQLException;
import java.util.Hashtable;

public class PlaylistService {
public boolean addPlaylist(String playlistname, Hashtable<String,Integer>playlist)throws SQLException
{
   boolean result=false;
   if((playlist.containsKey(playlistname)==false)){
       result = PlaylistDAO.createPlaylist(playlistname);
   }
  return result;
}
   public Hashtable<String,Integer> getAllPlaylist() throws SQLException {
    Hashtable<String,Integer>
            result=PlaylistDAO.viewAllPlaylist();
    return result;
    }
}
