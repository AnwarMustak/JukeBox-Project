package com.wynk.juckbox.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PlayListContentDAO
{
    public boolean addSongs(int songId,int playlistId)throws SQLException
    {
        PreparedStatement addSong = JuckboxConnection.getJuckboxConnection().prepareStatement("insert into PlayListSongs values(?,?);");
        addSong.setInt(1,playlistId);
        addSong.setInt(2,songId);
        int check = addSong.executeUpdate();
        return check>0?true:false;

    }
    public ArrayList<Integer> viewSong(int playlistId)throws SQLException
    {
        ArrayList<Integer> song = new ArrayList<>();
        PreparedStatement selectStatement = JuckboxConnection.getJuckboxConnection().prepareStatement("select*from PlayListSongs where playlistId=?");

        selectStatement.setInt(1,playlistId);
        ResultSet resultSet= selectStatement.executeQuery();
        while(resultSet.next()){
            song.add(resultSet.getInt(2));
        }
        return song;
    }
}