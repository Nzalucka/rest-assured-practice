package spotify;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class SearchTest extends SpotifyBaseTest{
    @Story("Spotify Search")
    @Severity(SeverityLevel.NORMAL)
    @Description("Search for Coldplay artist")
    @Test(description = "Search artist — verify status 200")
    public void searchArtist() {

                //.baseUri(SpotifyTestData.BASE_API_URI)
           Response response=
                   given()
                .spec(requestSpecification)
             //   .header("Authorization","Bearer "+token)
                .queryParam("q","Coldplay")
                .queryParam("type","artist")
                .when().get("/search")
                .then().spec(responseSpecification).statusCode(200)
                .body("artists.total", greaterThan(0))
                .body("artists.items.find " +
                        "{it.id=='4gzpq5DPGxSnKTe4SA8HAU' " +
                        "}.name",equalTo("Coldplay"))
                .body("artists.items.find { " +
                        "it.id == '4gzpq5DPGxSnKTe4SA8HAU' " +
                        "}.id", notNullValue())
                .body("artists.items.find {it.id=='4gzpq5DPGxSnKTe4SA8HAU'}.type",equalTo("artist"))
                           .extract().response();

        Map<String,Object>coldplay=response.jsonPath()
                .getMap("artists.items.find { it.id == '4gzpq5DPGxSnKTe4SA8HAU' }");
        System.out.println("coldplay: "+coldplay);



    }
}
