package tests;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

import static io.restassured.RestAssured.given;

public class BaseTest {
    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "https://reqres.in/api";
        RestAssured.requestSpecification = given()
                .header("x-api-key", "reqres_a9db5cb7d8c849029d3ba03cf8e6ddb5");
    }
}
