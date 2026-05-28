package model;

import java.util.List;

public class RemoveTracksBody {
private List<TrackUri>items;

    public RemoveTracksBody(List<TrackUri> items) {
        this.items = items;
    }

    public List<TrackUri> getItems() {
        return items;
    }
}
