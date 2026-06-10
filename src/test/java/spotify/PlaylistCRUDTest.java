package spotify;

import io.qameta.allure.*;
import io.restassured.response.Response;
import spotify.model.RemoveTracksBody;
import spotify.model.TrackUri;
import org.testng.annotations.Test;
import spotify.model.AddTracksBody;
import spotify.model.FollowPlaylistBody;
import spotify.model.PlaylistBody;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@Epic("Spotify API")
@Feature("Playlist")
public class PlaylistCRUDTest extends SpotifyBaseTest{

    @Story("Spotify Playlist CRUD")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Create a new playlist")
    @Test(description = "Create playlist — verify 201")
    public void createPlaylist() {
    Response response= given()
                .spec(requestSpecification)
                .body(SpotifyTestData.createPlaylistBody())
                .when().post("/me/playlists")
                .then().spec(responseSpecification)
                .statusCode(201)
                .body("name",equalTo("Natalia playlist"))
                .body("description",equalTo("Newly created playlist by N"))
                .body("id",notNullValue())
            .extract().response();

        String playlistId=response.jsonPath().getString("id");
        System.out.println("Created playlist ID: " + playlistId);
    }

    @Story("Spotify Playlist CRUD")
    @Severity(SeverityLevel.NORMAL)
    @Description("Update playlist name and description")
    @Test(description = "Update playlist — verify 200")
    public void updatePlaylist() {
        given()
                .spec(requestSpecification)
                .body(new PlaylistBody("Updated Playlist", "Updated description", false))
                .when().put("/playlists/"+SpotifyTestData.TEST_PLAYLIST_ID)
                .then().statusCode(200);

    }

    @Story("Spotify Playlist CRUD")
    @Severity(SeverityLevel.NORMAL)
    @Description("Add track to playlist")
    @Test(description = "Add tracks — verify 201")
    public void addTracksToPlaylist() {

        given()
                .spec(requestSpecification)
        .body(new AddTracksBody(0,List.of(SpotifyTestData.TRACK_URI,SpotifyTestData.TRACK_URI_2)))
        .when().post("/playlists/" + SpotifyTestData.TEST_PLAYLIST_ID + "/items")
        .then().
    spec(responseSpecification).statusCode(201)
                .body("snapshot_id", notNullValue());
    }

    @Story("Spotify Playlist CRUD")
    @Severity(SeverityLevel.NORMAL)
    @Description("Remove track from playlist")
    @Test(description = "Remove tracks — verify 200")
    public void removeTracksFromPlaylist() {
        given()
                .spec(requestSpecification)
                .body(new RemoveTracksBody(List.of(new TrackUri(SpotifyTestData.TRACK_URI))))
                .when().delete("/playlists/"+SpotifyTestData.TEST_PLAYLIST_ID+"/items")
                .then().statusCode(200);
    }
    @Story("Spotify Playlist CRUD")
    @Severity(SeverityLevel.NORMAL)
    @Description("Follow a playlist")
    @Test(description = "Follow playlist — verify 200")
    public void followPlaylist() {
        given()
                .spec(requestSpecification)
                .body(new FollowPlaylistBody(false))
                .when().put("/playlists/" + SpotifyTestData.TEST_PLAYLIST_ID + "/followers")
                .then().statusCode(200);
    }

}
