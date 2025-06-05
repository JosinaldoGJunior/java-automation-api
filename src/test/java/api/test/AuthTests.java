package api.test;

import core.BaseTest;
import io.restassured.RestAssured;
import io.restassured.specification.FilterableRequestSpecification;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;

@DisplayName("Authentication Tests")
public class AuthTests extends BaseTest {

    @Test
    @DisplayName("Should not access API without Authorization token")
    void shouldNotAccessApiWithoutToken() {
        FilterableRequestSpecification requestSpec = (FilterableRequestSpecification) RestAssured.requestSpecification;
        requestSpec.removeHeader("Authorization");

        given()
                .when()
                .get("/contas")
                .then()
                .statusCode(401);
    }

    @AfterEach
    void restoreHeaders() {
        // This method assumes the Authorization token might be set globally in BaseTest
        // or Config. If a token is usually expected, you should re-add it here.
        // Example if Config.getAuthToken() provides the token:
        /*
        Config.getAuthToken().ifPresent(token -> {
            FilterableRequestSpecification requestSpec = (FilterableRequestSpecification) RestAssured.requestSpecification;
            requestSpec.header("Authorization", "Bearer " + token);
        });
        */
        // If your global setup for RestAssured.requestSpecification does not usually
        // include an Authorization header, then this method can be removed,
        // or adjusted based on your specific global token handling.
    }
}