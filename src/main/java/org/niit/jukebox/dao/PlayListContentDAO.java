package org.niit.jukebox.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;

public class PlayListContentDAO {
    public boolean addSongsToPlaylist(int songId,int playlistId)throws SQLException {
        PreparedStatement preparedStatement=JukeboxConnection.getJukeboxConnection().prepareStatement("insert into playlistContent values(?,?)");
        preparedStatement.setInt(1,playlistId);
        preparedStatement.setInt(2,songId);
        int result=preparedStatement.executeUpdate();
        return result>0?true:false;
    }
    public ArrayList<Integer> viewSongsInPlaylist(int playlistId)throws SQLException{
        ArrayList<Integer>listOfSongsInPlayList=null;
        PreparedStatement statement=JukeboxConnection.getJukeboxConnection().prepareStatement("select * from playlistContent where playli_id=?");
        statement.setInt(1,playlistId);
        ResultSet resultSet=statement.executeQuery();
        if (resultSet.isBeforeFirst()){
            listOfSongsInPlayList=new ArrayList<>();
            while (resultSet.next()){
                listOfSongsInPlayList.add(resultSet.getInt(2));
            }
        }
        return listOfSongsInPlayList;
    }
    public boolean deleteAllPlaylistContent()throws SQLException{
        Statement statement=JukeboxConnection.getJukeboxConnection().createStatement();
        int check=statement.executeUpdate("delete from playlistContent");
        return check>0?true:false;
    }
    public Hashtable<String, Integer> viewAllPlaylistContent() throws SQLException {
        Hashtable<String, Integer> playlistContentHashTable = null;
        Statement statement = JukeboxConnection.getJukeboxConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("select * from playlistContent ;");
        if (resultSet.isBeforeFirst()) {
            playlistContentHashTable = new Hashtable<>();
            while (resultSet.next()) {
                playlistContentHashTable.put(resultSet.getString(1), resultSet.getInt(2));
            }
        }
        return playlistContentHashTable;
    }
}
