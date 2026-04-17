package tests;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.TestData;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class PatchUserTest extends BaseTest{
    @Test(description = "Patch user — verify status code 200")
    public void patchUserJob(){
        Response response=
                given()
                        .body("{\"job\": \"Analyst\"}").when().patch("/users/2")
                        .then().statusCode(200).extract().response();

        String job=response.jsonPath().getString("job");
        System.out.println(job);
        assertEquals(job,"Analyst","job should be analyst");
    }
    @Test(description = "Patch user — verify response with empty body")
    public void patchUserEmptyBody(){
        given()
               .body("")
                .when().patch("/users/2").then().log().all().statusCode(400);
    }

}
