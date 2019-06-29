package GUI;

import Controller.Controller;
import Logic.Albums;
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
//                try {
//                    ArrayList<SongInfo> songInfos=Controller.getRepository().getAlbum(new Albums(albumName,artist)).getSongs();
//                    Controller.getWindowsGUI().getPlayerGUI().setListToPlay(songInfos);
//                } catch (IOException e1) {
//                    e1.printStackTrace();
//                } catch (UnsupportedTagException e1) {
//                    e1.printStackTrace();
//                } catch (InvalidDataException e1) {
//                    e1.printStackTrace();
//                } catch (JavaLayerException e1) {
//                    e1.printStackTrace();
//                }
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

