package Logic;

import java.util.ArrayList;
import java.util.HashSet;

public class PlayList {
    String name;
    ArrayList<SongInfo> songs;
    public PlayList(String name){
        songs=new ArrayList<>();
        this.name=name;;
    }

    public ArrayList<SongInfo> getSongs() {
        return songs;

    }

    public void addSongs(SongInfo song) {
        songs.add(song);
    }
    public void removeSong(SongInfo song){
     if(songs.contains(song)){
         songs.remove(song);
     }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof PlayList && obj!=null && ((PlayList) obj).name!=null){

            if(((PlayList) obj).name.equals(this.name)){
                return true;
            }
        }
        return false;
    }

}
