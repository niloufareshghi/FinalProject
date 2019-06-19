package GUI;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.io.IOException;

public class Test {

    public static void main(String[] args) throws InvalidDataException, IOException, UnsupportedTagException, LineUnavailableException, UnsupportedAudioFileException {
        // write your code here
     //   System.out.println("test");
        JFrame.setDefaultLookAndFeelDecorated(true);
        WindowsGUI mainGUI =new WindowsGUI();


    }
}
