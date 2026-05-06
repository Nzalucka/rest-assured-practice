package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import model.CreateUserResponse;
import model.UserResponse;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;
import utils.TestData;

import static io.restassured.RestAssured.given;

public class SoftAssertionsTest extends BaseTest{
    @Story("Soft Assertions")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify GET /users/2 using soft assertions")
    @Test(description = "Get user — verify multiple fields with soft assertions")
    public void getUserWithSoftAssertions() {
        Response response=
        given()
                .when().get("/users/2")
                .then()
                .spec(responseSpec)
                .extract().response();

        UserResponse user=response
                .jsonPath()
                .getObject("data",UserResponse.class);

        SoftAssertions.assertSoftly(soft ->{
            soft.assertThat(user.getId()).as("user should be 2 not 3").isEqualTo(2);
            soft.assertThat(user.getEmail()).isEqualTo("janet.weaver@reqres.in");
            soft.assertThat(user.getFirstName()).isEqualTo("Janet");
            soft.assertThat(user.getLastName()).isEqualTo("Weaver");
            soft.assertThat(user.getAvatar()).isNotNull();

        });

        }
    @Story("Soft Assertions")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify POST /users using soft assertions")
    @Test(description = "Create user — verify fields with soft assertions")
    public void createUserWithSoftAssertions() {

        CreateUserResponse createdResponse=
                given()
                        .body(TestData.createUserBodyPojo())
                        .when().post("/users")
                        .then()
                        .spec(postResponseSpec)
                        .extract().response()
                        .as(CreateUserResponse.class);

        SoftAssertions.assertSoftly(soft-> {
            soft.assertThat(createdResponse.getId()).isNotNull();
            soft.assertThat(createdResponse.getCreatedAt()).isNotNull();


        });
    }}

