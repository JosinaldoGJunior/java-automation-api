package api.test;

import core.BaseTest;
import models.AccountPayload;
import models.TransactionPayload;
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

    @Test
    @DisplayName("Should insert transaction successfully")
    public void shouldInsertTransactionSuccessfully() {
        AccountPayload accountPayload = PayloadGenerator.generateAccountPayload();
        Integer accountId = Helpers.createAccountAndGetId(accountPayload);

        TransactionPayload transactionPayload = PayloadGenerator.generateTransactionPayload(accountId);

        given()
                .body(transactionPayload)
                .when()
                .post("/transacoes")
                .then()
                .statusCode(201);
    }

    @Test
    @DisplayName("Should validate mandatory fields for transaction")
    public void shouldValidateMandatoryFieldsForTransaction() {
        given()
                .when()
                .post("/transacoes")
                .then()
                .statusCode(400)
                .body("$", hasSize(8))
                .body("msg", hasItems(
                        "Data da Movimentação é obrigatório",
                        "Data do pagamento é obrigatório",
                        "Descrição é obrigatório",
                        "Interessado é obrigatório",
                        "Valor é obrigatório",
                        "Valor deve ser um número",
                        "Conta é obrigatório",
                        "Situação é obrigatório"
                ));
    }

    @Test
    @DisplayName("Should not insert transaction with future date")
    public void shouldNotInsertTransactionWithFutureDate() {
        AccountPayload accountPayload = PayloadGenerator.generateAccountPayload();
        Integer accountId = Helpers.createAccountAndGetId(accountPayload);

        TransactionPayload transactionPayload = PayloadGenerator.generateFutureTransactionPayload(accountId);

        given()
                .body(transactionPayload)
                .when()
                .post("/transacoes")
                .then()
                .statusCode(400)
                .body("$", hasSize(1))
                .body("msg", hasItem("Data da Movimentação deve ser menor ou igual à data atual"));
    }

    @Test
    @DisplayName("Should not delete account with transactions")
    public void shouldNotDeleteAccountWithTransactions() {
        AccountPayload accountPayload = PayloadGenerator.generateAccountPayload();
        Integer accountId = Helpers.createAccountAndGetId(accountPayload);

        TransactionPayload transactionPayload = PayloadGenerator.generateTransactionPayload(accountId);
        Helpers.createTransactionAndGetId(transactionPayload);

        given()
                .pathParam("id", accountId)
                .when()
                .delete("/contas/{id}")
                .then()
                .statusCode(500)
                .body("constraint", is("transacoes_conta_id_foreign"));
    }

    @Test
    @DisplayName("Should delete transaction")
    public void shouldDeleteTransaction() {
        AccountPayload accountPayload = PayloadGenerator.generateAccountPayload();
        Integer accountId = Helpers.createAccountAndGetId(accountPayload);

        TransactionPayload transactionPayload = PayloadGenerator.generateTransactionPayload(accountId);
        Integer transactionId = Helpers.createTransactionAndGetId(transactionPayload);

        given()
                .pathParam("id", transactionId)
                .when()
                .delete("/transacoes/{id}")
                .then()
                .statusCode(204);
    }
}
