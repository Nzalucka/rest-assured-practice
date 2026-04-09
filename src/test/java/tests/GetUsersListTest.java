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
    @Test(description = "Get users list page 2 — verify total pages count")
    public void getUsersListTotalPages(){
        Response response=
                given().when().get("/users?page=2")
                        .then().statusCode(200)
                        .log().body()
                        .extract().response();
        int totalPages=response.jsonPath().getInt("total_pages");
        assertEquals(totalPages,2,"totalPages number should be 2");

    }
    @Test(description = "Get users list page 1 — verify users per page count")
    public void getUsersListPerPage(){
        Response response=
                given().when().get("/users")
                        .then().statusCode(200)
                        .log().body()
                        .extract().response();
        int usersPerPage=response.jsonPath().getInt("per_page");
        assertEquals(usersPerPage,6,"per page number should be 6");
    }
    @Test(description = "Get users list — verify first user email")
    public void getFirstUserEmailFromList(){
        Response response=
                given().when().get("/users")
                        .then().statusCode(200)
                        .extract().response();
        String firstUserEmailPage1=response.jsonPath().getString("data[0].email");
        System.out.println("First user email: " + firstUserEmailPage1);
        assertEquals(firstUserEmailPage1, "george.bluth@reqres.in",
                "email should be george.bluth@reqres.in");
    }

    @Test(description = "Get users list — verify first user email — negative test")
    public void getFirstUserEmailFromList_negative(){
        Response response=
                given().when().get("/users")
                        .then().statusCode(200)
                        .log().ifValidationFails()
                        .extract().response();
        String firstUserEmailPage1=response.jsonPath().getString("data[0].email");
        System.out.println("First user email: " + firstUserEmailPage1);
        assertEquals(firstUserEmailPage1, "george1.bluth@reqres.in",
                "email should be george.bluth@reqres.in");
    }
    @Test(description = "Get users list — verify first user first name")
    public void getFirstUserFirstNameFromList(){
        Response response=
                given().when().get("/users")
                        .then().statusCode(200)
                        .extract().response();
        String firstName=response.jsonPath().getString("data[0].first_name");
        System.out.println("First name: "+firstName);
        assertEquals(firstName,"George", "first name should be George");
    }


}
