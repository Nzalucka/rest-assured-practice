package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class MultipartTest extends BaseTest{

    @Story("Multipart")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify file upload using multipart/form-data")
    @Test(description = "Upload file — verify status 200")
    public void uploadFile(){
        given()
                .baseUri("https://httpbin.org")
                .contentType("multipart/form-data")
                .multiPart("file", "test content", "text/plain")
                .multiPart("name","Natalia")
                .when().post("/post")
                .then().statusCode(200)
                .body("form.name",equalTo("Natalia"))
                .body("form.file", equalTo("test content"));
    }
    @Story("Multipart")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify real file upload using multipart/form-data")
    @Test(description = "Upload real file — verify status 200")
    public void uploadRealFile(){
        given()
                .baseUri("https://httpbin.org")
                .contentType("multipart/form-data")
                .multiPart("file",new File("src/test/resources/test.txt"))
                .when().post("/post")
                .then().statusCode(200)
                .time(lessThan(3000L))
                .body("files.file", notNullValue());
    }

}
