package clients;

import core.BaseTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.AccountPayload;
import static io.restassured.RestAssured.given;

/**
 * API Client for interacting with the /contas resource.
 */

public class AccountsClient  extends BaseTest {

    /**
     * Sends a POST request to create a new account.
     *
     * @param payload The AccountPayload object containing the data for the new account.
     * @return The full Response object from the API call.
     */
    public Response createAccount(AccountPayload payload) {
        return given()
                .header("Authorization", "JWT " + token)
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("/contas");
    }

    /**
     * Sends a PUT request to update an existing account specified by its ID.
     *
     * @param accountId The ID of the account to be updated.
     * @param payload The AccountPayload object containing the new data for the account.
     * @return The full Response object from the API call.
     */
    public Response updateAccount(Integer accountId, AccountPayload payload) {
        return given()
                .header("Authorization", "JWT " + token)
                .contentType(ContentType.JSON)
                .pathParam("id", accountId)
                .body(payload)
                .when()
                .put("/contas/{id}");
    }

    /**
     * Sends a DELETE request to delete a specific account by its ID.
     *
     * @param accountId The ID of the account to be deleted.
     * @return The full Response object from the API call.
     */
    public Response deleteAccount(Integer accountId) {
        return given()
                .header("Authorization", "JWT " + token)
                .pathParam("id", accountId)
                .when()
                .delete("/contas/{id}");
    }

    /**
     * Sends a non-authenticated GET request to the /contas endpoint.
     * This method is specifically designed for testing unauthorized access scenarios.
     *
     * @return The full Response object from the API call.
     */
    public Response getAccountsWithoutAuth() {
        return given()
                .when()
                .get("/contas");
    }
}
