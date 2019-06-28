package GUI;

import Logic.SongInfo;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ArtWorkGUI extends JPanel {
    static SongInfo songInfo;
    Image img;
    JLabel artTitle = new JLabel();
    JLabel artYear = new JLabel();
    JLabel artArtist = new JLabel();
    JLabel artAlbum = new JLabel();
    Font font1 = new Font("a", Font.BOLD, 10);
    Font font2 = new Font("a", Font.ITALIC, 10);

    public ArtWorkGUI(SongInfo songInfo) {
//        this.songInfo = songInfo;
//        setJlabel(artTitle, null, "Title");
//        setJlabel(artAlbum, null, "Album");
//        setJlabel(artArtist, null, "Artist");
//        setJlabel(artYear, null, "Year");
//        img =songInfo.getArtwork();
        setSongInfo(songInfo);

    }

    public void setSongInfo(SongInfo songInfo) {
        removeAll();
        this.songInfo = songInfo;
        img = this.songInfo.getArtwork();
        setBackground(Color.black);
        setJlabel(artTitle, songInfo.getTitle(), "Title ");

        setJlabel(artArtist, songInfo.getArtist(), "Artist");
        setJlabel(artAlbum, songInfo.getAlbum(), "Album  ");
        setJlabel(artYear, songInfo.getYear(), "Year ");
        JPanel songInfoP = new JPanel();
        songInfoP.setBackground(this.getBackground());
        songInfoP.add(artAlbum);
        songInfoP.add(artTitle);
        songInfoP.add(artArtist);
        songInfoP.add(artYear);
        songInfoP.setLayout(new BoxLayout(songInfoP, BoxLayout.Y_AXIS));
        setLayout(new BorderLayout());
//        songInfoP.setPreferredSize(new Dimension(50,50));
        add(songInfoP, BorderLayout.PAGE_END);
        updateUI();
    }

    public ArtWorkGUI() throws IOException {
//        img= ImageIO.read(new File(String.valueOf(getClass().getResource("Jpotify.png"))));

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(songInfo!=null)
        img = songInfo.getArtwork();
        System.out.println("done");
        if (img != null) {
            g.drawImage(img, 0, 0, this.getWidth(), this.getHeight() - 50, this);
        }
    }

    private void setJlabel(JLabel jlabel, String jlabelStr, String title) {
        jlabel.setBackground(this.getBackground());
        jlabel.setForeground(new Color(0xF80300));
        jlabel.setSize(10, 10);
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