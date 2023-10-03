package org.niit.jukebox.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JukeboxConnection {
    public static  Connection getJukeboxConnection()throws SQLException {
        Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/jukebox","root","Password");
        return connection;
    }
}
