package Logic;

public class Albums extends PlayList {
    private String artist;

    public Albums(String name, String artist) {
        super(name);
        this.artist = artist;
    }

    public String getArtist() {
        return artist;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Albums
                && (((Albums) obj).name.equals(this.name))
                && ((Albums) obj).getArtist().equals(this.artist)) {
            return true;
        }


        return false;
    }

}
