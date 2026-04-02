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
    @Test(description = "Get users list page 2 — verify 6 users")
    public void getUsersListSize() {
        Response response=
                given().when()
                        .get("/users?page=2").then()
                        .statusCode(200)
                        .extract().response();

        int listSize=response.jsonPath().getList("data").size();
        assertEquals(listSize,6,
                "list size should be 6");

    }
    @Test(description = "Get users list page 2 — verify page number")
    public void getUsersListPageNumber() {
        Response response=given()
                .when().get("/users?page=2")
                .then()
                .statusCode(200)
                .extract().response();
        int page=response.jsonPath().getInt("page");
        assertEquals(page,2,"page number should be 2");
    }
    @Test(description = "Get users list page 2 — verify total users count")
    public void getUsersListTotal(){
        Response response=given()
                .when().get("/users?page=2")
                .then()
                .statusCode(200)
                .log().body()
                .extract().response();
        int totalUsers=response.jsonPath().getInt("total");
        assertEquals(totalUsers,12,"totalUser number should be 12");

    }

}
