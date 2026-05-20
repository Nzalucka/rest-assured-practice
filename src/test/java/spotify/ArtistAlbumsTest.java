package spotify;


import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

public class ArtistAlbumsTest extends SpotifyBaseTest{
    @Story("Spotify Artist")
    @Severity(SeverityLevel.NORMAL)
    @Description("Get Coldplay albums")
    @Test(description = "Get artist albums — verify data")
    public void getArtistAlbums() {
        Response response=
                given()
                        .spec(requestSpecification)
                        .queryParam("include_groups", "album")
                        .queryParam("limit", 10)

                        .when().get("/artists/"+SpotifyTestData.COLDPLAY_ARTIST_ID+"/albums")
                        .then()
                        .spec(responseSpecification)
                        .statusCode(200)
                        .body("total", greaterThan(0))
                        .body("items[0].total_tracks", greaterThan(0))
                        .body("items[0].artists[0].name", equalTo("Coldplay"))
                        .body("items[0].album_type", equalTo("album"))
                        .extract().response();
        //sum of album
        int totalAlbums=response.jsonPath().getInt("total");
        System.out.println("=== COLDPLAY ALBUMS ===");
        System.out.println("Total albums: " + totalAlbums);

        List<String>albumNames=response.jsonPath().getList("items.name");
        System.out.println("Albums in response: " + albumNames.size());

        List<Integer> trackCounts = response.jsonPath().getList("items.total_tracks");
        System.out.println("track counts "+trackCounts);
        for (int i = 0; i < albumNames.size(); i++) {
            System.out.println((i + 1) + ". " + albumNames.get(i)
                    + " → " + trackCounts.get(i) + " tracks");
        }
        int totalTracks=trackCounts.stream().mapToInt(Integer::intValue).sum();
        System.out.println("Total tracks in response: " + totalTracks);
    }

    }

