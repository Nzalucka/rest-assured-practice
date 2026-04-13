package tests;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.TestData;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class UpdateUserTest extends BaseTest{
    @Test(description = "Update user — verify status code 200")
    public void updateUser(){
        given()
                .contentType("application/json")
                // .body("{\"name\": \"Natalia\", \"job\": \"QA Lead\"}")
                .body(TestData.updateUserBody("Natalia", "QA Lead"))
        .when().put("/users/2")
                .then().statusCode(200);

    }
    @Test(description = "Update user — verify name in response")
    public void updateUserVerifyName(){
        Response response=given()
                .contentType("application/json")
               // .body("{\"name\": \"Natalia-Updated\", \"job\": \"QA Lead\"}")
                .body(TestData.updateUserBody("Natalia-Updated", "QA Lead"))
                .when().put("/users/2")
                .then().statusCode(200).log().body().extract().response();

        String name=response.jsonPath().getString("name");
        System.out.println(name);
        assertEquals(name,"Natalia-Updated","name should be Natalia-Updated");
    }
    @Test(description = "Update user — verify job in response")
    public void updateUserVerifyjob(){
        Response response=
                given().contentType("application/json")
                       // .body("{\"name\": \"Natalia-Updated\", \"job\": \"QA Senior\"}")
                        .body(TestData.updateUserBody("Natalia-Updated","QA Senior"))
                        .when().put("/users/2")
                        .then().statusCode(200).extract().response();
        String job=response.jsonPath().getString("job");
        System.out.println(job);
        assertEquals(job,"QA Senior","job should be QA Senior");
    }
    @Test(description = "Update user — verify updatedAt is not null")
    public void updateUserVerifyUpdatedAt(){
        Response response=
                given()
                        .contentType("application/json")
                      //  .body("{\"name\": \"Natalia-Updated\", \"job\": \"QA Senior\"}")
                        .body(TestData.updateUserBody("Natalia-Updated", "QA Senior"))
                        .when().put("/users/2")
                        .then().statusCode(200).extract().response();
        String updatedAt = response.jsonPath().getString("updatedAt");
        System.out.println(updatedAt);
        assertNotNull(updatedAt, "updatedAt should not be null");

    }



}
