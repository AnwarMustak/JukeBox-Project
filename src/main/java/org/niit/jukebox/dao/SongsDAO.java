package org.niit.jukebox.dao;
import org.niit.jukebox.exception.JukeboxException;
import org.niit.jukebox.model.Songs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;

public class SongsDAO {
    //song_name,artist_name,genre,album_name,duration
    public ArrayList<Songs> getAllSongs()throws JukeboxException,SQLException {
        ArrayList<Songs>songsArrayList=null;
        Statement statement=JukeboxConnection.getJukeboxConnection().createStatement();
        ResultSet resultSet=statement.executeQuery("select * from songs;");
        if (resultSet.isBeforeFirst()) {
            songsArrayList = new ArrayList<>();
            while (resultSet.next()) {
                songsArrayList.add(new Songs(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6)));
            }
        }
//        Comparator<Songs> songsComparator=(o1, o2) ->o1.getSong_name().trim().compareTo(o2.getSong_name());
//        songsArrayList.sort(songsComparator);
//
        return songsArrayList;
    }
    //insert  songs(song_name,artist_name,genre,album_name,duration) values('Jawan Title Track','Anirudh Ravichander','Rock','Jawan','4:20');
    public boolean insertSong(Songs songs) throws SQLException{
        PreparedStatement preparedStatement=JukeboxConnection.getJukeboxConnection().prepareStatement("insert  songs(song_name,artist_name,genre,album_name,duration) values(?,?,?,?,?);");
        preparedStatement.setString(1,songs.getSong_name());
        preparedStatement.setString(2, songs.getArtist_name());
        preparedStatement.setString(3,songs.getGenre());
        preparedStatement.setString(4,songs.getAlbum_name());
        preparedStatement.setString(5,songs.getDuration());
        int result=preparedStatement.executeUpdate();
        return result>0?true:false;
    }

    public boolean deleteOneSongs(int songID)throws SQLException{
        PreparedStatement preparedStatement=JukeboxConnection.getJukeboxConnection().prepareStatement("delete from songs where song_id=?");
        preparedStatement.setInt(1,songID);
        return preparedStatement.execute();
    }


}
