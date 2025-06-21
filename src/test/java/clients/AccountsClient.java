package clients;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.AccountPayload;
import static io.restassured.RestAssured.given;


public class AccountsClient {

    public Response createAccount(AccountPayload payload) {
        return given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("/contas");
    }

    public Response updateAccount(Integer accountId, AccountPayload payload) {
        return given()
                .contentType(ContentType.JSON)
                .pathParam("id", accountId)
                .body(payload)
                .when()
                .put("/contas/{id}");
    }

    public Response deleteAccount(Integer accountId) {
        return given()
                .pathParam("id", accountId)
                .when()
                .delete("/contas/{id}");
    }

}
