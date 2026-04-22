package tests;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class BaseTest {
    public static ResponseSpecification responseSpec;
    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "https://reqres.in/api";
        RestAssured.requestSpecification =
                given()
                .header("x-api-key", "reqres_a9db5cb7d8c849029d3ba03cf8e6ddb5")
                        .contentType("application/json");

        responseSpec=new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectResponseTime(lessThan(3000L))
                .expectHeader("Access-Control-Allow-Origin", "*")
                .build();

    }




}
