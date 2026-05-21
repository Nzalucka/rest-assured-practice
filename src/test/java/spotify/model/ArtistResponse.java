package spotify.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class ArtistResponse {

    private String name;
    private String id;
    private String type;
    private String uri;

    public String getName() {
        return name;
    }
    public String getType() {
        return type;
    }
    public String getUri()  {
        return uri;
    }
    public String getId()  {
        return id;
    }
}
