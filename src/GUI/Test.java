package GUI;

import Controller.Controller;
import Logic.Load;
import Logic.PlayList;
import Logic.Repository;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import javazoom.jl.decoder.JavaLayerException;
import server.FileServer;
import server.Server;

import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.io.IOException;

public class Test {

    public static void main(String[] args) throws UnsupportedTagException, UnsupportedAudioFileException, InvalidDataException, IOException, JavaLayerException, ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        // write your code here
        JFrame.setDefaultLookAndFeelDecorated(true);
//        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        Controller.setRepository(new Repository());
        Load load=new Load();
        WindowsGUI mainGUI =new WindowsGUI();
        PlayList cur = null;
        for(PlayList playList : Controller.getRepository().getLists()){
            if (playList.getName().equals(">>SharedList"))
                cur = playList;
        }
        Server server = new Server(cur);
        FileServer  server1 = new FileServer(Controller.getAllSongs());
    }
}