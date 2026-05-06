package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CookiesTest extends BaseTest{
    @Story("Cookies")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify sending cookie in request")
    @Test(description = "Send cookie — verify server receives it")
    public void sendCookie() {

        given()
                .baseUri("https://httpbin.org")
                .cookie("session","abc123")
                .when().get("/cookies")
                .then().statusCode(200)
                .body("cookies.session",equalTo("abc123"));

    }
    @Story("Cookies")
    @Severity(SeverityLevel.NORMAL)
    @Description("Get cookie from response and use in next request")
    @Test(description = "Get cookie and use in next request")
    public void getCookieAndUseInNextRequest() {
        String session=
                given()
                        .baseUri("https://httpbin.org")
                        .redirects().follow(false)
                        .when().get("/cookies/set?session=xyz789")
                        .then().extract().cookie("session");
        System.out.println("Cookie: "+session);

        given().baseUri("https://httpbin.org")
                .cookie("session",session)
                .when().get("/cookies")
                .then().statusCode(200)
                .body("cookies.session",equalTo("xyz789"));
    }

}
