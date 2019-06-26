package Controller;

import GUI.*;
import Logic.Albums;
import Logic.Repository;
import Logic.SongInfo;

import java.util.ArrayList;

public class Controller {
    static WindowsGUI windowsGUI;
    static Repository repository;

    private Controller() {

    }

    /**
     * @param repository set repository
     *                   .all methods are calling repository's methods until next doc
     */
    public static void setRepository(Repository repository) {
        Controller.repository = repository;
    }

    public static Repository getRepository() {
        return repository;
    }

    public static void addSong(SongInfo songInfo) {
        repository.addSong(songInfo);
    }

    public static void removeSong(SongInfo songInfo) {
        repository.removeSong(songInfo);
    }

    public static void deleteSong(SongInfo songInfo) {
        if (windowsGUI.getArtsGUI().getMainGUI().getStatus().equals(MainStatus.ALBUMLIST)) {
            String name = songInfo.getAlbum();
            String artist = songInfo.getArtist();
            removeSong(songInfo);
            albumsStatus();
        } else {
            removeSong(songInfo);
            songsStatus();
        }
    }

    public static void songsStatus() {
        windowsGUI.getArtsGUI().getMainGUI().setSongsList(getAllSongs());
    }

    public static ArrayList getAllSongs() {
        return repository.getAllSongs();
    }


    public static void getAlbums() {
        repository.getAlbums();
    }

    public static void albumsStatus() {

        windowsGUI.getArtsGUI().getMainGUI().setAlbums(repository.getAlbums());
    }

    public static void albumSongsStatus(String name, String artists) {
        Albums album = new Albums(name, artists);
        windowsGUI.getArtsGUI().getMainGUI().setSongsListOfAlbum(repository.getAlbum(album));
    }
    public static void makeAndAddPL(String PLName){repository.makeAndAddNewPL(PLName);}
    public static void addSongToPL(String name, SongInfo song){repository.addSongToPL(name,song);}

    public static void setWindowsGUI(WindowsGUI windowsGUI1) {
        windowsGUI = windowsGUI1;
    }

    public static WindowsGUI getWindowsGUI() {
        return windowsGUI;
    }

}