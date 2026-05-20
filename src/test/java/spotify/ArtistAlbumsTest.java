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

    @Story("Spotify Artist")
    @Severity(SeverityLevel.NORMAL)
    @Description("Get Coldplay albums with pagination")
    @Test(description = "Get artist albums — pagination")
    public void getArtistAlbumsWithPagination() {
        // Page 1 — albums 1-10
        Response page1 = given()
                .spec(requestSpecification)
                .queryParam("include_groups", "album")
                .queryParam("limit", 10)
                .queryParam("offset", 0)
                .when().get("/artists/" + SpotifyTestData.COLDPLAY_ARTIST_ID + "/albums")
                .then().spec(responseSpecification).statusCode(200)
                .extract().response();

        Response page2=
                given()
                        .spec(requestSpecification)
                        .queryParam("include_groups","album")
                        .queryParam("limit", 10)
                        .queryParam("offset", 10)
                        .when().get("/artists/" + SpotifyTestData.COLDPLAY_ARTIST_ID + "/albums")
                        .then().spec(responseSpecification).statusCode(200)
                        .extract().response();

        List<String> page1AlbumNames=page1.jsonPath().getList("items.name");
        List<String> page2AlbumNames=page2.jsonPath().getList("items.name");

        System.out.println("=== PAGE 1 ===");
        for (int i=0; i<page1AlbumNames.size();i++){
            System.out.println((i+1)+"."+ page1AlbumNames.get(i));
        }
        System.out.println("=== PAGE 2 ===");
        for (int i=0;i<page2AlbumNames.size();i++){
            System.out.println((i+1)+"."+page2AlbumNames.get(i));
        }
        System.out.println("Total albums: " + (page1AlbumNames.size() + page2AlbumNames.size()));

        List<Integer> page1Tracks = page1.jsonPath().getList("items.total_tracks");
        List<Integer> page2Tracks = page2.jsonPath().getList("items.total_tracks");

       // System.out.println("tracks on page 1"+page1Tracks);
      //  System.out.println("tracks on page 2"+page2Tracks);
        System.out.println("=== PAGE 1 ===");
        for (int i=0; i<page1AlbumNames.size();i++){
            System.out.println((i+1)+"."+page1AlbumNames.get(i)+": "+page1Tracks.get(i)+" tracks");
        }

        System.out.println("Total albums: " + (page1AlbumNames.size() + page2AlbumNames.size()));

        // Total tracks both pages
        int totalTracks=page1Tracks.stream().mapToInt(Integer::intValue).sum()
                +page2Tracks.stream().mapToInt(Integer::intValue).sum();
        System.out.println("Total tracks all albums: " + totalTracks);


    }
    }

