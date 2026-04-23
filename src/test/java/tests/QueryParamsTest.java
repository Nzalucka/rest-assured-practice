package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class QueryParamsTest extends BaseTest {
    @Story("Query params")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify GET /users with query param page=2")
    @Test(description = "Get users list — verify page 2 using query param")
    public void getUsersListPage2() {

       Response response=
               given()
                       .queryParam("page",2)
                .when()
                       .get("/users")
                .then()
                       .spec(responseSpec)
                               .extract()
                       .response();
       int page= response.jsonPath().getInt("page");
        System.out.println(page);
       assertEquals(page,2,"Page should be 2");
    }

    @Story("Query params")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify GET /users with query param per_page=3")
    @Test(description = "Get users list — verify 3 users per page using query param")
    public void getUsersListPerPage3() {
        Response response=
                given()
                        .queryParam("per_page",3)
                        .when().get("/users")
                        .then().spec(responseSpec)
                        .extract().response();
        int perPage=response.jsonPath().getInt("per_page");
        System.out.println(perPage);
        assertEquals(perPage,3,"per page=3");
    }
    @Story("Query params")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify GET /users/{id} with path param id=3")
    @Test(description = "Get user by path param id=3")
    public void getUserByPathParam() {
        Response response=
                given()
                        .pathParam("id",3)
                        .when().get("/users/{id}")
                        .then().spec(responseSpec).extract().response();

        int id=response.jsonPath().getInt("data.id");
        System.out.println(id);
        assertEquals(id,3,"id should be 3");
    }

}
