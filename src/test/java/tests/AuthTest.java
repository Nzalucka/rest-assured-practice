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
}
