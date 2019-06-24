package GUI;

import Logic.Albums;
import Logic.PlayList;
import Logic.SongInfo;

import javax.sound.sampled.LineListener;
import javax.swing.*;
import java.awt.*;
import java.awt.dnd.Autoscroll;
import java.util.ArrayList;
import java.util.HashSet;

public class MainGUI extends JScrollPane {
    JPanel verticalListed = new JPanel();
    JPanel lineListed = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
    int Hsize = 0;
    GridLayout myGrid;
    private MainStatus status;

    public MainGUI() {
        super(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }

    private void setList() {
        verticalListed.removeAll();
        lineListed.removeAll();
        verticalListed.add(lineListed);
        setViewportView(verticalListed);
        myGrid = new GridLayout(1, 0, 0, 30);

        verticalListed.setLayout(myGrid);
    }


    public void setSongsList(ArrayList<SongInfo> songInfos) {
        setList();
        status = MainStatus.SONGS;
        Hsize = 0;
        addToList(new ShapedButton());
        for (int i = 0; i < songInfos.size(); i++) {
        addToList(new SongsButton(songInfos.get(i)));
//        for (int i = 0; i < 40; i++) {
//        addToList(new SongsButton(null));
        }
    }

    public void setSongsPlayList(PlayList playList) {
        setList();
        status = MainStatus.PLISTSONGS;
        Hsize = 0;
        addToList(new MainOfPListButton(playList.getName()));
        for (int i = 0; i < playList.getSongs().size(); i++) {
            addToList(new SongInListButton(playList.getSongs().get(i),playList.getName()));
        }
    }

    public void setSongsListOfAlbum(Albums album) {
        setList();
        status = MainStatus.ALBUMLIST;
        Hsize = 0;
        addToList(new AlbumButton(null,album.getName()));
        for (int i = 0; i < album.getSongs().size(); i++) {
            addToList(new SongsButton(album.getSongs().get(i)));
        }
    }
    public void setAlbums(ArrayList<Albums> albums){
        setList();
        status=MainStatus.ALBUMS;
        Hsize=0;
        for (int i = 0; i < albums.size(); i++) {
            addToList(new AlbumButton(null,(albums.get(i).getName())));
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
