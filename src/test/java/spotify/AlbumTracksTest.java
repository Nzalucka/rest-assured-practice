package spotify;

import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@Epic("Spotify API")
@Feature("Album")
public class AlbumTracksTest extends SpotifyBaseTest{
    @Story("Spotify Album")
    @Severity(SeverityLevel.NORMAL)
    @Description("Get tracks from Coldplay album")
    @Test(description = "Get album tracks — verify data")
    public void getAlbumTracks(){
    Response response= given()
                .spec(requestSpecification)
                .when().get("/albums/"+SpotifyTestData.COLDPLAY_ALBUM_ID+"/tracks")
                .then().spec(responseSpecification)
                .body("total",equalTo(10))
                .body("items.size()",equalTo(10))
                .body("items[0].name", equalTo("MOON MUSiC"))
                .body("items[0].type", equalTo("track"))
                .body("items[0].explicit", equalTo(false))
                .body("items[0].track_number", equalTo(1))
                .body("items[0].artists[0].name", equalTo("Coldplay"))
                .statusCode(200).extract().response();

        List<String> trackNames = response.jsonPath().getList("items.name");
        List<Integer> durations = response.jsonPath().getList("items.duration_ms");

        System.out.println("=== MOON MUSIC TRACKS ===");
        for (int i=0;i<trackNames.size();i++){
            System.out.println((i+1)+"."+trackNames.get(i)+" "+durations.get(i)+ " ms");
        }
    }
}
