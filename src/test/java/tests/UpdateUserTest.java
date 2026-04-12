package tests;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class UpdateUserTest extends BaseTest{
    @Test(description = "Update user — verify status code 200")
    public void updateUser(){
        given()
                .contentType("application/json")
                .body("{\"name\": \"Natalia\", \"job\": \"QA Lead\"}")
        .when().put("/users/2")
                .then().statusCode(200);

    }
    @Test(description = "Update user — verify name in response")
    public void updateUserVerifyName(){
        Response response=given()
                .contentType("application/json")
                .body("{\"name\": \"Natalia\", \"job\": \"QA Lead\"}")
                .when().put("/users/2")
                .then().statusCode(200).log().body().extract().response();

        String name=response.jsonPath().getString("name");
        System.out.println(name);
        assertEquals(name,"Natalia","name should be Natalia");
    }

}
