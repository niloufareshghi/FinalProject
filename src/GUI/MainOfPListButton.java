package GUI;

import Controller.Controller;
import Logic.Albums;
import Logic.PlayList;
import Logic.SongInfo;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import javazoom.jl.decoder.JavaLayerException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class MainOfPListButton extends ShapedButton {
    JButton addMusic = new JButton();
    JButton PLName = new JButton();
    String Name;

    public MainOfPListButton(String Name) {
        this.Name = Name;
        setAddMusic();
        setPLName();
        add(addMusic, BorderLayout.EAST);
        add(PLName, BorderLayout.PAGE_START);
        if (Name != null
                && (Name.equals(">>Favorites")
                || Name.equals(">>SharedList"))) {
            PLName.setEnabled(false);
            southButton.setEnabled(false);
        }
    }


    private void setAddMusic() {
        setButtonShape(addMusic);
        addMusic.setIcon(new ImageIcon(getClass().getResource("addMusic.png")));
        addMusic.setToolTipText("AddSong to Playlist");
        addMusic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> songTitles = new ArrayList<>();
                for (int i = 0; i < Controller.getRepository().getAllSongs().size(); i++) {
                    songTitles.add("Title:" + Controller.getRepository().getAllSongs().get(i).getTitle() +
                            "<--> Artist:" + Controller.getRepository().getAllSongs().get(i).getArtist());
                }
                String choice=(String)JOptionPane.showInputDialog( Controller.getWindowsGUI(),"Pick a song", "add to playList", JOptionPane.QUESTION_MESSAGE,
                        null, songTitles.toArray(), "Titan");
                if(choice!=null){
                    Controller.addSongToPL(Name,Controller.getRepository().getAllSongs().get(songTitles.indexOf(choice)));
                    Controller.getWindowsGUI().getArtsGUI().getMainGUI().setSongsPlayList(Controller.getRepository().getLists().get(Controller.getRepository().getLists().indexOf(new PlayList(Name))));
                }
            }
        });
    }

    private void setPLName() {
        PLName.setText(Name);
        setButtonShape(PLName);
        PLName.setToolTipText("Rename Play list");
        PLName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PlayList playList = new PlayList(Name);

                do {
                    playList.setName(JOptionPane.showInputDialog("Enter Name of PlayList: ", playList.getName()));
                } while (Controller.getRepository().getLists().contains(playList) || playList.getName() == null);
                PlayList playList1 = Controller.getRepository().getLists().get(Controller.getRepository().getLists().indexOf(new PlayList((Name))));
                playList1.setName(playList.getName());
                PLName.setText(playList1.getName());
                Controller.getWindowsGUI().getListGUI().getpListGUI().setUpdatePlPanel();
            }
        });
    }

    @Override
    protected void setPlayAction() {
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ArrayList<SongInfo> songInfos=Controller.getWindowsGUI().getArtsGUI().mainGUI.playListOn.getSongs();
                    Controller.getWindowsGUI().getPlayerGUI().setListToPlay(songInfos);
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (UnsupportedTagException e1) {
                    e1.printStackTrace();
                } catch (InvalidDataException e1) {
                    e1.printStackTrace();
                } catch (JavaLayerException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void setSouthButton() {
        setButtonShape(southButton);
        southButton.setText("DELETE PlayList");


        southButton.setFont(new Font("dl", Font.ROMAN_BASELINE, 12));
        southButton.setToolTipText("Delete this playlist");
        southButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.getRepository().removePL(Name);
                Controller.getWindowsGUI().getArtsGUI().getMainGUI().setSongsList(Controller.getAllSongs());
                Controller.getWindowsGUI().getListGUI().getpListGUI().setUpdatePlPanel();
                Controller.getWindowsGUI().getListGUI().getpListGUI().updateUI();
            }
        });

    }
}

