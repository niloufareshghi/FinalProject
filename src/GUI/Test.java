package GUI;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import javazoom.jl.decoder.JavaLayerException;

import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.io.IOException;

public class Test {

    public static void main(String[] args) throws UnsupportedTagException, UnsupportedAudioFileException, InvalidDataException, IOException, JavaLayerException {
        // write your code here
        JFrame.setDefaultLookAndFeelDecorated(true);
        WindowsGUI mainGUI =new WindowsGUI();

    }
}
