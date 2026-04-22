package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import static org.testng.Assert.assertEquals;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertNotNull;

public class GetUsersTest extends BaseTest {


        @Story("Get single user")
        @Severity(SeverityLevel.NORMAL)
        @Description("Verify that GET /users/2 returns status 200")
        @Test(description = "Get user — verify status code is 200")
        public void getUserStatusCode() {
            given()
                    .when()
                    .get("/users/2")
                    .then()
                    .statusCode(200);
        }
    @Story("Get single user")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify GET /users/2 using ResponseSpecification")
    @Test(description = "Get user — verify using response spec")
    public void getUserWithResponseSpec(){
            given()
                    .when()
                    .get("/users/2")
                    .then()
                    .spec(responseSpec);
    }



        @Story("Get single user")
        @Severity(SeverityLevel.NORMAL)
        @Test(description = "Get user — verify email")
        @Description("Verify email of user 2")
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

     @Story("Get single user")
     @Severity(SeverityLevel.NORMAL)
     @Description("Verify first name of user 2")
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

    @Story("Get single user")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify last name of user 2")
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


    @Story("Get single user")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify avatar is not null for user 2")
    @Test(description = "Get user — verify avatar is not null")
    public void getUserAvatar(){
            Response response= given()
                    .when()
                    .get("/users/2")
                    .then()
                    .extract().response();

            String avatar=response.jsonPath().getString("data.avatar");
            assertNotNull(avatar,"Avatar should not be null");

    }



}
