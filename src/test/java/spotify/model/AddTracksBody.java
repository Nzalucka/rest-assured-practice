package spotify.model;


import java.util.List;

public class AddTracksBody {
    private int position;
    private List<String>uris;

    public AddTracksBody(int position, List<String> uris) {
        this.position = position;
        this.uris = uris;
    }

    public int getPosition() {
        return position;
    }

    public List<String> getUris() {
        return uris;
    }
}
