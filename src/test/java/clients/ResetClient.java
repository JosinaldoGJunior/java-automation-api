package clients;

import core.BaseTest;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

/**
 * API Client for interacting with the /reset endpoint.
 */
public class ResetClient extends BaseTest {

    /**
     * Sends an authenticated GET request to /reset to clear user data.
     * This is useful for cleaning the environment before a test suite.
     * @return The full Response object from the API call.
     */
    public Response resetStateWithAuth() {
        return given()
                .header("Authorization", "JWT " + token)
            .when()
                .get("/reset");
    }

    /**
     * Sends a non-authenticated GET request to /reset.
     * Used specifically for auth tests to verify a 401 response.
     * @return The full Response object from the API call.
     */
    public Response resetStateWithoutAuth() {
        return given()
                // Note: No .header("Authorization") here
            .when()
                .get("/reset");
    }
}