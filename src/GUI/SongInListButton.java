package GUI;

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