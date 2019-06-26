package Logic;

import java.util.ArrayList;

public class Repository {
    private ArrayList<SongInfo> allSongs = new ArrayList<SongInfo>();
    //    private HashMap<String, ArrayList> PLHashMap;
//    private ArrayList<String> PLArrayList;
//    private ArrayList<String> albumList = new ArrayList<>();
    private ArrayList<Albums> albums;
    private ArrayList<PlayList> lists = new ArrayList<>();

    public boolean addSong(SongInfo song) {
        if (song != null) {
            allSongs.add(song);
            return true;
        }
        return false;

    }

    public boolean removeSong(SongInfo song) {
        if (allSongs.contains(song)) {
//            for(int i=0;i<PLArrayList.size();i++){
//                removeSongFromPL(PLArrayList.get(i),song);

//            }
            for (int i = 0; i < lists.size(); i++) {
                removeSongFromPL(lists.get(i).getName(), song);
            }
            allSongs.remove(song);
            setAlbums();
            return true;
        }
        return false;
    }

    public ArrayList<SongInfo> getAllSongs() {
        return allSongs;
    }

    public ArrayList<Albums> setAlbums() {
        albums = new ArrayList<>();

        for (int i = 0; i < allSongs.size(); i++) {
            Albums album = new Albums(allSongs.get(i).getAlbum(), allSongs.get(i).getArtist());
            if (!albums.contains(album)) {
                album.addSongs(allSongs.get(i));
                albums.add(album);
            } else {
                albums.get(albums.indexOf(album)).addSongs(allSongs.get(i));
            }

        }
        return albums;
    }

    public ArrayList<Albums> getAlbums() {
        setAlbums();
        return albums;
    }

    public Albums getAlbum(Albums album) {
        albums = setAlbums();
        if(albums.contains(album)) {
            int i = albums.indexOf(album);
            return albums.get(i);
        }
        else {
            return album;
        }

    }

    public void addNewPL(PlayList playList) {
        if (!lists.contains(playList))
            lists.add(playList);
    }

    public void makeAndAddNewPL(String name) {
        PlayList playList = new PlayList(name);
        addNewPL(playList);
    }

    public void addSongToPL(String name, SongInfo song) {
        lists.get(lists.indexOf(new PlayList(name))).addSongs(song);
    }

    public void removeSongFromPL(String name, SongInfo song) {

        lists.get(lists.indexOf(new PlayList(name))).removeSong(song);
    }

    public void removePL(String name) {
        if (lists.contains(new PlayList(name)))
            lists.remove(new PlayList(name));
    }

    public ArrayList<PlayList> getLists() {
        return lists;
    }
    /*
        PLHashMap.get(PLName).add(song);

    }

    public void addNewPlayList(String PLName) {
        ArrayList<SongInfo> newPL = new ArrayList();
        PLHashMap.put(PLName, newPL);
        PLArrayList.add(PLName);
    }

    public boolean removeSongFromPL(String PLName, SongInfo song) {
        if (PLHashMap.get(PLName).contains(song)) {
            PLHashMap.get(PLName).remove(song);
            return true;
        }
        return false;
    }

    public void removePL(String PLName) {
        PLArrayList.remove(PLHashMap.get(PLName));
        PLHashMap.remove(PLName);
    }

    public ArrayList<String> getPLArrayList() {
        return PLArrayList;
    }
    public ArrayList getPlayList(String PlName){
        return PLHashMap.get(PlName);
    }

    private HashMap setAlbums() {
        HashMap<String, ArrayList> findAlbum = new HashMap<>();
        albumList.clear();
        for (int i = 0; i < allSongs.size(); i++) {
            if (!findAlbum.containsKey(allSongs.get(i).getAlbum() + " // " + allSongs.get(i).getArtist())) {
                ArrayList<SongInfo> album = new ArrayList<>();
                findAlbum.put(allSongs.get(i).getAlbum() + " // " + allSongs.get(i).getArtist(), album);
                albumList.add(allSongs.get(i).getAlbum());;
            } else {
                findAlbum.get(allSongs.get(i).getAlbum() + " // " + allSongs.get(i).getArtist()).add(allSongs.get(i));
            }
        }
        return findAlbum;
    }

    public ArrayList<String> getAlbumsList() {
        setAlbums();
        return albumList;
    }
    public ArrayList getAlbum(String albumName){
        HashMap albums = setAlbums();
        return albums.get(albumName);

    }
*/

}

