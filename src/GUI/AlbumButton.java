package GUI;

import Controller.Controller;
import Logic.Albums;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AlbumButton extends ShapedButton {
    String albumName,artist;

    public AlbumButton(Image img,String AlbumName,String artist){

        super();
        this.albumName=AlbumName;
        this.artist=artist;
        southButton.setText("Album:"+AlbumName);
        img=Controller.getRepository().getAlbum(new Albums(albumName,artist)).getSongs().get(0).getArtwork();
        setBGImage(img);
    }
    @Override
    protected void setPlayAction(){
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
    @Override
    protected void setSouthButton(){
        setButtonShape(southButton);
        southButton.setForeground(Color.GREEN);
        southButton.setText("AlbumName:");
        southButton.setFont(new Font("AlbumName",Font.ROMAN_BASELINE,20));
        southButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.albumSongsStatus(albumName,artist);
            }
        });
    }
}

