package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import model.CreateUserResponse;
import model.UserResponse;
import org.testng.annotations.Test;
import utils.TestData;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.responseSpecification;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

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
    @Story("POJO")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify GET /users/2 response deserialized to UserResponse POJO")
    @Test(description = "Get user — deserialize response to POJO")
    public void getUserWithPojo() {
        Response response=
                given()
                        .when().body(TestData.createUserBodyPojo())
                        .get("/users/2")
                        .then()
                        .spec(responseSpec).extract().response();

        UserResponse user=response.jsonPath().getObject("data",UserResponse.class);
        assertEquals(user.getEmail(),"janet.weaver@reqres.in");
        assertEquals(user.getFirstName(),"Janet");
        assertEquals(user.getId(),2);
        assertNotNull(user.getAvatar());

        System.out.println("id: " + user.getId());
        System.out.println("email: " + user.getEmail());
        System.out.println("firstName: " + user.getFirstName());
        System.out.println("lastName: " + user.getLastName());
        System.out.println("avatar: " + user.getAvatar());
    }
    @Story("POJO")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify POST /users response deserialized to CreateUserResponse POJO")
    @Test(description = "Create user — deserialize response to POJO")
    public void createUserWithPojoDeserialization() {

        CreateUserResponse created=
                given()
                        .body(TestData.createUserBodyPojo())
                        .when()
                        .post("/users")
                        .then().spec(postResponseSpec)
                        .extract().response()
                        .as(CreateUserResponse.class);

        System.out.println("id: " + created.getId());
        System.out.println("createdAt: " + created.getCreatedAt());
        assertNotNull(created.getId());
        assertNotNull(created.getCreatedAt());

    }
    }
