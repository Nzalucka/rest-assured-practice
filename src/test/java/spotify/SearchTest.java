package spotify;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class SearchTest extends SpotifyBaseTest{
    @Story("Spotify Search")
    @Severity(SeverityLevel.NORMAL)
    @Description("Search for Coldplay artist")
    @Test(description = "Search artist — verify status 200")
    public void searchArtist() {
        given()
                //.baseUri(SpotifyTestData.BASE_API_URI)
                .spec(requestSpecification)
             //   .header("Authorization","Bearer "+token)
                .queryParam("q","Coldplay")
                .queryParam("type","artist")
                .when().get("/search")
                .then().spec(responseSpecification).statusCode(200);
    }
}
