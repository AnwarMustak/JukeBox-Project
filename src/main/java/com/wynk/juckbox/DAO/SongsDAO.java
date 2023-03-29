package com.wynk.juckbox.DAO;

import com.wynk.juckbox.model.Songs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SongsDAO {
       public ArrayList<Songs> selectAllJuckbox() throws SQLException {
           ArrayList<Songs> juckboxArrayList =new ArrayList<>();
        Statement selectStatement = JuckboxConnection.getJuckboxConnection().createStatement();
        ResultSet resultSet = selectStatement.executeQuery("select*from songs");
        while (resultSet.next()){
            juckboxArrayList.add(new Songs(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6)));
        }
        return juckboxArrayList;
    }
    public boolean insertJuckbox(Songs songs) throws SQLException {
        PreparedStatement insertSong = JuckboxConnection.getJuckboxConnection().prepareStatement("insert into songs(songId,songName,aitistName,gener,album,duration) values(?,?,?,?,?,?);");
        insertSong.setInt(1,songs.getSongId());
        insertSong.setString(2, songs.getSongName());
        insertSong.setString(3, songs.getAitistName());
        insertSong.setString(4, songs.getGener());
        insertSong.setString(5, songs.getAlbum());
        insertSong.setString(6, songs.getDuration());
        int check =insertSong.executeUpdate();
        return check>0?true:false;
    }
}
