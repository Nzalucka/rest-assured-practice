package tests;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.TestData;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class CreateUserTest extends BaseTest{
    @Test(description = "Create user — verify status code 201")
    public void createUserStatusCode(){
        given()
                .contentType("application/json")
               // .body("{\"name\": \"Natalia\", \"job\": \"QA Engineer\"}")
                .body(TestData.createUserBody())
                .when()
                .post("/users")
                .then()
                .log().all()
                .statusCode(201);
    }
    @Test(description = "Create user — verify name in response")
    public void createUserVerifyName() {
        Response response = given()
                .contentType("application/json")
               // .body("{\"name\": \"Natalia\", \"job\": \"QA Engineer\"}")
                .body(TestData.createUserBody())
                .when()
                .post("/users")
                .then()
                .log().body()
                .statusCode(201)
                .extract().response();

        String name = response.jsonPath().getString("name");
        assertEquals(name, "Natalia", "Name should be Natalia");
    }
    @Test(description = "Create user — verify job in response")
    public void createUserVerifyJob(){
        Response response = given()
                .contentType("application/json")
               // .body("{\"name\": \"Natalia\", \"job\": \"QA Engineer\"}")
                .body(TestData.createUserBody())
                        .when()
                .post("/users")
                .then()
                .log().body()
                .statusCode(201)
                .extract().response();

        String job=response.jsonPath().getString("job");
        System.out.println("job is: "+job);
        assertEquals(job,"QA Engineer","job should be QA Engineer");
    }
    @Test(description = "Create user — verify id is not null")
    public void createUserVerifyIdNotNull(){
        Response response=given()
                .contentType("application/json")
              //  .body("{\"name\": \"Natalia\", \"job\": \"QA Engineer\"}")
                .body(TestData.createUserBody())
                .when().post("/users")
                .then().log().body().statusCode(201).extract().response();
        int id=response.jsonPath().getInt("id");
        System.out.println("id is: "+ id);
        assertNotNull(id,"id should not be null");
    }
}
