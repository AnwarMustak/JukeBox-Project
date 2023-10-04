package org.niit.jukebox.service;

import org.niit.jukebox.dao.SongsDAO;
import org.niit.jukebox.exception.JukeboxException;
import org.niit.jukebox.model.Songs;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

public class SongsService {
    SongsDAO songsDAO = new SongsDAO();

    public void displayData() throws Exception {
        if (getSongsList() == null) {
            System.out.println("There No Songs Present In Your Data Base");
        } else {
            System.out.format("%5s\t%20s\t%20s\t%20s\t%20s\t%20s\t", "song_id", "song_name", "artist_name", "genre", "album_name", "duration");
            System.out.println(" ");
            System.out.println("******************************************************************************************************************************");
            Iterator<Songs> iterator = getSongsList().listIterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
        }
    }

    public Songs getSongBySongName(String songName, ArrayList<Songs> songsList) throws JukeboxException {
        Songs song = null;
        if (!songsList.isEmpty() && songName != null) {
            Iterator<Songs> list = songsList.listIterator();
            while (list.hasNext()) {
                Songs songObj = list.next();
                if (songObj.getSong_name().trim().equalsIgnoreCase(songName.trim())) {
                    song = songObj;
                }
            }
        } else {
            throw new JukeboxException("Please Provide Data to get Song");
        }
        return song;
    }

    public boolean addSongToDataBase(Songs song, ArrayList<Songs> songsList) throws Exception {
        boolean result = false;
        if (getSongBySongName(song.getSong_name(), songsList) == null) {
            songsDAO.insertSong(song);
            result = true;
        } else {
            throw new JukeboxException("Song Is Already Present In the Songs list");
        }
        return result;

    }

    public ArrayList<Songs> getSongsList() throws SQLException, JukeboxException {
        return songsDAO.getAllSongs();
    }

    public ArrayList<Songs> getSongsByAlbumName(String albumName, ArrayList<Songs> songsList) throws Exception {
        ArrayList<Songs> songsByAlbumName = null;
        if (!songsList.isEmpty() && albumName != null) {
            Iterator<Songs> songsIterator = songsList.listIterator();
            songsByAlbumName = new ArrayList<>();
            while (songsIterator.hasNext()) {
                Songs songs = songsIterator.next();
                if (songs.getAlbum_name().trim().equalsIgnoreCase(albumName.trim())) {
                    songsByAlbumName.add(songs);
                }
            }
        } else {
            throw new JukeboxException("Please Provide valid Data to get AlbumList");
        }
        return songsByAlbumName;
    }

    public ArrayList<Songs> getSongsByGenre(String genreName, ArrayList<Songs> songsArrayList) throws Exception {
        ArrayList<Songs> songsByGenre = null;
        if (!songsArrayList.isEmpty() && genreName != null) {
            Iterator<Songs> songsIterator = songsArrayList.listIterator();
            songsByGenre = new ArrayList<>();
            while (songsIterator.hasNext()) {
                Songs songs = songsIterator.next();
                if (songs.getGenre().trim().equalsIgnoreCase(genreName.trim())) {
                    songsByGenre.add(songs);
                }
            }
        } else {
            throw new JukeboxException("Please Provide valid Data to get Based On Genre");
        }
        return songsByGenre;
    }

    public ArrayList<Songs> getSongsByArtistName(String artistName, ArrayList<Songs> songsArrayList) throws Exception {
        ArrayList<Songs> songsByArtistName = null;
        if (!songsArrayList.isEmpty() && artistName != null) {
            Iterator<Songs> songsIterator = songsArrayList.listIterator();
            songsByArtistName = new ArrayList<>();
            while (songsIterator.hasNext()) {
                Songs songs = songsIterator.next();
                if (songs.getArtist_name().trim().equalsIgnoreCase(artistName.trim())) {
                    songsByArtistName.add(songs);
                }
            }
        } else {
            throw new JukeboxException("Please Provide valid Data to get Based On Genre");
        }
        return songsByArtistName;
    }

    public boolean deleteSongTable(String songName, ArrayList<Songs> songslist) throws SQLException, JukeboxException {
        boolean result = false;
        int songId=0;
        if (!songName.isEmpty() && !songslist.isEmpty()) {

            for (Songs s : songslist) {
//                System.out.println(s);
//                System.out.println(s.getSong_id());
                if (s.getSong_name().trim().equalsIgnoreCase(songName)) {
                    System.out.println("Hello");
                    songId = s.getSong_id();
//                    System.out.println(songId);
                    break;
                }
            }
//            System.out.println("I am Song ID "+songId);
            if (songId!=0) {
                System.out.println(songId);
                songsDAO.deleteOneSongs(songId);
                result = true;
            }else {
               throw new JukeboxException("Song is not Present");
            }

        } else {
            throw new JukeboxException("Provide Proper Details");
        }
        return result;
    }
}
