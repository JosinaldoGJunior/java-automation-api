package utils;

import models.AccountPayload;
import models.TransactionPayload;

import static io.restassured.RestAssured.given;

public class Helpers {

    public static Integer createAccountAndGetId(AccountPayload payload) {
        return given()
                .body(payload)
                .when()
                .post("/contas")
                .then()
                .statusCode(201)
                .extract()
                .path("id");
    }

    public static Integer createTransactionAndGetId(TransactionPayload payload) {
        return given()
                .body(payload)
                .when()
                .post("/transacoes")
                .then()
                .statusCode(201)
                .extract()
                .path("id");
    }
}
