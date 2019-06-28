package GUI;

import Logic.SongInfo;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ListGUI extends JPanel {
    PListGUI pListGUI=new PListGUI();
    ArtWorkGUI artWork =new ArtWorkGUI();

    GridBagConstraints gbc =new GridBagConstraints();

    public ListGUI() throws InvalidDataException, IOException, UnsupportedTagException {
        //setSize(w,h);
        setBackground(Color.DARK_GRAY);
        setLayout(new GridBagLayout());
        gbc.fill=GridBagConstraints.BOTH;
        gbc.weightx=1;
        gbc.weighty=3;
        gbc.gridwidth = 1;
        gbc.gridheight= 1;
        gbc.gridy = 0;
        gbc.gridx = 0;
        add(pListGUI,gbc);
        gbc.fill=GridBagConstraints.BOTH;
        gbc.gridy =1;
        gbc.gridx = 0;
        gbc.weighty=1;
        gbc.weightx=1;
        gbc.ipady=0;
        gbc.ipadx=0;
        gbc.gridheight=1;
        gbc.gridheight= 1;
        add(artWork,gbc);
    }

    public PListGUI getpListGUI() {
        return pListGUI;
    }

    public void setArtWork(SongInfo songInfo) throws InvalidDataException, IOException, UnsupportedTagException {
//        remove(artWork)
        this.artWork.setSongInfo(songInfo);
        updateUI();
//        add(this.artWork,gbc);
//        updateUI();
    }

    public ArtWorkGUI getArtWork() {
        return artWork;
    }
}
