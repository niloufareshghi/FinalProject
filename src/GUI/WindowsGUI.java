package GUI;

import Controller.Controller;
import Logic.Save;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import javazoom.jl.decoder.JavaLayerException;

import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

public class WindowsGUI extends JFrame {

    JPanel listGUI=new ListGUI();
    FriendsActivityGUI friendsActivityGUI=new FriendsActivityGUI();
    CenterGUI artsGUI=new CenterGUI();
    PlayerGUI playerGUI=new PlayerGUI();
    GridBagConstraints gbc=new GridBagConstraints();

    public WindowsGUI() throws InvalidDataException, IOException, UnsupportedTagException, UnsupportedAudioFileException, JavaLayerException {
        Controller.setWindowsGUI(this);
        designLayout();


    }
    private void addJpanel(Component component,int gridy,int gridx,int width,int height){
        gbc.gridwidth = width;
        gbc.gridheight= height;
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        getContentPane().add(component,gbc);
    }
    private void designLayout(){
        setLayout(new GridBagLayout());
        gbc.fill=GridBagConstraints.BOTH;
        gbc.weighty=0.5;
        gbc.weightx=1;
        gbc.ipady=600;
        gbc.ipadx=0;
        addJpanel(listGUI,0,0,1,2);
        gbc.ipadx=50;
        gbc.weightx=2;
        //here we changedchance
        addJpanel(artsGUI,0,1,1,1);
//        gbc.ipadx=100;
        gbc.weightx=0.5;
        addJpanel(friendsActivityGUI,0,2,1,1);
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.ipady=0;
        gbc.weightx=1;
        gbc.weighty=0;
        addJpanel(playerGUI,1,1,2,1);
//        pack();
        setSize(1000,1000);
        setVisible(true);
        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                Save save=new Save();
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public FriendsActivityGUI getFriendsActivityGUI() {
        return friendsActivityGUI;
    }

    public JPanel getListGUI() {
        return listGUI;
    }

    public CenterGUI getArtsGUI() {
        return artsGUI;
    }

    public PlayerGUI getPlayerGUI() {
        return playerGUI;
    }

}
