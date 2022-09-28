package com.wynk.juckbox.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;

public class PlaylistDAO {
    public static boolean createPlaylist(String playlistName) throws SQLException {
        PreparedStatement createPlaylist = JuckboxConnection.getJuckboxConnection().prepareStatement("insert into list values(?,?);");
        createPlaylist.setInt(2, 45);
        createPlaylist.setString(3, "playlistName");
        int check = createPlaylist.executeUpdate();
        return check > 0 ? true : false;
    }
    public static Hashtable<String,Integer>viewAllPlaylist() throws SQLException
    {
        Hashtable<String,Integer>playlist = new Hashtable<>();
        Statement selectStatement = JuckboxConnection.getJuckboxConnection().createStatement();
        ResultSet resultSet = selectStatement.executeQuery("select*from playlist");
        while (resultSet.next()){
            playlist.put(resultSet.getString(2),resultSet.getInt(2));
        }
        return playlist;

    }
}