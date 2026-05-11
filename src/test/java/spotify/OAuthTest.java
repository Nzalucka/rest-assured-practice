package spotify;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertNotNull;

public class OAuthTest {
    private static final String CLIENT_ID=System.getenv("SPOTIFY_CLIENT_ID");
    private static final String CLIENT_SECRET = System.getenv("SPOTIFY_CLIENT_SECRET");


    @Story("OAuth 2.0")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify Spotify OAuth 2.0 Client Credentials flow returns access token")
    @Test(description = "Spotify OAuth — get access token")
    public void getSpotifyToken(){
        String token=
                given()
                        .baseUri("https://accounts.spotify.com")
                        .contentType("application/x-www-form-urlencoded")
                        .formParam("grant_type", "client_credentials")
                        .formParam("client_id", CLIENT_ID)
                        .formParam("client_secret", CLIENT_SECRET)
                        .when().post("/api/token")
                        .then().statusCode(200)
                        .extract().path("access_token");


        System.out.println("token "+ token);
        assertNotNull(token,"token should not be null");
    }
}
