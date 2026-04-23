package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.testng.Assert.assertEquals;

public class DeleteUserTest extends BaseTest{

    @Story("Delete user")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that DELETE /users/2 returns status 204")
 @Test(description = "Delete user — verify status code 204")
    public void deleteUser(){
     given()
             .when().delete("/users/2").then()
             .log().status().statusCode(204);
 }


    @Story("Delete user")
    @Severity(SeverityLevel.MINOR)
    @Description("Verify that DELETE /users/999 returns 204 — reqres.in behavior")
@Test(description = "Delete non-existent user — verify status 204 (reqres.in behavior)")
    public void deleteNonExistentUser(){
    given()
            .when()
            .delete("/users/999")
            .then()
            .log().status()
            .statusCode(204);

}


    @Story("Delete user")
    @Severity(SeverityLevel.MINOR)
    @Description("Verify that DELETE /users/2 returns empty response body")
    @Test(description = "Delete user — verify response body is empty")
    public void deleteUserEmptyBody() {
     String body=
             given().when().delete("/users/2").then().statusCode(204)
                     .log()
                     .body()
        .extract().body().asString();
     assertEquals(body,"","Response body should be empty");

    }


    @Story("Delete user")
    @Severity(SeverityLevel.MINOR)
    @Description("Verify that DELETE /users/2 returns Access-Control-Allow-Origin header")
    @Test(description = "Delete user — verify Content-Type header")
    public void deleteUserVerifyHeader(){
     given().when().delete("/users/2")
             .then().log().headers()
             .statusCode(204)
             .header("Access-Control-Allow-Origin", "*");
    }

    @Story("Delete user")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify DELETE /users/2 using ResponseSpecification")
    @Test(description = "Delete user — verify using response spec")
    public void deleteUserWithResponseSpec() {
        given()
                .when()
                .delete("/users/2")
                .then()
                .spec(deleteResponseSpec);
    }

    @Story("Delete user")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that DELETE /users/2 responds in under 1 second")
    @Test(description = "Delete user — verify response time under 3 seconds")
    public void deleteUserResponseTime() {
        Response response=  given()
                .when()
                .delete("/users/2")
                .then()
                .statusCode(204)
                .time(lessThan(1000L))
                .extract().response();
        System.out.println("Response time: "+response.getTime()+" ms");
    }

}
