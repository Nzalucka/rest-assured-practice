package tests;


import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import static org.testng.Assert.assertEquals;

import static io.restassured.RestAssured.given;

public class GetUsersTest {

        @BeforeClass
        public void setUp() {
            RestAssured.baseURI = "https://reqres.in/api";
            RestAssured.requestSpecification=
                    given()
                    .header("x-api-key",
                    "reqres_a9db5cb7d8c849029d3ba03cf8e6ddb5");
        }

        @Test(description = "Get user — verify status code is 200")
        public void getUserStatusCode() {
            given()
                    .when()
                    .get("/users/2")
                    .then()
                    .statusCode(200);
        }
        @Test(description = "Get user — verify email")
    public void getUserEmail(){
         Response response=
                    given()
                    .when()
                    .get("/users/2")
                    .then()
                    .statusCode(200)
                    .extract().response();
            String email=response.jsonPath().getString("data.email");
            assertEquals(email,"janet.weaver@reqres.in","email doesnt exist");
        }

        @Test(description = "Get user — verify email")
        public void getUserEmail(){
         Response response=
                    given()
                    .when()
                    .get("/users/2")
                    .then()
                    .statusCode(200)
                    .extract().response();
            String email=response.jsonPath().getString("data.email");
            assertEquals(email,"janet.weaver@reqres.in","email doesnt exist");
        }

        @Test(description = "Get user - verify firstName")
    public void getUserFirstName(){
         Response response=
                    given()
                    .when()
                    .get("/users/2")
                    .then()
                    .statusCode(200)
                    .extract().response();
            String firstName=response.jsonPath().getString("data.first_name");
            assertEquals(firstName,"Janet","incorrect name");
        }

    @Test(description = "Get user - verify lastName")
    public void getUserLastName(){
          Response response=
                    given()
                    .when()
                    .get("/users/2")
                    .then()
                    .statusCode(200)
                    .extract().response();
            String lastName=response.jsonPath().getString("data.last_name");
            assertEquals(lastName,"Weaver");
    }



}
