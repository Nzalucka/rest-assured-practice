package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.TestData;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.responseSpecification;
import static org.testng.Assert.assertEquals;

public class PojoTest extends BaseTest{

    @Story("POJO")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify POST /users using POJO body")
    @Test(description = "Create user — POJO body")
    public void createUserWithPojo(){
     Response response= given()
                .body(TestData.createUserBodyPojo())
                .when().post("/users")
                .then().spec(postResponseSpec).extract().response();

    String name= response.jsonPath().getString("name");
    String job=response.jsonPath().getString("job");

        System.out.println("name is "+ name + " and job is "+job);
        assertEquals(name,"Natalia", "name should be Natalia");
        assertEquals(job, "QA Engineer", "job should be QA Engineer");

    }
}
