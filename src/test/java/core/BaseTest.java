// Em core/BaseTest.java
package core;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import org.junit.jupiter.api.BeforeAll;

import static org.hamcrest.Matchers.lessThan;

// Remova o @TestInstance, não é mais necessário com esta abordagem
public class BaseTest {

    @BeforeAll
    public static void setup() { // O método agora pode ser estático
        RestAssured.baseURI = Config.getBaseUrl();
        RestAssured.port = Config.getPort();

        // Pega o token do nosso gerenciador
        String token = TokenManager.getToken();

        RequestSpecBuilder reqBuilder = new RequestSpecBuilder();
        reqBuilder.setContentType(Config.getContentType());
        reqBuilder.addHeader("Authorization", "JWT " + token);
        reqBuilder.log(LogDetail.URI);
        RestAssured.requestSpecification = reqBuilder.build();

        ResponseSpecBuilder resBuilder = new ResponseSpecBuilder();
        resBuilder.expectResponseTime(lessThan(Config.getMaxTimeout()));
        RestAssured.responseSpecification = resBuilder.build();

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}