package api.test;

import core.BaseTest;
import models.AccountPayload;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.Helpers;
import utils.PayloadGenerator;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@DisplayName("Account Management Tests")
public class AccountsTests extends BaseTest {


    @Test
    @DisplayName("Should create account successfully")
    void shouldCreateAccountSuccessfully() {
        AccountPayload originalPayload = PayloadGenerator.generateAccountPayload();

        given()
                .body(originalPayload)
                .when()
                .post("/contas")
                .then()
                .statusCode(201)
                .body("nome", is(originalPayload.getNome()))
                .body("id", notNullValue());
    }

    @Test
    @DisplayName("Should not create account with same name")
    void shouldNotCreateAccountWithSameName() {

        AccountPayload originalPayload = PayloadGenerator.generateAccountPayload();
        Helpers.createAccountAndGetId(originalPayload);

        given()
                .body(originalPayload)
                .when()
                .post("/contas")
                .then()
                .statusCode(400)
                .body("error", is("Já existe uma conta com esse nome!"));
    }

    @Test
    @DisplayName("Should update account successfully")
    void shouldUpdateAccountSuccessfully() {

        AccountPayload originalPayload = PayloadGenerator.generateAccountPayload();

        Integer accountId = Helpers.createAccountAndGetId(originalPayload);
        Assertions.assertNotNull(accountId, "O ID da conta não pode ser nulo");

        AccountPayload updatedPayload = PayloadGenerator.generateAccountEditPayload();

        given()
                .body(updatedPayload)
                .pathParam("id", accountId)
                .when()
                .put("/contas/{id}")
                .then()
                .statusCode(200)
                .body("nome", is(updatedPayload.getNome()));
    }
}
