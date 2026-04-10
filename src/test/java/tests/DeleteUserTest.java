package tests;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteUserTest extends BaseTest{
 @Test(description = "Delete user — verify status code 204")
    public void deleteUser(){
     given()
             .when().delete("/users/2").then()
             .log().status().statusCode(204);
 }

}
