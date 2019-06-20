package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AlbumButton extends ShapedButton {
    String AlbumName;
    public AlbumButton(Image img,String AlbumName){
        super();
        this.AlbumName=AlbumName;
        southButton.setText("Album"+AlbumName);
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

            }
        });
    }
}

