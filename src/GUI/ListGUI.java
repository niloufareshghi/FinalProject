package GUI;

import Logic.SongInfo;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ListGUI extends JPanel {
    public ListGUI() throws InvalidDataException, IOException, UnsupportedTagException {
        //setSize(w,h);
        setBackground(Color.DARK_GRAY);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc =new GridBagConstraints();
        gbc.fill=GridBagConstraints.BOTH;
        gbc.weightx=1;
        gbc.weighty=3;
        gbc.gridwidth = 1;
        gbc.gridheight= 1;
        gbc.gridy = 0;
        gbc.gridx = 0;
        add(new PListGUI(),gbc);
        gbc.fill=GridBagConstraints.BOTH;
        gbc.gridy =1;
        gbc.gridx = 0;
        gbc.weighty=1;
        gbc.weightx=1;
        gbc.ipady=0;
        gbc.ipadx=0;
        gbc.gridheight=1;
        gbc.gridheight= 1;
        JPanel artWork =new JPanel();
        artWork.setBackground(Color.BLUE);
        add(new ArtWorkGUI(new SongInfo("C:/Heydar/java/Ap/testing/out/production/testing/Lams.mp3")),gbc);
    }
}
