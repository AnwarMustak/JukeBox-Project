package org.niit.jukebox.dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;

public class PlaylistDAO {
    public boolean createPlaylist(String playlistName) throws SQLException {
        PreparedStatement preparedStatement = JukeboxConnection.getJukeboxConnection().prepareStatement("insert into playlist(playlist_name) values(?) ;");
        preparedStatement.setString(1, playlistName);
        int result = preparedStatement.executeUpdate();
        return result > 0 ? true : false;
    }

    public Hashtable<String, Integer> viewAllPlaylist() throws SQLException {
        Hashtable<String, Integer> playlistHashTable = new Hashtable<>();
        Statement statement = JukeboxConnection.getJukeboxConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("select * from playlist;");
        while (resultSet.next()) {
                playlistHashTable.put(resultSet.getString(2), resultSet.getInt(1));
            }

        return playlistHashTable;
    }
    public boolean deletePlayList(String playlistName)throws SQLException{
        PreparedStatement preparedStatement=JukeboxConnection.getJukeboxConnection().prepareStatement("delete from playlist where playlist_name=?;");
        preparedStatement.setString(1,playlistName);
        int result=preparedStatement.executeUpdate();
        return result>0?true:false;
    }
}
