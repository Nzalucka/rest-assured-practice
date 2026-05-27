package spotify;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import spotify.model.PlaylistBody;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

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
}
