package test;

import core.BaseTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


@DisplayName("Authentication Tests")
public class AuthTests extends BaseTest {
    @Test
    @DisplayName("Should validate access only with token")
    void shouldAccessOnlyWithToken() {
        Response response = accountsClient.getAccountsWithoutAuth();
        response.then()
                .statusCode(401);
    }
}