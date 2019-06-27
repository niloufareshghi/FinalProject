package Logic;

import Controller.Controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Save {
    ArrayList<String> allSongs;
    ArrayList<ArrayList> PLists;
    Repository repository;

    public Save() {
        repository = Controller.getRepository();
        saveAllSongs();
        savePL();
    }

    private void setAllSongs(ArrayList<SongInfo> allSongs) {
        this.allSongs = new ArrayList<>();
        for (int i = 0; i < allSongs.size(); i++) {
            this.allSongs.add(allSongs.get(i).getFilename());
            System.out.println(allSongs.get(i).getFilename());
        }
    }

    private void setPLists(ArrayList<PlayList> pLists) {
        this.PLists = new ArrayList<>();
        for (int i = 0; i < pLists.size(); i++) {
            PlayList playList = (pLists.get(i));
            ArrayList<String> list = new ArrayList<>();
            list.add( playList.getName());
            System.out.println(list);
            for (int j = 0; j < playList.getSongs().size(); j++) {
                list.add(playList.getSongs().get(j).getFilename());

            }
            PLists.add(list);
        }


    }

    private void saveAllSongs() {
        setAllSongs(repository.getAllSongs());
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        try {
            // for writing or saving binary data
            fos = new FileOutputStream("allSong.txt");

            // converting java-object to binary-format
            oos = new ObjectOutputStream(fos);

            // writing or saving ArrayList values to stream
            oos.writeObject(allSongs);
//            oos.flush();
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("ArrayList object saved"
                + " to SaveArrayList.ser file");
    }

    private void savePL() {
        setPLists(repository.getLists());

        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        try {
            // for writing or saving binary data
            fos = new FileOutputStream("playLists.txt");

            // converting java-object to binary-format
            oos = new ObjectOutputStream(fos);

            // writing or saving ArrayList values to stream
            oos.writeObject(PLists);
            oos.flush();
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("!!!ArrayList object saved"
                + " to SaveArrayList.ser file");

    }
}
