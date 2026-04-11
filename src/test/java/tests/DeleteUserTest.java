package tests;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.testng.Assert.assertEquals;

public class DeleteUserTest extends BaseTest{
 @Test(description = "Delete user — verify status code 204")
    public void deleteUser(){
     given()
             .when().delete("/users/2").then()
             .log().status().statusCode(204);
 }
@Test(description = "Delete non-existent user — verify status 204 (reqres.in behavior)")
    public void deleteNonExistentUser(){
    given()
            .when()
            .delete("/users/999")
            .then()
            .log().status()
            .statusCode(204);

}
    @Test(description = "Delete user — verify response body is empty")
    public void deleteUserEmptyBody() {
     String body=
             given().when().delete("/users/2").then().statusCode(204)
                     .log()
                     .body()
        .extract().body().asString();
     assertEquals(body,"","Response body should be empty");

    }
    @Test(description = "Delete user — verify Content-Type header")
    public void deleteUserVerifyHeader(){
     given().when().delete("/users/2")
             .then().log().headers()
             .statusCode(204)
             .header("Access-Control-Allow-Origin", "*");
    }
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
