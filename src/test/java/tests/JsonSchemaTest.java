package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import utils.TestData;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class JsonSchemaTest extends BaseTest {

    @Story("JSON Schema validation")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify GET /users/2 response matches JSON schema")
    @Test(description = "Get user — verify JSON schema")
    public void getUserMatchesSchema(){
        given()
                .when().get("/users/2")
                .then()
                .spec(responseSpec)
                .body(matchesJsonSchemaInClasspath("schemas/user-schema.json"));

    }

    @Story("JSON Schema validation")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify GET /users response matches JSON schema")
    @Test(description = "Get user — verify JSON schema")
    public void getUsersListMatchesSchema(){
        given().when().get("/users")
                .then().spec(responseSpec)
                .body(matchesJsonSchemaInClasspath("schemas/users-list-schema.json"));

    }
    @Story("JSON Schema validation")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify POST /users response matches JSON schema")
    @Test(description = "Create user — verify JSON schema")
    public void createUserMatchesSchema(){
        given()
                .body(TestData.createUserBody())
                .when().post("/users")
                .then().spec(postResponseSpec)
                .body(matchesJsonSchemaInClasspath("schemas/create-user-schema.json"));
    }



}
