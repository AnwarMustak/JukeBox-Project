package com.wynk.juckbox.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JuckboxConnection {
    public static Connection getJuckboxConnection() throws SQLException {
        Connection connection= DriverManager.getConnection("jdbc:mysqul://localhost:3306/Jukebox","root","Password");
        return connection;
    }
}
