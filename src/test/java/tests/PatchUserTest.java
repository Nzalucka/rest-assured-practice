package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.TestData;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class PatchUserTest extends BaseTest{

    @Story("Patch user")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that PATCH /users/2 updates job and returns status 200")
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

    @Story("Patch user")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that PATCH /users/2 with empty body returns status 400")
    @Test(description = "Patch user — verify response with empty body")
    public void patchUserEmptyBody(){
        given()
               .body("")
                .when().patch("/users/2").then().log().all().statusCode(400);
    }

}
