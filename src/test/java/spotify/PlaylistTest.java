package spotify;

import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@Epic("Spotify API")
@Feature("Playlist")
public class PlaylistTest extends SpotifyBaseTest{
    @Story("Spotify Playlist")
    @Severity(SeverityLevel.NORMAL)
    @Description("Get playlist by ID")
    @Test(description = "Get playlist — verify data")
    public void getPlaylist() {
        Response response=
                given().spec(requestSpecification)
                        .when()
                        .get("/playlists/"+SpotifyTestData.PLAYLIST_ID)
                        .then()
                        .spec(responseSpecification)
                        .body("id", equalTo(SpotifyTestData.PLAYLIST_ID))
                        .body("name", equalTo("Coldplay Acoustic"))
                        .body("type", equalTo("playlist"))
                        .body("public", equalTo(true))
                        .body("owner.display_name", equalTo("Coldplay"))
                        .body("followers.total", greaterThan(0))
                        .body("images.size()", greaterThan(0))
                        .body("external_urls.spotify", notNullValue())
                        .statusCode(200).extract().response()
                        ;

        System.out.println("Playlist id: " + response.jsonPath().getString("id"));
        System.out.println("Name: " + response.jsonPath().getString("name"));
        System.out.println("Owner: " + response.jsonPath().getString("owner.display_name"));
        System.out.println("Public: " + response.jsonPath().getBoolean("public"));
        System.out.println("Followers: " + response.jsonPath().getInt("followers.total"));

    }
}
