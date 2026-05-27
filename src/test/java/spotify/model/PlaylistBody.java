package spotify.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PlaylistBody {
    private String name;
    private String description;

    @JsonProperty("public")
    private boolean isPublic;


    public PlaylistBody(String name, String description, boolean isPublic) {
        this.name = name;
        this.description = description;
        this.isPublic = isPublic;
    }

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public boolean isPublic() {
        return isPublic;
    }
}
