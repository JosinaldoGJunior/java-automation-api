package clients;

import core.BaseTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.TransactionPayload;
import static io.restassured.RestAssured.given;

/**
 * API Client for interacting with the /transacoes resource.
 */

public class TransactionsClient extends BaseTest {

    /**
     * Sends a POST request to /transacoes to create a new transaction.
     *
     * @param payload The TransactionPayload object containing the transaction data.
     * @return The full Response object from the API call.
     */
    public Response createTransaction(TransactionPayload payload) {
        return given()
                .header("Authorization", "JWT " + token)
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("/transacoes");
    }

    /**
     * Sends a DELETE request to /transacoes/{id} to delete a transaction.
     *
     * @param transactionId The ID of the transaction to be deleted.
     * @return The full Response object from the API call.
     */
    public Response deleteTransaction(Integer transactionId) {
        return given()
                .header("Authorization", "JWT " + token)
                .pathParam("id", transactionId)
                .when()
                .delete("/transacoes/{id}");
    }



    /**
     * Sends a POST request to /transacoes with an empty body to test mandatory field validation.
     *
     * @return The full Response object from the API call.
     */
    public Response createTransactionWithEmptyBody() {
        return given()
                .header("Authorization", "JWT " + token)
                .contentType(ContentType.JSON)
                .when()
                .post("/transacoes");
    }
}