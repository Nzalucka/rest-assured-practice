package spotify;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class TrackTest extends  SpotifyBaseTest{
    @Story("Spotify Track")
    @Severity(SeverityLevel.NORMAL)
    @Description("Get Coldplay track by ID")
    @Test(description = "Get track — verify Coldplay data")
    public void getTrack() {
        Response response=
                given()
                        .spec(requestSpecification)
                        .when().get("/tracks/"+SpotifyTestData.TRACK_ID)
                        .then()
                        .spec(responseSpecification)
                        .statusCode(200)
                        .body("id", equalTo(SpotifyTestData.TRACK_ID))
                        .body("name", equalTo("Yellow - Live from Spotify London"))
                        .body("type", equalTo("track"))
                        .body("explicit", equalTo(false))
                        .body("is_playable", equalTo(true))
                        .body("duration_ms", greaterThan(0))
                        .body("artists[0].name", equalTo("Coldplay"))
                        .body("album.name", equalTo("Live from Spotify London"))
                        .body("album.album_type", equalTo("single"))
                        .body("external_urls.spotify", notNullValue())
                        .extract().response();
        System.out.println("Track id: " + response.jsonPath().getString("id"));
        System.out.println("Track name: " + response.jsonPath().getString("name"));
        System.out.println("Artist: " + response.jsonPath().getString("artists[0].name"));
        System.out.println("Album: " + response.jsonPath().getString("album.name"));
        System.out.println("Duration ms: " + response.jsonPath().getInt("duration_ms"));
    }
}
