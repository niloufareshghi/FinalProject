package GUI;

import Controller.Controller;
import Logic.PlayList;
import Logic.SongInfo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SongInListButton extends SongsButton {
    String PLName;

    public SongInListButton(SongInfo songInfo, String PLNAme) {
        super(songInfo);
        this.PLName = PLNAme;
//        setDeleteButton();
        PLButton.setText(PLName + "-Change place");

    }

    @Override
    void setDeleteButton() {
        super.setDeleteButton();
        deleteButton.setToolTipText("Delete song from PlayList");
        deleteButton.removeActionListener(deleteButton.getActionListeners()[0]);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.getRepository().removeSongFromPL(PLName,getSongInfo());
                Controller.getWindowsGUI().getArtsGUI().getMainGUI().setSongsPlayList(Controller.getRepository().getLists().get(Controller.getRepository().getLists().indexOf(new PlayList(PLName))));
            }
        });
    }

    @Override
    protected void setPLButton() {

        setButtonShape(PLButton);
        PLButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> songTitles = new ArrayList<>();
                for (int i = 0; i < Controller.getWindowsGUI().getArtsGUI().getMainGUI().playListOn.getSongs().size(); i++) {
                    songTitles.add("Title:" + Controller.getWindowsGUI().getArtsGUI().getMainGUI().playListOn.getSongs().get(i).getTitle() +
                            "<--> Artist:" + Controller.getWindowsGUI().getArtsGUI().getMainGUI().playListOn.getSongs().get(i).getArtist());
                }
                String choice=(String)JOptionPane.showInputDialog( Controller.getWindowsGUI(),"Pick a song", "change with", JOptionPane.QUESTION_MESSAGE,
                        null, songTitles.toArray(), "Titan");
                if(choice!=null ) {
//                    Controller.addSongToPL(Name,Controller.getRepository().getAllSongs().get(songTitles.indexOf(choice)));
                    SongInfo newSongToSwap=Controller.getWindowsGUI().getArtsGUI().getMainGUI().playListOn.getSongs().get(songTitles.indexOf(choice));
                    int index=Controller.getWindowsGUI().getArtsGUI().getMainGUI().playListOn.getSongs().indexOf(getSongInfo());
                    Controller.getWindowsGUI().getArtsGUI().getMainGUI().playListOn.getSongs().set(songTitles.indexOf(choice) , getSongInfo());
                    Controller.getWindowsGUI().getArtsGUI().getMainGUI().playListOn.getSongs().set(index , newSongToSwap);
                    Controller.getWindowsGUI().getArtsGUI().getMainGUI().setSongsPlayList(Controller.getRepository().getLists().get(Controller.getRepository().getLists().indexOf(new PlayList(PLName))));


                }       }
        });
    }
}
