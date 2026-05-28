package spotify;

import spotify.model.PlaylistBody;

public class SpotifyTestData {
    public static final String COLDPLAY_ARTIST_ID = "4gzpq5DPGxSnKTe4SA8HAU";
    public static final String SEARCH_QUERY = "Coldplay";
    public static final String BASE_API_URI = "https://api.spotify.com";
    public static final String COLDPLAY_ALBUM_ID = "5SGtrmYbIo0Dsg4kJ4qjM6";
    public static final String PLAYLIST_ID = "4s1kXKyvA1bmD7hO8tAElu";
    public static final String TRACK_ID = "33koOQs551ijjVmLbmrcDc";
    public static final String TRACK_URI = "spotify:track:" + TRACK_ID;
    public static final String TEST_PLAYLIST_ID = "46LqDd851Hk68mR5u4fgYR";
    public static final String TRACK_ID_2 = "3AJwUDP919kvQ9QcozQPxg";
    public static final String TRACK_URI_2 = "spotify:track:" + TRACK_ID_2;

    public static PlaylistBody createPlaylistBody(){
        return new PlaylistBody("Natalia playlist",
                "Newly created playlist by N", false);
    }
}
