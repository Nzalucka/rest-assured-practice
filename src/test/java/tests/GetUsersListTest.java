package tests;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class GetUsersListTest extends BaseTest{
    @Test(description = "Get users list page 2 — verify status code 200")
    public void getUsersListStatusCode(){

                given()
                        .when()
                        .get("/users?page=2")
                        .then()
                        .statusCode(200)
                        .log().status();
    }

}
