package tests;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;

public class BaseTest {
    public static ResponseSpecification responseSpec;
    public static ResponseSpecification postResponseSpec;
    public static ResponseSpecification deleteResponseSpec;


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
                .expectContentType("application/json")
                .expectBody("data", notNullValue())
                .build();

        postResponseSpec=new ResponseSpecBuilder()
                .expectStatusCode(201)
                .expectResponseTime(lessThan(3000L))
                .expectContentType("application/json")
                .expectBody("id",notNullValue())
                .expectBody("createdAt",notNullValue())
                .build();

        deleteResponseSpec = new ResponseSpecBuilder()
                .expectStatusCode(204)
                .expectResponseTime(lessThan(3000L))
                .build();

    }




}
