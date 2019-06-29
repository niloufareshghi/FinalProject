package GUI;

import Controller.Controller;
import Logic.SongInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class SearchBarGUI extends JPanel {
    JTextField searchText=new JTextField("search...");
    public SearchBarGUI() {
        setBackground(new Color(0x01000A));
        FlowLayout flowLayout =new FlowLayout(FlowLayout.LEFT);
        searchText.setEnabled(true);
//        searchText.setSize(30,10);
        searchText.setBackground(Color.WHITE);
        searchText.setAlignmentX(Component.LEFT_ALIGNMENT);
        searchText.setPreferredSize(new Dimension(200,30));
        add(searchText);
        JButton searchButton =new JButton();
        searchButton.setIcon(new ImageIcon(getClass().getResource("loupe.png")));
        searchButton.setFocusPainted(false);
        searchButton.setBorderPainted(false);
        searchButton.setContentAreaFilled(false);
        add(searchButton);
        setAction(searchButton);
        setLayout(flowLayout);
    }
    private void setAction(JButton button){
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.getWindowsGUI().getArtsGUI().getMainGUI().setSongsList(search());
            }
        });
    }
    private ArrayList search(){
        ArrayList<SongInfo> songsOfsearch=new ArrayList<>();
        for(SongInfo s: Controller.getAllSongs()){
            if(s.getTitle().indexOf(searchText.getText())>=0
                    || s.getAlbum().indexOf(searchText.getText())>=0
                    || s.getArtist().indexOf(searchText.getText())>=0)
            {
                songsOfsearch.add(s);
            }
        }
        return songsOfsearch;
    }
}
