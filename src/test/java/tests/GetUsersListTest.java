package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class GetUsersListTest extends BaseTest{

    @Story("Get users list")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify status code 200 for GET /users?page=2")
    @Test(description = "Get users list page 2 — verify status code 200")
    public void getUsersListStatusCode(){

                given()
                        .when()
                        .get("/users?page=2")
                        .then()
                        .statusCode(200)
                        .log().status();
    }

    @Story("Get users list")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that users list contains 6 users on page 2")
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

    @Story("Get users list")
    @Severity(SeverityLevel.MINOR)
    @Description("Verify that page number is 2 in response")
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


    @Story("Get users list")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that total users count is 12")
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


    @Story("Get users list")
    @Severity(SeverityLevel.MINOR)
    @Description("Verify that total pages count is 2")
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

    @Story("Get users list")
    @Severity(SeverityLevel.MINOR)
    @Description("Verify that per_page value is 6")
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


    @Story("Get users list")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify email of first user on page 1")
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

    @Story("Get users list")
    @Severity(SeverityLevel.NORMAL)
    @Description("Negative test — verify email mismatch returns assertion error")
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


    @Story("Get users list")
    @Severity(SeverityLevel.MINOR)
    @Description("Verify first name of first user on page 1")
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


    @Story("Get users list")
    @Severity(SeverityLevel.MINOR)
    @Description("Verify last name of first user on page 1")
    @Test(description = "Get users list — verify first user last name")
    public void getFirstUserLastNameFromList(){
        Response response=
                given().when().get("/users")
                        .then().statusCode(200)
                        .extract().response();
        String lastName=response.jsonPath().getString("data[0].last_name");
        System.out.println("First name: "+lastName);
        assertEquals(lastName,"Bluth", "last name should be Bluth");
    }

}
