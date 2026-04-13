package tests;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class NegativeTests extends BaseTest{
    @Test(description = "Get non-existent user — verify status 404")
    public void getNonExistentUser(){
        given().when().get("users/999").then().log().body().statusCode(404);

    }

    @Test(description = "Post user — verify status 400 for missing body")
    public void postWithoutBody(){
        given().contentType("application/json").body("").when().post("users/2").then()
                .log().body().statusCode(400);


    }

}
