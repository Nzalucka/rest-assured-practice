package spotify;

import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@Epic("Spotify API")
@Feature("Album")
public class AlbumTest extends SpotifyBaseTest{
    @Story("Spotify Album")
    @Severity(SeverityLevel.NORMAL)
    @Description("Get Coldplay album by ID")
    @Test(description = "Get album — verify Coldplay data")
    public void getAlbum(){
        Response response=
                given()
                        .spec(requestSpecification)
                        .when().get("/albums/"+SpotifyTestData.COLDPLAY_ALBUM_ID)
                        .then().spec(responseSpecification).statusCode(200)
                        .body("id",equalTo(SpotifyTestData.COLDPLAY_ALBUM_ID))
                        .body("name", equalTo("Moon Music"))
                        .body("album_type", equalTo("album"))
                        .body("total_tracks", equalTo(10))
                        .body("release_date", equalTo("2024-10-04"))
                        .body("artists[0].name", equalTo("Coldplay"))
                        .extract().response();
        System.out.println("Album id: " + response.jsonPath().getString("id"));
        System.out.println("Name: " + response.jsonPath().getString("name"));
        System.out.println("Alum type: " + response.jsonPath().getString("album_type"));
        System.out.println("total tracks: " + response.jsonPath().getInt("total_tracks"));
        System.out.println("Release date: " + response.jsonPath().getString("release_date"));
        System.out.println("artist name "+ response.jsonPath().getString("artists[0].name"));


    }
    @Story("Spotify Album")
    @Severity(SeverityLevel.NORMAL)
    @Description("Get album with invalid ID")
    @Test(description = "Get album — invalid ID returns 404")
    public void getAlbum_invalidId() {
      Response response=  given()
                .spec(requestSpecification)
                .when().get("/albums/invalidId123")
                .then().spec(responseSpecification)
                .statusCode(400)
                .body("error.message", equalTo("Invalid base62 id")).extract().response();
        System.out.println("Error status: " + response.jsonPath().getInt("error.status"));
        System.out.println("Error message: " + response.jsonPath().getString("error.message"));
    }

}
