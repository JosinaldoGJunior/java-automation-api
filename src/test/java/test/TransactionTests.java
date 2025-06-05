package test;

import core.BaseTest;
import io.restassured.response.Response;
import models.TransactionPayload;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.PayloadGenerator;
import utils.TestDataSetup;
import static org.hamcrest.Matchers.*;

@DisplayName("Transaction Management Tests")
public class TransactionTests extends BaseTest {

    @Test
    @DisplayName("Should create transaction successfully")
    public void shouldInsertTransactionSuccessfully() {

        // Arrange - All data and preconditions are set up here.
        Integer accountId = TestDataSetup.createValidAccountAndGetId();
        TransactionPayload payload = PayloadGenerator.generateNewExpensePayload(accountId);

        // Action - Creating transaction
        Response createTransaction = transactionsClient.createTransaction(payload);

        // Assert - The response is validated.
        createTransaction.then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("descricao", is(payload.getDescricao()))
                .body("envolvido",is(payload.getEnvolvido()));

    }

    @Test
    @DisplayName("Should validate mandatory fields for transaction")
    void shouldValidateMandatoryFieldsForTransaction() {
        // Act
        Response attemptResponse = transactionsClient.createTransactionWithEmptyBody();

        // Assert
        attemptResponse.then()
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
    @DisplayName("Should not create a transaction with a future date")
    void shouldNotCreateTransactionWithFutureDate() {
        // Arrange - An account is created and a payload with a future date is prepared.
        Integer accountId = TestDataSetup.createValidAccountAndGetId();
        TransactionPayload payload = PayloadGenerator.generateFutureDateExpensePayload(accountId);

        // Act - Attempt to create the transaction.
        Response response = transactionsClient.createTransaction(payload);

        // Assert - Validate the business rule violation.
        response.then()
                .statusCode(400)
                .body("$", hasSize(1))
                .body("msg", hasItem("Data da Movimentação deve ser menor ou igual à data atual"));
    }

    @Test
    @DisplayName("Should not delete an account that has transactions")
    void shouldNotDeleteAccountWithTransactions() {
        // Arrange - Create an account and then create a transaction linked to that account.
        Integer accountId = TestDataSetup.createValidAccountAndGetId();
        TestDataSetup.createValidIncomeAndGetId(accountId);

        // Act - Attempt to delete the parent account.
        Response deleteAttemptResponse = accountsClient.deleteAccount(accountId);

        // Assert - Validate that the API returns a specific server error due to a database constraint.
        deleteAttemptResponse.then()
                .statusCode(500)
                .body("constraint", is("transacoes_conta_id_foreign"));
    }

    @Test
    @DisplayName("Should delete transaction successfully")
    void shouldDeleteTransactionSuccessfully() {

        // Arrange - An account with a transaction in it.
        Integer accountId = TestDataSetup.createValidAccountAndGetId();
        Integer transactionId = TestDataSetup.createValidIncomeAndGetId(accountId);

        // Act - The action is to delete the specific transaction we just created.
        Response deleteResponse = transactionsClient.deleteTransaction(transactionId);

        // Assert - We validate that the deletion was successful.
        deleteResponse.then()
                .statusCode(204);
    }
}