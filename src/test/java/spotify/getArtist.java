package spotify;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import spotify.model.ArtistResponse;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class getArtist extends SpotifyBaseTest{
    @Story("Spotify Artist")
    @Severity(SeverityLevel.NORMAL)
    @Description("Get Coldplay artist by ID")
    @Test(description = "Get artist — verify Coldplay data")
    public void getArtist() {
      Response response= given()
                .spec(requestSpecification)
                .when().get("/artists/"+SpotifyTestData.COLDPLAY_ARTIST_ID)
                .then().spec(responseSpecification)
                .statusCode(200).body("name",equalTo("Coldplay"))
                .body("name",equalTo("Coldplay"))
                .body("type",equalTo("artist"))
                .body("uri",equalTo("spotify:artist:4gzpq5DPGxSnKTe4SA8HAU"
                )).extract().response();



        System.out.println("Artist name: " + response.jsonPath().getString("name"));
        System.out.println("Artist id: " + response.jsonPath().getString("id"));
        System.out.println("Artist type: " + response.jsonPath().getString("type"));

        // POJO deserialization
        ArtistResponse artist=response.as(ArtistResponse.class);
        System.out.println("=== POJO ===");
        System.out.println("name:"+artist.getName());
        System.out.println("id: "+artist.getId());
        System.out.println("Type: "+artist.getType());
        System.out.println(" ");
    }
}
