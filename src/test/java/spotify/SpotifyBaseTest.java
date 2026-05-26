package spotify;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;



public class SpotifyBaseTest {
    protected String token;

    protected RequestSpecification requestSpecification;
    protected ResponseSpecification responseSpecification;


    @BeforeClass
    public void getToken() throws IOException {
        Properties props = new Properties();
        props.load(new FileInputStream("src/test/resources/spotify.properties"));

        String clientId = props.getProperty("spotify.client.id");
        String clientSecret = props.getProperty("spotify.client.secret");
        String refreshToken = props.getProperty("spotify.refresh_token");

        String accessToken = refreshAccessToken(clientId, clientSecret, refreshToken);

        token = given()
                .baseUri("https://accounts.spotify.com")
                .contentType("application/x-www-form-urlencoded")
                .auth().preemptive().basic(clientId, clientSecret)
                .formParam("grant_type", "client_credentials")
                .when().post("/api/token")
                .then().statusCode(200)
                .extract().path("access_token");

        requestSpecification=new RequestSpecBuilder()
            .setBaseUri("https://api.spotify.com")
                .addHeader("Authorization", "Bearer " + accessToken)
                .setBasePath("/v1")
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();

responseSpecification=new ResponseSpecBuilder()
        .expectContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                        .build();

        System.out.println("Token: " + token);

    }
    private String refreshAccessToken(String clientId, String clientSecret, String refreshToken) {
        return given()
                .baseUri("https://accounts.spotify.com")
                .contentType("application/x-www-form-urlencoded")
                .auth().preemptive().basic(clientId, clientSecret)
                .formParam("grant_type", "refresh_token")
                .formParam("refresh_token", refreshToken)
                .when().post("/api/token")
                .then().statusCode(200)
                .extract().path("access_token");
    }
}
