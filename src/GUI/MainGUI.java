package GUI;

import Controller.Controller;
import Logic.Albums;
import Logic.LyricFinder;
import Logic.PlayList;
import Logic.SongInfo;

import javax.sound.sampled.LineListener;
import javax.swing.*;
import java.awt.*;
import java.awt.dnd.Autoscroll;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class MainGUI extends JScrollPane {
    JPanel verticalListed = new JPanel();
    JPanel lineListed = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
    int Hsize = 0;
    GridLayout myGrid;
    private MainStatus status;
    ArrayList<SongInfo> songs;
    PlayList playListOn=null;
    Albums albumsOn=null;
    public MainGUI() {

        super(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }

    private void setList() {
        songs=new ArrayList<SongInfo>();
        PlayList playListOn=null;
        Albums albumsOn=null;
        verticalListed.removeAll();
        lineListed.removeAll();
        verticalListed.add(lineListed);
        setViewportView(verticalListed);
        myGrid = new GridLayout(1, 0, 0, 30);

        verticalListed.setLayout(myGrid);
    }

    public void Lyric(SongInfo song) throws IOException {
        String artist=song.getArtist();
        String title=song.getTitle();
        JLabel showLyric=new JLabel();
        String lyric=LyricFinder.getURLSource(artist,title);
//        String lyric=LyricFinder.getURLSource("saxon","747strangersinthenight");

        showLyric.setSize(350,350);
        showLyric.setText(lyric);
        JPanel moreOption=new JPanel();
        moreOption.add(showLyric);
        setViewportView(moreOption);
    }
    public void setSongsList(ArrayList<SongInfo> songInfos) {
        setList();
        songs=songInfos;
        status = MainStatus.SONGS;
        Hsize = 0;
        for (int i = 0; i < songInfos.size(); i++) {
            System.out.println(new SongsButton(songInfos.get(i)).path());
        }
        addToList(new ShapedButton());
        for (int i = 0; i < songInfos.size(); i++) {
            SongsButton songsButton= new SongsButton(songInfos.get(i)) ;
            addToList(songsButton);
//        for (int i = 0; i < 40; i++) {
//        addToList(new SongsButton(null));
        }
    }

    public void setSongsPlayList(PlayList playList) {
        setList();
        playListOn=playList;
        status = MainStatus.PLISTSONGS;
        Hsize = 0;
        addToList(new MainOfPListButton(playList.getName()));
        for (int i = 0; i < playList.getSongs().size(); i++) {
            addToList(new SongInListButton(playList.getSongs().get(i),playList.getName()));
        }
    }

    public void setSongsListOfAlbum(Albums album) {
        setList();
        albumsOn=album;
        status = MainStatus.ALBUMLIST;
        Hsize = 0;
        addToList(new AlbumButton(null,album.getName(),album.getArtist()));
        for (int i = 0; i < album.getSongs().size(); i++) {
            addToList(new SongsButton(album.getSongs().get(i)));

        }

    }
    public void setAlbums(ArrayList<Albums> albums){
        setList();
        status=MainStatus.ALBUMS;
        Hsize=0;
        for (int i = 0; i < albums.size(); i++) {
            addToList(new AlbumButton(null,albums.get(i).getName(),albums.get(i).getArtist()));
        }
    }

    public void addToList(Component component) {
        if (Hsize == 4) {
            JPanel helpList = new JPanel();
            helpList = lineListed;
            verticalListed.remove(lineListed);
            verticalListed.add(helpList);
            lineListed = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
            lineListed.add(component);
            myGrid.setRows(myGrid.getRows() + 1);
            verticalListed.add(lineListed);
            Hsize = 1;
        } else {
            lineListed.add(component);
            Hsize++;
        }
    }

    public MainStatus getStatus() {
        return status;
    }
}
