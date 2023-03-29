package com.wynk.juckbox.Main;

import com.wynk.juckbox.model.Songs;
import com.wynk.juckbox.service.PlayListContentService;
import com.wynk.juckbox.service.PlaylistService;
import com.wynk.juckbox.service.SimpleAudioPlayer;
import com.wynk.juckbox.service.SongsService;

import java.sql.SQLException;
import java.util.*;

public class JuckboxMain {
    void displayMenu(){
        System.out.println("                              ");
        System.out.println("Please select option below : ");
        System.out.println("--------------------------------");
        System.out.println("1           Song\n2           Playlist\n3           Player\n4           Exit\n");
    }
    void displaySongMenu(){
        System.out.println("");
        System.out.println("Please select option below : \n");
        System.out.println("--------------------------------");
        System.out.println("1         Add Song\n2         Search song By name\n3         Search Song By Gener\n4         Search Song By Album\n5         Search Song By Artist\n6         Main Menu\n7         Exit\n");
    }
    void displayPlaylistMenu(){
        System.out.println("                                 ");
        System.out.println("--------->Welcome to Playlist My Friend<----------");
        System.out.println(" ");
        System.out.println("Please select option below : ");
        System.out.println("--------------------------------");
        System.out.println("1           All Playlist\n2           Creat PlayList\n3           Add Song to Playlist\n4           Add album to playList\n5           View Playlist\n6           Back Menu\n7           Exit\n");
    }
    void playerMenu(){
        System.out.println("Player menu");
        System.out.println("******");
        System.out.println("1  Pause\n2  Resume\n3  Forward\n4  Backward\n5  Restart\n6  Next Song");
    }

    public static void main(String[] args) throws Exception {

        JuckboxMain juckboxMain =new JuckboxMain();
        Scanner scanner = new Scanner(System.in);
        SongsService songsService = new SongsService();
        PlaylistService playlistService =new PlaylistService();
        PlayListContentService playListContentService=new PlayListContentService();
        SimpleAudioPlayer simpleAudioPlayer=new SimpleAudioPlayer();

        System.out.println("-------------------------------------------------------------welcome To JuckBox---------------------------------------------------------");
        System.out.println(" ");
        System.out.println("--------------------------------------------------------------Song you Have-------------------------------------------------------------");

        char option;
        char option1;
        char option2;

        try {
            Hashtable<String, Integer> playlistServiceHashtable;
            songsService.displaySongs();
            ArrayList<Songs> songsList;

            do {
                juckboxMain.displayMenu();
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        do {
                            songsList= songsService.getSongs();
                            juckboxMain.displaySongMenu();
                            int songchoice = scanner.nextInt();
                            switch (songchoice) {
                                case 1:
                                    scanner.nextLine();
                                    System.out.println("Please Enter Song Name : ");
                                    String songName = scanner.nextLine();

                                    System.out.println("Please Enter the Artist Name : ");
                                    String artistName = scanner.nextLine();

                                    System.out.println("Please Enter the Gener : ");
                                    String genre = scanner.nextLine();

                                    System.out.println("Please Enter the Album Name : ");
                                    String albumName = scanner.nextLine();

                                    System.out.println("Please Enter Duration : ");
                                    String duration = scanner.nextLine();

                                    Songs songs = new Songs(110, songName, artistName, genre, albumName, duration);
                                   if (songsService.addSongs(songs, songsList)){
                                    songsService.displaySongs();}
                                   else throw new Exception("Song is not added");
                                    break;
                                case 2:
                                    System.out.println("Song to search : ");
                                    scanner.nextLine();
                                    String song = scanner.nextLine();
                                    ArrayList<Songs> songsArrayList = songsService.selectSongsByName(song, songsList);
                                    if (!songsArrayList.isEmpty()){
                                        for (Songs sList:songsArrayList)
                                    System.out.println(sList);}
                                    else {
                                        throw new Exception("Song is not found");
                                    }
                                    break;
                                case 3:
                                    System.out.println("Genre Search : ");
                                    scanner.nextLine();
                                    String ganre = scanner.nextLine();
                                    ArrayList<Songs> generList = songsService.selectSongsByGenre(ganre, songsList);
                                    if (!generList.isEmpty()){
                                        for (Songs gList:generList)
                                    System.out.println(gList);}
                                    else {
                                        throw new Exception("Genre is not found");
                                    }

                                    break;
                                case 4:
                                    System.out.println("Album Search : ");
                                    scanner.nextLine();
                                    String album = scanner.nextLine();
                                    ArrayList<Songs> albumArrayList = songsService.selectSongsByAlbum(album, songsList);
                                    if (!albumArrayList.isEmpty()){
                                        for (Songs aList:albumArrayList)
                                            System.out.println(aList);
                                    }
                                    else {
                                        throw new Exception("Album not found");
                                    }
                                    break;
                                case 5:
                                    System.out.println("Artist Search : ");
                                    scanner.nextLine();
                                    String artist = scanner.nextLine();
                                    ArrayList<Songs> artistList = songsService.selectSongsByArtist(artist, songsList);
                                    if (!artistList.isEmpty()){
                                        for (Songs arList:artistList)
                                    System.out.println(arList);}
                                    break;

                                case 6:
                                    System.out.println("Would you like to Continue");
                                    break;
                                case 7:
                                    System.out.println("ThankYou");
                                    System.exit(0);
                                    break;
                            }
                            System.out.println("would u like Continue please Enter 'r'");
                            option = scanner.next().charAt(0);
                        }
                        while (option == 'r');
                        break;
                    case 2:{
                        do {
                            playlistServiceHashtable = playlistService.getAllPlaylist();
                            songsList = songsService.getSongs();
                            juckboxMain.displayPlaylistMenu();
                            int menuList = scanner.nextInt();
                            switch (menuList) {
                                case 1:
                                    Set<String> playList = playlistServiceHashtable.keySet();
                                    for (String pList : playList)
                                        System.out.println(pList);
                                    break;
                                case 2:
                                    scanner.nextLine();
                                    System.out.println("Please Enter playlist Name : ");
                                    String listName = scanner.nextLine();
                                    boolean position = playlistService.addPlaylist(listName, playlistServiceHashtable,14);
                                    if (position) {
                                        System.out.println("play list is created ");
                                    }
                                    else {
                                        throw new Exception("Playlist not Added");
                                    }
                                    break;
                                case 3:
                                    scanner.nextLine();
                                    System.out.println("please enter song to Playlist : ");
                                    String song = scanner.nextLine();
                                    System.out.println("Enter the Playlist Name : ");
                                    String playlistName = scanner.nextLine();
                                    boolean positions = playListContentService.addSongtOPlaylist(songsList, playlistServiceHashtable, song, playlistName);
                                    if (positions) {
                                        System.out.println("song is add to playlist");
                                    }
                                    else {
                                        throw new Exception("Song is not added to Playlist");
                                    }
                                    break;
                                case 4:
                                    scanner.nextLine();
                                    System.out.println("Please add album to playlist : ");
                                    String album = scanner.nextLine();
                                    System.out.println("Enter playList Name : ");
                                    String playlist = scanner.nextLine();
                                    boolean positionss = playListContentService.addSongByAlbumName(songsList, playlistServiceHashtable, album, playlist);
                                    if (positionss) {
                                        System.out.println("Album added to playlist");
                                    }
                                    else throw new Exception("Album is not Added to playlist");
                                    break;
                                case 5:
                                    scanner.nextLine();
                                    System.out.println("please Enter the playlist Name : ");
                                    String playName = scanner.nextLine();
                                    ArrayList<Songs> playlistContent = playListContentService.playlistContent(playName, playlistServiceHashtable, songsList);
                                    for (Songs pContent:playlistContent){
                                    System.out.println(pContent);}
                                    break;
                                case 6:
                                    System.out.println("main menu");
                                    break;
                                case 7:
                                    System.out.println("thankyou");
                                    System.exit(0);
                                    break;
                            }
                            System.out.println("Enter 'c' to Continue through Playlist Menu ");
                            option1 = scanner.next().charAt(0);
                        }
                        while (option1 == 'c');
                        break;
                    }
                    case 3:
                        playlistServiceHashtable = playlistService.getAllPlaylist();
                        songsList = songsService.getSongs();
                        char next;
                        int id;
                        do{
                            boolean quit = false;
                            System.out.println("Please Enter the PlayList Name That you wanted to Play");
                            scanner.nextLine();
                            String playlistName=scanner.nextLine();
                            ArrayList<Songs> playlistSongs= playListContentService.playlistContent(playlistName,playlistServiceHashtable,songsList);
                            Iterator<Songs> iterator = playlistSongs.iterator();
                            id=iterator.next().getSongId();
                            simpleAudioPlayer.playSongs(id);
                            while (!quit){
                                juckboxMain.playerMenu();
                                int action = scanner.nextInt();
                                System.out.println("--------------------------------------------------------");
                                scanner.nextLine();
                                switch (action){
                                    case 1: simpleAudioPlayer.pause();
                                        break;
                                    case 2: //playerService.resume(id);
                                        simpleAudioPlayer.play();
                                        break;
                                    case 3: simpleAudioPlayer.forward();
                                        break;
                                    case 4: simpleAudioPlayer.backward();
                                        break;
                                    case 5:
                                        simpleAudioPlayer.restart(id);
                                        break;
                                    case 6: if(iterator.hasNext()){
                                        id = iterator.next().getSongId();
                                        simpleAudioPlayer.stop();
                                        System.out.println("Song is Playing Sucessfully");
                                        simpleAudioPlayer.playSongs(id); }
                                    else {
                                        simpleAudioPlayer.stop();
                                        System.out.println("There is No Next Song Available in PlayList");
                                        quit=true;
                                    }
                                        break;
                                    default:
                                        System.out.println("Sorry You have selected the wrong Option");
                                        quit=true;
                                        simpleAudioPlayer.stop();
                                        break;
                                }
                            }

                            System.out.println("if you want to continue with another Playlist enter 'y' ");

                            next=scanner.next().charAt(0);
                        }while (next=='y'); break;
                    case 4:
                        System.out.println("thankyou");
                        break;
                }
                System.out.println("Enter 'r' to continue to main Menu ");
                option2 = scanner.next().charAt(0);
            }
            while (option2 == 'r');

        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        catch (InputMismatchException e){
            System.out.println(e.getMessage());
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}