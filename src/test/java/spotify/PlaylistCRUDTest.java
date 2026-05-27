package spotify;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class PlaylistCRUDTest extends SpotifyBaseTest{

    @Story("Spotify Playlist CRUD")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Create a new playlist")
    @Test(description = "Create playlist — verify 201")
    public void createPlaylist() {
        given()
                .spec(requestSpecification)
                .body(SpotifyTestData.createPlaylistBody())
                .when().post("/me/playlists")
                .then().spec(responseSpecification)
                .statusCode(201)
                .body("name",equalTo("Natalia playlist"))
                .body("description",equalTo("Newly created playlist by N"))
                .body("id",notNullValue());


    }
}
