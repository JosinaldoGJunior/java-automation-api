package core;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.lessThan;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseTest {

    @BeforeAll
    public void setup() {
        RestAssured.baseURI = Config.getBaseUrl();
        RestAssured.port = Config.getPort();
        String token = getToken();

        RequestSpecBuilder reqBuilder = new RequestSpecBuilder();
        reqBuilder.setContentType(Config.getContentType());
        reqBuilder.addHeader("Authorization", "JWT " + token);
        reqBuilder.log(LogDetail.URI);
        RestAssured.requestSpecification = reqBuilder.build();

        ResponseSpecBuilder resBuilder = new ResponseSpecBuilder();
        resBuilder.expectResponseTime(lessThan(Config.getMaxTimeout()));
        RestAssured.responseSpecification = resBuilder.build();

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        RestAssured.get("/reset").then().statusCode(200);
    }

    private String getToken() {
        Map<String, String> login = new HashMap<>();
        login.put("email", Config.getUserEmail());
        login.put("senha", Config.getUserPassword());

        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(login)
                .when()
                .post("/signin");

        response.then().statusCode(200);

        return response.then().extract().path("token");
    }
}