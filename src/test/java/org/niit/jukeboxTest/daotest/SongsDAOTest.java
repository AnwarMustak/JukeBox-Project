package org.niit.jukeboxTest.daotest;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.niit.jukebox.dao.SongsDAO;
import org.niit.jukebox.exception.JukeboxException;
import org.niit.jukebox.model.Songs;
import org.niit.jukebox.service.SongsService;

import java.sql.SQLException;
import java.util.ArrayList;

public class SongsDAOTest {
    SongsDAO songsDAO;
    SongsService songsService;

    @Before
    public void setUp() {
        songsDAO = new SongsDAO();
        songsService=new SongsService();
    }
    @After
    public void tearDown(){
        songsDAO=null;
        songsService=null;
    }
    @Test
    public void getAllSongsTest() throws SQLException, JukeboxException {
        ArrayList<Songs>songsArrayList=songsDAO.getAllSongs();
        Assert.assertNotNull(songsArrayList);
        Assert.assertFalse(songsArrayList.isEmpty());
    }
    @Test
    public void insertSongTest()throws SQLException{
        Songs songToInsert = new Songs(0, "Test Song Name1", "Test Artist Name", "Test Genre", "Test Album Name", "00:00");

        // Act: Insert the song into the database
        boolean insertionResult = songsDAO.insertSong(songToInsert);
        // Assert: Check if the insertion was successful
        Assert.assertTrue("Insertion should be successful",insertionResult);
        //change the values to check

        // Optionally, you can further assert that the inserted song exists in the database.
    }


}
