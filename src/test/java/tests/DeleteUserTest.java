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
@Test(description = "Delete non-existent user — verify status 204 (reqres.in behavior)")
    public void deleteNonExistentUser(){
    given()
            .when()
            .delete("/users/999")
            .then()
            .log().status()
            .statusCode(204);

}
}
