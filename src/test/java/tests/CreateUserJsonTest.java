package tests;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.JsonReader;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class CreateUserJsonTest extends BaseTest{
    @Test(description = "Create user from JSON file — verify name")
    public void createUserFromJson() throws IOException {

        Response response=
                given()
                        .contentType("application/json")
                        .body(JsonReader.getCreateUserBody())
                        .when().post("/users")
                        .then().statusCode(201).extract().response();
        String name = response.jsonPath().getString("name");
        System.out.println(name);
        assertEquals(name,JsonReader.getData("name"),
                "Name should match JSON file");
    }


}
