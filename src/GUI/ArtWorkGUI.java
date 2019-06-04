package GUI;

import Logic.SongInfo;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ArtWorkGUI extends JPanel {
    SongInfo songInfo;
    Image img;
    JLabel artTitle = new JLabel();
    JLabel artYear = new JLabel();
    JLabel artArtist = new JLabel();
    JLabel artAlbum = new JLabel();
    Font font1 = new Font("a", Font.BOLD, 12);
    Font font2 = new Font("a", Font.ITALIC, 12);

    public ArtWorkGUI(SongInfo songInfo) {
//        this.songInfo = songInfo;
//        setJlabel(artTitle, null, "Title");
//        setJlabel(artAlbum, null, "Album");
//        setJlabel(artArtist, null, "Artist");
//        setJlabel(artYear, null, "Year");
//        img =songInfo.getArtwork();
        this.songInfo = songInfo;
        img = this.songInfo.getArtwork();
        setBackground(Color.black);
        setJlabel(artTitle, songInfo.getInfo()[0], "Title ");
        setJlabel(artArtist, songInfo.getInfo()[1], "Artist");
        setJlabel(artAlbum, songInfo.getInfo()[2], "Album  ");
        setJlabel(artYear, songInfo.getInfo()[3], "Year ");
        JPanel songInfoP = new JPanel();
        songInfoP.setBackground(this.getBackground());
        songInfoP.add(artAlbum);
        songInfoP.add(artTitle);
        songInfoP.add(artArtist);
        songInfoP.add(artYear);
        songInfoP.setLayout(new BoxLayout(songInfoP, BoxLayout.Y_AXIS));
        setLayout(new BorderLayout());
        add(songInfoP, BorderLayout.PAGE_END);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        img = songInfo.getArtwork();
        if (img != null) {
            g.drawImage(img, 0, 0, this.getWidth(), this.getHeight() - 50, this);
        }
    }

    private void setJlabel(JLabel jlabel, String jlabelStr, String title) {
        jlabel.setBackground(this.getBackground());
        jlabel.setForeground(new Color(0xF80300));
        jlabel.setSize(100, 10);
        if (jlabelStr != null) {
            jlabel.setFont(font2);
            jlabel.setText(title.toUpperCase() + ": " + jlabelStr);
        } else {
            jlabel.setFont(font1);
            jlabel.setText(title.toUpperCase() + "::" + "Unknown");
        }
    }
    /*private void setImg(Image img) throws IOException {
        if (img != null) {
            this.img = img;
        }
        else {
            BufferedImage image = ImageIO.read(new File(String.valueOf(getClass().getResource("music.png"))));
        }
    }*/
}