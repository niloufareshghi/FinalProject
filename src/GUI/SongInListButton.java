package GUI;

import Controller.Controller;
import Logic.PlayList;
import Logic.SongInfo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

            }
        });
    }
}