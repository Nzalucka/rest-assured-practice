package spotify;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;
import spotify.model.ArtistResponse;

import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

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


    @Story("Spotify Artist")
    @Severity(SeverityLevel.NORMAL)
    @Description("Get Coldplay artist using POJO deserialization")
    @Test(description = "Get artist — verify data using POJO")
    public void getArtistWithPojo(){
        ArtistResponse artist=
                given()
                        .spec(requestSpecification)
                        .when().get("/artists/"+SpotifyTestData.COLDPLAY_ARTIST_ID)
                        .then().spec(responseSpecification).statusCode(200)
                        .extract().response().as(ArtistResponse.class);
        assertEquals(artist.getName(), "Coldplay");
        assertEquals(artist.getType(), "artist");
        assertNotNull(artist.getId());
    }

    @Story("Spotify Artist")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify getArtist response matches JSON schema")
    @Test(description = "Get artist — verify JSON schema")
    public void getArtist_matchesSchema() {
        given()
                .spec(requestSpecification)
                .when().get("/artists/"+SpotifyTestData.COLDPLAY_ARTIST_ID)
                .then().spec(responseSpecification)
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath
                        ("schemas/spotify/schemas/artist-schema.json"));
    }
    @Story("Spotify Artist")
    @Severity(SeverityLevel.NORMAL)
    @Description("Get Coldplay artist — soft assertions")
    @Test(description = "Get artist — verify all fields with soft assertions")
    public void getArtistSoftAssertions() {
        System.out.println("Token: " + token);
        ArtistResponse artist=
                given()
                        .spec(requestSpecification)
                        .when().get("/artists/"+ SpotifyTestData.COLDPLAY_ARTIST_ID)
                        .then().spec(responseSpecification)
                        .statusCode(200)
                        .extract().response().as(ArtistResponse.class);

        SoftAssertions soft=new SoftAssertions();
        soft.assertThat(artist.getId()).isEqualTo(SpotifyTestData.COLDPLAY_ARTIST_ID);
        soft.assertThat(artist.getName()).isEqualTo("Coldplay");
        soft.assertThat(artist.getType()).isEqualTo("artist");
        soft.assertThat(artist.getType()).isEqualTo("artist");
        soft.assertThat(artist.getUri()).isEqualTo("spotify:artist:" + SpotifyTestData.COLDPLAY_ARTIST_ID);
        soft.assertAll();

    }
    @Test(description = "Get artist — verify string matchers")
    public void getArtist_stringMatchers() {
        given()
                .spec(requestSpecification)
                .when().get("/artists/" + SpotifyTestData.COLDPLAY_ARTIST_ID)
                .then().log().all().spec(responseSpecification)
                .statusCode(200)
                .body("uri",startsWith("spotify:artist:"))
                .body("external_urls.spotify",containsString("spotify.com"))
                .body("name",not(equalTo("")))
                .body("images", not(empty()))
                .body("type", equalTo("artist"))
                .body("images",hasSize(greaterThan(0)))
                .body("href", containsString("api.spotify.com"))
                ;
    }
    @Test(description = "Get artist — body vs assertThat comparison")
    public void getArtist_bodyVsAssertThat() {

        // Sposób 1 — bez POJO, .body() z Hamcrest:
        given()
                .spec(requestSpecification)
                .when().get("/artists/" + SpotifyTestData.COLDPLAY_ARTIST_ID)
                .then().spec(responseSpecification)
                .statusCode(200)
                .body("name", equalTo("Coldplay"))
                .body("type", equalTo("artist"))
                .body("uri", startsWith("spotify:artist:"));

        // Sposób 2 — z POJO, assertThat z AssertJ:
        ArtistResponse artist = given()
                .spec(requestSpecification)
                .when().get("/artists/" + SpotifyTestData.COLDPLAY_ARTIST_ID)
                .then().spec(responseSpecification)
                .statusCode(200)
                .extract().response()
                .as(ArtistResponse.class);

        assertThat(artist.getName()).isEqualTo("Coldplay");
        assertThat(artist.getType()).isEqualTo("artist");
        assertThat(artist.getId()).isNotNull();
        assertThat(artist.getUri()).startsWith("spotify:artist:");
    }
    @Story("Spotify Artist")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify response headers for getArtist")
    @Test(description = "Get artist — verify response headers")
    public void getArtist_headerValidation() {
        given()
                .spec(requestSpecification).when()
                .get("/artists/" + SpotifyTestData.COLDPLAY_ARTIST_ID)
                .then().spec(responseSpecification)
                .statusCode(200).header("Content-Type",containsString("application/json"))
                .header("cache-control",notNullValue())
                .header("x-content-type-options", equalTo("nosniff"))
                .header("x-robots-tag", containsString("noindex"));

}
    @Story("Spotify Artist")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify response time is under 3 seconds")
    @Test(description = "Get artist — verify response time")
    public void getArtist_responseTime() {
        given().spec(requestSpecification).when()
                .get("/artists/" + SpotifyTestData.COLDPLAY_ARTIST_ID)
                .then().spec(responseSpecification)
                .statusCode(200).time(lessThan(3000L));

    }
    @Story("Spotify Artist")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify artist albums contain specific albums")
    @Test(description = "Get artist albums — verify hasItems")
    public void getArtistAlbums_hasItems() {
     Response response=   given().spec(requestSpecification)
                .queryParam("include_groups", "album")
                .queryParam("limit", 10)
                .when().get("/artists/" + SpotifyTestData.COLDPLAY_ARTIST_ID+"/albums")
                .then().spec(responseSpecification)
                .statusCode(200)
                .body("items.name",hasItems("Moon Music","Ghost Stories"))
             .extract().response();

     List<String>response_name =response.jsonPath().getList("items.name");
        System.out.println(response_name);

        System.out.println("List size: "+response_name.size());
        System.out.println("first item: "+response_name.get(0));

        // forEach:
        System.out.println("All albums:");
        response_name.forEach(System.out::println);

        // Stream — only albums with  "Music":
        System.out.println("Albumy z 'Music':");
        response_name.stream()
                .filter(name->name.contains("Music"))
                .forEach(System.out::println);

List<Integer>track_counts=
        response.jsonPath().getList("items.total_tracks");
        System.out.println(track_counts);

        System.out.println("Total tracks: "+track_counts.stream().mapToInt(Integer::intValue).sum());
        System.out.println("Max tracks: "+track_counts.stream().mapToInt(Integer::intValue).max().getAsInt());
        System.out.println("min tracks: "+track_counts.stream().mapToInt(Integer::intValue).min().getAsInt());
        System.out.println("All tracks > 0: "+track_counts.stream().allMatch(t->t>0));
    }}
