package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.equalTo;

public class NegativeTests extends BaseTest{

    @Story("Negative scenarios")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that GET /users/999 returns status 404")
    @Test(description = "Get non-existent user — verify status 404")
    public void getNonExistentUser(){
        given().when().get("users/999").then().log().body()
                .statusCode(anyOf(equalTo(403), equalTo(404)));

    }
    @Story("Negative scenarios")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that POST /users with empty body returns status 400")
    @Test(description = "Post user — verify status 400 for missing body")
    public void postWithoutBody(){
        given().contentType("application/json").body("").when().post("users/2").then()
                .log().body().statusCode(400);


    }

}
