package tests;

import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
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

    @DataProvider(name = "usersData")
    public Object[][] provideUsersData() throws IOException {
        return JsonReader.getUsersData();
    }
    @Test(description = "Create user — data driven from JSON",
            dataProvider = "usersData")
    public void createUserDataDriven(String name, String job) throws IOException {
        Map<String, String> body = new HashMap<>();
        body.put("name", name);
        body.put("job", job);

        Response response = given()
                .body(body)
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .extract().response();
        String actualName=response.jsonPath().getString("name");
        assertEquals(actualName,name,"Name should match");
        System.out.println("Created user: " + name + " / " + job);
    }

}
