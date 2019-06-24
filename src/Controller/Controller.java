package Controller;

import GUI.*;
import Logic.Repository;
import Logic.SongInfo;

import java.util.ArrayList;

public class Controller {
    static WindowsGUI windowsGUI;
    static Repository repository;

    private Controller() {

    }

    /**
     *
     * @param repository set repository
     *                   .all methods are calling repository's methods until next doc
     */
    public static void setRepository(Repository repository) {
        Controller.repository = repository;
    }

    public static void addSong(SongInfo songInfo){
        repository.addSong(songInfo);
    }
    public static void removeSong(SongInfo songInfo){
        repository.removeSong(songInfo);
    }

    public static ArrayList getAllSongs() {
        return repository.getAllSongs();
    }


    public static void getAlbums(){
        repository.getAlbums();
    }
    public static void songsListButtonAction() {
        windowsGUI.getArtsGUI().getMainGUI().setSongsList(getAllSongs());
    }


    public static void setWindowsGUI(WindowsGUI windowsGUI1) {
        windowsGUI = windowsGUI1;
    }

}