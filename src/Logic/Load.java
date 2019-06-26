package Logic;

import Controller.Controller;
import GUI.PListGUI;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Load {
    ArrayList<String> allSongs;
    ArrayList<ArrayList> PLists;
    Repository repository;

    public Load() throws InvalidDataException, IOException, UnsupportedTagException {
        repository = Controller.getRepository();
        loadAllSong();
        loadPL();
    }

    private void setAllSongs() throws InvalidDataException, IOException, UnsupportedTagException {
        if (allSongs != null) {
            for (int i = 0; i < allSongs.size(); i++) {
                Controller.addSong(new SongInfo(allSongs.get(i)));
            }

        }
    }

    private void setPLists() throws InvalidDataException, IOException, UnsupportedTagException {
        if (PLists != null) {
            for (int i = 0; i < PLists.size(); i++) {
                String playlistName;
                playlistName = (String) PLists.get(i).get(0);
                Controller.makeAndAddPL(playlistName);
                for (int j = 1; j < PLists.get(i).size(); j++) {
                    SongInfo songInfo=new SongInfo((String) PLists.get(i).get(j));
                    songInfo = (SongInfo) Controller.getAllSongs().get(Controller.getAllSongs().indexOf(songInfo));
                    Controller.addSongToPL(playlistName,songInfo);
                }
            }
        } else {
            Controller.makeAndAddPL(">>Favorites");
            Controller.makeAndAddPL(">>SharedList");

        }
    }

    public void loadAllSong() throws InvalidDataException, IOException, UnsupportedTagException {
        allSongs = null;
        // creating input stream variables
        FileInputStream fis = null;
        ObjectInputStream ois = null;

        // creating List reference to hold AL values
        // after de-serialization

        try {
            // reading binary data
            if (Files.exists(Paths.get("allSong.txt"))) {
                fis = new FileInputStream("allSong.txt");

                // converting binary-data to java-object
                ois = new ObjectInputStream(fis);

                // reading object's value and casting ArrayList<String>
                allSongs = (ArrayList<String>) ois.readObject();
            }
        } catch (FileNotFoundException fnfex) {
            fnfex.printStackTrace();
        } catch (IOException ioex) {
            ioex.printStackTrace();
        } catch (ClassNotFoundException ccex) {
            ccex.printStackTrace();
        }

        System.out.println("ArrayList object de-serialized"
                + " from SaveArrayList.ser file\n");
        setAllSongs();
    }

    public void loadPL() throws InvalidDataException, IOException, UnsupportedTagException {
        PLists = null;
        // creating input stream variables
        FileInputStream fis = null;
        ObjectInputStream ois = null;

        // creating List reference to hold AL values
        // after de-serialization

        try {
            // reading binary data
            if (Files.exists(Paths.get("playLists.txt"))) {
                fis = new FileInputStream("playLists.txt");

                // converting binary-data to java-object
                ois = new ObjectInputStream(fis);

                // reading object's value and casting ArrayList<String>
                PLists = (ArrayList<ArrayList>) ois.readObject();
            }
        } catch (FileNotFoundException fnfex) {
            fnfex.printStackTrace();
        } catch (IOException ioex) {
            ioex.printStackTrace();
        } catch (ClassNotFoundException ccex) {
            ccex.printStackTrace();
        }

        System.out.println("ArrayList object de-serialized"
                + " from SaveArrayList.ser file\n");

        setPLists();
    }

}
