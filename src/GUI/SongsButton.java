package GUI;

import Controller.Controller;
import Logic.SongInfo;
import org.omg.CORBA.BAD_INV_ORDER;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SongsButton extends ShapedButton {
    String Title ;
    JButton deleteButton=new JButton("DELETE");
    JButton PLButton =new JButton();
    private SongInfo songInfo;
    public SongsButton(SongInfo songInfo){
        setSongInfo(songInfo);

        setDeleteButton();
        add(deleteButton, BorderLayout.WEST);
        setPLButton();
        add(PLButton,BorderLayout.PAGE_START);

    }
    @Override
    protected void setSouthButton(){
        setButtonShape(southButton);
        southButton.setText("*MORE OPTIONS");
        southButton.setFont(new Font("option",Font.ROMAN_BASELINE,15));
        southButton.setToolTipText("See more about song");
        southButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
    void setDeleteButton(){
        deleteButton.setIcon(new ImageIcon(getClass().getResource("trash.png")));
        deleteButton.setText("D");
        deleteButton.setToolTipText("Delete song from JPotify");
        setButtonShape(deleteButton);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.deleteSong(songInfo);
            }
        });
    }
    protected void setPLButton(){
        PLButton.setText("+Add to PLAYLIST");
        setButtonShape(PLButton);
        PLButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
    public void setSongInfo(SongInfo songInfo){
        this.songInfo=songInfo;
        if(songInfo!=null){
            setInfo();
        }
    }
    private void setInfo(){
        setBGImage(songInfo.getArtwork());
        setPlayButtonTexT(songInfo.getTitle());
    }
    @Override
    protected void setPlayAction(){
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
