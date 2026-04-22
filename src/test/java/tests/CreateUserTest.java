package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.TestData;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class CreateUserTest extends BaseTest{

    @Story("Create user")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that POST /users returns status code 201")
    @Test(description = "Create user — verify status code 201")
    public void createUserStatusCode(){
        given()
               // .body("{\"name\": \"Natalia\", \"job\": \"QA Engineer\"}")
                .body(TestData.createUserBody())
                .when()
                .post("/users")
                .then()
                .log().all()
                .statusCode(201);
    }

    @Story("Create user")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that POST /users returns name in response")
    @Test(description = "Create user — verify name in response")
    public void createUserVerifyName() {
        Response response = given()

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


    @Story("Create user")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that POST /users returns job in response")
    @Test(description = "Create user — verify job in response")
    public void createUserVerifyJob(){
        Response response = given()

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


    @Story("Create user")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that POST /users returns non-null id in response")
    @Test(description = "Create user — verify id is not null")
    public void createUserVerifyIdNotNull(){
        Response response=given()

              //  .body("{\"name\": \"Natalia\", \"job\": \"QA Engineer\"}")
                .body(TestData.createUserBody())
                .when().post("/users")
                .then().log().body().statusCode(201).extract().response();
        int id=response.jsonPath().getInt("id");
        System.out.println("id is: "+ id);
        assertNotNull(id,"id should not be null");
    }
}
