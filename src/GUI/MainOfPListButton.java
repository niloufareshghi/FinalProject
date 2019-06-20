package GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainOfPListButton extends  ShapedButton{
    JButton addMusic = new JButton();
    JButton PLName = new JButton();
    String Name;
    public MainOfPListButton(String Name){
        this.Name=Name;
        setAddMusic();
        setPLName();
        add(addMusic,BorderLayout.EAST);
        add(PLName,BorderLayout.PAGE_START);
    }


    private void setAddMusic (){
        setButtonShape(addMusic);
        addMusic.setIcon(new ImageIcon(getClass().getResource("addMusic.png")));
        addMusic.setToolTipText("AddSong to Playlist");
        addMusic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
    private void setPLName(){
        PLName.setText(Name);
        setButtonShape(PLName);
        PLName.setToolTipText("Rename Play list");
        PLName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
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
        southButton.setText("DELETE PlayList");
        southButton.setFont(new Font("dl",Font.ROMAN_BASELINE,12));
        southButton.setToolTipText("Delete this playlist");
        southButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}

