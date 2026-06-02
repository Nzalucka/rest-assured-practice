package spotify;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import tests.BaseTest;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.hamcrest.Matchers.equalTo;

public class NegativeTests extends SpotifyBaseTest {
    @Story("Spotify Negative")
    @Severity(SeverityLevel.NORMAL)
    @Description("Get artist with invalid token returns 401")
    @Test(description = "Invalid token — verify 401")
    public void getArtist_invalidToken() {
        given()
                .baseUri(SpotifyTestData.BASE_API_URI)
                .header("Authorization", "Bearer  invalid_token")
                .when().get("/artists/"+SpotifyTestData.COLDPLAY_ARTIST_ID)
                .then().statusCode(401)
                .body("error.status",equalTo(401))
                .body("error.message",equalTo("Invalid access token"));
    }

    @Story("Spotify Negative")
    @Severity(SeverityLevel.NORMAL)
    @Description("Get artist with invalid ID returns 400")
    @Test(description = "Invalid ID — verify 400")
    public void getArtist_invalidId(){
        given()
                .spec(requestSpecification)
                .when().get("/artists/invalidId123").then().statusCode(400);
    }
}
