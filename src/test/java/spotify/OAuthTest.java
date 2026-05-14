package spotify;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertNotNull;

public class OAuthTest extends SpotifyBaseTest{
    private static final String CLIENT_ID=System.getenv("SPOTIFY_CLIENT_ID");
    private static final String CLIENT_SECRET = System.getenv("SPOTIFY_CLIENT_SECRET");


    @Story("OAuth 2.0")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify Spotify OAuth 2.0 Client Credentials flow returns access token")
    @Test(description = "Spotify OAuth — get access token")
    public void getSpotifyToken(){


        System.out.println("token "+ token);
        assertNotNull(token,"token should not be null");
    }
}
