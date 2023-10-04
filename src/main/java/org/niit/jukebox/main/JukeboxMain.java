package org.niit.jukebox.main;
import org.niit.jukebox.exception.JukeboxException;
import org.niit.jukebox.model.Songs;
import org.niit.jukebox.service.PlayListContentService;
import org.niit.jukebox.service.PlayListService;
import org.niit.jukebox.service.PlayerService;
import org.niit.jukebox.service.SongsService;


import java.io.IOException;

import java.util.*;



public class JukeboxMain {
    public void welcomeMenu() {
        System.out.println(" ");
        System.out.println("🎵🎵*************************************************🎵Welcome To Jukebox🎵********************************************🎵🎵");
        System.out.println(" ");
        System.out.println("***************************🎵🎵Sometimes music is the Only medicine the heart and Soul need🎵🎵****************************"+"\n");
    }

    public void columnValues() {
        System.out.format("%5s\t%20s\t%20s\t%20s\t%20s\t%20s\t", "song_id", "song_name", "artist_name", "genre", "album_name", "duration");
        System.out.println(" ");
        System.out.println(" ");
    }

    public void mainMenu() {
        System.out.println("------------------------   Hi I am Main Menu   ---------------------------- ");
        System.out.println(" 1)        Songs\n 2)        Playlist\n 3)        Player\n 4)        Exit\n 5)        Delete");
        System.out.println("Please Select Appropriate options : ");
    }

    public void songsMenu() {
        System.out.println("------------------------   Hi I am Songs Menu   ---------------------------- ");
        System.out.println(" 1)        Add a Song\n 2)        Search A Song By Song Name\n 3)        Search A Song By Artist Name\n 4)        Search A Song By Genre\n 5)        Search A Song By Album Name\n 6)        Return To Main\n 7)        Exit");
        System.out.println("Please Select Appropriate options : ");
    }

    public void playlistMenu() {
        System.out.println("------------------------   Hi I am Playlist Menu   ---------------------------- ");
        System.out.println(" 1)        Show All Playlist\n 2)        Create Playlist\n 3)        Add Song To Playlist\n 4)        Add Album to Playlist\n 5)        display Playlist Contains\n 6)        Return To Main\n 7)        Exit");
        System.out.println("Please Select Appropriate options : ");
    }

    public void deleteMenu() {
        System.out.println("------------------------   Hi I am Delete Menu   ---------------------------- ");
        System.out.println(" 1)        Delete Song\n 2)        Return To Main\n 3)        Exit");
        System.out.println("Please Select Appropriate options : ");
    }

    public void playerMenu() {
        System.out.println("------------------------   Hi I am Player Menu   ---------------------------- ");
//        System.out.println(" 1)        Play Song\n 2)        Play Playlist\n 3)        Return To Main\n 4)        Exit");
        System.out.println(" 1)        Search Song And Play\n 2)        Play All Song\n 3)        Play Playlist Song's\n 4)        Return To Main\n 5)        Exit");
        System.out.println("Please Select Appropriate options : ");
    }

    public void playerOptionForOneSong() {
        System.out.println("------------------------   Hi I have Some Player Options   ---------------------------- ");
        System.out.println(" 1)        Play\n 2)        Pause\n 3)        Resume\n 4)        Restart\n 5)        Forward For +10\n 6)        Backward For -10\n 7)        Stop");

    }

    public void playerOption() {
        System.out.println("------------------------   Hi I have Some Player Options   ---------------------------- ");
        System.out.println(" 1)        Play\n 2)        Pause\n 3)        Resume\n 4)        Restart\n 5)        Forward For +10\n 6)        Backward For -10\n 7)        Stop\n 8)        Next");

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PlayListContentService playListContentService = new PlayListContentService();
        PlayListService playListService = new PlayListService();
        SongsService songsService = new SongsService();
        JukeboxMain jukeboxMain = new JukeboxMain();
        char case1End;
        char case2End;
        char case3End;
        char case4End;
        char case5End;

        ArrayList<Songs> songsList;
        Hashtable<String, Integer> playlistdata;

        jukeboxMain.welcomeMenu();
        jukeboxMain.columnValues();
        try {
            playlistdata = playListService.getAllPlayList();
            songsList = songsService.getSongsList();
            for (Songs songs : songsList) {
                System.out.println(songs);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        do {
            int case1Choice= 0;
            jukeboxMain.mainMenu();
            try {
                 case1Choice = scanner.nextInt();
            }catch (InputMismatchException exception){
                System.out.println("Please Select The Option in Number Format");
            }
            scanner.nextLine();
            switch (case1Choice) {

                case 1:
                    do {
                        jukeboxMain.songsMenu();
                        int option=0;
                        try {
                             option = scanner.nextInt();
                        }catch (InputMismatchException exception){
                            System.out.println("Please Select The Option in Number Format");
                        }
                        scanner.nextLine();
                        switch (option) {
                            case 1:

                                try {
                                    playlistdata = playListService.getAllPlayList();
                                    songsList = songsService.getSongsList();
                                    System.out.println("Please enter the Song Name : ");
                                    String songName = scanner.nextLine();
                                    System.out.println("Please enter Artist Name : ");
                                    String artistName = scanner.nextLine();
                                    System.out.println("Please enter the genre Name : ");
                                    String genre = scanner.nextLine();
                                    System.out.println("Please enter the Album Name : ");
                                    String albumName = scanner.nextLine();
                                    System.out.println("Please enter the Duration : ");
                                    String duration = scanner.nextLine();
                                    Songs song = new Songs(songName, artistName, genre, albumName, duration);
                                    boolean songAdd = songsService.addSongToDataBase(song, songsList);
                                    if (songAdd) {
                                        System.out.println("Song is Added Successfully");
                                        System.out.println("---------------------------------------------------------------------------------------");
                                        System.out.println("Updated Song List");
                                        songsList = songsService.getSongsList();
                                        for (Songs songs : songsList) {
                                            System.out.println(songs);
                                        }
                                    } else {
                                        System.out.println("Song Is Not Added");
                                    }

                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                                break;
                            case 2:
                                try {
                                    playlistdata = playListService.getAllPlayList();
                                    songsList = songsService.getSongsList();
                                    System.out.println("Please Enter the Song Name : ");
                                    String songNameToSearch = scanner.nextLine();
                                    Songs songsBySongName = songsService.getSongBySongName(songNameToSearch, songsList);
                                    if (songsBySongName != null) {
                                        jukeboxMain.columnValues();
                                        System.out.println(songsBySongName);
                                    } else {
                                        System.out.println("Sorry the Song is not Present");
                                    }

                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                                break;
                            case 3:
                                try {
                                    playlistdata = playListService.getAllPlayList();
                                    songsList = songsService.getSongsList();
                                    System.out.println("Please Enter the Artist Name : ");
                                    String artistNameToSearch = scanner.nextLine();
                                    ArrayList<Songs> songsByArtistName = songsService.getSongsByArtistName(artistNameToSearch, songsList);
                                    if (!songsByArtistName.isEmpty()) {
                                        Iterator<Songs> songsIterator = songsByArtistName.listIterator();
                                        jukeboxMain.columnValues();
                                        while (songsIterator.hasNext()) {
                                            System.out.println(songsIterator.next());
                                        }
                                    } else {
                                        System.out.println("Sorry Artist is Not Present in you Songs list");
                                    }
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                                break;
                            case 4:
                                try {
                                    playlistdata = playListService.getAllPlayList();
                                    songsList = songsService.getSongsList();
                                    System.out.println("Please Enter The Genre Name : ");
                                    String genreToSearch = scanner.nextLine();
                                    ArrayList<Songs> songsByGenre = songsService.getSongsByGenre(genreToSearch, songsList);

                                    if (!songsByGenre.isEmpty()) {
                                        Iterator<Songs> songsIterator = songsByGenre.listIterator();
                                        jukeboxMain.columnValues();
                                        while (songsIterator.hasNext()) {
                                            System.out.println(songsIterator.next());
                                        }
                                    } else {
                                        System.out.println("Sorry Genre is Not Present in your Song list");
                                    }

                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                                break;
                            case 5:
                                try {
                                    playlistdata = playListService.getAllPlayList();
                                    songsList = songsService.getSongsList();
                                    System.out.println("Please Enter the Album Name : ");
                                    String albumNameToSearch = scanner.nextLine();
                                    ArrayList<Songs> songsByAlbum = songsService.getSongsByAlbumName(albumNameToSearch, songsList);

                                    if (!songsByAlbum.isEmpty()) {
                                        Iterator<Songs> songsIterator = songsByAlbum.listIterator();
                                        jukeboxMain.columnValues();
                                        while (songsIterator.hasNext()) {
                                            System.out.println(songsIterator.next());
                                        }
                                    } else {
                                        System.out.println("Sorry Album is Not Present int List");
                                    }

                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                                break;
                            case 6:
                                System.out.println("Returning To Main");
                                break;
                            case 7:
                                System.exit(0);
                                break;

                            default:
                                System.out.println("Please Enter The Valid Option");
                        }
                        System.out.println("Do want to Continue with the Song Menu");
                        case1End = scanner.next().charAt(0);
                    } while (case1End == 'y');
                    break;

                case 2:

                    do {
                        jukeboxMain.playlistMenu();
                        int choice=0;
                        try {
                             choice = scanner.nextInt();
                        }catch (InputMismatchException exception){
                            System.out.println("Please Select The Option in Number Format");
                        }
                        scanner.nextLine();
                        switch (choice) {
                            case 1:
                                try {
                                    playlistdata = playListService.getAllPlayList();
                                    songsList = songsService.getSongsList();
                                    Set<String> allPlaylists = playlistdata.keySet();
                                    if (!allPlaylists.isEmpty()) {
                                        int i = 1;
                                        System.out.println("----------------   The Playlist's You Have in Playlist Menu     ------------------ ");
                                        for (String list : allPlaylists) {
                                            System.out.println(" " + i + ")        " + list);
                                            i++;
                                        }
                                    } else {
                                        System.out.println("Playlist is empty");
                                    }
                                    break;
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                                break;
                            case 2:
                                try {
                                    playlistdata = playListService.getAllPlayList();
                                    System.out.println("Please enter the Playlist Name : ");
                                    String newPlaylistName = scanner.nextLine();
                                    if (newPlaylistName != null) {
                                        boolean result = playListService.addPlayList(newPlaylistName, playlistdata);
                                        if (result) {
                                            System.out.println("Playlist is Added Successfully");
                                        } else {
                                            System.out.println("Sorry Playlist is Already Present");
                                        }
                                    } else {
                                        System.out.println("Please Enter The Proper Name To Your Play list");
                                    }
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                                break;
                            case 3:
                                try {
                                    playlistdata = playListService.getAllPlayList();
                                    songsList = songsService.getSongsList();
                                    System.out.println("Please Enter Song Name Which You Want Add in Your PlayList");
                                    String songName = scanner.nextLine();
                                    System.out.println("Please Enter the Playlist Name in which You won't to Add The Song");
                                    String playlistName = scanner.nextLine();
                                    if (songName != null && playlistName != null) {
                                        boolean result = playListContentService.addSongToPlayList(songsList, playlistdata, playlistName, songName);
                                        if (result) {
                                            System.out.println("Song is Added To The Playlist Successfully");
                                        } else {
                                            System.out.println("Song is Not Added");
                                        }
                                    } else {
                                        System.out.println("Please Enter the Valid Details");
                                    }
                                } catch (InputMismatchException e){
                                    System.out.println("Please Provide Proper Details");
                                }
                                catch (Exception e) {
                                    System.out.println("Song Is Already Present In Your Playlist");
                                }
                                break;
                            case 4:
                                try {
                                    playlistdata = playListService.getAllPlayList();
                                    songsList = songsService.getSongsList();
                                    System.out.println("Please Enter Album Name Which You Want Add in Your PlayList");
                                    String albumName = scanner.nextLine();
                                    System.out.println("Please Enter the Playlist Name in which You won't to Add The Song");
                                    String playlistNameToAddAlbum = scanner.nextLine();
                                    if (albumName != null && playlistNameToAddAlbum != null) {
                                        boolean result = playListContentService.addAlbumToPlaylist(songsList, playlistdata, albumName, playlistNameToAddAlbum);
                                        if (result) {
                                            System.out.println("Album " + albumName + " is Added Successfully To Playlist");
                                        } else {
                                            System.out.println("Album is Already Present In Your Playlist");
                                        }
                                    }
                                }catch (InputMismatchException exception){
                                    System.out.println("Please Provide Valid Details");
                                }
                                catch (Exception e) {
                                    System.out.println("Album is Already Present In Your Playlist");
                                }
                                break;
                            case 5:
                                try {

                                    playlistdata = playListService.getAllPlayList();
                                    songsList = songsService.getSongsList();
                                    System.out.println("Please Enter the Playlist Name :");
                                    String playlistNameYouWont = scanner.nextLine();
                                    if (playlistNameYouWont != null) {
                                        ArrayList<Songs> playlistContain = playListContentService.playlistContent(playlistNameYouWont, playlistdata, songsList);
                                        if (playlistContain != null) {
                                            System.out.println("********************************************              " + playlistNameYouWont + "            ***************************************************");
                                            jukeboxMain.columnValues();
                                            for (Songs songs : playlistContain) {
                                                System.out.println(songs);
                                            }
                                        } else {
                                            System.out.println("There is No Songs in Your Playlist");
                                        }
                                    } else {
                                        System.out.println("Invalid Details");
                                    }
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                                break;
                            case 6:
                                System.out.println("Returning To Main");
                                break;
                            case 7:
                                System.exit(0);
                                break;
                            default:
                                System.out.println("Please Enter valid Option");
                        }
                        System.out.println("Do want to Continue with the Playlist Menu");
                        case2End = scanner.next().charAt(0);
                    } while (case2End == 'y');
                    break;
                case 3:

                    do {
                        PlayerService audioPlayer = new PlayerService();
                        jukeboxMain.playerMenu();
                        int option=0;
                        try {
                             option = scanner.nextInt();
                        }catch (InputMismatchException exception){
                            System.out.println("Please Select The Option in Number Format");
                        }
                        scanner.nextLine();
                        switch (option) {
                            case 1:
                                do {
                                    try {
                                        boolean sContinue = false;
                                        int id = 0;
                                        songsList = songsService.getSongsList();
                                        System.out.println("The Songs You Have In Your Songs list");
                                        if (!songsList.isEmpty()) {
                                            for (Songs songs : songsList) {
                                                System.out.println(songs);
                                            }
                                            Iterator<Songs> songsIterator = songsList.listIterator();
                                            System.out.println("Please Enter The Song Name");
                                            String nameOfTheSong = scanner.nextLine();
                                            for (Songs songs : songsList) {
                                                if (songs.getSong_name().trim().equalsIgnoreCase(nameOfTheSong)) {
                                                    id = songs.getSong_id();
                                                }
                                            }
                                            if (id != 0) {
                                                while (!sContinue) {
                                                    jukeboxMain.playerOptionForOneSong();
                                                    int action = scanner.nextInt();
                                                    scanner.nextLine();
                                                    switch (action) {
                                                        case 1:
                                                            audioPlayer.playSong(id);
                                                            audioPlayer.play();
                                                            break;
                                                        case 2:
                                                            audioPlayer.pause();
                                                            break;
                                                        case 3:
                                                            audioPlayer.resumeAudio(id);
                                                            break;
                                                        case 4:
                                                            audioPlayer.restart(id);
                                                            break;
                                                        case 5:
                                                            audioPlayer.forWord10sec();
                                                            break;
                                                        case 6:
                                                            audioPlayer.backWord10sec();
                                                            break;
                                                        case 7:
                                                            audioPlayer.stop();
                                                            sContinue = true;
                                                            break;
                                                        default:
                                                            System.out.println("Sorry Please Select Proper Option Given");

                                                    }
                                                }
                                            }
                                            System.out.println("Song is Not Present");
                                        }
                                    }
                                    catch (Exception e) {
                                        System.out.println(e.getMessage());
                                    }

                                    System.out.println("Do You Won't Continue with Play All Songs");
                                    case5End = scanner.next().charAt(0);
                                    scanner.nextLine();
                                } while (case5End == 'y');
                                break;

                            case 2:
                                do {
                                    try {
                                        boolean sContinue = false;
                                        int id;
                                        songsList = songsService.getSongsList();
                                        if (!songsList.isEmpty()) {
                                            for (Songs songs : songsList) {
                                                System.out.println(songs);
                                            }
                                            Iterator<Songs> songsIterator = songsList.listIterator();
                                            id = songsIterator.next().getSong_id();
                                            if (id != 0) {
                                                while (!sContinue) {
                                                    jukeboxMain.playerOption();
                                                    int action = scanner.nextInt();
                                                    scanner.nextLine();
                                                    switch (action) {
                                                        case 1:
                                                            audioPlayer.playSong(id);
                                                            audioPlayer.play();
                                                            break;
                                                        case 2:
                                                            audioPlayer.pause();
                                                            break;
                                                        case 3:
                                                            audioPlayer.resumeAudio(id);
                                                            break;
                                                        case 4:
                                                            audioPlayer.restart(id);
                                                            break;
                                                        case 5:
                                                            audioPlayer.forWord10sec();
                                                            break;
                                                        case 6:
                                                            audioPlayer.backWord10sec();
                                                            break;
                                                        case 7:
                                                            audioPlayer.stop();
                                                            sContinue = true;
                                                            break;
                                                        case 8:
                                                            if (songsIterator.hasNext()) {
                                                                id = songsIterator.next().getSong_id();
                                                                audioPlayer.stop();
                                                                audioPlayer.playSong(id);
                                                            } else {
                                                                audioPlayer.stop();
                                                                System.out.println("There is No more Song's Available In Your Playlist");
                                                                sContinue = true;
                                                            }
                                                            break;

                                                        default:
                                                            System.out.println("Sorry Please Select Proper Option Given");

                                                    }
                                                }
                                            }
                                        }
                                    }catch (InputMismatchException exception){
                                        System.out.println("Please Select The Option in Number Format");
                                    }catch (IOException exception){
                                        System.out.println("Please Provide The Songs Path Properly");
                                    }
                                    catch (Exception e) {
                                        System.out.println(e.getMessage());
                                    }
                                    System.out.println("Do You Won't Continue with Play All Songs");
                                    case5End = scanner.next().charAt(0);
                                    scanner.nextLine();
                                } while (case5End == 'y');
                                break;

                            case 3:
                                do {
                                    try {
                                        boolean quite = false;
                                        int id;
                                        playlistdata = playListService.getAllPlayList();
                                        songsList = songsService.getSongsList();
                                        Set<String> allPlaylists = playlistdata.keySet();
                                        if (!allPlaylists.isEmpty()) {
                                            int i = 1;
                                            System.out.println("----------------   The Playlist's You Have in Playlist Menu     ------------------ ");
                                            for (String list : allPlaylists) {
                                                System.out.println(" " + i + ")        " + list);
                                                i++;
                                            }
                                            System.out.println("Please Enter the Playlist Name Which You Won't To Play");
                                            String playlistName = scanner.nextLine();
                                            ArrayList<Songs> playlistContainSongs = playListContentService.playlistContent(playlistName, playlistdata, songsList);
                                            if (!playlistContainSongs.isEmpty()) {
                                                jukeboxMain.columnValues();
                                                for (Songs songInPlist : playlistContainSongs) {
                                                    System.out.println(songInPlist);
                                                }
                                                Iterator<Songs> songsInPlaylist = playlistContainSongs.iterator();
                                                id = songsInPlaylist.next().getSong_id();
//                                                  audioPlayer.playSong(id);
//                                                  audioPlayer.play();
                                                while (!quite) {
                                                    jukeboxMain.playerOption();
                                                    int action = scanner.nextInt();
                                                    scanner.nextLine();
                                                    switch (action) {
                                                        case 1:
                                                            audioPlayer.playSong(id);
                                                            audioPlayer.play();
                                                            break;
                                                        case 2:
                                                            audioPlayer.pause();
                                                            break;
                                                        case 3:
                                                            audioPlayer.resumeAudio(id);
                                                            break;
                                                        case 4:
                                                            audioPlayer.restart(id);
                                                            break;
                                                        case 5:
                                                            audioPlayer.forWord10sec();
                                                            break;
                                                        case 6:
                                                            audioPlayer.backWord10sec();
                                                            break;
                                                        case 7:
                                                            audioPlayer.stop();
                                                            quite = true;
                                                            break;
                                                        case 8:
                                                            if (songsInPlaylist.hasNext()) {
                                                                id = songsInPlaylist.next().getSong_id();
                                                                audioPlayer.stop();
                                                                audioPlayer.playSong(id);
                                                            } else {
                                                                audioPlayer.stop();
                                                                System.out.println("There is No more Song's Available In Your Playlist");
                                                                quite = true;
                                                            }
                                                            break;

                                                        default:
                                                            System.out.println("Sorry Please Select Proper Option Given");
                                                    }
                                                }

                                            } else {
                                                System.out.println("There is No Songs In Playlist");
                                            }
                                        } else {
                                            System.out.println("Playlist is empty");
                                        }

                                    }catch (InputMismatchException exception){
                                        System.out.println("Please Select The Option in Number Format");
                                    }catch (IOException exception){
                                        System.out.println("Please Provide The Songs Path Properly");
                                    }
                                    catch (Exception e) {
                                        System.out.println(e.getMessage());
                                    }
                                    System.out.println("Do You Won't Continue with Playlist");
                                    case5End = scanner.next().charAt(0);
                                    scanner.nextLine();
                                } while (case5End == 'y');
                                break;
                            case 4:
                                System.out.println("Returning To main");
                                break;
                            case 5:
                                System.exit(0);
                                break;
                            default:
                                System.out.println("Please Enter the Valid Option");
                        }
                        System.out.println("Do You Want To Continue With Player Menu");
                        case4End = scanner.next().charAt(0);
                    } while (case4End == 'y');
                    break;
                case 5:
                    do {
                        jukeboxMain.deleteMenu();
                        int option=0;
                        try {
                             option = scanner.nextInt();
                        }catch (InputMismatchException exception){
                            System.out.println("Please Select The Option in Number Format");
                        }
                        scanner.nextLine();
                        switch (option) {
                            case 1:
                                try {
                                    songsList = songsService.getSongsList();
                                    System.out.println("Please Enter the Song Name : ");
                                    String songName = scanner.nextLine();
                                    if (songName != null) {
                                        boolean result = songsService.deleteSongTable(songName, songsList);
                                        if (result) {
                                            System.out.println("Song is Deleted Successfully");
                                        } else {
                                            System.out.println("Song is not Deleted");
                                        }
                                    } else {
                                        System.out.println("Value Should Not me null");
                                    }
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }

                                break;

                            case 2:
                                System.out.println("Returning To Main");
                                break;
                            case 3:
                                System.exit(0);
                                break;
                            default:
                                System.out.println("Please Choose Proper Option Given");

                        }
                        System.out.println("Do Want To Continue with the Delete Menu if Yes Press 'Y' if No Press 'N'");
                        case3End = scanner.next().charAt(0);
                    } while (case3End == 'y');
                    break;
                case 4:
                    System.out.println("Thank you");
                    System.out.println("Have A Nice Day");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please Choose Proper Option Given");
            }
            System.out.println("Do want to Continue With Main Menu");
            case1End = scanner.next().charAt(0);
        } while (case1End == 'y');
        System.out.println("Thank you");
        System.out.println("Have A Nice Day");
    }
}