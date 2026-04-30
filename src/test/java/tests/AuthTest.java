package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import utils.TestData;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class AuthTest extends BaseTest{
    @Story("Authentication")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify POST /login returns token")
    @Test(description = "Login — verify token is returned")
    public void loginAndGetToken(){
        given()
                .body(TestData.loginBody())
                .when().post("/login")
                .then()
                .spec(basicResponseSpec)
                .body("token",notNullValue());


    }

    @Story("Authentication")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify login returns token and token can be used in subsequent request")
    @Test(description = "Login — get token and use it in next request")
    public void loginAndUseToken() {
        String token=
                given()
                        .body(TestData.loginBody())
                        .when().post("/login")
                        .then().spec(basicResponseSpec)
                        .extract().path("token");
        System.out.println("token"+token);

        given()
                .header("Authorization", "Bearer "+token)
                .when()
                .get("/users/2")
                .then().spec(responseSpec);

    }
}
