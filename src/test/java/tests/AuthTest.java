package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.TestData;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import static org.testng.Assert.assertEquals;

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
    @Story("Authentication")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify token can be used to get users list")
    @Test(description = "Login — use token to get users list")
    public void loginAndVerifyUsers() {
       String token= given()
                .body(TestData.loginBody())
                .when().post("/login")
                .then().spec(basicResponseSpec)
                .extract().path("token");
        System.out.println("token is"+token);

       Response response= given().header("Authorization","Bearer "+token)
                .when().get("/users")
                .then().spec(responseSpec).extract().response();

       int size=response.jsonPath().getList("data").size();
        System.out.println(size);
        assertEquals(size, 6, "list should have 6 users");

    }

    @Story("Authentication")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify token can be used to get user by id")
    @Test(description = "Login — use token to get user by id")
    public void loginAndVerifyUserById() {
        String token= given()
                .body(TestData.loginBody())
                .when().post("/login")
                .then().spec(basicResponseSpec)
                .extract().path("token");
        System.out.println("token is"+token);

        Response response= given().header("Authorization","Bearer "+token)
                .when().get("/users/3")
                .then().spec(responseSpec).extract().response();

        int id=response.jsonPath().getInt("data.id");
        System.out.println(id);
        assertEquals(id,3,"id should be 3");

    }
    @Story("Authentication")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify token can be used to get user email")
    @Test(description = "Login — use token to verify user email")
    public void loginAndVerifyUserEmail() {
        String token=
                given()
                        .body(TestData.loginBody())
                        .when().post("/login")
                        .then().spec(basicResponseSpec).extract().path("token");

        System.out.println("token is "+ token);

        Response response=
                given()
                        .header("Authorization", "Bearer "+token)
                        .when().get("/users/2")
                        .then().spec(responseSpec)
                        .extract().response();

        String email=response.jsonPath().getString("data.email");
        System.out.println(email);
        assertEquals(email,"janet.weaver@reqres.in", "email should be janet.weaver@reqres.in");
    }
}
